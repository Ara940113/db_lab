package site.metacoding.chatting.v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

// JWP = �����������
// 1. ���� �޽����� username���� üŷ

// 2. ������ : 
// 3. ALL:�޽���
// 4. CHAT:���̵�:�޽���

public class MyServerSocket {

	// ������ (����ޱ�) - ���ν�����
	ServerSocket serverSocket;
	List<�������㽺����> ��������Ʈ;

	// ������ �޽��� �޾Ƽ� ������ (Ŭ���̾�Ʈ ������)

	public MyServerSocket() {
		try {
			serverSocket = new ServerSocket(2000);
			��������Ʈ = new Vector<>(); // ����ȭ�� ó���� ArrayList
			while (true) {
				Socket socket = serverSocket.accept(); // main ������
				System.out.println("Ŭ���̾�Ʈ �����");
				�������㽺���� t = new �������㽺����(socket);
				��������Ʈ.add(t);
				System.out.println("��������Ʈ ũ�� : " + ��������Ʈ.size());
				new Thread(t).start();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ���� Ŭ����
	class �������㽺���� implements Runnable {

		String username;
		Socket socket;
		BufferedReader reader;
		BufferedWriter writer;
		boolean isLogin;

		public �������㽺����(Socket socket) {
			this.socket = socket;

			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// ALL:�ӽñ�
		public void chatPublic(String msg) {
			try {
				for (�������㽺���� t : ��������Ʈ) { // ���� : �÷��� Ÿ��, ������ : �÷���
					if (t != this) {
						t.writer.write(msg + "\n");
						t.writer.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// CHAT:����ȣ:�ȳ�
		public void chatPrivate(String username, String msg) {
			try {
				for (�������㽺���� t : ��������Ʈ) { // ���� : �÷��� Ÿ��, ������ : �÷���
					if (t.username.equals(username)) {
						t.writer.write(msg + "\n");
						t.writer.flush();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// ��� �������� �˻��
		// ALL:�ȳ�
		// CHAT:���:�ȳ�
		public void jwp(String inputData) {
			// 1. �������� �и�
			String[] token = inputData.split(":");
			String protocol = token[0];
			if (protocol.equals("ALL")) {
				String msg = token[1];
				chatPublic(msg);
			} else if (protocol.equals("CHAT")) {
				String username = token[1];
				String msg = token[2];
				chatPrivate(username, msg);
			} else { // �������� ��� ����.
				System.out.println("�������� ����");
			}
		}

		@Override
		public void run() {

			// ���� �޽����� username�̴�.
			try {
				username = reader.readLine();
				isLogin = true;
			} catch (Exception e) {
				isLogin = false;
				System.out.println("username�� ���� ���߽��ϴ�.");
			}

			while (isLogin) {
				try {
					String inputData = reader.readLine();

					// �޽��� �޾����ϱ� List<�������㽺����> ��������Ʈ <== ���⿡ ���
					// ��� Ŭ���̾�Ʈ���� �޽��� ���� (for�� ������!!)
					jwp(inputData);
				} catch (Exception e) {
					try {
						System.out.println("��� ���� : " + e.getMessage());
						isLogin = false;
						��������Ʈ.remove(this);
						reader.close();
						writer.close();
						socket.close();
					} catch (Exception e1) {
						System.out.println("�������� ���μ��� ���� " + e1.getMessage());
					}
				}

			}
		}

	}

	// 192.168.0.132
	public static void main(String[] args) {
		new MyServerSocket();
	}
}
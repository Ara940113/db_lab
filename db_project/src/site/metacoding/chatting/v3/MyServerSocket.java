package site.metacoding.chatting.v3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class MyServerSocket {

	// ������ (����ޱ�) - ���ν�����
	ServerSocket serverSocket;
	List<�������㽺����> ��������Ʈ;

	// ������ �޽��� �޾Ƽ� ������ (Ŭ���̾�Ʈ ������)

	public MyServerSocket() {
		try {
			serverSocket = new ServerSocket(1785);
			��������Ʈ = new Vector<>(); // Vector : ����ȭ�� ó���� ArrayList
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

		Socket socket;
		BufferedReader reader;
		BufferedWriter writer;
		boolean isLogin = true; // ���������� ���� �� try~catch ��� �ȵ���

		public �������㽺����(Socket socket) {
			this.socket = socket;
			// new �ɶ����� ���� ���� ���ܿ� ���۰� �������
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			// ���ν��������� �δ��� ���� �ֱ� ���ؼ��� ���۸� ���⿡ ���� �ȴ�.
			// while�� �����޸� ���ο� ����������! ���ο� �ǿ� while �޾�����!!
			while (isLogin) {
				try {
					String inputData = reader.readLine();
					System.out.println("from Ŭ���̾�Ʈ : " + inputData);

					// �޽��� �޾����ϱ� List<�������㽺����> ��������Ʈ <== ���⿡ ���
					// ��� Ŭ���̾�Ʈ���� �޼��� ���� (for�� ������)
					for (�������㽺���� t : ��������Ʈ) { // ���� : �÷��� Ÿ��, ������ : �÷���
						if (t != this) { // �ڱ��ڽſ��Ե� �޽����� �����ʰ�
							t.writer.write(inputData + "\n");
							t.writer.flush();
						}

					}

				} catch (Exception e) {

					try {
						System.out.println("��Ž���:" + e.getMessage());
						isLogin = false; // while�� ���������� ���� ����
						��������Ʈ.remove(this); // ��������Ʈ�� ���� �������ϱ� �����
						reader.close();//
						writer.close();// ����� �������÷����� ������ �ð��� �ɷ� ����� ���ϰ� �ɸ��� ������ �̸� ������
						socket.close();// ����� i/o�� �߻��ϴϱ�
					} catch (Exception e1) {
						System.out.println("�����������μ�������" + e1.getMessage());
					}

				}

			}

		}

	}

	public static void main(String[] args) {
		new MyServerSocket();
	}
}
package site.metacoding.chatting.v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {

	String username;

	Socket socket;

	// ������
	Scanner sc;
	BufferedWriter writer;

	// ������
	BufferedReader reader;

	public MyClientSocket() {
		try {
			socket = new Socket("192.168.0.132", 2000);

			sc = new Scanner(System.in);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ���ο� ������ (�б� ����)
			new Thread(new �б����㽺����()).start();

			// ���� username ���� ��������
			System.out.println("���̵� �Է��ϼ���.");
			username = sc.nextLine();
			writer.write(username + "\n"); // ���ۿ� ���
			writer.flush(); // ���ۿ� ��� ���� stream���� ���������
			System.out.println(username + "�� ������ ���۵Ǿ����ϴ�.");

			// ���� ������ (���� ����)
			while (true) {
				String keyboardInputData = sc.nextLine();
				writer.write(keyboardInputData + "\n"); // ���ۿ� ���
				writer.flush(); // ���ۿ� ��� ���� stream���� ���������
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class �б����㽺���� implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					String inputData = reader.readLine();
					System.out.println(inputData);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new MyClientSocket();
	}
}
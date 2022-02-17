package site.metacoding.chatting.v4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MyClientSocket {

	Socket socket;

	// ������ (Ű����� �޾Ƽ� �ٷ� �� ������)
	BufferedWriter writer;
	Scanner sc;

	// ������ (�д� ������)
	BufferedReader reader;

	public MyClientSocket() {
		try {
			socket = new Socket("192.168.0.132", 2000); // 1�� ���� ���� 127.0.0.1 : ������ �ּ� �ڱ��ּ�

			// 2�� ���ۿ���
			sc = new Scanner(System.in);
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// ���ο� ������� (�б�����) - ���ν����庸�� ���߿� ������ ������ ������ ���ű� ������ �ȿ´�.
			new Thread(new �б����㽺����()).start();

			// ���ν����� (��������)
			while (true) {
				String keyboardInputData = sc.nextLine();
				writer.write(keyboardInputData + "\n"); // ���ۿ� ���
				writer.flush(); // ���ۿ� ��� ���� stream���� ��������� - ����� ����
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����Ŭ������ ����� ������ : ���������� �� �� �� �ִ�.
	class �б����㽺���� implements Runnable {

		@Override
		public void run() {
			try {
				while (true) {
					String inputData = reader.readLine();
					System.out.println("���� �޽��� : " + inputData);
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
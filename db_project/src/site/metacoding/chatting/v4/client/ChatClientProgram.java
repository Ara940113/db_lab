package site.metacoding.chatting.v4.client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChatClientProgram extends JFrame {

	JPanel northPanel, southPanel;
	JTextArea chatList;

	public ChatClientProgram() {
		setTitle("MyChat1.0");
		setSize(250, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		northPanel = new JPanel();
		southPanel = new JPanel();

		add(northPanel, BorderLayout.NORTH);
		add(new JButton("ºæ≈Õ"), BorderLayout.CENTER);
		add(southPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	private void init() {

	}

	public static void main(String[] args) {
		new ChatClientProgram();
	}

}

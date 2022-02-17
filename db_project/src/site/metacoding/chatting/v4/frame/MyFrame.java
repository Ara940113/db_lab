package site.metacoding.chatting.v4.frame;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame() {
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public MyFrame(int w, int h) {
		setSize(w, h);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

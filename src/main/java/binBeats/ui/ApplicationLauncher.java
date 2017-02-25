package main.java.binBeats.ui;

import javax.swing.*;

public class ApplicationLauncher {
	public static void run() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 300);
		
		JLabel panel = new JLabel("Projekt BinBeats");
		frame.add(panel);
		
		frame.setVisible(true);
	}
}

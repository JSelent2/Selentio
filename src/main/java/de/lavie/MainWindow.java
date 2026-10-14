package de.lavie;

import javax.swing.JFrame;

public class MainWindow
{

	public static JFrame fenster;

	public static void mainWindow()
	{
		fenster = new JFrame("Selentio");
		fenster.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

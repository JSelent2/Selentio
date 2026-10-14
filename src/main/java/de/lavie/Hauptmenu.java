package de.lavie;

public class Hauptmenu
{

	Hauptmenu()
	{
		MainWindow.fenster.getContentPane().removeAll();
		Startseite startseite = new Startseite();
		MainWindow.fenster.getContentPane().add(startseite.panel);
		MainWindow.fenster.getContentPane().revalidate();
		MainWindow.fenster.getContentPane().repaint();
	}

}

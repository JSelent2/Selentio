package de.lavie;

import java.awt.*;

import javax.swing.*;

public class Elektronik 
{
	Elektronik() 
	{

		Pageview elektronik = new Pageview(MainWindow.fenster, "Elektronik", Color.BLUE, Color.WHITE);

		JButton menü = new JButton("Hauptmenü");
		menü.addActionListener(e -> 
		{
			MainWindow.fenster.getContentPane().removeAll();
			new Hauptmenu();
		});

		JButton widerstand = new JButton("Widerstandsrechner");
		widerstand.addActionListener(e -> 
		{
			MainWindow.fenster.getContentPane().removeAll();
			new Widerstandsrechner();
		});

		elektronik.gbc.gridx = 0;
		elektronik.gbc.gridy = 0;
		elektronik.gbc.gridwidth = 1;

		elektronik.gbc.gridx = 1;
		elektronik.gbc.gridy = 1;
		elektronik.add(menü, elektronik.gbc);

		elektronik.gbc.gridx = 1;
		elektronik.gbc.gridy = 2;
		elektronik.add(widerstand, elektronik.gbc);

		MainWindow.fenster.add(elektronik);

		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();

		elektronik.setVisible(true);
	}
}

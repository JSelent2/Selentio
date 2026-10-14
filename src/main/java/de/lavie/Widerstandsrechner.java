package de.lavie;

import java.awt.Color;

import javax.swing.JButton;

public class Widerstandsrechner 
{
	Widerstandsrechner() 
	{

		Pageview widerstand_berechnen = new Pageview(MainWindow.fenster, "Widerstandsrechner", Color.ORANGE, Color.CYAN);

		JButton menü = new JButton("Hauptmenü");
		menü.addActionListener(e -> 
		{
			new Hauptmenu();
		});

		JButton widerstand_rechner = new JButton("Rechner");
		//widerstand_rechner.addActionListener(e -> widerstand_berechnung(fenster, widerstand_berechnen));

		widerstand_berechnen.gbc.gridwidth = 1;

		widerstand_berechnen.gbc.gridx = 1;
		widerstand_berechnen.gbc.gridy = 1;
		widerstand_berechnen.add(menü, widerstand_berechnen.gbc);
		widerstand_berechnen.gbc.gridy = 2;
		widerstand_berechnen.add(widerstand_rechner, widerstand_berechnen.gbc);

		MainWindow.fenster.add(widerstand_berechnen);

		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();
		
		widerstand_berechnen.setVisible(true);
	}
}

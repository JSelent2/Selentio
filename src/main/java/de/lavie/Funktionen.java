package de.lavie;

import java.awt.Color;

import javax.swing.JButton;

public class Funktionen 
{
	Funktionen() 
	{

		Pageview funktionen = new Pageview(MainWindow.fenster, "Funktionen", new Color(50, 70, 230), new Color(200, 90, 30));

		JButton elektronikButton = new JButton("Elektronik");
		elektronikButton.addActionListener(e -> 
		{
			new Elektronik();
		});

		JButton menü = new JButton("Hauptmenü");
		menü.addActionListener(e -> 
		{
			new Hauptmenu();
		});

		funktionen.gbc.gridwidth = 1;

		funktionen.gbc.gridy = 1;
		funktionen.gbc.gridx = 1;
		funktionen.add(menü, funktionen.gbc);

		funktionen.gbc.gridx = 1;
		funktionen.gbc.gridy = 2;
		funktionen.add(elektronikButton, funktionen.gbc);

		MainWindow.fenster.add(funktionen);
		MainWindow.fenster.revalidate();
		MainWindow.fenster.repaint();

		funktionen.setVisible(true);
	}
}

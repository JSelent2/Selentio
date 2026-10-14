package de.lavie;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Startseite
{
	public Pageview panel;

	public Startseite()
	{
		panel = new Pageview(MainWindow.fenster, "Selentio", Color.RED, Color.BLACK);
		JLabel label2 = new JLabel("Startseite");
		label2.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
		label2.setForeground(new Color(255, 200, 230));

		panel.gbc.gridy = 2;
		panel.add(label2, panel.gbc);

		panel.gbc.gridy = 3;
		JButton funktionenButton = new JButton("Funktionen");
		funktionenButton.addActionListener(e -> {
			new Funktionen();
		});
		panel.add(funktionenButton, panel.gbc);

		panel.gbc.gridy = 4;
		JButton notizenButton = new JButton("Notizen");
		notizenButton.addActionListener(e -> {
			NotizenListe notizenListe = new NotizenListe();
			notizenListe.notizen_anlegen();
		});
		panel.add(notizenButton, panel.gbc);

		panel.gbc.gridy = 5;
		JButton kontaktButton = new JButton("Kontakt");
		kontaktButton.addActionListener(e -> {
			System.out.println("Kontakt geklickt!");
			// KontaktCsv kontaktCsv = new KontaktCsv();
			// kontaktCsv.kontakte();
		});
		panel.add(kontaktButton, panel.gbc);

	}
}

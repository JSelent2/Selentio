package de.lavie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KontaktCSV
{
	Pageview kontakte = new Pageview(MainWindow.fenster, "Kontakte", Color.GRAY, Color.WHITE);

	public ArrayList<String> CsvNamen = new ArrayList<>();

	int file_count;

	public void fill_list(String pfad)
	{
		file_count = 0;

		File ordner = new File(pfad);

		File[] dateien = ordner.listFiles((dir, name) -> name.endsWith(".csv"));

		if (dateien != null)
		{
			CsvNamen.clear();

			for (File datei : dateien)
			{
				CsvNamen.add(datei.getName());
				file_count++;
			}
		}
	}

	public void kontakte()
	{
		kontakte.removeAll();

		JButton menü = new JButton("Hauptmenü");
		kontakte.gbc.gridy++;
		menü.addActionListener(e -> {
			new Hauptmenu();
		});
		JButton neuer_kontakt = new JButton("Neuen Kontakt hinzufügen");
		kontakte.gbc.gridy++;
		neuer_kontakt.addActionListener(e -> {
			neuer_kontakt();
		});

		fill_list("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV");

		kontakte.gbc.gridwidth = 1;

		kontakte.gbc.gridx = 1;
		kontakte.gbc.gridy++;
		kontakte.add(menü, kontakte.gbc);
		kontakte.gbc.gridy++;
		kontakte.add(neuer_kontakt, kontakte.gbc);

		for (int i = 0; i < file_count; i++)
		{
			int index = i;
			SButton kontakt_button = new SButton(CsvNamen.get(index), index);
			kontakt_button.addActionListener(e -> {
				Path path = Paths.get("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\daten.csv");
				try
				{
					List<String> lines = Files.readAllLines(path);
					for (String line : lines)
					{
						String[] values = line.split(";");
						JLabel message = new JLabel("E-Mail: " + values[0] + "Vorname: " + values[1] + "Nachname: "
								+ values[2] + "MitarbeiterID: " + values[3] + "Telefonnummer: " + values[4]);
					}
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				MainWindow.fenster.repaint();
				MainWindow.fenster.revalidate();
			});
			kontakte.gbc.gridy++;
			kontakte.add(kontakt_button, kontakte.gbc);
			kontakte.gbc.gridx++;
			SButton delete_button = new SButton("Delete", index);
			kontakte.add(delete_button, kontakte.gbc);
			delete_button.addActionListener(e -> {
				kontakte.remove(delete_button);
				kontakte.remove(kontakt_button);
				MainWindow.fenster.repaint();
				MainWindow.fenster.revalidate();
				try
				{
					Files.delete(Path.of("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\" + CsvNamen.get(index)));
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			});
			kontakte.gbc.gridx++;
			SButton edit_button = new SButton("Edit", index);
			kontakte.add(edit_button, kontakte.gbc);
			edit_button.addActionListener(e -> {
				JTextField text_editor = new JTextField();
			});
		}
		MainWindow.fenster.add(kontakte);
		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();

		kontakte.setVisible(true);
	}
	
	public void neuer_kontakt()
	{
		kontakte.gbc.gridy++;
		JTextField kontakt_eingabe = new JTextField(40);
		
	}

}

package de.lavie;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class KontaktCSV
{
	DefaultTableModel model;
	JTable table;
	Path path = Paths.get("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\daten.csv");
	int buttonClickCounter = 1;
	JScrollPane scrollPane;
	
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
				if (buttonClickCounter % 2 == 1)
				{
					useTable(); //Loads a table with the data from the CSV file
					System.out.println("buttonClickCounter modulo 2 = " + buttonClickCounter % 2);
					MainWindow.fenster.repaint();
					MainWindow.fenster.revalidate();
					buttonClickCounter++;
				}
				else
				{
					MainWindow.fenster.remove(scrollPane);
					MainWindow.fenster.remove(table);
					MainWindow.fenster.repaint();
					MainWindow.fenster.revalidate();
					buttonClickCounter++;
				}
			});
			kontakte.gbc.gridy++;
			kontakte.add(kontakt_button, kontakte.gbc);
			kontakte.gbc.gridx = 2;
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
				MainWindow.fenster.repaint();
				MainWindow.fenster.revalidate();
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
		kontakte.add(kontakt_eingabe, kontakte.gbc);
		kontakt_eingabe.addActionListener(e ->
		{
			String string_kontakt_eingabe = kontakt_eingabe.getText();
			String[] line = string_kontakt_eingabe.split(";");
			int row_count = line.length;
			if(string_kontakt_eingabe != null && row_count == 5)
			{
				int line_number = 0;
				try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\daten.csv")))
				{
					while (reader.readLine() != null)
					{
						line_number++;
					}
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
				System.out.println("Wert von integer line_count in zeile 132: " + row_count + " " + line[0] +" "+ line[1] +" "+ line[2] +" "+  line[3] +" "+ line[4]);
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\daten.csv")))
				{
					for (int k = 0; k < line_number; k++)
					{
						String existingLine = Files.readAllLines(Path.of("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\CSV\\daten.csv")).get(k);
						writer.write(existingLine);
						writer.newLine();
					}
					writer.write(line[0] + ";" + line[1] + ";" + line[2] + ";" + line[3] + ";" + line[4]);
					writer.newLine();
					kontakte.remove(kontakt_eingabe);
					kontakte();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
			else 
			{
				System.out.println("Ungültige Eingabe. Bitte geben Sie 5 Werte durch Semikolons getrennt ein.");
			}
		});
		
		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();
	}
	
	public void useTable()
	{
		try
		{
			String[] spalten_namen = {"E-Mail: ", "Vorname: ", "Nachname: ", "MitarbeiterID: ", "Telefonnummer: "};
			kontakte.gbc.gridy++;
			model = new DefaultTableModel(null, spalten_namen);
			table = new JTable(model);
			List<String> lines = Files.readAllLines(path);
			int columnCount = 0;
			int rowCount = 0;
			for (String line : lines)
			{
				String[] values = line.split(";");
				model.addRow(values);
				
				System.out.println("Zeile hinzugefügt: " + line + "columnCount: " + columnCount);
				
				if (model.getValueAt(rowCount, 0) == null || model.getValueAt(rowCount, 0) == "" || model.getValueAt(rowCount, 0) == "NULL")
				{
					model.removeRow(rowCount--);
				}
				rowCount++;
			}
			model.addRow(new Object[]{"", "", "", "", ""});
			kontakte.gbc.weightx = 1;
			kontakte.gbc.gridx = 1;
			scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new java.awt.Dimension(1400, 900));
			kontakte.add(scrollPane, kontakte.gbc);
			model.addTableModelListener(e1 ->
			{
				if (e1.getType() == TableModelEvent.UPDATE)
				{
					int row = e1.getFirstRow();
					int column = e1.getColumn();
					String newValue = (String) model.getValueAt(row, column);
					System.out.println("Wert in Zeile " + row + ", Spalte " + column + " wurde geändert zu: " + newValue);
					updateTable();
				}
				MainWindow.fenster.repaint();
				MainWindow.fenster.revalidate();
			});
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	void updateTable()
	{
		try
		{
			List<String> updatedLines = new ArrayList<>();
			for (int i1 = 0; i1 < model.getRowCount(); i1++)
			{
				StringBuilder stringBuilder = new StringBuilder();
				for (int j = 0; j < model.getColumnCount(); j++)
				{
					stringBuilder.append(model.getValueAt(i1, j));
					if (j < model.getColumnCount() - 1)
					{
						stringBuilder.append(";");
					}
				}
				updatedLines.add(stringBuilder.toString());
			}
			Files.write(path, updatedLines);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();
	}

}

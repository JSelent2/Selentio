package de.lavie;

import java.util.*;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class NotizenListe 
{
	
	Pageview notizen_anlegen = new Pageview(MainWindow.fenster, "Notizen anlegen", Color.ORANGE, Color.YELLOW);
	
	public ArrayList<String> DateiNamen = new ArrayList<>();
	
	int file_count;
	
	public void fill_list(String pfad)
	{
		file_count = 0;
		
		File ordner = new File(pfad);
		
		File[] dateien = ordner.listFiles((dir, name) -> name.endsWith(".txt"));
		
		if (dateien != null)
		{
			DateiNamen.clear();
			
			for (File datei : dateien)
			{
				DateiNamen.add(datei.getName());
				file_count++;
			}
		}
	}
	
	public void notiz_speichern(String notiz_name) 
	{
		JTextField notiz_inhalt = new JTextField(30);
		System.out.println("Notiz_speichern 2. zeile");
		notizen_anlegen.add(notiz_inhalt, notizen_anlegen.gbc);
		MainWindow.fenster.repaint();
		MainWindow.fenster.revalidate();
		System.out.println("Notiz_speichern 5. zeile");
		notiz_inhalt.addActionListener(e -> 
		{
			if (notiz_inhalt.getText().isEmpty()) 
			{
				notizen_anlegen();
			}
			else 
			{
				String notiz_eingabe = notiz_inhalt.getText();
				System.out.println("Test" + notiz_eingabe);
				try (FileWriter writer = new FileWriter("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\" + notiz_name + ".txt")) 
				{
					writer.write(notiz_eingabe);
					fill_list("C:\\Users\\Jselent\\Desktop\\Neuer Ordner");
				} 
				catch (IOException ex) 
				{
					new Hauptmenu();
				}
				notizen_anlegen();
			}
		});
	}
	
	public void notizen_anlegen() 
	{	
	    	notizen_anlegen.removeAll();
	    	notizen_anlegen.label.setFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 48));
	    	
	    	JButton neue_notiz = new JButton("Neue Notiz");
	    	notizen_anlegen.gbc.gridy = 2;
	    	neue_notiz.addActionListener(e -> 
	    	{
	    		System.out.println("Geklickt!!!!!");
	    		neue_notiz();
	    	});
	    	
	    	JButton menü = new JButton("Hauptmenü");
	    	menü.addActionListener(e -> 
	    	{
	    		new Hauptmenu();
	    	});
	    	
	    	
	    	fill_list("C:\\Users\\Jselent\\Desktop\\Neuer Ordner");
	
	    	notizen_anlegen.gbc.gridwidth = 1;
	    	
	    	
	    	
	    	notizen_anlegen.gbc.gridx = 1;
	    	notizen_anlegen.gbc.gridy++;
	    	notizen_anlegen.add(menü, notizen_anlegen.gbc);
	    	notizen_anlegen.gbc.gridy++;
	    	notizen_anlegen.add(neue_notiz, notizen_anlegen.gbc);
	    	
	    	for (int i = 0; i < file_count; i++)
	    	{
	    		int index = i;
	    		SButton notiz_button = new SButton(DateiNamen.get(index), index);
	    		notiz_button.addActionListener(e -> 
	    		{
	    			System.out.println("Button geklickt!");
	    			try 
	    			{
		    			System.out.println(index);
	    				String inhalt = Files.readString(Path.of("C:\\Users\\Jselent\\Desktop\\Neuer ordner\\" + DateiNamen.get(index)));
	    				
		    			JOptionPane.showMessageDialog(null, inhalt);
	    			}	
	    			catch (IOException ex)
	    			{
	    				ex.printStackTrace();
	    			}
	    			MainWindow.fenster.repaint();
	    			MainWindow.fenster.revalidate();
	    		});
	    		notizen_anlegen.gbc.gridy++;
	    		notizen_anlegen.add(notiz_button, notizen_anlegen.gbc);
	    		notizen_anlegen.gbc.gridx++;
	    		SButton delete_button = new SButton("Delete", index);
	    		notizen_anlegen.add(delete_button, notizen_anlegen.gbc);
	    		notizen_anlegen.gbc.gridx--;
	    		delete_button.addActionListener(e ->
	    		{
	    			notizen_anlegen.remove(delete_button);
	    			notizen_anlegen.remove(notiz_button);
	    			MainWindow.fenster.repaint();
	    			MainWindow.fenster.revalidate();
	    			try 
	    			{
	    				Files.delete(Path.of("C:\\Users\\Jselent\\Desktop\\Neuer Ordner\\" + DateiNamen.get(index)));
	    			}
	    			catch (IOException ex)
	    			{	
	    				System.out.print(ex);
	    			};
	    		});
	    	};
	    	
	    	MainWindow.fenster.add(notizen_anlegen);
	    	
	    	MainWindow.fenster.repaint();
	    	MainWindow.fenster.revalidate();
	    	
	    	notizen_anlegen.setVisible(true);
	    }
	
	public void neue_notiz() 
	{
		JTextField text_field = new JTextField(20);
		System.out.println("TestTestNeueNotizWirdErfolgreichGecalled und kommt bis zur zweiten zeile!");
		notizen_anlegen.gbc.gridy++;
		System.out.println("TestTestNeueNotizWirdErfolgreichGecalled und kommt bis zur vierten zeile!");
		notizen_anlegen.add(text_field, notizen_anlegen.gbc);
		MainWindow.fenster.getContentPane().repaint();
		MainWindow.fenster.getContentPane().revalidate();
		System.out.println("TestTestNeueNotizWirdErfolgreichGecalled und kommt bis zur siebten zeile!");
		text_field.addActionListener(e -> 
		{
			if (text_field.getText().isEmpty()) 
			{
				notizen_anlegen();
			} 
			else 
			{
				String notiz_name = text_field.getText();
				MainWindow.fenster.remove(text_field);
				MainWindow.fenster.revalidate();
				MainWindow.fenster.repaint();
				notiz_speichern(notiz_name);
				notizen_anlegen.gbc.gridy++;
			}
		});

		notizen_anlegen.gbc.gridy++;
	}
	
}

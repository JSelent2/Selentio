package de.lavie;

import javax.swing.*;
import java.awt.*;

public class Pageview extends GradientPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2693081642278347028L;
	public JFrame frame;
	public JLabel label;
	public Pageview panel;
	public GridBagConstraints gbc;

	public Pageview(JFrame fenster, String string, Color color1, Color color2)
	{
		super(color1, color2);
		frame = fenster;
		standardLayout(string);
	}

	public void standardLayout(String string)
	{
		frame.getContentPane().removeAll();
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.NORTH;
		label = new JLabel(string);
		label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 48));
		label.setForeground(Color.BLACK);
		gbc.gridwidth = 2;
		add(label, gbc);
	}
}

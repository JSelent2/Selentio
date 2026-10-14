package de.lavie;

import javax.swing.*;

public class SButton extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 84210261653591976L;
	public int id;

	public SButton(String text, int id)
	{
		super(text);
		this.id = id;
	}

}

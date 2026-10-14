package de.lavie;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3289475740075328719L;
	public Color color1;
	public Color color2;

	public GradientPanel(Color one, Color two)
	{
		color1 = one;
		color2 = two;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}
}
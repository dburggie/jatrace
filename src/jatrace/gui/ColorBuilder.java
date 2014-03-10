package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ColorBuilder extends JPanel implements FocusListener
{
	
	private JTextField red, green, blue;
	
	public ColorBuilder(String colorName)
	{
		super(new GridLayout(2,1,0,2));
		
		if (colorName == null || colorName.equals(""))
		{
			colorName = "Color";
		}
		
		JLabel label = new JLabel(colorName, JLabel.CENTER);
		label.setPreferredSize(new Dimension(0,25));
		add(label);
		
		red = new JTextField(5);
		//red.addActionListener(this);
		red.addFocusListener(this);
		
		green = new JTextField(5);
		//green.addActionListener(this);
		green.addFocusListener(this);
		
		blue = new JTextField(5);
		//blue.addActionListener(this);
		blue.addFocusListener(this);
		
		add(buildFullInputArea());
		
		setOpaque(true);
	}
	
	/** builds all three input areas */
	private JPanel buildFullInputArea()
	{
		JPanel panel = new JPanel(new GridLayout(1,3,5,0));
		
		panel.add( buildSingleTextArea("red:",red) );
		panel.add( buildSingleTextArea("green:",green) );
		panel.add( buildSingleTextArea("blue:",blue) );
		
		return panel;
		
	}
	
	/** Builds a single input area from a string and a textfield */
	private JPanel buildSingleTextArea(String s, JTextField f)
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel(s, JLabel.CENTER), BorderLayout.LINE_START);
		panel.add(f, BorderLayout.CENTER);
		
		return panel;
	}
	
	public jatrace.Color build()
	{
		
		double r, g, b;
		String input;
		
		//parse red
		input = red.getText();
		try {
			r = toDouble(input);
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			red.requestFocus();
			return null;
		}
		
		//parse green
		input = green.getText();
		try {
			g = toDouble(input);
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			green.requestFocus();
			return null;
		}
		
		//parse blue
		input = blue.getText();
		try {
			b = toDouble(input);
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			blue.requestFocus();
			return null;
		}
		
		return new jatrace.Color(r,g,b);
		
	}
	
	private static double toDouble(String s) throws NumberFormatException
	{
		
		double d = 0.0;
		try {
			d = Double.parseDouble(s);
		}
		
		catch (NumberFormatException e)
		{
			String l = "couldn't decode " + s + " as double, attempting as int";
			System.out.println(l);
			d = Integer.decode(s) / 256.0;
		}
		
		
		return d;
	}
	
	// Focus Listener Interface Below
	
	public void focusGained(FocusEvent e)
	{
		Component c = e.getComponent();
		if (c instanceof JTextField)
		{
			((JTextField)c).selectAll();
		}
	}
	
	public void focusLost(FocusEvent e) { }
	
	
}

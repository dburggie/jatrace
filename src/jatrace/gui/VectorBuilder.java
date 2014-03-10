package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VectorBuilder extends JPanel implements FocusListener
{
	
	private JTextField xField, yField, zField;
	
	public VectorBuilder(String vectorName)
	{
		super(new GridLayout(2,1,0,2));
		
		if (vectorName == null || vectorName.equals(""))
		{
			vectorName = "Vector";
		}
		
		JLabel label = new JLabel(vectorName, JLabel.CENTER);
		label.setPreferredSize(new Dimension(0,25));
		add(label);
		
		xField = new JTextField(5);
		xField.addFocusListener(this);
		
		yField = new JTextField(5);
		yField.addFocusListener(this);
		
		zField = new JTextField(5);
		zField.addFocusListener(this);
		
		add(buildFullInputArea());
		
		setOpaque(true);
	}
	
	/** builds all three input areas */
	private JPanel buildFullInputArea()
	{
		
		JPanel panel = new JPanel(new GridLayout(1,3,5,0));
		
		panel.add( buildSingleTextArea("x:",xField) );
		panel.add( buildSingleTextArea("y:",yField) );
		panel.add( buildSingleTextArea("z:",zField) );
		
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
	
	public jatrace.Vector build()
	{
		
		double x,y,z;
		String input;
		
		//parse xField
		input = xField.getText();
		try {
			x = toDouble(input);
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			xField.requestFocus();
			return null;
		}
		
		//parse yField
		try {
			y = toDouble( yField.getText() );
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			yField.requestFocus();
			return null;
		}
		
		//parse zField
		try {
			z = toDouble( zField.getText() );
		
		} catch (NumberFormatException e) {
			System.out.println("couldn't decode "+ input);
			zField.requestFocus();
			return null;
		}
		
		return new jatrace.Vector(x,y,z);
		
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
			d = (double) Integer.decode(s);
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

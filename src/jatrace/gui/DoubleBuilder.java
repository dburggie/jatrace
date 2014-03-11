package jatrace.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DoubleBuilder extends JPanel implements FocusListener
{
	
	JLabel label;
	JTextField field;
	
	public DoubleBuilder(String name, double value)
	{
		super(new GridLayout(1,2));
		setPreferredSize(new Dimension(0,25));
		
		label = new JLabel(name, JLabel.CENTER);
		add(label);
		
		field = new JTextField( Double.toString(value), 5 );
		field.addFocusListener(this);
		add(field);
	}
	
	public double getValue() throws NumberFormatException
	{
		
		String input = field.getText();
		double d = 0.0;
		try 
		{
			d = Double.parseDouble(input);	
		}
		
		catch (NumberFormatException e)
		{
			String l;
			l = "couldn't decode " + input + " as double, attempting as int";
			System.out.println(l);
			d = (double) Integer.decode(input);
		}
		
		return d;
		
	}
	
	public void setValue(double d)
	{
		field.setText( Double.toString(d) );
	}
	
	public void requestFocus()
	{
		field.requestFocus();
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

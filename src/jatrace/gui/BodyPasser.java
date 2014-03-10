package jatrace.gui;

import jatrace.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public interface BodyPasser
{
	public void setBody(String t, Body b);
	public Body getBody();
	
	public void setPrevBodyPasser(BodyPasser p);
	public void setNextBodyPasser(BodyPasser p);
	public void insertBefore(BodyPasser p);
	public void insertAfter(BodyPasser p);
	public void remove();
}


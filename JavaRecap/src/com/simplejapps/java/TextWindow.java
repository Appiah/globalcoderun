package com.simplejapps.java;

import java.awt.Frame;
import java.awt.AWTEvent;
import java.awt.TextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;

public class TextWindow extends Frame {
	
    TextArea textarea;
    boolean doubleSpace = false;
    
    TextWindow(String title) {
        super(title);
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        setLocation(50,50);
        Font font = new Font("default",Font.BOLD,14);
        textarea = new TextArea(20,80);
        textarea.setBackground(Color.cyan);
        textarea.setFont(font);
        add(textarea);
        line();
        pack();
        setVisible(true);
    }
    public void line(String str) {
        if(str != null)
            textarea.append(str);
        line();
    }
    public void line(StringBuffer sb) {
        line(sb.toString());
    }
    public void line() {
        if(doubleSpace)
            textarea.append("\n");
        textarea.append("\n   ");
    }
    public void part(String str) {
        if(str != null)
            textarea.append(str);
    }
    public void setSingleSpace() {
        doubleSpace = false;
    }
    public void setDoubleSpace() {
        doubleSpace = true;
    }
    public void processWindowEvent(WindowEvent event) {
        if(event.getID() == WindowEvent.WINDOW_CLOSING)
            System.exit(0);
    }
    public static void main(String [] args) {
    	TextWindow("Aireceive Desktop Console");
    }
}

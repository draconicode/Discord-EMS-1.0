package fr.dreregon.Discord_EMS.system;
/**
Copyright 2018 Dreregon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
	
	/*
	 This file is part of the BlueJ program. 
	 Copyright (C) 1999-2009  Michael Klling and John Rosenberg 
	 
	 This program is free software; you can redistribute it and/or 
	 modify it under the terms of the GNU General Public License 
	 as published by the Free Software Foundation; either version 2 
	 of the License, or (at your option) any later version. 
	 
	 This program is distributed in the hope that it will be useful, 
	 but WITHOUT ANY WARRANTY; without even the implied warranty of 
	 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
	 GNU General Public License for more details. 
	 
	 You should have received a copy of the GNU General Public License 
	 along with this program; if not, write to the Free Software 
	 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA. 
	 
	 This file is subject to the Classpath exception as provided in the  
	 LICENSE.txt file that accompanied this code.
	 */

	import java.awt.Color;
	import java.awt.Component;
	import java.awt.Font;
	import java.util.ArrayList;
	import java.util.List;

	import javax.swing.Box;
	import javax.swing.BoxLayout;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	/**
	 ** @version $Id: MultiLineLabel.java 6164 2009-02-19 18:11:32Z polle $
	 ** @author Justin Tan
	 ** A multi-line Label-like AWT component.
	 **/
	public class Sys_Label extends JPanel
	{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		protected int fontAttributes = Font.PLAIN;
	    protected float alignment;
	    protected Color col = null;
	  protected int spacing = 0;
	    
	    /**
	     ** Constructor - make a multiline label
	     **/
	    public Sys_Label(String text, float alignment)
	    {
	        this.alignment = alignment;
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        if(text != null)
	            setText(text);
	    }

	    /**
	     ** Constructor, defaults to centered text
	     **/
	    public Sys_Label(String text)
	    {
	        this(text, LEFT_ALIGNMENT);
	    }

	    /**
	     * Constructor, empty with the given alignment
	     */
	    public Sys_Label(float alignment)
	    {
	        this(null, alignment);
	    }

	    /**
	     * Constructor, empty with the given alignment and line spacing
	     */
	    public Sys_Label(float alignment, int spacing)
	    {
	        this(null, alignment);
	        this.spacing = spacing;
	    }

	    /**
	     ** Constructor - make an empty multiline label
	     **/
	    public Sys_Label()
	    {
	        this(null, LEFT_ALIGNMENT);
	    }
	  
	    public void setText(String text)
	    {
	        // clear the existing lines from the panel
	        removeAll();
	        addText(text);
	    }
	  
	    public void addText(String text)
	    {
	        addText(text, 12);
	    }
	    
	    public void addText(String text, int size)
	    {
	        if(spacing > 0)
	            add(Box.createVerticalStrut(spacing));

	        String strs[] = splitLines(text);
	        JLabel l;
	        Font font = new Font("SansSerif", fontAttributes, size);

	        for (int i = 0; strs != null && i < strs.length; i++) {
	            l = new JLabel(strs[i]);
	            l.setFont(font);
	            l.setAlignmentX(alignment);

	            if (col != null)
	                l.setForeground(col);

	            add(l);
	        }   
	    }

	    /**
	     * Splits "string" by "Delimiter"
	     * 
	     * @param str - the string to be split
	     * @param delimiter - the field delimiter within str
	     * @returns an array of Strings
	     */
	    public static String[] split(String str, String delimiter)
	    {
	        List<String> strings = new ArrayList<String>();
	        int start = 0;
	        int len = str.length();
	        int dlen = delimiter.length();
	        int offset = str.lastIndexOf(delimiter); // First of all, find the
	        // Last occurance of the Delimiter
	        // Stop empty delimiters
	        if (dlen < 1)
	            return null;
	        else if (offset < 0) // one element
	        {
	            String[] result = {str};
	            return result;
	        }

	        //
	        // Append the delimiter onto the end if it doesn't already exit
	        //
	        if (len > offset + dlen) {
	            str += delimiter;
	            len += dlen;
	        }

	        do {
	            // Get the new Offset
	            offset = str.indexOf(delimiter, start);
	            strings.add(str.substring(start, offset));

	            // Get the new Start position
	            start = offset + dlen;
	        } while ((start < len) && (offset != -1));

	        // Convert the list into an Array of Strings
	        String result[] = new String[strings.size()];
	        strings.toArray(result);
	        return result;
	    }

	    /**
	     * Splits "string" into lines (stripping end-of-line characters)
	     * 
	     * @param str - the string to be split
	     * @returns an array of Strings
	     */
	    public static String[] splitLines(String str)
	    {
	        return (str == null ? null : split(str, "\n"));
	    }

	    public void addText(String text, boolean bold, boolean italic)
	    {
	        int oldAttributes = fontAttributes;
	        setBold(bold);
	        setItalic(italic);
	        addText(text);
	        fontAttributes = oldAttributes;
	    }

	    public void setForeground(Color col)
	    {
	        this.col = col;    
	        Component[] components = this.getComponents();
	        for (int i = 0; i < components.length; i++) {
	      Component component = components[i];
	      component.setForeground(col);
	    }
	    }
	      
	    public void setItalic(boolean italic)
	    {
	        if(italic)
	           fontAttributes |= Font.ITALIC;
	        else
	            fontAttributes &= ~Font.ITALIC;
	    }
	  
	    public void setBold(boolean bold)
	    {
	        if(bold)
	            fontAttributes |= Font.BOLD;
	        else
	            fontAttributes &= ~Font.BOLD;
	    }
	}



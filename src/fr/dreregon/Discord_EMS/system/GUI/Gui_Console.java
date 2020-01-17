package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

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
public class Gui_Console {

	public static JFrame frmDiscordemsConsole;
	public static boolean opened = false;
	private static JTextPane consoleArea = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(boolean visible) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Gui_Console window = new Gui_Console();
					window.frmDiscordemsConsole.setVisible(visible);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(visible == true) {
					opened = true;
				}else {
					opened = false;
				}

			}
		});
	}



	/**
	 * Create the application.
	 */
	public Gui_Console() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDiscordemsConsole = new JFrame();
		frmDiscordemsConsole.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
		});
		frmDiscordemsConsole.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				opened = false;
			}
		});
		frmDiscordemsConsole.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Console.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmDiscordemsConsole.setTitle("Discord-EMS Console");
		frmDiscordemsConsole.setBounds(100, 100, 800, 500);
		frmDiscordemsConsole.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		consoleArea.setFont(new Font("Lucida Console", Font.PLAIN, 11));

		JScrollPane scrollPane = new JScrollPane(consoleArea);
		scrollPane.setInheritsPopupMenu(true);
		scrollPane.setAutoscrolls(true);
		frmDiscordemsConsole.getContentPane().add(scrollPane, BorderLayout.CENTER);

		consoleArea.setForeground(Sys_Util.greyple);
		consoleArea.setBackground(Sys_Util.notblack);
		consoleArea.setEditable(false);
		scrollPane.setViewportView(consoleArea);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(consoleArea, popupMenu);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.setIcon(new ImageIcon(Gui_Console.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = consoleArea.getSelectedText();
				if(selected.equals("")) {
					return;
				}else {
					Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
					c.setContents(new StringSelection(selected), null);
				}
			}
		});
		popupMenu.add(mntmCopy);
	}

	/**
	 * Output text into consoleArea
	 * @param string
	 */
	public static void println(String string) {
		append(string, Sys_Util.greyple);
	}

	public static void err(String string) {
		append(string, Sys_Util.discordRed);
	}
	
	public static void printClr(String string, Color c) {
		append(string, c);
	}

	private static void append(String string, Color color) {
		StyledDocument doc = consoleArea.getStyledDocument();
		SimpleAttributeSet aset = new SimpleAttributeSet();
		StyleConstants.setForeground(aset, color);
		StyleConstants.setBackground(aset, Sys_Util.notblack);
		StyleConstants.setBold(aset, false);
		try
		{
			doc.insertString(0,"", null );
			doc.insertString(doc.getLength(), string+"\n", aset );
		}
		catch(Exception e) { System.out.println(e); }
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

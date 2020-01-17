package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;
import fr.dreregon.updater.UpdateChecker;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Cursor;
import java.awt.Toolkit;

/**
Copyright 2019 Dreregon

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
public class Gui_UpdateChecking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JFrame frame;
	public static JLabel lblCheckingForUpdates = new JLabel("Checking for updates...");
	public static JLabel logoLabel = new JLabel("");
	public static JLabel gifLabel = new JLabel("");
	
	public static JPanel panel = new JPanel();
	public static JButton btnUpdate = new JButton("Update");
	public static JButton btnCancel = new JButton("Cancel");
	public static JTextPane resultLabel = new JTextPane();
	
	public static String upNewVer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Gui_UpdateChecking frame1 = new Gui_UpdateChecking();
					frame1.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public Gui_UpdateChecking() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_UpdateChecking.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16_Update.png")));
		frame.setType(Type.UTILITY);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 540, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Sys_Util.discorddark);
		frame.setContentPane(contentPane);
		frame.setLocationRelativeTo(null);

		
		lblCheckingForUpdates.setBounds(10, 0, 514, 27);
		lblCheckingForUpdates.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCheckingForUpdates.setForeground(Color.WHITE);
		lblCheckingForUpdates.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		logoLabel.setBounds(136, 40, 256, 256);
		logoLabel.setIcon(new ImageIcon(Gui_UpdateChecking.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_256.png")));
		logoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(null);
		contentPane.add(lblCheckingForUpdates);
		
		
		gifLabel.setIcon(new ImageIcon(Gui_UpdateChecking.class.getResource("/fr/dreregon/Discord_EMS/system/media/loading.gif")));
		gifLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		gifLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gifLabel.setBounds(299, 203, 64, 64);
		contentPane.add(gifLabel);
		contentPane.add(logoLabel);
		
		
		panel.setVisible(false);
		panel.setBackground(Sys_Util.notblack);
		panel.setBounds(10, 11, 514, 279);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		btnUpdate.setBounds(415, 245, 89, 23);
		btnUpdate.setEnabled(false);
		panel.add(btnUpdate);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		
		btnCancel.setBounds(10, 245, 89, 23);
		panel.add(btnCancel);
		resultLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		resultLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		resultLabel.setForeground(Color.WHITE);
		resultLabel.setEditable(false);
		resultLabel.setBounds(10, 11, 494, 196);
		resultLabel.setBackground(Sys_Util.notblack);
		StyledDocument doc = resultLabel.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		panel.add(resultLabel);
		
		for (Component a : panel.getComponents()) {
			a.setVisible(false);
		}
		
		

		Runnable r = new Runnable() {
			@Override
			public void run() {
				UpdateChecker.checkForUpdate();
			}
		};

		Thread t = new Thread(r);
		t.start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(t != null) t.interrupt();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateChecker.update(upNewVer);
			}
		});
	} 
	
	public static void close() {
		if(frame != null) frame.dispose();
	}
	
	public static void updateAvailable(boolean isThereAnUpdate, String version) {
		if(isThereAnUpdate) {
			panel.setVisible(true);
			btnCancel.setVisible(true);
			btnUpdate.setVisible(true);
			resultLabel.setVisible(true);
			
			gifLabel.setVisible(false);
			logoLabel.setVisible(false);
			lblCheckingForUpdates.setVisible(false);
			
			frame.repaint();
			
			resultLabel.setText("New Update available !\\n\nNew : "+version+"\nCurrent : "+Sys_Start.version);
			btnUpdate.setEnabled(true);
			
			
		}else {
			panel.setVisible(true);
			btnCancel.setVisible(true);
			btnUpdate.setVisible(true);
			resultLabel.setVisible(true);
			
			gifLabel.setVisible(false);
			logoLabel.setVisible(false);
			lblCheckingForUpdates.setVisible(false);
			
			frame.repaint();
		}
	}
}

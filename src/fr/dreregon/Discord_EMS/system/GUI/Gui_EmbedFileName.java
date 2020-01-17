package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Json;
import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Gui_EmbedFileName {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmChooseFileName;
	private JTextField textField;
	public static boolean opened = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Gui_EmbedFileName window = new Gui_EmbedFileName();
					window.frmChooseFileName.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("EmbedFileName");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_EmbedFileName() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChooseFileName = new JFrame();
		frmChooseFileName.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(!Sys_Util.openedWindows.isEmpty()) {
				int index = Sys_Util.openedWindows.indexOf("EmbedFileName");
				Sys_Util.openedWindows.remove(index);
				}
			}
		});
		frmChooseFileName.setResizable(false);
		frmChooseFileName.setTitle(BUNDLE.getString("Gui_EmbedFileName.frmChooseFileName.title")); //$NON-NLS-1$
		frmChooseFileName.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_EmbedFileName.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmChooseFileName.setBounds(100, 100, 450, 130);
		frmChooseFileName.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChooseFileName.getContentPane().setBackground(Sys_Util.discorddark);
		frmChooseFileName.setLocationRelativeTo(null);
		frmChooseFileName.getContentPane().setLayout(null);
		if(Sys_Start.windows) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
		}else {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		}
		
		JLabel lblChooseFileName = new JLabel(BUNDLE.getString("Gui_EmbedFileName.lblChooseFileName.text")); //$NON-NLS-1$
		lblChooseFileName.setForeground(Color.WHITE);
		lblChooseFileName.setBounds(10, 11, 167, 14);
		frmChooseFileName.getContentPane().add(lblChooseFileName);
		
		textField = new JTextField();
		textField.setBounds(10, 35, 424, 20);
		frmChooseFileName.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBackground(Sys_Util.notblack);
		textField.setForeground(Color.WHITE);
		textField.setCaretColor(Color.WHITE);
		
		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_EmbedFileName.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("")) {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous devez entrer un nom de fichier !", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You cannot save with an empty name !", Sys_MsgType.ERROR);
					}
					
					return;
				}else {
					Sys_Json.createEmbedJson("Discord-EMS/Saved-Embeds/"+textField.getText()+".json", Gui_Main.builder2);
					frmChooseFileName.dispose();
				}
				
			}
		});
		btnNewButton.setBounds(345, 67, 89, 23);
		frmChooseFileName.getContentPane().add(btnNewButton);
	}
}

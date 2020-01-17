package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_SwingLink;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.util.ResourceBundle;

public class Gui_About {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private JDialog frmAboutDiscordEms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					Gui_About window = new Gui_About();
					window.frmAboutDiscordEms.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_About() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
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
		
		frmAboutDiscordEms = new JDialog();
		frmAboutDiscordEms.setModalityType(ModalityType.APPLICATION_MODAL);
		frmAboutDiscordEms.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmAboutDiscordEms.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_About.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmAboutDiscordEms.setTitle(BUNDLE.getString("Gui_About.frmAboutDiscordEms.title")); //$NON-NLS-1$
		frmAboutDiscordEms.setResizable(false);
		frmAboutDiscordEms.setBounds(100, 100, 430, 320);
		frmAboutDiscordEms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAboutDiscordEms.getContentPane().setLayout(null);
		frmAboutDiscordEms.getContentPane().setBackground(Sys_Util.discorddark);
		frmAboutDiscordEms.setLocationRelativeTo(null);
		
		JLabel appTitle = new JLabel(BUNDLE.getString("Gui_About.appTitle.text")); //$NON-NLS-1$
		appTitle.setVerticalAlignment(SwingConstants.TOP);
		appTitle.setHorizontalAlignment(SwingConstants.CENTER);
		appTitle.setForeground(Color.WHITE);
		appTitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		appTitle.setBounds(10, 11, 404, 32);
		frmAboutDiscordEms.getContentPane().add(appTitle);
		
		JLabel lblApplogo = new JLabel();
		lblApplogo.setIcon(new ImageIcon(Gui_About.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		lblApplogo.setBounds(381, 248, 32, 32);
		frmAboutDiscordEms.getContentPane().add(lblApplogo);
		
		JLabel lblPoweredByJda = new JLabel(BUNDLE.getString("Gui_About.lblPoweredByJda.text")); //$NON-NLS-1$
		lblPoweredByJda.setForeground(Color.WHITE);
		lblPoweredByJda.setBounds(10, 96, 171, 14);
		frmAboutDiscordEms.getContentPane().add(lblPoweredByJda);
		
		Sys_SwingLink lblJdajavaDiscord = new Sys_SwingLink("JDA (Java Discord API) - 3.8.1_454","https://github.com/DV8FromTheWorld/JDA");
		lblJdajavaDiscord.setForeground(Color.WHITE);
		lblJdajavaDiscord.setBounds(10, 121, 203, 22);
		frmAboutDiscordEms.getContentPane().add(lblJdajavaDiscord);
		
		Sys_SwingLink lblNewLabel = new Sys_SwingLink("log4j over slf4j - 1.7.25","https://logging.apache.org/log4j/2.x/");
		lblNewLabel.setBounds(10, 146, 203, 20);
		frmAboutDiscordEms.getContentPane().add(lblNewLabel);
		
		Sys_SwingLink lblSlfj = new Sys_SwingLink("slf4j - 1.7.25","https://www.slf4j.org/");
		lblSlfj.setBounds(10, 169, 203, 22);
		frmAboutDiscordEms.getContentPane().add(lblSlfj);
		
		JLabel lblJuly = new JLabel(Sys_Start.version+" - 2019");
		lblJuly.setHorizontalAlignment(SwingConstants.CENTER);
		lblJuly.setForeground(Color.WHITE);
		lblJuly.setBounds(239, 266, 132, 14);
		frmAboutDiscordEms.getContentPane().add(lblJuly);
		
		JLabel lblNewLabel_1 = new JLabel("Developed by Dreregon");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 46, 404, 22);
		frmAboutDiscordEms.getContentPane().add(lblNewLabel_1);
		
		Sys_SwingLink sys_SwingLink = new Sys_SwingLink("JNA - 5.3.1", "https://github.com/java-native-access/jna");
		sys_SwingLink.setForeground(Color.WHITE);
		sys_SwingLink.setBounds(10, 193, 203, 22);
		frmAboutDiscordEms.getContentPane().add(sys_SwingLink);
		
		Sys_SwingLink sys_SwingLink_1 = new Sys_SwingLink("JNA Platform - 5.3.1", "https://github.com/java-native-access/jna");
		sys_SwingLink_1.setForeground(Color.WHITE);
		sys_SwingLink_1.setBounds(10, 217, 203, 22);
		frmAboutDiscordEms.getContentPane().add(sys_SwingLink_1);
		
		Sys_SwingLink sys_SwingLink_2 = new Sys_SwingLink("imgscalr - 4.2", "https://github.com/rkalla/imgscalr");
		sys_SwingLink_2.setForeground(Color.WHITE);
		sys_SwingLink_2.setBounds(10, 241, 203, 22);
		frmAboutDiscordEms.getContentPane().add(sys_SwingLink_2);
	}
}

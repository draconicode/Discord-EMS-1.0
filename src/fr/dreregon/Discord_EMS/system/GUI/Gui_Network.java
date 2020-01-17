package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_DynamicsSetter;
import fr.dreregon.Discord_EMS.system.Sys_Splash;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class Gui_Network {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private JFrame frmNetworkError;
	public static boolean offline = false;

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
					Gui_Network window = new Gui_Network();
					window.frmNetworkError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_Network() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNetworkError = new JFrame();
		frmNetworkError.setResizable(false);
		frmNetworkError.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Network.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmNetworkError.setTitle(BUNDLE.getString("Gui_Network.frmNetworkError.title")); //$NON-NLS-1$
		frmNetworkError.setBounds(100, 100, 615, 264);
		frmNetworkError.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNetworkError.getContentPane().setBackground(Sys_Util.discorddark);
		frmNetworkError.getContentPane().setLayout(null);
		frmNetworkError.setLocationRelativeTo(null);
		
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
		
		Sys_Splash.remSplash();
		
		JLabel lblNewLabel = new JLabel(BUNDLE.getString("Gui_Network.lblNewLabel.text")); //$NON-NLS-1$
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 589, 38);
		frmNetworkError.getContentPane().add(lblNewLabel);
		
		JLabel lblItSeemsThat = new JLabel(BUNDLE.getString("Gui_Network.lblItSeemsThat.text")); //$NON-NLS-1$
		lblItSeemsThat.setForeground(Color.WHITE);
		lblItSeemsThat.setHorizontalAlignment(SwingConstants.CENTER);
		lblItSeemsThat.setBounds(10, 60, 589, 14);
		frmNetworkError.getContentPane().add(lblItSeemsThat);
		
		JLabel lblButDontWorry = new JLabel(BUNDLE.getString("Gui_Network.lblButDontWorry.text")); //$NON-NLS-1$
		lblButDontWorry.setForeground(Color.WHITE);
		lblButDontWorry.setHorizontalAlignment(SwingConstants.CENTER);
		lblButDontWorry.setBounds(10, 85, 589, 14);
		frmNetworkError.getContentPane().add(lblButDontWorry);
		
		JLabel lblOfCourseYou = new JLabel(BUNDLE.getString("Gui_Network.lblOfCourseYou.text")); //$NON-NLS-1$
		lblOfCourseYou.setForeground(Color.WHITE);
		lblOfCourseYou.setVerticalAlignment(SwingConstants.TOP);
		lblOfCourseYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblOfCourseYou.setBounds(10, 98, 589, 14);
		frmNetworkError.getContentPane().add(lblOfCourseYou);
		
		JLabel lblSaveYourEmbedmessage = new JLabel(BUNDLE.getString("Gui_Network.lblSaveYourEmbedmessage.text")); //$NON-NLS-1$
		lblSaveYourEmbedmessage.setForeground(Color.WHITE);
		lblSaveYourEmbedmessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaveYourEmbedmessage.setBounds(10, 110, 589, 14);
		frmNetworkError.getContentPane().add(lblSaveYourEmbedmessage);
		
		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_Network.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gui_Main.main(true);
				Sys_DynamicsSetter.setOfflineLabel("Offline mode", Gui_Main.connectedBotName);
				offline = true;
				JOptionPane.showMessageDialog(null, "You are now in offline mode !", "Info", JOptionPane.INFORMATION_MESSAGE);
				frmNetworkError.dispose();
			}
		});
		btnNewButton.setBounds(454, 176, 145, 23);
		frmNetworkError.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel(); //$NON-NLS-1$
		lblNewLabel_1.setIcon(new ImageIcon(Gui_Network.class.getResource("/fr/dreregon/Discord_EMS/system/media/netError.png")));
		lblNewLabel_1.setBounds(10, 8, 208, 219);
		frmNetworkError.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton(BUNDLE.getString("Gui_Network.btnNewButton_1.text")); //$NON-NLS-1$
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(454, 204, 145, 23);
		frmNetworkError.getContentPane().add(btnNewButton_1);
	}
}

package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.dreregon.Discord_EMS.system.Sys_Splash;
import fr.dreregon.Discord_EMS.system.Sys_SwingLink;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Window.Type;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class Gui_TokenError {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private JFrame frmError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui_TokenError window = new Gui_TokenError();
					window.frmError.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_TokenError() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Sys_Splash.remSplash();
		
		frmError = new JFrame();
		frmError.setAlwaysOnTop(true);
		frmError.setType(Type.POPUP);
		frmError.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_TokenError.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmError.setTitle(BUNDLE.getString("Gui_TokenError.frmError.title")); //$NON-NLS-1$
		frmError.setResizable(false);
		frmError.setBounds(100, 100, 450, 170);
		frmError.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmError.getContentPane().setBackground(Sys_Util.discorddark);
		frmError.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel(BUNDLE.getString("Gui_TokenError.lblNewLabel.text")); //$NON-NLS-1$
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Gui_TokenError.class.getResource("/fr/dreregon/Discord_EMS/system/media/error_32x.png")));
		frmError.getContentPane().setLayout(new MigLayout("", "[444px]", "[32px][][][23px][]"));
		
		JLabel lblErrorThe = new JLabel(BUNDLE.getString("Gui_TokenError.lblErrorThe.text")); //$NON-NLS-1$
		lblErrorThe.setForeground(Color.WHITE);
		lblErrorThe.setHorizontalAlignment(SwingConstants.CENTER);
		frmError.getContentPane().add(lblErrorThe, "cell 0 1,alignx center");
		
		JLabel lblNewLabel_1 = new JLabel(BUNDLE.getString("Gui_TokenError.lblNewLabel_1.text")); //$NON-NLS-1$
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frmError.getContentPane().add(lblNewLabel_1, "cell 0 2,alignx center");
		
		JPanel panel = new JPanel();
		frmError.getContentPane().add(panel, "flowx,cell 0 3,alignx center");
		panel.setBackground(Sys_Util.discorddark);
		
		Sys_SwingLink link = new Sys_SwingLink("https://discordapp.com/developers/applications/me", "https://discordapp.com/developers/applications/me");
		link.setText(BUNDLE.getString("Gui_TokenError.link.text")); //$NON-NLS-1$
		link.setToolTipText(BUNDLE.getString("Gui_TokenError.link.toolTipText")); //$NON-NLS-1$
		panel.add(link);
		link.setForeground(Color.GREEN);
		link.setHorizontalAlignment(SwingConstants.CENTER);
		frmError.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny top");
		
		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_TokenError.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmError.dispose();
				//System.exit(1);
			}
		});
		frmError.getContentPane().add(btnNewButton, "cell 0 4,alignx center");
	}
}

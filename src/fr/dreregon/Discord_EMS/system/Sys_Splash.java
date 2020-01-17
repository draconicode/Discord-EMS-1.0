package fr.dreregon.Discord_EMS.system;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.SwingConstants;

import fr.dreregon.Discord_EMS.system.GUI.Gui_Main;

public class Sys_Splash extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Sys_Splash frame = new Sys_Splash();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setBackground(new Color(0,0,0,0));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sys_Splash() {
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setType(Type.UTILITY);
		setModal(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 854, 500);
		contentPane = new JPanel();
		setUndecorated(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Sys_Splash.class.getResource("/fr/dreregon/Discord_EMS/system/media/loading.gif")));
		lblNewLabel.setBounds(457, 287, 72, 71);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("");
		label.setBounds(295, 128, 255, 255);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(Sys_Splash.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_256.png")));
		label.setOpaque(false);
		contentPane.add(label);
	}

	public static void remSplash() {
		if(frame != null) {
			if(frame.isVisible()) {
				frame.dispose();
				if(Gui_Main.frmDES != null) {
					Gui_Main.frmDES.requestFocus();
				}	
			}
		}
		
		
	}
}

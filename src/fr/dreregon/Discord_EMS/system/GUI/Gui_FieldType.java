package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Gui_FieldType {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmSelectFieldType;
	public static boolean opened = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					Gui_FieldType window = new Gui_FieldType();
					window.frmSelectFieldType.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("FieldType");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_FieldType() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelectFieldType = new JFrame();
		frmSelectFieldType.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(!Sys_Util.openedWindows.isEmpty()) {
				int index = Sys_Util.openedWindows.indexOf("FieldType");
				Sys_Util.openedWindows.remove(index);
				}
			}
		});
		frmSelectFieldType.getContentPane().setBackground(Sys_Util.discorddark);
		frmSelectFieldType.setTitle(BUNDLE.getString("Gui_FieldType.frmSelectFieldType.title")); //$NON-NLS-1$
		frmSelectFieldType.setResizable(false);
		frmSelectFieldType.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_FieldType.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmSelectFieldType.setBounds(100, 100, 300, 125);
		frmSelectFieldType.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSelectFieldType.getContentPane().setLayout(null);
		frmSelectFieldType.setLocationRelativeTo(null);
		
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
		
		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_FieldType.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.setBounds(10, 11, 136, 74);
		frmSelectFieldType.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(BUNDLE.getString("Gui_FieldType.btnNewButton_1.text")); //$NON-NLS-1$
		btnNewButton_1.setBounds(148, 11, 136, 74);
		frmSelectFieldType.getContentPane().add(btnNewButton_1);
		
		//EVENTHANDLERS
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gui_FieldEdit.opened == true) {
					return;
				}else {
					Gui_FieldEdit.main("","",false, false);
					frmSelectFieldType.dispose();
				}
				
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gui_BlankFieldEdit.opened == true) {
					return;
				}else {
					Gui_BlankFieldEdit.main(false, 0);
					frmSelectFieldType.dispose();
				}
				
			}
		});
	}

}

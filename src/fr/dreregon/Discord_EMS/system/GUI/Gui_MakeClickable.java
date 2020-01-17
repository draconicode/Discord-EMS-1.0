package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.JTextComponent;

import fr.dreregon.Discord_EMS.system.Sys_DynamicsSetter;
import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Gui_MakeClickable {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmMakeClickable;
	private JTextField textField;
	private static String name;
	public static String finalString;
	private static JTextComponent jej;
	public static boolean opened = false;

	/**
	 * Launch the application.
	 * @param descArea 
	 */
	public static void main(String replacement, JTextComponent j) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					Gui_MakeClickable window = new Gui_MakeClickable();
					window.frmMakeClickable.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("MakeClickable");
					name = replacement;
					jej = j;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_MakeClickable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMakeClickable = new JFrame();
		frmMakeClickable.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(!Sys_Util.openedWindows.isEmpty()) {
				int index = Sys_Util.openedWindows.indexOf("MakeClickable");
				Sys_Util.openedWindows.remove(index);
				}
			}
		});
		frmMakeClickable.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_MakeClickable.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmMakeClickable.setTitle(BUNDLE.getString("Gui_MakeClickable.frmMakeClickable.title")); //$NON-NLS-1$
		frmMakeClickable.setResizable(false);
		frmMakeClickable.setBounds(100, 100, 500, 101);
		frmMakeClickable.setLocationRelativeTo(null);
		frmMakeClickable.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		frmMakeClickable.getContentPane().setBackground(Sys_Util.discorddark);
		frmMakeClickable.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setBounds(10, 36, 407, 25);
		frmMakeClickable.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setBackground(Sys_Util.notblack);
		textField.setCaretColor(Color.WHITE);

		JButton btnNewButton = new JButton(BUNDLE.getString("Gui_MakeClickable.btnNewButton.text")); //$NON-NLS-1$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String link = textField.getText();
				if(!link.startsWith("http")) {
					if(Sys_Start.fr) {
						Sys_Message.main("Ce lien est invalide, il doit commencer avec <http(s)>", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("This link is invalid. It must start with <http(s)>", Sys_MsgType.ERROR);
					}
					
					return;
				}
				finalString = "["+name+"]("+link+")";
				Sys_DynamicsSetter.replaceSelection(jej, finalString);
				frmMakeClickable.dispose();
			}
		});
		btnNewButton.setBounds(427, 36, 57, 25);
		frmMakeClickable.getContentPane().add(btnNewButton);

		JLabel lblEnterTheLink = new JLabel(BUNDLE.getString("Gui_MakeClickable.lblEnterTheLink.text")); //$NON-NLS-1$
		lblEnterTheLink.setForeground(Color.WHITE);
		lblEnterTheLink.setBounds(10, 11, 172, 14);
		frmMakeClickable.getContentPane().add(lblEnterTheLink);

		
	}
}

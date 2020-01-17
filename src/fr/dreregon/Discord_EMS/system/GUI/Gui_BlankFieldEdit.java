package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_BlankField;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Gui_BlankFieldEdit {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmAddBlankField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static int num1 = 0;
	public static boolean opened = false;

	/**
	 * Launch the application.
	 */
	public static void main(boolean inline, int status) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Gui_BlankFieldEdit window = new Gui_BlankFieldEdit(inline, status);
					window.frmAddBlankField.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("BlankField");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_BlankFieldEdit(boolean inline, int status) {
		initialize(inline, status);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean inline, int status) {
		frmAddBlankField = new JFrame();
		frmAddBlankField.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(!Sys_Util.openedWindows.isEmpty()) {
				int index = Sys_Util.openedWindows.indexOf("BlankField");
				Sys_Util.openedWindows.remove(index);
				}
			}
		});
		
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
		
		frmAddBlankField.setResizable(false);
		frmAddBlankField.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmAddBlankField.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_BlankFieldEdit.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmAddBlankField.setTitle(BUNDLE.getString("Gui_BlankFieldEdit.frmAddBlankField.title")); //$NON-NLS-1$
		frmAddBlankField.setBounds(100, 100, 270, 145);
		frmAddBlankField.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddBlankField.getContentPane().setLayout(null);
		frmAddBlankField.getContentPane().setBackground(Sys_Util.discorddark);
		frmAddBlankField.setLocationRelativeTo(null);
		
		JButton btnAddBlankField = new JButton(BUNDLE.getString("Gui_BlankFieldEdit.btnAddBlankField.text")); //$NON-NLS-1$
		btnAddBlankField.setBounds(10, 84, 244, 23);
		frmAddBlankField.getContentPane().add(btnAddBlankField);
		
		JRadioButton rdbtnTrue = new JRadioButton(BUNDLE.getString("Gui_BlankFieldEdit.rdbtnTrue.text")); //$NON-NLS-1$
		rdbtnTrue.setForeground(Color.WHITE);
		
		buttonGroup.add(rdbtnTrue);
		rdbtnTrue.setBounds(6, 28, 109, 23);
		rdbtnTrue.setBackground(Sys_Util.discorddark);
		frmAddBlankField.getContentPane().add(rdbtnTrue);
		
		JLabel lblInline = new JLabel(BUNDLE.getString("Gui_BlankFieldEdit.lblInline.text")); //$NON-NLS-1$
		lblInline.setForeground(Color.WHITE);
		lblInline.setBounds(10, 11, 244, 14);
		frmAddBlankField.getContentPane().add(lblInline);
		
		JRadioButton rdbtnFalse = new JRadioButton(BUNDLE.getString("Gui_BlankFieldEdit.rdbtnFalse.text")); //$NON-NLS-1$
		rdbtnFalse.setForeground(Color.WHITE);
		
		buttonGroup.add(rdbtnFalse);
		rdbtnFalse.setBounds(6, 54, 109, 23);
		rdbtnFalse.setBackground(Sys_Util.discorddark);
		frmAddBlankField.getContentPane().add(rdbtnFalse);
		
		if(inline) {
			rdbtnTrue.setSelected(true);
		}else {
			rdbtnFalse.setSelected(true);
		}
		
		
		
		btnAddBlankField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sys_BlankField bField = new Sys_BlankField(rdbtnTrue.isSelected(),0);
				String bName = "";
				if(status == 0) {
					bName = generateFieldFileName();
				}else if(status == 1) {
					bName = Gui_Main.comboField.getSelectedItem().toString();
				}
				
				Gui_Main.blankFieldList.add(bField);
				//Sys_Json.createBFieldJson("Discord-EMS/Fields/"+bName+".json", bField);
				if(!Gui_Main.comboField.getSelectedItem().equals(bName)) {
					Gui_Main.comboField.addItem(bName);
				}
				frmAddBlankField.dispose();
			}
		});
	}
	
	private String generateFieldFileName() {
		String name = null;
		num1 = num1+1;
		name="BlankField_"+num1;
		
		return name;
	}
	
}

package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class Gui_FieldEdit {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	public static JFrame frmFieldEdit;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public static int num = 0;
	private static String title_E = "";
	private static String desc_E = "";
	private static boolean inline_E = false;
	private static boolean edit_E = false;
	public static boolean opened = false;
	
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String title, String desc, boolean inline, boolean edit) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				title_E=title;
				desc_E=desc;
				inline_E=inline;
				edit_E = edit;
				try {
					Gui_FieldEdit window = new Gui_FieldEdit();
					window.frmFieldEdit.setVisible(true);
					opened = true;
					Sys_Util.openedWindows.add("FieldEdit");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_FieldEdit() {
		initialize(title_E, desc_E, inline_E, edit_E);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param blank 
	 * @param public static String 
	 */
	private void initialize(String title, String desc, boolean inline, boolean edit) {
		frmFieldEdit = new JFrame();
		frmFieldEdit.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				opened = false;
				if(!Sys_Util.openedWindows.isEmpty()) {
				int index = Sys_Util.openedWindows.indexOf("FieldEdit");
				Sys_Util.openedWindows.remove(index);
				}
			}
		});
		frmFieldEdit.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_FieldEdit.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16.png")));
		frmFieldEdit.getContentPane().setBackground(Sys_Util.discorddark);
		frmFieldEdit.setResizable(false);
		frmFieldEdit.setTitle(BUNDLE.getString("Gui_FieldEdit.frmFieldEdit.title")); //$NON-NLS-1$
		frmFieldEdit.setBounds(100, 100, 405, 330);
		frmFieldEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFieldEdit.setLocationRelativeTo(null);
		
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
		
		JLabel lblTitle = new JLabel(BUNDLE.getString("Gui_FieldEdit.lblTitle.text")); //$NON-NLS-1$
		lblTitle.setBounds(10, 11, 60, 14);
		lblTitle.setForeground(Color.WHITE);
		
		textField = new JTextField();
		textField.setBounds(10, 31, 300, 20);
		textField.setForeground(Color.WHITE);
		textField.setBackground(Sys_Util.notblack);
		textField.setCaretColor(Color.white);
		textField.setSelectionColor(Sys_Util.blurple);
		textField.setColumns(10);
		textField.setText(title_E);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 87, 300, 203);
		
		JRadioButton rdbtnFalse = new JRadioButton(BUNDLE.getString("Gui_FieldEdit.rdbtnFalse.text")); //$NON-NLS-1$
		rdbtnFalse.setBounds(320, 30, 61, 22);
		rdbtnFalse.setBackground(Sys_Util.discorddark);
		rdbtnFalse.setForeground(Color.white);
		buttonGroup.add(rdbtnFalse);
		
		JRadioButton rdbtnTrue = new JRadioButton(BUNDLE.getString("Gui_FieldEdit.rdbtnTrue.text")); //$NON-NLS-1$
		rdbtnTrue.setBounds(320, 53, 73, 23);
		buttonGroup.add(rdbtnTrue);
		rdbtnTrue.setBackground(Sys_Util.discorddark);
		rdbtnTrue.setForeground(Color.white);
		
		if(inline_E) {
			rdbtnTrue.setSelected(true);
		}else {
			rdbtnFalse.setSelected(true);
		}
		
		JLabel lblDescription = new JLabel(BUNDLE.getString("Gui_FieldEdit.lblDescription.text")); //$NON-NLS-1$
		lblDescription.setBounds(10, 62, 60, 14);
		lblDescription.setForeground(Color.WHITE);
		
		JLabel lblAlignment = new JLabel(BUNDLE.getString("Gui_FieldEdit.lblAlignment.text")); //$NON-NLS-1$
		lblAlignment.setBounds(320, 11, 73, 14);
		lblAlignment.setForeground(Color.WHITE);
		
		JButton btnDone = new JButton(BUNDLE.getString("Gui_FieldEdit.btnDone.text")); //$NON-NLS-1$
		btnDone.setBounds(320, 265, 70, 25);
		frmFieldEdit.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBackground(Sys_Util.notblack);
		textArea.setForeground(Color.white);
		textArea.setCaretColor(Color.white);
		textArea.setSelectionColor(Sys_Util.blurple);
		scrollPane.setViewportView(textArea);
		textArea.setText(desc_E);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(textArea, popupMenu);
		
		JMenuItem mntmMakeClickable = new JMenuItem(BUNDLE.getString("Gui_FieldEdit.mntmMakeClickable.text")); //$NON-NLS-1$
		mntmMakeClickable.setIcon(new ImageIcon(Gui_FieldEdit.class.getResource("/fr/dreregon/Discord_EMS/system/media/web2.png")));
		
		popupMenu.add(mntmMakeClickable);
		frmFieldEdit.getContentPane().add(scrollPane);
		frmFieldEdit.getContentPane().add(lblTitle);
		frmFieldEdit.getContentPane().add(lblDescription);
		frmFieldEdit.getContentPane().add(textField);
		frmFieldEdit.getContentPane().add(rdbtnFalse);
		frmFieldEdit.getContentPane().add(lblAlignment);
		frmFieldEdit.getContentPane().add(rdbtnTrue);
		frmFieldEdit.getContentPane().add(btnDone);
		
		mntmMakeClickable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gui_MakeClickable.opened) {
					return;
				}
				String txt = textArea.getSelectedText();
				if(txt==null) {
					Sys_Message.main("You must select something.", Sys_MsgType.ERROR);
					return;
				}
				Gui_MakeClickable.main(txt, textArea);
			}
		});
		
		JButton btnCancel = new JButton(BUNDLE.getString("Gui_FieldEdit.btnCancel.text")); //$NON-NLS-1$
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmFieldEdit.dispose();
			}
		});
		btnCancel.setBounds(320, 229, 70, 25);
		frmFieldEdit.getContentPane().add(btnCancel);
		
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText();
				String desc = textArea.getText();
				boolean inline = rdbtnTrue.isSelected();
				if(title.equals("") && desc.equals("")) {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous devez au moins avoir rempli le titre et la description "
								+ "pour ajouter un field !\nSi vous voulez un champ vide, utilisez la fenêtre \"Champ vide\".", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You must have filled the title and the description "
								+ "to add a field !\nIf you want a blank field, use \"Blankfield\" window instead.", Sys_MsgType.ERROR);
					}
					
					return;
				}
				Field field = new Field(title, desc, inline);
				
				Gui_Main.fieldList.add(field);
				String names = generateFieldFileName();
				//Sys_Json.createFieldJson("Discord-EMS/Fields/"+names+".json", field);
				if(!Gui_Main.comboField.getSelectedItem().equals(names)) {
					Gui_Main.comboField.addItem(names);
				}
				
				textField.setText("");
				textArea.setText("");
				
				frmFieldEdit.dispose();
				
			}
		});
		
		
	}
	
	private String generateFieldFileName() {
		String name = null;
		if(edit_E) {
			
		}else {
			num = num+1;
		}
		
		name="Field_"+num;
		
		return name;
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

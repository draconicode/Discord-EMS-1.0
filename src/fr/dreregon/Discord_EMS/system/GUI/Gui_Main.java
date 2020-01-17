package fr.dreregon.Discord_EMS.system.GUI;

import static fr.dreregon.Discord_EMS.system.Sys_Util.*;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.dv8tion.jda.core.entities.MessageEmbed.Field;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;

import fr.dreregon.Discord_EMS.system.*;
import fr.dreregon.Discord_EMS.system.libs.Scalr;
import fr.dreregon.Discord_EMS.system.libs.Scalr.Mode;

import javax.swing.event.ChangeEvent;
import javax.swing.border.LineBorder;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Dimension;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Gui_Main {

	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$
	private static boolean edit = false;
	private static boolean playLoadAnim = false;
	private static boolean playLoadAnim2 = false;

	private JPanel descPannel;
	private JScrollPane scrollPane;
	private JLabel lblDescription;
	private JLabel lblFields;
	private JPanel fieldPanel;

	public static JFrame frmDES;
	public static JLabel connectedBotName = new JLabel(BUNDLE.getString("Gui_Main.connectedBotName.text")); //$NON-NLS-1$
	public static JLabel botLabel;
	public static JTextField titleF;
	public static JTextField imgField;
	public static JTextField thmnailField;
	public static JTextField a_nameField;
	public static JTextField a_URLField;
	public static JTextField f_nameField;
	public static JTextField f_URLField;
	public static JCheckBox chckbxAuthor;
	public static JCheckBox chckbxFooter;
	public static JCheckBox timeStampCheckbx;
	public static ArrayList<Field> fieldList = new ArrayList<>();
	public static ArrayList<Sys_BlankField> blankFieldList = new ArrayList<>();
	public static JTextArea descArea;
	public static JComboBox<String> comboField = null;
	public static JTextPane colorPick = new JTextPane();
	public static Sys_Embed builder2;
	public static JLabel thumbnailPrevLbl = new JLabel("");
	public static JLabel imagePrevLbl = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(boolean editable) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					edit = editable;
					Gui_Main window = new Gui_Main();
					window.frmDES.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_Main() {
		initialize(edit);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean editable) {

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


		frmDES = new JFrame();
		frmDES.setPreferredSize(new Dimension(1210, 670));
		frmDES.setMaximumSize(new Dimension(1210, 2147483647));
		frmDES.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmDES.getContentPane().setBackground(discorddark);
		frmDES.setTitle(BUNDLE.getString("Gui_Main.frmDES.title")); //$NON-NLS-1$
		frmDES.setBounds(100, 100, 1220, 695);
		frmDES.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDES.setLocationRelativeTo(null);

		JLabel lblTitle = new JLabel(BUNDLE.getString("Gui_Main.lblTitle.text"));
		lblTitle.setForeground(Color.WHITE);

		JPanel titlePannel = new JPanel();
		titlePannel.setMinimumSize(new Dimension(400, 25));
		titlePannel.setMaximumSize(new Dimension(400, 25));

		lblDescription = new JLabel(BUNDLE.getString("Gui_Main.lblDescription.text"));
		lblDescription.setForeground(Color.WHITE);

		descPannel = new JPanel();
		descPannel.setBorder(null);
		descPannel.setMinimumSize(new Dimension(400, 2147483647));
		descPannel.setMaximumSize(new Dimension(400, 342));

		JButton btnSend = new JButton(BUNDLE.getString("Gui_Main.btnSend.text"));
		btnSend.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Save.png")));

		lblFields = new JLabel(BUNDLE.getString("Gui_Main.lblFields.text")); //$NON-NLS-1$
		lblFields.setForeground(Color.WHITE);

		fieldPanel = new JPanel();
		fieldPanel.setBackground(discorddark);

		scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(400, 342));
		scrollPane.setMaximumSize(new Dimension(400, 2000));

		JPopupMenu descPopup = new JPopupMenu();
		

		JMenuItem mntmMakeClickable = new JMenuItem(BUNDLE.getString("Gui_Main.mntmMakeClickable.text")); //$NON-NLS-1$
		mntmMakeClickable.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/web2.png")));
		descPopup.add(mntmMakeClickable);

		JMenuItem mntmPaste_3 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_3.text")); //$NON-NLS-1$
		mntmPaste_3.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		descPopup.add(mntmPaste_3);
		titlePannel.setLayout(null);

		JPopupMenu titlePopup = new JPopupMenu();

		JMenuItem mntmPaste_2 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_2.text")); //$NON-NLS-1$
		mntmPaste_2.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		titlePopup.add(mntmPaste_2);
		fieldPanel.setLayout(null);

		JButton addFieldBtn = new JButton(BUNDLE.getString("Gui_Main.addFieldBtn.text")); //$NON-NLS-1$
		addFieldBtn.setBounds(0, 0, 259, 21);
		fieldPanel.add(addFieldBtn);

		JButton btnEdit = new JButton(BUNDLE.getString("Gui_Main.btnEdit.text")); //$NON-NLS-1$
		btnEdit.setBounds(0, 41, 130, 35);
		fieldPanel.add(btnEdit);

		JButton btnDelete = new JButton(BUNDLE.getString("Gui_Main.btnDelete.text")); //$NON-NLS-1$
		btnDelete.setBounds(130, 41, 129, 35);
		fieldPanel.add(btnDelete);

		JPopupMenu imgPopup = new JPopupMenu();

		JMenuItem mntmPaste = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste.text")); //$NON-NLS-1$
		mntmPaste.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		imgPopup.add(mntmPaste);

		JLabel lblNewLabel_1 = new JLabel(BUNDLE.getString("Gui_Main.lblNewLabel_1.text")); //$NON-NLS-1$
		lblNewLabel_1.setForeground(Color.WHITE);

		JPopupMenu thmPopup = new JPopupMenu();

		JMenuItem mntmPaste_1 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_1.text")); //$NON-NLS-1$
		mntmPaste_1.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		thmPopup.add(mntmPaste_1);
		
		JButton btnColor = new JButton(BUNDLE.getString("Gui_Main.btnColor.text"));

		JButton btnReset = new JButton(BUNDLE.getString("Gui_Main.btnReset.text"));
		btnReset.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16.png")));

		JPopupMenu a_namePopup = new JPopupMenu();
		
		JMenuItem mntmPaste_4 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_4.text")); //$NON-NLS-1$
		mntmPaste_4.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		a_namePopup.add(mntmPaste_4);

		JLabel lblAuthorName = new JLabel(BUNDLE.getString("Gui_Main.lblAuthorName.text")); //$NON-NLS-1$
		lblAuthorName.setForeground(Color.WHITE);

		JPopupMenu a_imgPopup = new JPopupMenu();

		JMenuItem mntmPaste_5 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_5.text")); //$NON-NLS-1$
		mntmPaste_5.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		a_imgPopup.add(mntmPaste_5);

		JLabel lblAvatarUrloptional = new JLabel(BUNDLE.getString("Gui_Main.lblAvatarUrloptional.text")); //$NON-NLS-1$
		lblAvatarUrloptional.setForeground(Color.WHITE);

		JPopupMenu f_namePopup = new JPopupMenu();

		JMenuItem mntmPaste_6 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_6.text")); //$NON-NLS-1$
		mntmPaste_6.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		f_namePopup.add(mntmPaste_6);

		JPopupMenu f_imgPopup = new JPopupMenu();

		JMenuItem mntmPaste_7 = new JMenuItem(BUNDLE.getString("Gui_Main.mntmPaste_7.text")); //$NON-NLS-1$
		mntmPaste_7.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
		f_imgPopup.add(mntmPaste_7);

		JLabel lblFooterTitle = new JLabel(BUNDLE.getString("Gui_Main.lblFooterTitle.text")); //$NON-NLS-1$
		lblFooterTitle.setForeground(Color.WHITE);

		JLabel lblIconUrloptional = new JLabel(BUNDLE.getString("Gui_Main.lblIconUrloptional.text")); //$NON-NLS-1$
		lblIconUrloptional.setForeground(Color.WHITE);

		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(notblack);
		statusPanel.setBorder(new LineBorder(new Color(255, 255, 255)));

		JLabel label = new JLabel("Image URL (optional) :");
		label.setForeground(Color.WHITE);

		JPanel separator_1 = new JPanel();
		separator_1.setBackground(notblack);

		JPanel panel = new JPanel();
		panel.setBackground(notblack);

		JPanel separator_3 = new JPanel();
		separator_3.setBackground(notblack);

		JPanel thmPrevPanel = new JPanel();
		thmPrevPanel.setBackground(new Color(30, 33, 36));

		JPanel imfPrevPanel = new JPanel();
		imfPrevPanel.setBackground(new Color(30, 33, 36));
		
		Sys_MenuBar menuBar = new Sys_MenuBar();
		menuBar.setBorder(new LineBorder(notblack));
		menuBar.setBackground(Color.GRAY);
		menuBar.setColor(notblack);
		frmDES.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu(BUNDLE.getString("Gui_Main.mnFile.text")); //$NON-NLS-1$
		mnFile.setForeground(Color.WHITE);
		menuBar.add(mnFile);

		JMenuItem mntmSaveEmbed = new JMenuItem(BUNDLE.getString("Gui_Main.mntmSaveEmbed.text")); //$NON-NLS-1$
		mntmSaveEmbed.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Save.png")));
		mnFile.add(mntmSaveEmbed);

		JMenuItem mntmLoadEmbed = new JMenuItem(BUNDLE.getString("Gui_Main.mntmLoadEmbed.text")); //$NON-NLS-1$
		mntmLoadEmbed.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Load.png")));
		mnFile.add(mntmLoadEmbed);

		JMenuItem mntmOpenConfigFolder = new JMenuItem(BUNDLE.getString("Gui_Main.mntmOpenConfigFolder.text")); //$NON-NLS-1$
		mntmOpenConfigFolder.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16_Folder.png")));
		mnFile.add(mntmOpenConfigFolder);

		JMenuItem mntmChangeToken = new JMenuItem(BUNDLE.getString("Gui_Main.mntmChangeToken.text")); //$NON-NLS-1$
		mntmChangeToken.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16_bot.png")));
		mnFile.add(mntmChangeToken);

		JMenuItem mntmExit = new JMenuItem(BUNDLE.getString("Gui_Main.mntmExit.text")); //$NON-NLS-1$
		mntmExit.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Exit.png")));
		mnFile.add(mntmExit);

		JMenu mnOptions = new JMenu(BUNDLE.getString("Gui_Main.mnOptions.text")); //$NON-NLS-1$
		mnOptions.setForeground(Color.WHITE);
		menuBar.add(mnOptions);

		JMenu mnLangages = new JMenu(BUNDLE.getString("Gui_Main.mnLangages.text"));
		mnLangages.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Language.png")));
		mnOptions.add(mnLangages);

		JCheckBoxMenuItem chckbxmntmFranais = new JCheckBoxMenuItem(BUNDLE.getString("Gui_Main.chckbxmntmFranais.text")); //$NON-NLS-1$
		chckbxmntmFranais.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/fr32x.png")));
		chckbxmntmFranais.setSelected(Sys_Start.fr);
		mnLangages.add(chckbxmntmFranais);

		JCheckBoxMenuItem chckbxmntmAnglais = new JCheckBoxMenuItem(BUNDLE.getString("Gui_Main.chckbxmntmAnglais.text")); //$NON-NLS-1$
		chckbxmntmAnglais.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/en32x.png")));
		chckbxmntmAnglais.setSelected(Sys_Start.en);
		mnLangages.add(chckbxmntmAnglais);

		JMenuItem mntmConsole = new JMenuItem(BUNDLE.getString("Gui_Main.mntmConsole.text")); //$NON-NLS-1$
		mntmConsole.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Console.png")));
		mnOptions.add(mntmConsole);

		JMenuItem mntmCheckForUpdates = new JMenuItem(BUNDLE.getString("Gui_Main.mntmCheckForUpdates.text")); //$NON-NLS-1$
		mntmCheckForUpdates.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16_Update.png")));
		mnOptions.add(mntmCheckForUpdates);

		JMenu mnHelp = new JMenu(BUNDLE.getString("Gui_Main.mnHelp.text")); //$NON-NLS-1$
		mnHelp.setForeground(Color.WHITE);
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem(BUNDLE.getString("Gui_Main.mntmAbout.text")); //$NON-NLS-1$
		mntmAbout.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16.png")));
		mnHelp.add(mntmAbout);

		JMenuItem mntmEmbedanatomy = new JMenuItem(BUNDLE.getString("Gui_Main.mntmEmbedanatomy.text")); //$NON-NLS-1$
		mntmEmbedanatomy.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_16Help.png")));
		mnHelp.add(mntmEmbedanatomy);
		
		thmPrevPanel.setLayout(null);
		thmPrevPanel.add(thumbnailPrevLbl);
		
		imfPrevPanel.setLayout(null);
		imfPrevPanel.add(imagePrevLbl);
		
		imagePrevLbl.setBounds(0, 0, 208, 213);
		imagePrevLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		thumbnailPrevLbl.setBounds(0, 0, 208, 218);
		thumbnailPrevLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		thumbnailPrevLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
		descArea = new JTextArea();
		descArea.setMinimumSize(new Dimension(400, 342));
		descArea.setMaximumSize(new Dimension(400, 2000));
		descArea.setFont(UIManager.getFont("PopupMenu.font"));
		descArea.setForeground(Color.WHITE);
		descArea.setLineWrap(true);
		descArea.setBackground(notblack);
		descArea.setCaretColor(Color.WHITE);
		scrollPane.setViewportView(descArea);
		
		comboField = new JComboBox<String>();
		comboField.addItem("Select a Field.");
		comboField.setMaximumRowCount(100);
		comboField.setForeground(Color.black);
		comboField.setBounds(0, 21, 259, 20);
		fieldPanel.add(comboField);
		
		titleF = new JTextField();
		titleF.setMaximumSize(new Dimension(400, 25));
		titleF.setBackground(notblack);
		titleF.setBounds(0, 0, 400, 20);
		titlePannel.add(titleF);
		titleF.setColumns(10);
		titleF.setCaretColor(Color.WHITE);
		titleF.setForeground(Color.white);
		
		imgField = new JTextField();
		imgField.setBackground(notblack);
		imgField.setCaretColor(Color.white);
		imgField.setForeground(Color.WHITE);
		imgField.setColumns(10);
		
		thmnailField = new JTextField();
		thmnailField.setBackground(notblack);
		thmnailField.setCaretColor(Color.white);
		thmnailField.setForeground(Color.WHITE);
		thmnailField.setColumns(10);
		
		colorPick.setBackground(Color.BLACK);
		colorPick.setEditable(false);
		
		timeStampCheckbx = new JCheckBox(BUNDLE.getString("Gui_Main.timeStampCheckbx.text"));
		timeStampCheckbx.setBackground(discorddark);
		timeStampCheckbx.setForeground(Color.white);

		chckbxAuthor = new JCheckBox(BUNDLE.getString("Gui_Main.chckbxAuthor.text"));
		chckbxAuthor.setBackground(discorddark);
		chckbxAuthor.setForeground(Color.white);

		a_nameField = new JTextField();
		a_nameField.setEnabled(false);
		a_nameField.setBackground(Color.DARK_GRAY);
		a_nameField.setForeground(Color.white);
		a_nameField.setCaretColor(Color.white);
		a_nameField.setColumns(10);
		
		a_URLField = new JTextField();
		a_URLField.setEnabled(false);
		a_URLField.setBackground(Color.DARK_GRAY);
		a_URLField.setForeground(Color.white);
		a_URLField.setCaretColor(Color.white);
		a_URLField.setColumns(10);
		
		chckbxFooter = new JCheckBox(BUNDLE.getString("Gui_Main.chckbxFooter.text"));
		chckbxFooter.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxFooter.setForeground(Color.WHITE);
		chckbxFooter.setBackground(new Color(54, 57, 62));

		f_nameField = new JTextField();
		f_nameField.setEnabled(false);
		f_nameField.setColumns(10);
		f_nameField.setBackground(Color.DARK_GRAY);
		f_nameField.setForeground(Color.white);
		f_nameField.setCaretColor(Color.white);
		
		f_URLField = new JTextField();
		f_URLField.setEnabled(false);
		f_URLField.setColumns(10);
		f_URLField.setBackground(Color.DARK_GRAY);
		f_URLField.setForeground(Color.white);
		f_URLField.setCaretColor(Color.white);
		
		connectedBotName.setHorizontalAlignment(SwingConstants.CENTER);
		connectedBotName.setForeground(discordRed);

		botLabel = new JLabel(BUNDLE.getString("Gui_Main.botLabel.text")); //$NON-NLS-1$
		botLabel.setHorizontalAlignment(SwingConstants.CENTER);
		botLabel.setForeground(new Color(255, 255, 255));
		
		addPopup(titleF, titlePopup);
		addPopup(descArea, descPopup);
		addPopup(imgField, imgPopup);
		addPopup(thmnailField, thmPopup);
		addPopup(f_URLField, f_imgPopup);
		addPopup(a_nameField, a_namePopup);
		addPopup(a_URLField, a_imgPopup);
		addPopup(f_nameField, f_namePopup);

		/*
		 * ##############################################################################
		 * 
		 * Line 477 to 669 : Grouplayouts setup(s)
		 * 
		 * ##############################################################################
		 */
		GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
		gl_statusPanel.setHorizontalGroup(
				gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusPanel.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_statusPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(connectedBotName, GroupLayout.PREFERRED_SIZE, 212, Short.MAX_VALUE)
								.addComponent(botLabel, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
						.addContainerGap())
				);
		gl_statusPanel.setVerticalGroup(
				gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusPanel.createSequentialGroup()
						.addGap(10)
						.addComponent(botLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(connectedBotName))
				);
		statusPanel.setLayout(gl_statusPanel);
		
		GroupLayout groupLayout = new GroupLayout(frmDES.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(titlePannel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(descPannel, 0, 0, Short.MAX_VALUE))
								.addComponent(lblDescription))
						.addGap(10)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(f_nameField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
												.addComponent(lblFields)
												.addComponent(a_URLField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
												.addComponent(a_nameField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
												.addComponent(fieldPanel, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
												.addComponent(f_URLField)
												.addComponent(chckbxFooter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblAvatarUrloptional, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblAuthorName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblFooterTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblIconUrloptional, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(chckbxAuthor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(0, 10, Short.MAX_VALUE)
										.addComponent(timeStampCheckbx, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
						.addGap(10)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(2)
														.addComponent(label, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(colorPick, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
																.addComponent(btnColor, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))
												.addGroup(groupLayout.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
																.addComponent(thmPrevPanel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
																.addComponent(imfPrevPanel, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
																.addComponent(imgField, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
																.addComponent(thmnailField, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(10)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))
						.addGap(10)
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(statusPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
										.addComponent(btnReset, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
								.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.addGap(9))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
						.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
						.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(10)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblTitle)
										.addGap(3)
										.addComponent(titlePannel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblDescription)
										.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblFields)
										.addGap(3)
										.addComponent(fieldPanel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addComponent(statusPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnColor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(colorPick, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
										.addGap(16)
										.addComponent(label)))
						.addGap(6)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
														.addGap(25)
														.addComponent(chckbxAuthor)
														.addGap(3)
														.addComponent(lblAuthorName)
														.addGap(3)
														.addComponent(a_nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(15)
														.addComponent(lblAvatarUrloptional)
														.addGap(3)
														.addComponent(a_URLField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(22)
														.addComponent(chckbxFooter)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblFooterTitle)
														.addGap(2)
														.addComponent(f_nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(13)
														.addComponent(lblIconUrloptional)
														.addGap(1)
														.addComponent(f_URLField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18)
														.addComponent(timeStampCheckbx))
												.addComponent(descPannel, GroupLayout.PREFERRED_SIZE, 517, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(416)
										.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addGap(11))
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGap(104)
										.addComponent(imgField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(imfPrevPanel, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
										.addGap(18)
										.addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(thmnailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(thmPrevPanel, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
										.addGap(1))
								.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE))
						.addContainerGap())
				);
		
		GroupLayout gl_descPannel = new GroupLayout(descPannel);
		gl_descPannel.setHorizontalGroup(
				gl_descPannel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_descPannel.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap())
				);
		gl_descPannel.setVerticalGroup(
				gl_descPannel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				);
		
		descPannel.setLayout(gl_descPannel);
		frmDES.getContentPane().setLayout(groupLayout);
		
		
		
		//Downloads the image and displays it onto the specified jlabel
		Runnable dlIMGPrev = new Runnable() {
			@Override
			public void run() {
			try {
				downloadPrevImg(new URL(imgField.getText()), imagePrevLbl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			}
		};
		Runnable dlTHMPrev = new Runnable() {
			@Override
			public void run() {
			try {
				downloadPrevImg(new URL(thmnailField.getText()), thumbnailPrevLbl);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			}
		};
		
		/*
		 *##############################################################################
		 * 
		 * EVENTS
		 * 
		 *##############################################################################
		 */
		
		//TODO Resize of prev THMjlabel
		thmPrevPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				thumbnailPrevLbl.setSize(e.getComponent().getSize());
			}
		});
		
		//TODO resize of prev IMGjlabel
		imfPrevPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				imagePrevLbl.setSize(e.getComponent().getSize());
			}
		});

		//TODO animThm
		thmnailField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(thmnailField.getText().startsWith("http") &&
						thmnailField.getText().endsWith(".jpg") || thmnailField.getText().endsWith(".jpeg") || thmnailField.getText().endsWith(".png") || thmnailField.getText().endsWith(".gif")) {
					playLoadAnim2 = true;
					initAnimThreads(true);
					Thread thmDL = new Thread(dlTHMPrev);
					thmDL.start();
				}else {
					try {
						thumbnailPrevLbl.setIcon(null);
						initAnimThreads(false);
						playLoadAnim2 = false;

					}catch(NullPointerException ignored) {}
				}
			}
		});

		//TODO animImg
		imgField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(imgField.getText().startsWith("http") &&
						imgField.getText().endsWith(".jpg") || imgField.getText().endsWith(".jpeg") || imgField.getText().endsWith(".png") || imgField.getText().endsWith(".gif")) {
					playLoadAnim = true;
					initAnimThreads(true);
					Thread imgDL = new Thread(dlIMGPrev);
					imgDL.start();
				}else {
					try {
						imagePrevLbl.setIcon(null);
						initAnimThreads(false);
						playLoadAnim = false;
					}catch(NullPointerException ignored) {}
				}
			}
		});

		//TODOcheck for updates
		mntmCheckForUpdates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gui_Network.offline) return;
				Gui_UpdateChecking.main(null);
			}
		});

		//TODO titlePaste
		mntmPaste_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					titleF.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO descPaste
		mntmPaste_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					descArea.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO a_namePaste
		mntmPaste_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					a_nameField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO a_urlPaste
		mntmPaste_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					a_URLField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO f_namePaste
		mntmPaste_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					f_nameField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO f_ulrPaste
		mntmPaste_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					f_URLField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO thmPaste
		mntmPaste_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					thmnailField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO imgPaste
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable t = c.getContents(this);
				if (t == null)
					return;
				try {
					imgField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
				} catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});

		//TODO Console menuItem
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Gui_Console.opened) {
					Gui_Console.main(true);
				}else {
					return;
				}
			}
		});

		//TODO Load embed menuItem
		mntmLoadEmbed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser("Discord-EMS/Saved-Embeds/");
				chooser.setFileFilter(new Sys_JsonFilter());
				chooser.setSize(1280, 720);
				chooser.setFileFilter(new Sys_JsonFilter());
				chooser.setAcceptAllFileFilterUsed(false);
				int retour=chooser.showOpenDialog(chooser.getParent());
				if(retour==JFileChooser.APPROVE_OPTION){
					chooser.getSelectedFile().getName();
					chooser.getSelectedFile().getAbsolutePath();
					Sys_Embed eb = Sys_Json.getEmbedJson(chooser.getSelectedFile().getAbsolutePath());
					try {
						println("<EmbedLoader> - LOADING FILE ["+chooser.getSelectedFile().getName()+"]");
						Sys_LoadEmbed.loadEmbed(eb);
					} catch (Exception e1) {
						//e1.printStackTrace();
						e1.printStackTrace();
						if(Sys_Start.fr) {
							Sys_Message.main("Erreur pendant l'importation.", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("Error while importing the embed.", Sys_MsgType.ERROR);
						}
					}
				}else{
				}
			}
		});

		//TODO language fr listener
		chckbxmntmFranais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sys_Config.saveCfg("language", "fr");
					println("<System> - Succesfuly switched to language fr");
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
					chckbxmntmAnglais.setSelected(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					return;
				}
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						Sys_Start.restart();
					}
				};
				Sys_Message.main("Le langage a bien été changé, \nLes changements ne seront prit en compte\n qu'après un redemmarage de l'outil.", Sys_MsgType.INFO, runnable);
			}
		});

		//TODO language en listener
		chckbxmntmAnglais.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sys_Config.saveCfg("language", "en");
					println("<System> - Succesfuly switched to language en");
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
					chckbxmntmFranais.setSelected(false);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					return;
				}
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						Sys_Start.restart();
					}
				};
				Sys_Message.main("The language has been modified, \nYou must restart the tool for the changes to take effect.", Sys_MsgType.INFO, runnable);

			}
		});

		//TODO make clickable menuItem
		mntmMakeClickable.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(Gui_MakeClickable.opened) {
					return;
				}
				String txt = descArea.getSelectedText();
				if(txt==null) {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous devez selectioner du texte.", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You must select something.", Sys_MsgType.ERROR);
					}
					return;
				}
				Gui_MakeClickable.main(txt, descArea);
			}
		});

		//TODO change bot menuItem
		mntmChangeToken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gui_BotListV2.opened == true) {
					return;
				}else {
					Gui_BotListV2.main(1);
				}
			}
		});

		//TODO open cfg folder menuItem
		mntmOpenConfigFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file = new File ("Discord-EMS/");
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		//TODO exit menuItem
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		//TODO embed structure menuItem
		mntmEmbedanatomy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = "https://imgur.com/a/yOb5n";
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		//TODO about menuItem
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gui_About.main(null);
			}
		});

		//TODO SAVE EMBED MENUITEM
		mntmSaveEmbed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gui_EmbedFileName.opened ==true) {
					return;
				}
				if(titleF.getText().equals("") || descArea.getText().equals("")) {
					if(Sys_Start.fr) {
						Sys_Message.main("Le titre et la description sont le minimum pour sauvegarder un Embed !", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You must have at least, filled the Title and the Description to save an Embed-Message !", Sys_MsgType.ERROR);
					}
					return;
				}

				if(!imgField.getText().startsWith("") || !thmnailField.getText().startsWith("")){
					if(!imgField.getText().startsWith("http") || !imgField.getText().startsWith("https")) {
						if(Sys_Start.fr) {
							Sys_Message.main("L'URL de l'image doit être un lien http/https !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The image URL must be a http/https link !", Sys_MsgType.ERROR);
						}
						return;
					}

					if(!thmnailField.getText().startsWith("http") || !thmnailField.getText().startsWith("https")) {
						if(Sys_Start.fr) {
							Sys_Message.main("L'URL de la miniature doit être un lien http/https !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The thumbnail URL must be a http/https link !", Sys_MsgType.ERROR);
						}
						return;
					}
				}

				builder2 = new Sys_Builder().buildSysEmbed(titleF, descArea, imgField, thmnailField, a_nameField, a_URLField, f_nameField, f_URLField, 
						timeStampCheckbx, chckbxAuthor, chckbxFooter, colorPick, comboField, fieldList, blankFieldList);
				Gui_EmbedFileName.main(null);
			}
		});

		//TODO reset btn
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}
		});

		//TODO color btn
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gui_ColorChooser.opened == true) {
					return;
				}else {
					if(colorPick.getBackground().equals(Color.BLACK)) {
						colorPick.setBackground(Color.RED);
					}
					Gui_ColorChooser.main(colorPick.getBackground());
				}
			}
		});

		//TODO add Field btn
		addFieldBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Gui_FieldType.opened == true) {
					return;
				}else {
					Gui_FieldType.main(null);
				}
			}
		});

		//TODO edit field btn
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboField.getSelectedItem().toString().startsWith("Select")) {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous devez selectionner un champ", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("Please select a field.", Sys_MsgType.ERROR);
					}
					return;
				}
				if(comboField.getSelectedItem().toString().startsWith("Field")) {
					Field field = null;
					String fi = comboField.getSelectedItem().toString().replace("Field_", "");
					int index = Integer.parseInt(fi);
					field = fieldList.get(index-1);
					String title = field.getName();
					String desc = field.getValue();
					boolean inline = field.isInline();
					Gui_FieldEdit.main(title,desc,inline, true);
				}else if(comboField.getSelectedItem().toString().startsWith("BlankField")) {
					Sys_BlankField bField = null;
					String fi = comboField.getSelectedItem().toString().replace("BlankField_", "");
					int index = Integer.parseInt(fi);
					bField = blankFieldList.get(index-1);
					boolean inline = bField.isInline();
					Gui_BlankFieldEdit.main(inline, 1);
				}
			}
		});

		//TODO delete field btn
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboField.getSelectedItem().toString().startsWith("Field") || comboField.getSelectedItem().toString().startsWith("BlankField")) {
					if(comboField.getSelectedItem().toString().startsWith("Field")) {
						String fi = comboField.getSelectedItem().toString().replace("Field_", "");
						int index = Integer.parseInt(fi);
						fieldList.remove(index-1);
					}

					if(comboField.getSelectedItem().toString().startsWith("BlankField")) {
						String fi = comboField.getSelectedItem().toString().replace("BlankField_", "");
						int index = Integer.parseInt(fi);
						blankFieldList.remove(index-1);
					}

					Sys_Message.main("Deleted field : "+comboField.getSelectedItem()+".", Sys_MsgType.INFO);
					if(comboField.getSelectedItem().toString().startsWith("Field")) {
						Gui_FieldEdit.num = Gui_FieldEdit.num-1;
					}
					if(comboField.getSelectedItem().toString().startsWith("BlankField")) {
						Gui_BlankFieldEdit.num1 = Gui_BlankFieldEdit.num1 - 1;
					}
					comboField.removeItem(comboField.getSelectedItem());
				}else {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous devez selectionner un champ", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("Please select a field.", Sys_MsgType.ERROR);
					}
					return;
				}
			}
		});

		//FIXME SendBtn
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Gui_Network.offline == true) {
					if(Sys_Start.fr) {
						Sys_Message.main("Vous ne pouvez pas envoyer de messages en mode hors-ligne\nSoyez sûr d'avoir un acces à internet puis réessayez.", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You cannot send Embed-Messages in offline mode.\nBe sure to have internet acces, and restart Discord-EMS.", Sys_MsgType.ERROR);
					}
					return;
				}
				if(titleF.getText().equals("") || descArea.getText().equals("")){
					if(Sys_Start.fr) {
						Sys_Message.main("Le titre et la description sont le minimum pour envoyer un Embed !", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You must have at least, filled the Title and the Description to send an Embed-Message !", Sys_MsgType.ERROR);
					}
					return;
				}
				if(!imgField.getText().startsWith("") || !thmnailField.getText().startsWith("")){
					if(!imgField.getText().startsWith("http") || !imgField.getText().startsWith("https")) {
						if(Sys_Start.fr) {
							Sys_Message.main("L'URL de l'image doit être un lien http/https !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The image URL must be a http/https link !", Sys_MsgType.ERROR);
						}
						return;
					}
					if(!thmnailField.getText().startsWith("http") || !thmnailField.getText().startsWith("https")) {
						if(Sys_Start.fr) {
							Sys_Message.main("L'URL de la miniature doit être un lien http/https !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The thumbnail URL must be a http/https link !", Sys_MsgType.ERROR);
						}
						return;
					}
				}
				if(chckbxAuthor.isSelected()) {
					if(a_nameField.getText().equals("") || a_URLField.getText().equals("")){
						if(Sys_Start.fr) {
							Sys_Message.main("L'Auteur doit être rempli !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The author must be filled ! ", Sys_MsgType.ERROR);
						}
						return;
					}
				}
				if(chckbxFooter.isSelected()) {
					if(f_nameField.getText().equals("") || f_URLField.getText().equals("")){
						if(Sys_Start.fr) {
							Sys_Message.main("Le bas de page doit être rempli !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The footer must be filled !", Sys_MsgType.ERROR);
						}
						return;
					}
				}
				Gui_Adress.main(null);
			}
		});

		//TODO author chkBx state listener
		chckbxAuthor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxAuthor.isSelected()) {
					a_nameField.setEnabled(true);
					a_URLField.setEnabled(true);
					a_nameField.setBackground(notblack);
					a_URLField.setBackground(notblack);
				}else {
					a_nameField.setText("");
					a_URLField.setText("");
					a_nameField.setEnabled(false);
					a_URLField.setEnabled(false);
					a_nameField.setBackground(Color.DARK_GRAY);
					a_URLField.setBackground(Color.DARK_GRAY);

				}
			}
		});

		//TODO footer chkBx state listener
		chckbxFooter.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxFooter.isSelected()) {
					f_nameField.setEnabled(true);
					f_URLField.setEnabled(true);
					f_nameField.setBackground(notblack);
					f_URLField.setBackground(notblack);
				}else {
					f_nameField.setText("");
					f_URLField.setText("");
					f_nameField.setEnabled(false);
					f_URLField.setEnabled(false);
					f_nameField.setBackground(Color.DARK_GRAY);
					f_URLField.setBackground(Color.DARK_GRAY);
				}
			}
		});
	}

	/**
	 * Resets every text, the field list, the color.
	 */
	public static void reset() {
		titleF.setText("");
		descArea.setText("");
		imgField.setText("");
		thmnailField.setText("");
		a_nameField.setText("");
		a_URLField.setText("");
		f_nameField.setText("");
		f_URLField.setText("");
		timeStampCheckbx.setSelected(false);
		chckbxAuthor.setSelected(false);
		chckbxFooter.setSelected(false);
		colorPick.setBackground(Color.black);
		comboField.removeAllItems();
		comboField.addItem("Select a Field.");
		fieldList.clear();
		blankFieldList.clear();

		//deleteFields();
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

	/**
	 * Manages the animation's threads.
	 * @param run true to launch, otherwise false.
	 */
	private void initAnimThreads(boolean run) {
		//System.out.println("B : " + run);
		if(run) {
			final AtomicBoolean testTHT = new AtomicBoolean(false);
			final AtomicBoolean testIMT = new AtomicBoolean(false);
			Thread.getAllStackTraces().keySet().forEach((thread) -> {
				if(thread.getName().equals("thtThread")) {
					testTHT.set(true);
					if(thread.isInterrupted()) {
						thread.start();
					}
				}else if(thread.getName().equals("imtThread")) {
					testIMT.set(true);
					if(thread.isInterrupted()) {
						thread.start();
					}
				}
			});
			if(testTHT.get() && testIMT.get()) {
				return;
			}
			if(!testIMT.get()) {
				Runnable pi = new Runnable() {
					@Override
					public void run() {
						while(playLoadAnim) {
							try {
								imagePrevLbl.setIcon(Sys_Start.loadFrameList.get(0));
								Thread.sleep(100);
								imagePrevLbl.setIcon(Sys_Start.loadFrameList.get(1));
								Thread.sleep(100);
								imagePrevLbl.setIcon(Sys_Start.loadFrameList.get(2));
								Thread.sleep(100);
							} catch (InterruptedException ignored) {}
						}

					}
				};

				Thread imT = new Thread(pi);
				imT.setName("imtThread");
				imT.start();
			}

			if(!testTHT.get()) {
				Runnable pt = new Runnable() {
					@Override
					public void run() {
						while(playLoadAnim2) {
							try {
								thumbnailPrevLbl.setIcon(Sys_Start.loadFrameList.get(0));
								Thread.sleep(100);
								thumbnailPrevLbl.setIcon(Sys_Start.loadFrameList.get(1));
								Thread.sleep(100);
								thumbnailPrevLbl.setIcon(Sys_Start.loadFrameList.get(2));
								Thread.sleep(100);
							} catch (InterruptedException ignored) {}
						}

					}
				};

				Thread thT = new Thread(pt);
				thT.setName("thtThread");
				thT.start();
			}
		} else {
			playLoadAnim = false;
			playLoadAnim2 = false;
		}
	}
	
	/**
	 * Downloads and display preview img given by url
	 * @param url
	 * @param j
	 */
	private void downloadPrevImg(URL url, JLabel j) {
		URLConnection connection = null;
		try {
			connection = url.openConnection();
			connection.setRequestProperty("User-Agent", url.toString());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(connection.getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
			playLoadAnim = false;
			playLoadAnim2 = false;
			initAnimThreads(false);
			Sys_Util.printerr("<GUI_Main> - Failed to retrieve Img form imgURL >> "+url.toString()+" >> "+e.toString());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			j.setIcon(new ImageIcon(Gui_Main.class.getResource("/fr/dreregon/Discord_EMS/system/media/load_fail.png")));
			return ;
		}
		
		BufferedImage finalImg = null;
		
		if(j.getWidth() > j.getHeight()) finalImg = Scalr.resize(bi, Mode.AUTOMATIC, j.getHeight(), j.getHeight());
		
		if(j.getWidth() < j.getHeight()) finalImg = Scalr.resize(bi, Mode.AUTOMATIC, j.getWidth(), j.getWidth());
		
		playLoadAnim = false;
		playLoadAnim2 = false;
		initAnimThreads(false);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		j.setIcon(new ImageIcon(finalImg));
	}
}

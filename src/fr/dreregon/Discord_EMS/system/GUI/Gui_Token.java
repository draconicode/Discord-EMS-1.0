package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.dreregon.Discord_EMS.bot.Bot;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_SwingLink;
import fr.dreregon.Discord_EMS.system.Sys_Util;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;
import net.dv8tion.jda.api.requests.ErrorResponse;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.util.ResourceBundle;

public class Gui_Token extends JFrame {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	public static boolean saveSelected = false;
	public static String tk = null;

	/**
	 * Launch the application.
	 */
	public static void main(int status) {
		try {
			if(Sys_Start.en == true && Sys_Start.fr == false) {
				BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
			}else if(Sys_Start.en == false && Sys_Start.fr == true) {
				BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
			}
			Sys_Util.openedWindows.add("Token");
			Gui_Token dialog = new Gui_Token(status);
			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Gui_Token(int status) {
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



		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Token.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		setTitle(BUNDLE.getString("Gui_Token.this.title")); //$NON-NLS-1$
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Sys_Util.discorddark);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(null);
		{
			JLabel lblEnterYourToken = new JLabel(BUNDLE.getString("Gui_Token.lblEnterYourToken.text")); //$NON-NLS-1$
			lblEnterYourToken.setForeground(Color.WHITE);
			lblEnterYourToken.setBounds(10, 11, 122, 14);
			contentPanel.add(lblEnterYourToken);
		}
		{
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setForeground(Color.WHITE);
			textField.setBounds(10, 36, 424, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setBackground(Sys_Util.notblack);
			textField.setCaretColor(Color.white);

			JPopupMenu popupMenu = new JPopupMenu();
			addPopup(textField, popupMenu);

			JMenuItem mntmPaste = new JMenuItem(BUNDLE.getString("Gui_Token.mntmPaste.text")); //$NON-NLS-1$
			mntmPaste.setIcon(new ImageIcon(Gui_Token.class.getResource("/fr/dreregon/Discord_EMS/system/media/filecopy.png")));
			mntmPaste.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
					Transferable t = c.getContents(this);
					if (t == null)
						return;
					try {
						textField.setText((String) t.getTransferData(DataFlavor.stringFlavor));
					} catch (Exception e){
						e.printStackTrace();
					}
				}
			});
			popupMenu.add(mntmPaste);
		}

		Sys_SwingLink lblNewLabel = new Sys_SwingLink("Where do i find my token ?","https://discordapp.com/developers/applications/me");
		if(Sys_Start.fr) {
			lblNewLabel.setText("Où puis-je trouver mon token ?");
			lblNewLabel.setLink("https://discordapp.com/developers/applications/me");
		}
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(213, 0, 221, 34);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(Sys_Util.discorddark);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			JCheckBox chckbxRememberMyToken = new JCheckBox(BUNDLE.getString("Gui_Token.chckbxRememberMyToken.text")); //$NON-NLS-1$
			chckbxRememberMyToken.setForeground(Color.WHITE);
			chckbxRememberMyToken.setBackground(new Color(240, 240, 240));
			chckbxRememberMyToken.setHorizontalAlignment(SwingConstants.LEFT);
			chckbxRememberMyToken.setBackground(Sys_Util.discorddark);

			buttonPane.add(chckbxRememberMyToken);
			{
				JButton okButton = new JButton(BUNDLE.getString("Gui_Token.okButton.text")); //$NON-NLS-1$
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						saveSelected = chckbxRememberMyToken.isSelected();
						if(chckbxRememberMyToken.isSelected()) {
							if(Bot.getJDA() != null) {
								if(Bot.getJDA().getPresence().getStatus().equals(OnlineStatus.ONLINE)) {
									Bot.stopBot();
								}
							}
							try {
								Bot.startBot(textField.getText(), status);
							} catch (ErrorResponseException e) {
								if(e.getErrorResponse() == ErrorResponse.INVALID_TOKEN ) {
									Sys_Util.println("<Token> - Invalid Token");
									Gui_TokenError.main(null);
								}
								dispose();
								return;
							}
							tk = textField.getText();
							dispose();
						}else {
							try {
								if(Bot.getJDA() != null) {
									if(Bot.getJDA().getPresence().getStatus().equals(OnlineStatus.ONLINE)) {
										Bot.stopBot();
									}
								}
								Bot.startBot(textField.getText(), status);
							} catch (ErrorResponseException e) {
								if(e.getErrorResponse() == ErrorResponse.INVALID_TOKEN ) {
									Sys_Util.println("<Token> - Invalid Token");
									Gui_TokenError.main(null);
								}
								dispose();
								return;
							}
							dispose();
						}
					}
				});
				okButton.setActionCommand(BUNDLE.getString("Gui_Token.okButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton(BUNDLE.getString("Gui_Token.cancelButton.text")); //$NON-NLS-1$
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(status == 1) {
						}else {
							dispose();
						}
					}
				});
				cancelButton.setActionCommand(BUNDLE.getString("Gui_Token.cancelButton.actionCommand")); //$NON-NLS-1$
				buttonPane.add(cancelButton);
			}
		}
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

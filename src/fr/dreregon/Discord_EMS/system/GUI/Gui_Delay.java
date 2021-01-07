package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

/**
Copyright 2018 Dreregon

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
public class Gui_Delay {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private JFrame frmDelayMessage;
	private static Guild guildf;
	private static TextChannel channelf;
	private static boolean everyonef;
	private static EmbedBuilder msgf;

	/**
	 * Launch the delay selector window, providing in which channel and the message to send.
	 * @param guild
	 * @param channel
	 * @param everyone
	 * @param msg
	 */
	public static void main(Guild guild, TextChannel channel, boolean everyone, EmbedBuilder msg) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					guildf = guild;
					channelf = channel;
					everyonef = everyone;
					msgf = msg;
					Gui_Delay window = new Gui_Delay();
					window.frmDelayMessage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_Delay() {
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
		
		frmDelayMessage = new JFrame();
		frmDelayMessage.setResizable(false);
		frmDelayMessage.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Delay.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmDelayMessage.setTitle(BUNDLE.getString("Gui_Delay.frmDelayMessage.title")); //$NON-NLS-1$
		frmDelayMessage.setBounds(100, 100, 300, 130);
		frmDelayMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDelayMessage.setLocationRelativeTo(null);
		frmDelayMessage.getContentPane().setBackground(Sys_Util.discorddark);
		
		JLabel lblDelayMessageUntil = new JLabel(BUNDLE.getString("Gui_Delay.lblDelayMessageUntil.text")); //$NON-NLS-1$
		lblDelayMessageUntil.setHorizontalAlignment(SwingConstants.CENTER);
		lblDelayMessageUntil.setForeground(Color.WHITE);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Date date = new Date();
		String t = dateFormat.format(date);
		String h = t.substring(0, 2);
		int hour = Integer.parseInt(h);
		int dum = hour;
		for(int i=0; i<24-hour; i++) {
			comboBox.addItem(""+dum+":00");
			comboBox.addItem(""+dum+":05");
			comboBox.addItem(""+dum+":10");
			comboBox.addItem(""+dum+":15");
			comboBox.addItem(""+dum+":20");
			comboBox.addItem(""+dum+":25");
			comboBox.addItem(""+dum+":30");
			comboBox.addItem(""+dum+":35");
			comboBox.addItem(""+dum+":40");
			comboBox.addItem(""+dum+":45");
			comboBox.addItem(""+dum+":50");
			comboBox.addItem(""+dum+":55");
			dum++;
		}
		
		JButton btnDelay = new JButton(BUNDLE.getString("Gui_Delay.btnDelay.text")); //$NON-NLS-1$
		GroupLayout groupLayout = new GroupLayout(frmDelayMessage.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDelay, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDelayMessageUntil, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBox, 0, 274, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblDelayMessageUntil)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnDelay)
					.addGap(181))
		);
		frmDelayMessage.getContentPane().setLayout(groupLayout);
		
		btnDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = (String) comboBox.getSelectedItem();
				String toFloat = selected.replace(":", ".");
				float f1 = Float.parseFloat(toFloat);
				DateFormat dateFormat = new SimpleDateFormat("HH:mm");
				Date date = new Date();
				String t2 = dateFormat.format(date).replace(":", ".");
				float f2 = Float.parseFloat(t2);
				if(f1<f2) {
					if(Sys_Start.fr) {
						Sys_Message.main("L'heure sélectionée ne peut pas être dans le passé.", Sys_MsgType.ERROR);
					}else {
						Sys_Message.main("You must select a time ahead of now.", Sys_MsgType.ERROR);
					}
					
					return;
				}else {
				
				Gui_DelayStatus.main(selected, guildf, channelf, msgf, everyonef);
				if(Sys_Start.fr) {
					Sys_Message.main("Le message sera envoyé à "+selected+".\n", Sys_MsgType.INFO);
				}else {
					Sys_Message.main("Your message will be sent at "+selected+".\n", Sys_MsgType.INFO);
				}
				frmDelayMessage.dispose();
				}
			}
		});
	}
}

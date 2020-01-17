package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.InsufficientPermissionException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import javax.swing.SwingConstants;

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
public class Gui_DelayStatus {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$

	private JFrame frmDMStatus;

	/**
	 * Launch the delay status window, with remainig time and destination.
	 * At the end of the timer, it will send the embed message into the selected channel, tagging @everyone if bool tag = true. 
	 * @param delay
	 * @param guild
	 * @param channel
	 * @param message
	 * @param tag
	 */
	public static void main(String delay, Guild guild, TextChannel channel, EmbedBuilder message, boolean tag) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					String delay1 = delay;
					Gui_DelayStatus window = new Gui_DelayStatus(delay1, guild, channel, message, tag);
					window.frmDMStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui_DelayStatus(String delay, Guild guild, TextChannel channel, EmbedBuilder message, boolean tag) {
		initialize(delay, guild, channel, message, tag);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String delay, Guild guild, TextChannel channel, EmbedBuilder message, boolean tag) {
		
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
		
		boolean timestamp = Gui_Main.timeStampCheckbx.isSelected();
		
		frmDMStatus = new JFrame();
		frmDMStatus.getContentPane().setBackground(Sys_Util.discorddark);
		frmDMStatus.setResizable(false);
		frmDMStatus.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_DelayStatus.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		frmDMStatus.setTitle(BUNDLE.getString("Gui_DelayStatus.frmDMStatus.title")); //$NON-NLS-1$
		frmDMStatus.setBounds(100, 100, 325, 170);
		frmDMStatus.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JLabel lblTheMessageWill = new JLabel(BUNDLE.getString("Gui_DelayStatus.lblTheMessageWill.text")); //$NON-NLS-1$
		lblTheMessageWill.setBounds(10, 11, 220, 14);
		lblTheMessageWill.setForeground(Color.WHITE);
		
		JLabel timeLbl = new JLabel(delay);
		timeLbl.setHorizontalAlignment(SwingConstants.TRAILING);
		timeLbl.setBounds(168, 11, 141, 14);
		timeLbl.setForeground(Color.WHITE);
		frmDMStatus.getContentPane().setLayout(null);
		frmDMStatus.getContentPane().add(lblTheMessageWill);
		frmDMStatus.getContentPane().add(timeLbl);
		
		JLabel lblCurrentTimeIs = new JLabel(BUNDLE.getString("Gui_DelayStatus.lblCurrentTimeIs.text")); //$NON-NLS-1$
		lblCurrentTimeIs.setForeground(Color.WHITE);
		lblCurrentTimeIs.setBounds(10, 26, 220, 14);
		frmDMStatus.getContentPane().add(lblCurrentTimeIs);
		
		JLabel label = new JLabel(""); //$NON-NLS-1$
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setForeground(Color.WHITE);
		label.setBounds(153, 26, 156, 14);
		frmDMStatus.getContentPane().add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 51, 299, 2);
		frmDMStatus.getContentPane().add(separator);
		
		JLabel lblInGuild = new JLabel(BUNDLE.getString("Gui_DelayStatus.lblInGuild.text")); //$NON-NLS-1$
		lblInGuild.setForeground(Color.WHITE);
		lblInGuild.setBounds(10, 64, 113, 14);
		frmDMStatus.getContentPane().add(lblInGuild);
		
		JLabel lblInChannel = new JLabel(BUNDLE.getString("Gui_DelayStatus.lblInChannel.text")); //$NON-NLS-1$
		lblInChannel.setForeground(Color.WHITE);
		lblInChannel.setBounds(10, 79, 113, 14);
		frmDMStatus.getContentPane().add(lblInChannel);
		
		JLabel label_1 = new JLabel("<guild>");
		label_1.setHorizontalAlignment(SwingConstants.TRAILING);
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(133, 64, 176, 14);
		frmDMStatus.getContentPane().add(label_1);
		label_1.setText(guild.getName());
		
		JLabel label_2 = new JLabel("<channel>");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(133, 79, 176, 14);
		frmDMStatus.getContentPane().add(label_2);
		label_2.setText(channel.getName());
		
		JButton btnCancel = new JButton(BUNDLE.getString("Gui_DelayStatus.btnCancel.text")); //$NON-NLS-1$
		btnCancel.setBounds(10, 107, 89, 23);
		frmDMStatus.getContentPane().add(btnCancel);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
				Date date2 = new Date();
				String compare = dateFormat2.format(date2);
				label.setText(compare);
				if(compare.substring(0 ,5).equals(delay)) {
					try {
						if(tag) {
							channel.sendMessage("@everyone").queue();
						}
						if(timestamp) {
							Date date = new Date();
							message.setTimestamp(date.toInstant());
						}
						channel.sendMessage(message.build()).queue();
						if(Sys_Start.fr) {
							Sys_Message.main("Message envoyé !", Sys_MsgType.INFO);
						}else {
							Sys_Message.main("Succesfuly sent your message !", Sys_MsgType.INFO);
						}
						
					} catch (Exception e) {
						Sys_Util.println("<Gui_DelayStatus> - ERROR : Couldn't send the embedmessage -> ["+e.getMessage()+"]");
						//e.printStackTrace();
						if(e instanceof InsufficientPermissionException) {
							if(Sys_Start.fr) {
								Sys_Message.main("Le message n'a pas pu être envoyé.\n["+e.getMessage()+"]\n"
										+"In Guild <"+guild.getName()+">, TextChannel <"+channel.getName()+">.", Sys_MsgType.WARN);
							}else {
								Sys_Message.main("Couldn't send your message.\n["+e.getMessage()+"]\n"
										+"In Guild <"+guild.getName()+">, TextChannel <"+channel.getName()+">.", Sys_MsgType.WARN);
							}
							
						}else {
							if(Sys_Start.fr) {
								Sys_Message.main("Le message n'a pas pu être envoyé.\n["+e.getMessage()+"]\n"
										+"In Guild <"+guild.getName()+">, TextChannel <"+channel.getName()+">.", Sys_MsgType.WARN);
							}else {
								Sys_Message.main("Couldn't send your message.\n["+e.getMessage()+"]\n"
										+"In Guild <"+guild.getName()+">, TextChannel <"+channel.getName()+">.", Sys_MsgType.WARN);
							}
						}
					}
					timer.cancel();
					frmDMStatus.dispose();
				}
			}
		}, 0, 1000);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.cancel();
				Sys_Message.main("Delay canceled.", Sys_MsgType.INFO);
				frmDMStatus.dispose();
			}
		});
	}
}

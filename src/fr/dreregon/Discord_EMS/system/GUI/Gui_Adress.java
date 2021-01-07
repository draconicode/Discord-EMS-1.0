package fr.dreregon.Discord_EMS.system.GUI;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JPanel;

import fr.dreregon.Discord_EMS.bot.BotListener;
import fr.dreregon.Discord_EMS.system.Sys_Builder;
import fr.dreregon.Discord_EMS.system.Sys_DynamicsSetter;
import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComboBox;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import javax.swing.JLabel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.exceptions.PermissionException;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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
public class Gui_Adress extends JDialog {
	private static ResourceBundle BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages"); //$NON-NLS-1$
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JComboBox<Guild> guildBox = new JComboBox<Guild>();
	public static JComboBox<TextChannel> channBox = new JComboBox<TextChannel>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if(Sys_Start.en == true && Sys_Start.fr == false) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_en_US"); //$NON-NLS-1$
				}else if(Sys_Start.en == false && Sys_Start.fr == true) {
					BUNDLE = ResourceBundle.getBundle("fr.dreregon.Discord_EMS.system.GUI.messages_fr_FR"); //$NON-NLS-1$
				}
				try {
					Gui_Adress dialog = new Gui_Adress();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Gui_Adress() {
		setTitle(BUNDLE.getString("Gui_Adress.this.title")); //$NON-NLS-1$
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Gui_Adress.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		setAlwaysOnTop(true);
		setBounds(100, 100, 440, 205);
		setLocationRelativeTo(Gui_Main.frmDES);
		
		guildBox.removeAllItems();
		Sys_DynamicsSetter.setItemsInCombo(BotListener.getGuildsList(), guildBox);
		
		Guild guild = (Guild) guildBox.getSelectedItem();
        channBox.removeAllItems();
       
        
        for (TextChannel c : guild.getTextChannels()) {
          channBox.addItem(c);
        }
		
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(Sys_Util.discorddark);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(54, 57, 62));
		panel_1.setBounds(10, 11, 414, 92);
		panel.add(panel_1);
		guildBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == 1)
		        {
					Guild guild = (Guild) guildBox.getSelectedItem();
			          channBox.removeAllItems();
			          for (TextChannel c : guild.getTextChannels()) {
			            channBox.addItem(c);
			          }
		        }
			}
		});
		
		
		guildBox.setBounds(0, 20, 414, 20);
		panel_1.add(guildBox);
		
		JLabel label = new JLabel(BUNDLE.getString("Gui_Adress.label.text")); //$NON-NLS-1$
		label.setForeground(Color.WHITE);
		label.setBounds(0, 0, 46, 14);
		panel_1.add(label);
		
		
		channBox.setBounds(0, 66, 414, 20);
		panel_1.add(channBox);
		
		JLabel label_1 = new JLabel(BUNDLE.getString("Gui_Adress.label_1.text")); //$NON-NLS-1$
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(0, 46, 64, 14);
		panel_1.add(label_1);
		
		JButton btnSend = new JButton(BUNDLE.getString("Gui_Adress.btnSend.text")); //$NON-NLS-1$
		btnSend.setIcon(new ImageIcon(Gui_Adress.class.getResource("/fr/dreregon/Discord_EMS/system/media/Discord-EMS_32.png")));
		btnSend.setBounds(308, 114, 116, 51);
		panel.add(btnSend);
		
		JButton btnNewButton_1 = new JButton(BUNDLE.getString("Gui_Adress.btnNewButton_1.text")); //$NON-NLS-1$
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(10, 114, 116, 51);
		panel.add(btnNewButton_1);
		
		JCheckBox chckbxTageveryone = new JCheckBox(BUNDLE.getString("Gui_Adress.chckbxTageveryone.text")); //$NON-NLS-1$
		chckbxTageveryone.setForeground(new Color(255, 255, 255));
		chckbxTageveryone.setBackground(Sys_Util.discorddark);
		chckbxTageveryone.setBounds(132, 116, 170, 14);
		panel.add(chckbxTageveryone);
		
		JCheckBox chckbxDelay = new JCheckBox(BUNDLE.getString("Gui_Adress.chckbxDelay.text")); //$NON-NLS-1$
		chckbxDelay.setForeground(Color.WHITE);
		chckbxDelay.setBackground(Sys_Util.discorddark);
		chckbxDelay.setBounds(132, 142, 97, 23);
		panel.add(chckbxDelay);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) throws PermissionException {
				
				if(!Gui_Main.imgField.getText().equals("") || !Gui_Main.thmnailField.getText().equals("")) {
				}
				
				if(chckbxDelay.isSelected()) {
					EmbedBuilder builder = new EmbedBuilder();
					builder = new Sys_Builder().buildEmbed(Gui_Main.titleF, Gui_Main.descArea, Gui_Main.imgField, Gui_Main.thmnailField, Gui_Main.a_nameField,
							Gui_Main.a_URLField, Gui_Main.f_nameField, Gui_Main.f_URLField, 
							Gui_Main.timeStampCheckbx, Gui_Main.chckbxAuthor, Gui_Main.chckbxFooter, Gui_Main.colorPick, Gui_Main.comboField, Gui_Main.fieldList, Gui_Main.blankFieldList);
					Guild guild = (Guild) guildBox.getSelectedItem();
					TextChannel chanBox = (TextChannel) channBox.getSelectedItem();
					Gui_Delay.main(guild, chanBox, chckbxTageveryone.isSelected(), builder);
					dispose();
					return;
				}

				
				
				

				EmbedBuilder builder = new EmbedBuilder();
				builder = new Sys_Builder().buildEmbed(Gui_Main.titleF, Gui_Main.descArea, Gui_Main.imgField, Gui_Main.thmnailField, Gui_Main.a_nameField,
						Gui_Main.a_URLField, Gui_Main.f_nameField, Gui_Main.f_URLField, 
						Gui_Main.timeStampCheckbx, Gui_Main.chckbxAuthor, Gui_Main.chckbxFooter, Gui_Main.colorPick, Gui_Main.comboField, Gui_Main.fieldList, Gui_Main.blankFieldList);

				Guild guild = (Guild) guildBox.getSelectedItem();
				TextChannel chanBox = (TextChannel) channBox.getSelectedItem();
				TextChannel chann = guild.getTextChannelById(chanBox.getId());

				try {
					if(chckbxTageveryone.isSelected()) {
						chann.sendMessage("@everyone").queue();
					}
					chann.sendMessage(builder.build()).queue();
					if(Sys_Start.fr) {
						Sys_Message.main("Message envoyé !", Sys_MsgType.INFO);
					}else {
						Sys_Message.main("Succesfuly sent your message !", Sys_MsgType.INFO);
					}
					
				} catch (Exception e) {
					Sys_Util.println("<Gui_Main> - ERROR : Couldn't send the embedmessage -> ["+e.getMessage()+"]");
					//e.printStackTrace();
					if(e instanceof InsufficientPermissionException) {
						if(Sys_Start.fr) {
							Sys_Message.main("Le message n'a pas pu être envoyé.\n["+e.getMessage()+"]\n"
									+"In Guild <"+guild.getName()+">, TextChannel <"+chann.getName()+">.", Sys_MsgType.WARN);
						}else {
							Sys_Message.main("Couldn't send your message.\n["+e.getMessage()+"]\n"
									+"In Guild <"+guild.getName()+">, TextChannel <"+chann.getName()+">.", Sys_MsgType.WARN);
						}
						
					}else {
						if(Sys_Start.fr) {
							Sys_Message.main("Le message n'a pas pu être envoyé.\n["+e.getMessage()+"]\n"
									+"In Guild <"+guild.getName()+">, TextChannel <"+chann.getName()+">.", Sys_MsgType.WARN);
						}else {
							Sys_Message.main("Couldn't send your message.\n["+e.getMessage()+"]\n"
									+"In Guild <"+guild.getName()+">, TextChannel <"+chann.getName()+">.", Sys_MsgType.WARN);
						}
					}
				}
				dispose();
			}
		});
		
		chckbxTageveryone.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(chckbxTageveryone.isSelected()) {
					Guild guild = (Guild) guildBox.getSelectedItem();
					boolean perm = guild.getSelfMember().hasPermission(Permission.MESSAGE_MENTION_EVERYONE);
					int ppl = guild.getMembers().size();
					if(perm) {
						if(ppl >= 10) {
							if(Sys_Start.fr) {
								Sys_Message.main("Cela va notifier plus de 10 personnes !\nSoyez sûr d'avoir la permission de le faire !", Sys_MsgType.WARN);
							}else {
								Sys_Message.main("This will tag more than 10 people !\nBe sure to have the permission to do that !", Sys_MsgType.WARN);
							}
							
						}
					}else {
						if(Sys_Start.fr) {
							Sys_Message.main("Le bot n'a pas la permission de tagger @everyone !", Sys_MsgType.ERROR);
						}else {
							Sys_Message.main("The bot does not have the permission to tag @everyone !", Sys_MsgType.ERROR);
							
						}
						chckbxTageveryone.setSelected(false);
					}
					
					
				}
			}
		});

	}
}

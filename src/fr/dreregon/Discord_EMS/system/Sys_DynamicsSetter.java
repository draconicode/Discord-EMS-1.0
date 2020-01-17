package fr.dreregon.Discord_EMS.system;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;

public class Sys_DynamicsSetter {

	public static void setItemsInCombo(List<Guild> list, JComboBox<Guild> combobox) {
		for(Guild g : list) {
			combobox.addItem(g);
		}
	}
	
	public static void resetComboGuild(JComboBox<Guild> combobox) {
		combobox.removeAllItems();
	}
	
	public static void resetComboChann(JComboBox<TextChannel> combobox) {
		combobox.removeAllItems();
	}
	
	public static void setConnectedBotName(String botName, JLabel label) {
		if(botName == "Offline mode") {
			label.setForeground(Sys_Util.discordYellow);
			label.setText("Connected as \""+botName+"\"");
		}
		label.setText("Connected");
		label.setForeground(Sys_Util.discordGreen);
	}
	
	public static void setOfflineLabel(String string, JLabel label) {
			label.setText(string);
			label.setForeground(Sys_Util.discordYellow);
	}
	
	public static void replaceSelection(JTextComponent j, String replacement) {
		if(j instanceof JTextArea) {
			j.replaceSelection(replacement);
		}
		
		if(j instanceof JTextField) {
			j.replaceSelection(replacement);
		}
			
		
	}
	
	
}

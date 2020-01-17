package fr.dreregon.Discord_EMS.bot;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import fr.dreregon.Discord_EMS.system.Sys_Bot;
import fr.dreregon.Discord_EMS.system.Sys_DynamicsSetter;
import fr.dreregon.Discord_EMS.system.Sys_Json;
import fr.dreregon.Discord_EMS.system.Sys_Splash;
import fr.dreregon.Discord_EMS.system.Sys_Util;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Main;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Network;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Token;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.DisconnectEvent;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ExceptionEvent;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.ReconnectedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class BotListener implements EventListener{
	private static List<Guild> guilds;

	@Override
	public void onEvent(Event e) {
		if(e instanceof ReadyEvent) {
			Sys_Splash.remSplash();
			JDA jda = Bot.getJDA();
			BotListener.guilds = jda.getGuilds();
			Sys_DynamicsSetter.setConnectedBotName(Bot.botName(), Gui_Main.connectedBotName);
			URL url = null;
			try {
				url = new URL(jda.getSelfUser().getAvatarUrl());
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			URLConnection connection = null;
			try {
				connection = url.openConnection();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			connection.setRequestProperty("User-Agent", jda.getSelfUser().getAvatarUrl());
			BufferedImage img1 = null;
			try {
				img1 = ImageIO.read(connection.getInputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
				try {
					img1 = ImageIO.read(Gui_Main.class.getResource("/fr/dreregon/Discord_ES/system/media/Discord_logo32x.png"));
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
			ImageIcon img2 = new ImageIcon(img1);
			Image img3 = img2.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
			
			if(img3 != null) {
				Gui_Main.botLabel.setIcon(new ImageIcon(img3));
			}else {
				Gui_Main.botLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Gui_Main.class.getResource("/fr/dreregon/Discord_ES/system/media/Discord_logo32x.png"))));
			}
			
			
			Gui_Main.botLabel.setText(jda.getSelfUser().getName());
			if(Gui_Token.saveSelected) {
				Sys_Json.createBotJson("Discord-EMS/Bots/Bot/bot-"+jda.getSelfUser().getId()+".json", new Sys_Bot(jda.getSelfUser().getName(), Gui_Token.tk, jda.getSelfUser().getAvatarUrl(), jda.getSelfUser().getId()));
			}
		}
		
		if(e instanceof ExceptionEvent) {
			Sys_Util.println(((ExceptionEvent) e).getCause().toString());
		}
		
		if(e instanceof DisconnectEvent) {
			System.out.println("<Event> - Disconnected");
			Gui_Main.connectedBotName.setText("Disconnected");
			Gui_Main.connectedBotName.setForeground(Sys_Util.discordRed);
			Gui_Network.offline = true;
		}
		
		if(e instanceof ReconnectedEvent) {
			Sys_Util.println("<Event> - Reconnected");
			Gui_Main.connectedBotName.setText("Connected");
			Gui_Main.connectedBotName.setForeground(Sys_Util.discordGreen);
			Gui_Network.offline = false;
		}
		
	}
	
	public static List<Guild> getGuildsList() {
		return guilds;
	}

}

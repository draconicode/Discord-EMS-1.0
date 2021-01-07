package fr.dreregon.Discord_EMS.system;

import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import fr.dreregon.Discord_EMS.bot.Bot;
import fr.dreregon.Discord_EMS.system.GUI.Gui_BlankFieldEdit;
import fr.dreregon.Discord_EMS.system.GUI.Gui_BotListV2;
import fr.dreregon.Discord_EMS.system.GUI.Gui_ColorChooser;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Console;
import fr.dreregon.Discord_EMS.system.GUI.Gui_FieldEdit;
import fr.dreregon.Discord_EMS.system.GUI.Gui_FieldType;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Main;
import fr.dreregon.Discord_EMS.system.GUI.Gui_MakeClickable;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Network;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Token;

public class Sys_Start {

	public static String version = "1.7.0_0";
	public static boolean windows = false;
	public static boolean fr = false;
	public static boolean en = true;
	
	public static ImageIcon frame1;
	public static ImageIcon frame2;
	public static ImageIcon frame3;
	
	public static ArrayList<ImageIcon> loadFrameList = new ArrayList<>();

	/**
	 * Starting method. Checks for files, language, os (for look and feel)
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		Sys_Util.printClr("<Discord Embed-Messages Sender v"+Sys_Start.version+">", Sys_Util.discordGreen);
		if(args != null && args.length>0) {
			if(args[0].equals("-showConsole")) {
				Sys_Util.println("<Start> - Starting custom console");
				Gui_Console.main(true);
				if(args.length == 2) {

				}

			}else {
				Gui_Console.main(false);
			}
			if(args[0].equals("-debug")) {
				Sys_Util.printClr("<Start> - Overriding start routine, launching in debug mode (Offline)."
						+ "\n<Start> - Note that Look and feel, Language will not work. (falling back to default settings)", Sys_Util.discordYellow);
				Gui_Console.main(true);
				Gui_Network.offline = true;
				Gui_Main.main(true);
				return;
			}
		}

		checkForSystemFiles();
		setLanguageFromProperties();//bit

		if(System.getProperty("os.name").contains("Windows")) {
			windows = true;
			Sys_Util.println("<Start> - Setting Look&Feel to WINDOWS.");
		}else {
			Sys_Util.println("<Start> - Setting Look&Feel to CROSSPLATFORM.");
		}
		try {
			checkAndStart();
		} catch (Exception e) {
			Sys_Util.printerr("Failed to launch");
			JOptionPane.showMessageDialog(null, "Couldn't launch Discord-EMS.\nCheck your connection, or try re-starting the tool.\n\n"+e.getCause()+"\n"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Checks for system files. If not present, they will be created.
	 */
	private static void checkForSystemFiles() {
		File systemFolder = null;
		File properties = null;

		Sys_Util.println("<Start> - Checking system files...");
		
		try {
			frame1 = new ImageIcon(Sys_Start.class.getResource("/fr/dreregon/Discord_EMS/system/media/anim/imgPreviewLoading/load_f1.png"));
			frame2 = new ImageIcon(Sys_Start.class.getResource("/fr/dreregon/Discord_EMS/system/media/anim/imgPreviewLoading/load_f2.png"));
			frame3 = new ImageIcon(Sys_Start.class.getResource("/fr/dreregon/Discord_EMS/system/media/anim/imgPreviewLoading/load_f3.png"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		loadFrameList.add(frame1);
		loadFrameList.add(frame2);
		loadFrameList.add(frame3);

		systemFolder = new File("Discord-EMS");
		properties = new File("Discord-EMS"+File.separator+"DEMS.properties");

		if(!systemFolder.exists()) {
			systemFolder.mkdir();
			Sys_Util.println("<Start> - Created config folder [Discord-EMS/]");
		}else {
			Sys_Util.println("<Start> - System folder ok.");
		}

		if(!properties.exists()) {
			try {
				Sys_Util.println("<Start> - Properties file not found ; Creating properties file...");
				properties.createNewFile();
				Sys_Config.saveCfg("language", "en");
				Sys_Util.println("<Start> - Done.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Sys_Util.println("<Start> - Properties file ok.");
		}
	}

	/**
	 * Set locale from the saved language in the properties file.
	 */
	private static void setLanguageFromProperties() {
		String language = null;
		try {
			language = Sys_Config.getValue("language");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		switch(language) {
		case "en": en = true;
			fr = false;
			Sys_Util.println("<Start> - Setting locale to en-US");
		break;
		case "fr": fr = true;
			en = false;
			Sys_Util.println("<Start> - Setting locale to fr-FR");
		break;
		case "null" : en = true;
			fr = false;
			Sys_Util.println("<Start> - Setting locale to en-US");
		}
	}

	/**
	 * Check if the system files exist, then start the tool if only one bot is saved.
	 * If more than one bot is saved, it starts the bot list.
	 * If no bot is saved, it starts the token window.
	 */
	public static void checkAndStart() {
		Sys_Util.println("<Start> - Checking Bot files...");
		File bots = null;
		File botFolder = null;
		File savedEmbeds = null;
		File avatarFolder = null;
		try {

			savedEmbeds = new File("Discord-EMS"+File.separator+"Saved-Embeds");
			if(!savedEmbeds.exists()) {
				savedEmbeds.mkdir();
				Sys_Util.println("<Start> - Created Embed folder [Discord-EMS/Embeds/]");
			}

			bots = new File("Discord-EMS"+File.separator+"Bots");
			if(!bots.exists()) {
				bots.mkdir();
				Sys_Util.println("<Start> - Created bots folder [Discord-EMS/Bots/]");
			}

			botFolder = new File("Discord-EMS"+File.separator+"Bots"+File.separator+"Bot");
			if(!botFolder.exists()) {
				botFolder.mkdir();
				Sys_Util.println("<Start> - Created bot folder [Discord-EMS/Bots/Bot/]");
			}

			avatarFolder = new File(bots.getAbsolutePath()+File.separator+"Avatar");
			if(!avatarFolder.exists()) {
				avatarFolder.mkdir();
				Sys_Util.println("<Start> - Created bots folder [Discord-EMS/Bots/Avatar/]");
			}

		} catch (Exception e) {
			Sys_Util.println("<Start> - Error while creating system folders : "+e.getMessage());
		}

		Sys_Util.println("<Start> - Checking for saved bots...");

		if(botFolder.listFiles().length == 0) {
			Sys_Util.println("<Start> - No bot(s) found, launching token window.");
			Gui_Token.main(0);
			Sys_Splash.remSplash();
		}else if(botFolder.listFiles().length == 1) {
			File[] botFiles = botFolder.listFiles();
			String tok = Sys_Json.getBot(botFolder.getAbsolutePath()+"/"+botFiles[0].getName()).getToken();
			Bot.startBot(tok, 0);
			Sys_Util.println("<Start> - One bot found, launching.");
		}else if(botFolder.listFiles().length > 1) {
			Sys_Util.println("<Start> - More than one bot found, launching bot list window.");
			Gui_BotListV2.main(0);
			Sys_Splash.remSplash();
		}


	}

	/**
	 * Restarts the tool by stopping the bot, then closing all windows before reopening them.
	 */
	public static void restart() {
		Bot.stopBot();
		Gui_Main.frmDES.dispose();
		if(!Sys_Util.openedWindows.isEmpty()) {
			for(int i = 0; i < Sys_Util.openedWindows.size(); i++) {
				switch(Sys_Util.openedWindows.get(i)) {
				case "FieldEdit" : Gui_FieldEdit.frmFieldEdit.dispose();
				break;
				case "BlankField" : Gui_BlankFieldEdit.frmAddBlankField.dispose();
				break;
				case "FieldType" : Gui_FieldType.frmSelectFieldType.dispose();
				break;
				case "ColorChooser" : Gui_ColorChooser.frmDiscordemsColorChooser.dispose();
				break;
				case "MakeClickable" : Gui_MakeClickable.frmMakeClickable.dispose();
				break;
				case "EmbedFileName" : Gui_MakeClickable.frmMakeClickable.dispose();
				break;
				}
				if(Sys_Util.openedWindows.get(i).equals("BotList")) {
					for(Window f : Gui_BotListV2.getOwnerlessWindows()) {
						if(!f.equals(Gui_Console.frmDiscordemsConsole)) {
							f.dispose();
						}
					}
				}
				if(Sys_Util.openedWindows.get(i).equals("Token")) {
					for(Window f : Gui_Token.getOwnerlessWindows()) {
						if(!f.equals(Gui_Console.frmDiscordemsConsole)) {
							f.dispose();
						}
					}
				}
				Sys_Util.println("<Restart> - Closed window "+Sys_Util.openedWindows.get(i));
			}
			Sys_Util.openedWindows.clear();
		}
		Sys_Start.main(null);
	}

}

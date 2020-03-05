package fr.dreregon.Discord_EMS.bot;

import javax.security.auth.login.LoginException;

import fr.dreregon.Discord_EMS.system.Sys_Splash;
import fr.dreregon.Discord_EMS.system.Sys_Util;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Main;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Network;
import fr.dreregon.Discord_EMS.system.GUI.Gui_TokenError;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.ErrorResponseException;
import net.dv8tion.jda.core.requests.ErrorResponse;

public class Bot {
	private static JDA jda;
	
	/**
	 * Launch the bot with the given token and system status
	 * @param token
	 * @param status
	 */
	public static void startBot(String token, int status) {
		Sys_Splash.main(null);
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(token).build();
		} catch (ErrorResponseException e) {
			//Sys_Util.println(e.getErrorResponse().toString());
			Sys_Util.println("<Bot> - Error : "+e.getMessage());
			
			if(e.getErrorResponse() == ErrorResponse.INVALID_TOKEN ) {
				Gui_TokenError.main(null);
				Sys_Util.println("<Bot> - Invalid Token");
				return;
			}
			
			if(e.getErrorResponse() == ErrorResponse.SERVER_ERROR){
				Gui_Network.main(null);
				Sys_Splash.remSplash();
				Sys_Util.println("<Bot> - Server error");
			}
			
			return;
		} catch (LoginException e1){
			Sys_Util.println("<Bot> - Error : "+e1.getMessage());
			Gui_TokenError.main(null);
			return;
		}
		
		jda.addEventListener(new Object[] {new BotListener()});
		
		
		switch(status) {
		case 0 : Gui_Main.main(false);
		break;
		case 1 : Sys_Util.println("<Bot> - Main_GUI is already open.");
		break;
		}
		
	}
	
	
	/**
	 * Stops the jda bot
	 */
	public static void stopBot() {
		getJDA().shutdownNow();
		Gui_Main.connectedBotName.setBackground(Sys_Util.discordRed);
		Sys_Util.println("<Bot> - Stopped jda bot.");
	}
	
	
	/**
	 * @return Current launched bot's JDA
	 */
	public static JDA getJDA() {
		return jda;
	}
	
	/**
	 * 
	 * @return Current launched bot's name
	 */
	public static String botName() {
		String botName = getJDA().getSelfUser().getName();
		return botName;
		
	}

}

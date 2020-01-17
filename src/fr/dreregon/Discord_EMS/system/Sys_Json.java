package fr.dreregon.Discord_EMS.system;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.dv8tion.jda.core.entities.MessageEmbed.Field;

public class Sys_Json {
	
	/**
	 * Saving a Field obj into json file
	 * @param path
	 * @param field
	 */
	public static void createFieldJson(String path, Field field) {
		ObjectMapper outJson = new ObjectMapper();
		try {
			outJson.writeValue(new File(path), field);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		File f = new File(path);
		f.deleteOnExit();
		
	}
	
	/**
	 * Saving a Sys_Field obj into json file
	 * @param path
	 * @param field
	 */
	public static void createSysFieldJson(String path, Sys_Field field) {
		ObjectMapper outJson = new ObjectMapper();
		try {
			outJson.writeValue(new File(path), field);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		File f = new File(path);
		f.deleteOnExit();
		
	}
	
	/**
	 * Retrieve a Sys_Field obj then return a Field obj with same values 
	 * @param file
	 */
	public static Field getEmbedField(String file) {
		ObjectMapper inJson = new ObjectMapper();
		Sys_Field field = null;
		try {
			field = inJson.readValue(new File(file), Sys_Field.class);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		Field fieldFinal = new Field(field.getName(), field.getValue(), field.isInline());
		
		return fieldFinal;
		
	}

	/**
	 * Saving a Sys_BlankField obj into json file
	 * @param path
	 * @param bField
	 */
	public static void createBFieldJson(String path, Sys_BlankField bField) {
		ObjectMapper outJson = new ObjectMapper();
		try {
			outJson.writeValue(new File(path), bField);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		File f = new File(path);
		f.deleteOnExit();
	}
	
	/**
	 * Retrieve Sys_BlankField obj from json file
	 * @param file
	 */
	public static Sys_BlankField getBlankField(String file) {
		ObjectMapper inJson = new ObjectMapper();
		Sys_BlankField bfield = null;
		try {
			bfield = inJson.readValue(new File(file), Sys_BlankField.class);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		return bfield;
		
	}
	
	/**
	 * Saving a Sys_Embed object into json file
	 * @param path
	 * @param builder2
	 */
	public static void createEmbedJson(String path, Sys_Embed builder2) {
		ObjectMapper outEmbed = new ObjectMapper();
		try {
			outEmbed.writeValue(new File(path), builder2);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		Sys_Message.main("The Embed was saved in the <Discord-EMS/Saved-Embeds> folder.", Sys_MsgType.INFO);
	}
	
	/**
	 * Retrieve Sys_Embed obj from json file
	 * @param path
	 */
	public static Sys_Embed getEmbedJson(String path) {
		ObjectMapper inJson = new ObjectMapper();
		Sys_Embed embed = null;
		try {
			embed = inJson.readValue(new File(path), Sys_Embed.class);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
		return embed;
		
	}
	
	/**
	 * Saving a Sys_Bot object into json file
	 * @param path
	 * @param bot
	 */
	public static void createBotJson(String path, Sys_Bot bot) {
		ObjectMapper out = new ObjectMapper();
		Sys_Bot sbot = bot;
		try {
			out.writeValue(new File(path), sbot);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
		}
	}
	
	/**
	 * @param path
	 * @return {@link Sys_Bot}
	 */
	public static Sys_Bot getBot(String path) {
		ObjectMapper in = new ObjectMapper();
		Sys_Bot bot = null;
		try {
			bot = in.readValue(new File(path), Sys_Bot.class);
		} catch (IOException e) {
			Sys_Util.println(e.getStackTrace());
			System.out.println("çamerde");
		}
		return bot;
	}
	

}

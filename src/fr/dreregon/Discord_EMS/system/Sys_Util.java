package fr.dreregon.Discord_EMS.system;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.dreregon.Discord_EMS.system.GUI.Gui_Console;
import fr.dreregon.Discord_EMS.system.libs.Scalr;
import fr.dreregon.Discord_EMS.system.libs.Scalr.Mode;

public class Sys_Util {
	public final static String json = "json";
	public static ArrayList<String> openedWindows = new ArrayList<>();
	
	public static Color discorddark = new Color(54, 57, 62);
	public static Color blurple = new Color(114, 137, 218);
	public static Color greyple = new Color(153, 170, 181);
	public static Color notblack = new Color(30, 33, 36);
	public static Color discordGreen = new Color(67,181,129);
	public static Color discordRed = new Color(240,71,71);
	public static Color discordYellow = new Color(250,166,26);
	public static Color discordGrey = new Color(153, 170, 181);
    

    /**
     * Get the extension of a file.
     * @return Returns an ImageIcon, or null if the path was invalid.
     * @param File
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Output to standard System.out and to GUI_Console.
     * @param string
     */
    public static void println(String string) {
    	System.out.println(string);
    	Gui_Console.println(string);
    }
    
    public static void printClr(String string, Color color) {
    	System.out.println(string);
    	Gui_Console.printClr(string, color);
    }
    
    public static void printerr(String string) {
    	System.err.println(string);
    	Gui_Console.err(string);
    }
    
    public static void println(int var) {
    	System.out.println(var);
    	Gui_Console.println(""+var);
    }
    
    public static void println(Throwable throwable) {
    	System.out.println(throwable);
    	Gui_Console.println(""+throwable);
    }

	public static void println(StackTraceElement[] stackTrace) {
		System.err.println(stackTrace);
		Gui_Console.err(stackTrace.toString());
	}
	
	public static void downloadPrevImg(URL url, JLabel j) {
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
			e.printStackTrace();
			Sys_Util.println("<Sys_Utils> - Failed to retrieve Img form imgURL >> "+url.toString());
			j.setIcon(null);
			return ;
			
		}
		BufferedImage finalImg = Scalr.resize(bi, Mode.AUTOMATIC, j.getWidth(), j.getHeight());
		j.setIcon(new ImageIcon(finalImg));
		
	}
    
    

   

}

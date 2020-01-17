package fr.dreregon.Discord_EMS.system;

import java.awt.Color;
import java.util.ArrayList;

import fr.dreregon.Discord_EMS.system.GUI.Gui_BlankFieldEdit;
import fr.dreregon.Discord_EMS.system.GUI.Gui_FieldEdit;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Main;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;

public class Sys_LoadEmbed {
	public static void loadEmbed(Sys_Embed embed) {

		String title = null;
		String desc= null;
		String img= null;
		String thumb= null;
		String authorN= null;
		String authorI= null;
		String footerN= null;
		String footerI= null;
		ArrayList<Sys_Field> fields= null;
		ArrayList<Sys_BlankField> bFields= null;
		boolean timeS= false;
		int[] color= null;
		try {
			title = embed.getTitle();
			desc = embed.getDescription();
			img = embed.getImage();
			thumb = embed.getThumbnail();
			authorN = embed.getAuthorName();
			authorI = embed.getAuthorImg();
			footerN = embed.getFooterName();
			footerI = embed.getFooterImg();
			fields = embed.getFieldList();
			bFields = embed.getBlankFList();
			timeS = embed.isTimestamp();
			color = embed.getColor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gui_Main.reset();

		Gui_Main.titleF.setText(title);
		Sys_Util.println("<EmbedLoader> - Loaded title");
		Gui_Main.descArea.setText(desc);
		Sys_Util.println("<EmbedLoader> - Loaded description");

		if(img != null) {
			Gui_Main.imgField.setText(img);
			Sys_Util.println("<EmbedLoader> - Loaded image");
		}
		if(thumb != null) {
			Gui_Main.thmnailField.setText(thumb);
			Sys_Util.println("<EmbedLoader> - Loaded thumbnail");
		}

		if(authorN != null) {
			Gui_Main.chckbxAuthor.setSelected(true);
			Gui_Main.a_nameField.setText(authorN);
			Sys_Util.println("<EmbedLoader> - Loaded author");
		}
		if(authorI != null) {
			Gui_Main.a_URLField.setText(authorI);
			Sys_Util.println("<EmbedLoader> - Loaded author image");
		}

		if(footerN != null) {
			Gui_Main.chckbxFooter.setSelected(true);
			Gui_Main.f_nameField.setText(footerN);
			Gui_Main.f_URLField.setText(footerI);
			Sys_Util.println("<EmbedLoader> - Loaded footer");
		}

		if(fields != null && bFields != null) {
			int fsize = fields.size();
			int fbsize = bFields.size();
			if(fsize+fbsize != 0) {
				Gui_Main.comboField.removeAllItems();
				Gui_Main.comboField.addItem("Select a Field.");
				Gui_Main.fieldList.clear();
				Gui_Main.blankFieldList.clear();

				Sys_Field fi = null;
				Sys_BlankField fb = null;
				int j = 1;
				int name = 1;
				for(int i = 0; i<fsize+fbsize ; i++) {
					if(i<fsize) {
						fi= fields.get(i);
					}
					if(i<fbsize) {
						fb = bFields.get(i);
					}

					if(fi != null) {
						if(fi.getOrder() == j) {
							Sys_Util.println("<EmbedLoader> - Loaded field "+(int)name);
							Gui_Main.fieldList.add(new Field(fi.getName(), fi.getValue(), fi.isInline()));
							//Sys_Json.createSysFieldJson("Discord-EMS/Fields/Field_"+(int)name+".json", fi);
							Gui_Main.comboField.addItem("Field_"+(int)name);
							Gui_FieldEdit.num = name;
							j+=1;
						}
					}

					if(fb != null) {
						if(fb.getOrder() == j) {
							String n = ""+(name);
							Sys_Util.println("<EmbedLoader> - Loaded Blankfield "+n);
							//Sys_Json.createBFieldJson("Discord-EMS/Fields/BlankField_"+n+".json", fb);
							Gui_Main.blankFieldList.add(fb);
							Gui_Main.comboField.addItem("BlankField_"+n);
							Gui_BlankFieldEdit.num1 = name;
							j+=1;
						}
					}
					name+=1;
				}
			}
		}


		Gui_Main.timeStampCheckbx.setSelected(timeS);
		Sys_Util.println("<EmbedLoader> - Time stamp set to [true]");

		Gui_Main.colorPick.setBackground(new Color(color[0], color[1], color[2]));
		Sys_Util.println("<EmbedLoader> - Loaded color ["+color[0]+","+color[1]+","+color[2]+"]");

		Sys_Util.printClr("<EmbedLoader> - Succesfully loaded the embed into the main gui", Sys_Util.discordGreen);
		Sys_Message.main("The Embed was loaded sucessfully.", Sys_MsgType.INFO);



	}
}

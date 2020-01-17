package fr.dreregon.Discord_EMS.system;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed.Field;

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
public class Sys_Builder {

	/**
	 * @param titleF
	 * @param descArea
	 * @param imgField
	 * @param thmnailField
	 * @param a_nameField
	 * @param a_URLField
	 * @param f_nameField
	 * @param f_URLField
	 * @param timeStampCheckbx
	 * @param chckbxAuthor
	 * @param chckbxFooter
	 * @param colorPick
	 * @param comboField
	 * @return Sys_Embed
	 */
	public Sys_Embed buildSysEmbed(JTextField titleF, JTextArea descArea, JTextField imgField, JTextField thmnailField
			,JTextField a_nameField, JTextField a_URLField, JTextField f_nameField, JTextField f_URLField, 
			JCheckBox timeStampCheckbx, JCheckBox chckbxAuthor, JCheckBox chckbxFooter,  JTextPane colorPick, JComboBox<String> comboField
			,ArrayList<Field> fieldList, ArrayList<Sys_BlankField> bfieldList) {

		Sys_Embed builder2 = new Sys_Embed();

		String title_s = "";
		String desc_s = "";
		String img_s = "";
		String thm_s = "";
		String author_s = "";
		String authorImg_s = "";
		String footer_s = "";
		String footerImg_s = "";

		//title check
		if(titleF.getText().equals("")) {

		}else {
			//title = true;
			title_s = titleF.getText();
			builder2.setTitle(title_s);
			Sys_Util.println("<EmbedBuilder> - Added title");
		}

		//desc check
		if(descArea.getText().equals("")) {
		}else {
			//desc = true;
			desc_s = descArea.getText();
			builder2.setDescription(desc_s);
			Sys_Util.println("<EmbedBuilder> - Added description");
		}

		//fields check
		if(comboField.getItemCount() == 1 && comboField.getSelectedItem() == "Select a Field.") {
			Sys_Util.println("<EmbedBuilder> - Skipped fields");
		}else {
			//fields = true;
			ArrayList<Sys_Field> sfieldList = new ArrayList<>();
			ArrayList<Sys_BlankField> bFList = new ArrayList<>();

			int listCombo = comboField.getItemCount();
			for(int i = 0; i<listCombo ; i++) {
				String itemName = comboField.getItemAt(i).toString();
				if(itemName == "Select a Field.") continue;
				if(itemName.startsWith("BlankField")) {
					String fi = comboField.getItemAt(i).toString().replace("BlankField_", "");
					int index = Integer.parseInt(fi);
					Sys_BlankField bf = bfieldList.get(index-1);
					bFList.add(new Sys_BlankField(bf.isInline(), i));
					Sys_Util.println("<EmbedBuilder> - Succesfuly added field "+itemName+", inline : "+bf.isInline() );
				}else if(itemName.startsWith("Field")) {
					String fi = comboField.getItemAt(i).toString().replace("Field_", "");
					int index = Integer.parseInt(fi);
					Field f = fieldList.get(index-1);
					sfieldList.add(new Sys_Field(f.getName(), f.getValue(), f.isInline(), i));
					Sys_Util.println("<EmbedBuilder> - Succesfuly added field "+itemName+", inline : "+f.isInline());
				}
			}
			builder2.setFieldList(sfieldList);
			builder2.setBlankFList(bFList);
		}

		//image check
		if(imgField.getText().equals("")) {
			Sys_Util.println("<EmbedBuilder> - Skipped image");
		}else {
			if(imgField.getText().startsWith("http")) {
				img_s = imgField.getText();
				builder2.setImage(img_s);
				Sys_Util.println("<EmbedBuilder> - Added image");
			}else {
				Sys_Util.println("<EmbedBuilder> - Missing http(s) prefix, skipping image");
			}

		}

		if(thmnailField.getText().equals("")) {
			Sys_Util.println("<EmbedBuilder> - Skipped thumbnail");
		}else {
			if(thmnailField.getText().startsWith("http")) {
				thm_s = thmnailField.getText();
				builder2.setThumbnail(thm_s);
				Sys_Util.println("<EmbedBuilder> - Added thumbnail");
			}else {
				Sys_Util.println("<EmbedBuilder> - Missing http(s) prefix, skipping thumbnail");
			}

		}

		//author check
		if(!chckbxAuthor.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped author");
		}else {
			//author = true;
			if(!a_URLField.getText().equals("") && a_URLField.getText().startsWith("http")) {
				authorImg_s = a_URLField.getText();
			}
			if(!a_nameField.getText().equals("")) {
				author_s = a_nameField.getText();
				if(authorImg_s.equals("")) {
					builder2.setAuthorName(author_s);
					Sys_Util.println("<EmbedBuilder> - Added author");
				}else {
					builder2.setAuthorName(author_s);
					builder2.setAuthorImg(authorImg_s);
					Sys_Util.println("<EmbedBuilder> - Added author with avatar");
				}
			}else {
			}
		}

		//footer
		if(!chckbxFooter.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped footer");
		}else {
			//footer = true;
			if(!f_URLField.getText().equals("") && f_URLField.getText().startsWith("http") && !f_nameField.getText().equals("")) {
				footerImg_s = f_URLField.getText();
				footer_s = f_nameField.getText();
				builder2.setFooterName(footer_s);
				builder2.setFooterImg(footerImg_s);
				Sys_Util.println("<EmbedBuilder> - Added footer");
			}

		}
		//timestamp
		if(!timeStampCheckbx.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped timestamp");
		}else {
			builder2.setTimestamp(true);
			Sys_Util.println("<EmbedBuilder> - Set timestamp to [true]");
		}

		int[] colore = {colorPick.getBackground().getRed(),colorPick.getBackground().getGreen(),colorPick.getBackground().getBlue()};
		builder2.setColor(colore);
		Sys_Util.println("<EmbedBuilder> - Added color ["+colore[0]+","+colore[1]+","+colore[2]+"]");

		return builder2;
	} 


	/**
	 * @param titleF
	 * @param descArea
	 * @param imgField
	 * @param thmnailField
	 * @param a_nameField
	 * @param a_URLField
	 * @param f_nameField
	 * @param f_URLField
	 * @param timeStampCheckbx
	 * @param chckbxAuthor
	 * @param chckbxFooter
	 * @param colorPick
	 * @param comboField
	 * @return EmbedBuilder
	 */
	public EmbedBuilder buildEmbed(JTextField titleF, JTextArea descArea, JTextField imgField, JTextField thmnailField
			,JTextField a_nameField, JTextField a_URLField, JTextField f_nameField, JTextField f_URLField, 
			JCheckBox timeStampCheckbx, JCheckBox chckbxAuthor, JCheckBox chckbxFooter,  JTextPane colorPick, JComboBox<String> comboField, 
			ArrayList<Field> fieldList, ArrayList<Sys_BlankField> bfieldList) {

		EmbedBuilder builder = new EmbedBuilder();

		String title_s = "";
		String desc_s = "";
		String img_s = "";
		String thm_s = "";
		String author_s = "";
		String authorImg_s = "";
		String footer_s = "";
		String footerImg_s = "";

		if(titleF.getText().equals("")) {

		}else {
			title_s = titleF.getText();
			builder.setTitle(title_s);
			Sys_Util.println("<EmbedBuilder> - Added title");
		}

		if(descArea.getText().equals("")) {

		}else {
			desc_s = descArea.getText();
			builder.setDescription(desc_s);
			Sys_Util.println("<EmbedBuilder> - Added description");
		}

		if(comboField.getItemCount() == 1 && comboField.getSelectedItem() == "Select a Field.") {
			Sys_Util.println("<EmbedBuilder> - Skipped fields");
		}else {
			int listCombo = comboField.getItemCount();
			for(int i = 0; i<listCombo ; i++) {
				String itemName = comboField.getItemAt(i).toString();
				if(itemName == "Select a Field.") continue;
				if(itemName.startsWith("BlankField")) {
					String fi = comboField.getItemAt(i).toString().replace("BlankField_", "");
					int index = Integer.parseInt(fi);
					Sys_BlankField bf = bfieldList.get(index-1);
					builder.addBlankField(bf.isInline());
					Sys_Util.println("<EmbedBuilder> - Succesfuly added field "+itemName+", inline : "+bf.isInline() );
				}else if(itemName.startsWith("Field")) {
					String fi = comboField.getItemAt(i).toString().replace("Field_", "");
					int index = Integer.parseInt(fi);
					Field f = fieldList.get(index-1);
					builder.addField(f.getName(), f.getValue(), f.isInline());
					Sys_Util.println("<EmbedBuilder> - Succesfuly added field "+itemName+", inline : "+f.isInline());
				}
			}
		}

		if(imgField.getText().equals("")) {
			Sys_Util.println("<EmbedBuilder> - Skipped image");
		}else {
			if(imgField.getText().startsWith("http")) {
				img_s = imgField.getText();
				builder.setImage(img_s);
				Sys_Util.println("<EmbedBuilder> - Added image");
			}else {
				Sys_Util.println("<EmbedBuilder> - Missing http(s) prefix, skipping image");
			}

		}

		if(thmnailField.getText().equals("")) {
			Sys_Util.println("<EmbedBuilder> - Skipped thumbnail");
		}else {
			if(thmnailField.getText().startsWith("http")) {
				thm_s = thmnailField.getText();
				builder.setThumbnail(thm_s);
				Sys_Util.println("<EmbedBuilder> - Added thumbnail");
			}else {
				Sys_Util.println("<EmbedBuilder> - Missing http(s) prefix, skipping thumbnail");
			}

		}

		if(!chckbxAuthor.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped author");
		}else {
			if(!a_URLField.getText().equals("") && a_URLField.getText().startsWith("http")) {
				authorImg_s = a_URLField.getText();
			}

			if(!a_nameField.getText().equals("")) {
				author_s = a_nameField.getText();
				if(authorImg_s.equals("")) {
					builder.setAuthor(author_s);
					Sys_Util.println("<EmbedBuilder> - Added author");
				}else {
					builder.setAuthor(author_s, null, authorImg_s);
					Sys_Util.println("<EmbedBuilder> - Added author with avatar");
				}
			}else {
			}
		}

		if(!chckbxFooter.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped footer");
		}else {
			if(!f_URLField.getText().equals("") && f_URLField.getText().startsWith("http") && !f_nameField.getText().equals("")) {
				footerImg_s = f_URLField.getText();
				footer_s = f_nameField.getText();
				builder.setFooter(footer_s, footerImg_s);
				Sys_Util.println("<EmbedBuilder> - Added footer");
			}
		}

		if(!timeStampCheckbx.isSelected()) {
			Sys_Util.println("<EmbedBuilder> - Skipped timestamp");
		}else {
			Date date = new Date();
			builder.setTimestamp(date.toInstant());
			Sys_Util.println("<EmbedBuilder> - Added timestamp");
		}

		builder.setColor(colorPick.getBackground());
		Sys_Util.println("<EmbedBuilder> - Added the color "+colorPick.getBackground());
		Sys_Util.println("<EmbedBuilder> - Succesfully built the EmbedMessage");

		return builder;

	}

}

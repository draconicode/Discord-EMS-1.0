package fr.dreregon.Discord_EMS.system;

import java.util.ArrayList;

public class Sys_Embed {
	
	private String title;
	private String description;
	private String image;
	private String thumbnail;
	private String authorName;
	private String authorImg;
	private String footerName;
	private String footerImg;
	private ArrayList<Sys_Field> fieldList;
	private ArrayList<Sys_BlankField> blankFList;
	private boolean timestamp;
	private int[] color;
	
	public Sys_Embed() {}
	
	public Sys_Embed(String title, String desc, String image, String thumbnail, String authorN, 
					 String authorI, String footerN, String footerI, boolean timestamp, 
					 ArrayList<Sys_Field> fields, int[] color, ArrayList<Sys_BlankField> blankFList) {
		this.title = title;
		this.description = desc;
		this.image = image;
		this.thumbnail = thumbnail;
		this.authorName = authorN;
		this.authorImg = authorI;
		this.footerName = footerN;
		this.footerImg = footerI;
		this.timestamp = timestamp;
		this.fieldList = fields;
		this.color = color;
		this.blankFList = blankFList;
	}
	

	public ArrayList<Sys_BlankField> getBlankFList() {
		return blankFList;
	}

	public void setBlankFList(ArrayList<Sys_BlankField> blankFList) {
		this.blankFList = blankFList;
	}



	public int[] getColor() {
		return color;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

	public ArrayList<Sys_Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(ArrayList<Sys_Field> fieldList) {
		this.fieldList = fieldList;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorImg() {
		return authorImg;
	}
	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}
	public String getFooterName() {
		return footerName;
	}
	public void setFooterName(String footerName) {
		this.footerName = footerName;
	}
	public String getFooterImg() {
		return footerImg;
	}
	public void setFooterImg(String footerImg) {
		this.footerImg = footerImg;
	}
	public boolean isTimestamp() {
		return timestamp;
	}
	public void setTimestamp(boolean timestamp) {
		this.timestamp = timestamp;
	}
	
	

}

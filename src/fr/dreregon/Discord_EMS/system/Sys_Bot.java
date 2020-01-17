package fr.dreregon.Discord_EMS.system;

public class Sys_Bot {
	
	private String name;
	private String token;
	private String avatarURL;
	private String id;
	
	public Sys_Bot() {}
	
	public Sys_Bot(String name, String token, String avatarURL, String id) {
		this.name = name;
		this.token = token;
		this.avatarURL = avatarURL;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}
}

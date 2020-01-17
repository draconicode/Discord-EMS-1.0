package fr.dreregon.Discord_EMS.system;

public class Sys_Field {
	
	private String value;
	private String name;
	private boolean inline;
	private int order;
	
	public Sys_Field() {}
	
	public Sys_Field(String name, String value, boolean inline, int order) {
		this.name = name;
		this.value = value;
		this.inline = inline;
		this.order = order;
	}


	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInline() {
		return inline;
	}

	public void setInline(boolean inline) {
		this.inline = inline;
	}


}

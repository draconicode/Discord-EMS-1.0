package fr.dreregon.Discord_EMS.system;

public class Sys_BlankField {
	
	private boolean inline;
	private int order;
	
	public Sys_BlankField() {}
	
	public Sys_BlankField(boolean inline, int order) {
		this.inline =  inline;
		this.order = order;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public boolean isInline() {
		return inline;
	}

	public void setInline(boolean inline) {
		this.inline = inline;
	}
	
	

}

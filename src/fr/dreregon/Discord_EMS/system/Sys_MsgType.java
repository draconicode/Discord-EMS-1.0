package fr.dreregon.Discord_EMS.system;
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
public enum Sys_MsgType {
	
	ERROR(0,"Error", "/fr/dreregon/Discord_EMS/system/media/error_32x.png"),
	INFO(1,"Info", "/fr/dreregon/Discord_EMS/system/media/info_32x.png"),
	WARN(2,"Warning", "/fr/dreregon/Discord_EMS/system/media/warn_32x.png");
	
	private int code;
	private String name;
	private String icon;
	
	Sys_MsgType(int code, String name, String icon) {
		this.code = code;
		this.name = name;
		this.icon = icon;
	}
	
	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getIcon() {
		return icon;
	}

	
	
	

}

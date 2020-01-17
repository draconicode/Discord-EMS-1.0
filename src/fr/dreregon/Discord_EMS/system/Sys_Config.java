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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class Sys_Config {
	private static Properties cfg = new Properties();
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @throws FileNotFoundException
	 */
	public static void saveCfg(String key, String value) throws FileNotFoundException {
		FileOutputStream out = new FileOutputStream("Discord-EMS/DEMS.properties");
		try {
			
			cfg.setProperty(key, value);
			cfg.store(out, null);
			Sys_Util.println("<Config> - Succesfuly saved property <"+value+"> in <"+key+"> key.");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retrieve the value associated with the specified key in the properties file.
	 * @param key
	 * @return Value associated with the key
	 * @throws FileNotFoundException
	 */
	public static String getValue(String key) throws FileNotFoundException {
		String value = "";
		FileInputStream in = new FileInputStream("Discord-EMS/DEMS.properties");
		try {
			cfg.load(in);
			value = cfg.getProperty(key);
			Sys_Util.println("<Config> - Succesfuly retrieved property <"+value+"> in <"+key+"> key.");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}

package fr.dreregon.updater;

import static fr.dreregon.Discord_EMS.system.GUI.Gui_UpdateChecking.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.dreregon.Discord_EMS.system.Sys_Message;
import fr.dreregon.Discord_EMS.system.Sys_MsgType;
import fr.dreregon.Discord_EMS.system.Sys_Start;
import fr.dreregon.Discord_EMS.system.Sys_Util;
import fr.dreregon.Discord_EMS.system.GUI.Gui_Network;

/**
Copyright 2019 Dreregon

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
public class UpdateChecker {
	/**
	 * Checks for an update via retrieving version file from github, and comparing it to internal string 
	 */
	public static void checkForUpdate() {
		if(Gui_Network.offline) {
			Sys_Message.main("You are in offline mode ! You cannot check for updates !", Sys_MsgType.ERROR);
			return;
		}
		
		String currentVersion = Sys_Start.version;
		Sys_Util.println("<Updater> - Checking for new update... Current is "+currentVersion);
		
		//Download and save the version file on github before comparing it to current
		try {
			URL website = new URL("https://raw.githubusercontent.com/Dreregon/Discord-EMS/master/version.txt");
			FileUtils.copyURLToFile(website, new File("version.txt"), 60000, 60000);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		File newVer = new File("version.txt");
		if(newVer.exists()) {
			try {
				List<String> ls = Files.readAllLines(newVer.toPath());
				String newVersion = ls.get(0);
				
				String verNew = newVersion.replace(".", " ").replace("_", " ");
				String verOld = currentVersion.replace(".", " ").replace("_", " ");
				
				String[] n_main = verNew.split(" ");
				
				String[] o_main = verOld.split(" ");

				int n_export = Integer.parseInt(n_main[3]);
				int n_mainVer = Integer.parseInt(n_main[0]);
				int n_featureVer = Integer.parseInt(n_main[1]);
				int n_debugVer = Integer.parseInt(n_main[2]);
				
				int o_export = Integer.parseInt(o_main[3]);
				int o_mainVer = Integer.parseInt(o_main[0]);
				int o_featureVer = Integer.parseInt(o_main[1]);
				int o_debugVer = Integer.parseInt(o_main[2]);
				
				boolean e = false;
				boolean m = false;
				boolean f = false;
				boolean d = false;
				
				if(n_mainVer > o_mainVer) {
					Sys_Util.println("<Updater> - Update found ! Current is "+currentVersion+", New is "+newVersion);
					m = true;
					upNewVer = newVersion;
					updateAvailable(true, newVersion);
					return;
				}else {
					m = false;
				}
				
				if(n_featureVer > o_featureVer) {
					Sys_Util.println("<Updater> - Update found ! Current is "+currentVersion+", New is "+newVersion);
					f = true;
					upNewVer = newVersion;
					updateAvailable(true, newVersion);
					return;
				}else {
					f = false;
				}
				
				if(n_debugVer > o_debugVer && n_featureVer == o_featureVer) {
					Sys_Util.println("<Updater> - Update found ! Current is "+currentVersion+", New is "+newVersion);
					d = true;
					upNewVer = newVersion;
					updateAvailable(true, newVersion);
					return;
				}else {
					d = false;
				}
				
				if(n_export > o_export && n_featureVer == o_featureVer && n_mainVer == o_mainVer && n_debugVer == o_debugVer) {
					Sys_Util.println("<Updater> - Update found ! Current is "+currentVersion+", New is "+newVersion);
					e = true;
					upNewVer = newVersion;
					updateAvailable(true, newVersion);
					return;
				}else {
					e = false;
				}

				if(!e && !d && !f && !m) {
					updateAvailable(false, newVersion);
					resultLabel.setText("No Update available.\n\nLatest : "+newVersion+"\nCurrent : "+Sys_Start.version);
					Sys_Util.println("<Updater> - No update found. (Current is "+currentVersion+", Retrieved is "+newVersion+")");
					newVer.delete();
					return;
				}
			} catch (Exception e) {
				Sys_Message.main("Error : could not check for updates.\n"+e.getMessage(), Sys_MsgType.ERROR);
				Sys_Util.printerr("<Updater> - Couldn't check for updates : "+e.getMessage());
			}
		}
	}
	
	public static void update(String newVer) {
		Runnable target = new Runnable() {
			@Override
			public void run() {
				close();
				File newVerFile = new File("version.txt");
				newVerFile.delete();
				UpdateStatus.main(newVer);
			}
		};
		Thread d = new Thread(target);
		d.start();
	}
}

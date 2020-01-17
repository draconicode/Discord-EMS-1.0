package fr.dreregon.Discord_EMS.system;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Sys_JsonFilter extends FileFilter{
	public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Sys_Util.getExtension(f);
        if (extension != null) {
            if (extension.equals(Sys_Util.json)){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //The description of this filter
    public String getDescription() {
        return "Embed json file";
    }

}

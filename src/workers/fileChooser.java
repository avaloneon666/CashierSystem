/*
Created By: Jorge Avalos
Date: 2/1/2020
Description of Class:
This class is the file manager of the program,  it will allow the user to look for the input file.
 */

package workers;

import javax.swing.*;
import java.io.File;

public class fileChooser {

    //fileOpen method allows the user to select the input file.
    public File fileOpen() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            System.exit(0);
        }

        return null;
    }

    //fileSave method allows the user to both select the save destination of the file as well as name the file.
    public String fileSave() {

        int saveSelection = JOptionPane.showOptionDialog(null, "Please select a File Destination and enter a File Name", "Receipt Maker", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"OK", "Exit"}, "Ok");
        if (saveSelection == 1) {
            System.exit(0);
        } else if (saveSelection == 0) {
            JFileChooser chooser = new JFileChooser();

            int returnVal = chooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                return chooser.getSelectedFile().getAbsolutePath();
            }

        }
        return null;
    }

}

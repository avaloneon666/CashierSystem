package Workers;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    public File fileOpen() {
        String fileSource = "";
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileSource = chooser.getSelectedFile().getAbsolutePath();
        }
        return chooser.getSelectedFile();
    }

    public String fileSave() {
        String fileSource = "";
        JFileChooser chooser = new JFileChooser();

        int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileSource = chooser.getSelectedFile().getAbsolutePath();
        }
        return chooser.getSelectedFile().getAbsolutePath();
    }

}

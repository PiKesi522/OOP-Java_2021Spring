package Lab07;
import java.io.*;

public class Lab07_c_01 {
    public static void ReadFile(File path, int depth) {
        String spath = "";
        // The space before the File
        for (int i = 0; i < depth; i++) {
            spath += "\t\t";
        }
        if (depth != 0)
            spath += "|- ";
            // The prefix of the File


        File[] child = path.listFiles();
        // Get all the Catelogs and Files in this Path 
        if (child == null)
        // If this File is not a Catelog
            return;
            // We don't need to deal with it anymore

        for (File file : child) {
            System.out.println(spath + file.getName() + (file.isDirectory() ? ':' : ""));
            // If this File is a Catelog, then it need a character
            if (file.isDirectory()) {
                ReadFile(file, depth + 1);
                // Deal with the Catelog
            }
        }
    }
    public static void main(String[] args) {
        // File file = new File(args[0]);
        File file = new File("C:\\Java_for_vscode\\restaurant");
        ReadFile(file, 0);
    }
}
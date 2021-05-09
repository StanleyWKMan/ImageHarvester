package util;

import java.io.File;

public class FileUtils {

    public static void createDirectories(String filepath) {
        File directory = new File(filepath);
        if (!directory.exists()) {
            directory.mkdirs();
            System.out.println("Directory Created!");
        } else {
            System.out.println("Directory Existed!");
        }
    }

    public static boolean ifDirectoryExist(String filepath) {
        return false;
    }

}

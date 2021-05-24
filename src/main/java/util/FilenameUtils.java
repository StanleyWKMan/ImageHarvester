package util;

import java.util.ArrayList;
import java.util.List;

public class FilenameUtils {

    public static String buildPath(String rootpath, String filename) {
        return String.format("%s/%s", rootpath, filename);
    }

    public static String buildPath(String rootpath, int part, String extension) {
        return String.format("%s/%d%s", rootpath, part, extension);
    }

    public static String buildPath(String rootpath, String filename, String extension) {
        return String.format("%s/%s%s", rootpath, filename, extension);
    }

    public static String buildPath(String rootpath, String filename, int part, String extension) {
        return String.format("%s/%s/%d%s", rootpath, filename, part, extension);
    }

    public static List<String> buildPaths(String rootpath, String filename, List<String> urls) {
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            String extension = getFileExtension(urls.get(i));
            paths.add(buildPath(rootpath, filename, i, extension));
        }
        return paths;
    }

    public static String getFileExtension(String path) {
        return path.substring(path.lastIndexOf('.'));
    }

}

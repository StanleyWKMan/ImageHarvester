package util;

public class FilenameUtils {

    public static String buildPath(String rootpath, String filename, String extension) {
        return String.format("%s/%s%s", rootpath, filename, extension);
    }

    public static String buildPath(String rootpath, String filename, int part, String extension) {
        return String.format("%s/%s-%d%s", rootpath, filename, part, extension);
    }

    public static String getFileExtension(String path) {
        return path.substring(path.lastIndexOf('.'));
    }

}

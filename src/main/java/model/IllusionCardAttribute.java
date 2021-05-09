package model;

import java.util.Locale;

public enum IllusionCardAttribute {

    ID,
    UPLOAD_DATETIME,
    UPLOADER,
    SIZE,
    SOURCE,
    RATING,
    SCORE,
    IMAGE_URL,
    NONE;

    public static IllusionCardAttribute fromString(String string) {
        string = string.toLowerCase();
        return switch (string) {
            case "id" -> ID;
            case "posted" -> UPLOAD_DATETIME;
            case "by" -> UPLOADER;
            case "size" -> SIZE;
            case "source" -> SOURCE;
            case "rating" -> RATING;
            case "score" -> SCORE;
            default -> NONE;
        };
    }

}

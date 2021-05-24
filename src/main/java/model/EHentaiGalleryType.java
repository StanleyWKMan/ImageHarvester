package model;

public enum EHentaiGalleryType {

    DOUJINSHI,
    MANGA,
    ARTIST_CG,
    GAME_CG,
    WESTERN,
    NON_H,
    IMAGE_SET,
    COSPLAY,
    ASIAN_PORN,
    MISC;

    public static EHentaiGalleryType toType(String type) {
        type = type.toLowerCase();
        return switch (type) {
            case "doujinshi" -> DOUJINSHI;
            case "artist cg" -> ARTIST_CG;
            case "manga" -> MANGA;
            case "game cg" -> GAME_CG;
            case "western" -> WESTERN;
            case "non h" -> NON_H;
            case "image set" -> IMAGE_SET;
            case "cosplay" -> COSPLAY;
            case "asian porn" -> ASIAN_PORN;
            case "misc" -> MISC;
            default -> null;
        };
    }
}

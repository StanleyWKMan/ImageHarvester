package dao;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public class DanbooruGalleryDTO extends AbstractBooruGalleryDTO {

    @BsonProperty(value = "uploader id")
    private String uploaderId;

    @BsonProperty(value = "source url")
    private String sourceUrl;

    @BsonProperty(value = "favourite")
    private int favourite;

    public DanbooruGalleryDTO(ObjectId objectId, String id, String rating, int score, String imageUrl, String uploadDateTime, List<String> tags, int width, int height, String uploaderId, String sourceUrl, int favourite) {
        super(objectId, id, rating, score, imageUrl, uploadDateTime, tags, width, height);
        this.uploaderId = uploaderId;
        this.sourceUrl = sourceUrl;
        this.favourite = favourite;
    }

    public DanbooruGalleryDTO() {
        super();
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public int getFavourite() {
        return favourite;
    }

    public void setFavourite(int favourite) {
        this.favourite = favourite;
    }
}

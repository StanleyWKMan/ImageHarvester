package dao;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class AbstractBooruGalleryDTO {

    private ObjectId objectId;

    @BsonProperty(value = "id")
    private String id;

    @BsonProperty(value = "rating")
    private String rating;

    @BsonProperty(value = "score")
    private int score;

    @BsonProperty(value = "image url")
    private String imageUrl;

    @BsonProperty(value = "upload datetime")
    private String uploadDateTime;

    @BsonProperty(value = "tags")
    private List<String> tags;

    @BsonProperty(value = "image width")
    private int width;

    @BsonProperty(value = "image height")
    private int height;

    public AbstractBooruGalleryDTO(ObjectId objectId, String id, String rating, int score, String imageUrl, String uploadDateTime, List<String> tags, int width, int height) {
        this.objectId = objectId;
        this.id = id;
        this.rating = rating;
        this.score = score;
        this.imageUrl = imageUrl;
        this.uploadDateTime = uploadDateTime;
        this.tags = tags;
        this.width = width;
        this.height = height;
    }

    public AbstractBooruGalleryDTO() {
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(String uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

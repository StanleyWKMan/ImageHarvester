package model.gallery;

import model.Rating;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBooruGallery extends AbstractGallery{

    private String postId;

    private Rating rating;

    private int score;

    private String imageUrl;

    private OffsetDateTime uploadDateTime;

    private List<String> tags;

    private int width;

    private int height;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.setName(postId);
        this.postId = postId;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
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
        this.setImageUrls(new ArrayList<>(Collections.singleton(imageUrl)));
        this.imageUrl = imageUrl;
    }

    public OffsetDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(OffsetDateTime uploadDateTime) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.postId)
                .append("rating", this.rating)
                .append("image url", this.imageUrl)
                .append("image width", this.width)
                .append("image height", this.height)
                .append("score", this.score)
                .append("upload datetime", this.uploadDateTime)
                .toString();
    }
}

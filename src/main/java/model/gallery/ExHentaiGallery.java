package model.gallery;

import model.EHentaiGalleryType;
import model.GalleryType;

import java.time.OffsetDateTime;
import java.util.List;

public class ExHentaiGallery extends AbstractGallery {

    private OffsetDateTime uploadDate;

    private String uploader;

    private int length;

    private List<String> tags;

    private int score;

    private EHentaiGalleryType galleryType;

    public ExHentaiGallery() {
        this.setGalleryType(GalleryType.GALLERY);
    }

    public OffsetDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(OffsetDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public EHentaiGalleryType getEHentaiGalleryType() {
        return galleryType;
    }

    public void setEHentaiGalleryType(EHentaiGalleryType galleryType) {
        this.galleryType = galleryType;
    }
}

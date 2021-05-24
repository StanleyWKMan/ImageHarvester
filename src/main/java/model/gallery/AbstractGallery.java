package model.gallery;

import model.GalleryType;

import java.util.List;
import java.util.UUID;

public abstract class AbstractGallery {

    private List<String> imageUrls;

    private String name;

    private String referrer;

    private GalleryType galleryType;

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public GalleryType getGalleryType() {
        return galleryType;
    }

    public void setGalleryType(GalleryType galleryType) {
        this.galleryType = galleryType;
    }
}

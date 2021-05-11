package model.gallery;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GelbooruGallery extends AbstractBooruGallery {

    private String uploaderId;

    private String sourceUrl;

    private int favorite;

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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("favourite", this.favorite)
                .append("uploader id", this.uploaderId)
                .append("source url", this.sourceUrl)
                .toString();
    }
}

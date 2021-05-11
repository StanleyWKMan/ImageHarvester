package service;

import model.gallery.GelbooruGallery;

import java.util.List;

public interface GelbooruHarvestService extends HarvestService {

    List<GelbooruGallery> getGalleriesFromPosts(List<String> urls);

    GelbooruGallery getGalleryFromPost(String url);

    List<String> getPostLinksFromSearchResult(String url);

    List<String> getSearchResultUrlsFromQueries(String url);

    List<GelbooruGallery> getGalleriesFromQueries(String url);
}

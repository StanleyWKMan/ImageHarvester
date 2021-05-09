package service;

import model.gallery.AbstractBooruGallery;
import model.gallery.IllusionCardGallery;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

public interface IllusionHarvestService extends HarvestService {

    int TIMEOUT = 10000;

    List<String> getPostLinksFromSearchResult(String url);

    List<String> getSearchResultUrlsFromQueries(String url);

    IllusionCardGallery getGalleryFromPost(String url);

    List<IllusionCardGallery> getGalleriesFromPosts(List<String> postLinks);

    AbstractBooruGallery buildGalleryFromDocument(Document document);

    String buildQueryUrl(Map<String, String> urlComponents);

}

package service.impl;

import model.Rating;
import model.gallery.GelbooruGallery;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.GelbooruHarvestService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class GelbooruHarvestServiceImpl implements GelbooruHarvestService {

    @Override
    public List<GelbooruGallery> getGalleriesFromQueries(String url) {
        List<GelbooruGallery> galleries = new ArrayList<>();

        List<String> searchResults = this.getSearchResultUrlsFromQueries(url);
        searchResults.forEach(searchResult -> {
            List<String> posts = this.getPostLinksFromSearchResult(searchResult);
            galleries.addAll(this.getGalleriesFromPosts(posts));
        });

        return galleries;
    }

    @Override
    public List<String> getSearchResultUrlsFromQueries(String url) {
        Set<String> results = new HashSet<>();
        results = this.getAllResultUrls(results, url);
        results.forEach(System.out::println);
        return new ArrayList<>(results);
    }

    @Override
    public List<String> getPostLinksFromSearchResult(String url) {
        Document document = this.getDocument(url);
        Set<String> postLinks = new HashSet<>();
        Elements elements = document.select("article.thumbnail-preview").select("a");
        elements.forEach(element -> {
            postLinks.add(element.absUrl("href"));
            System.out.println(element.absUrl("href"));
        });
        return new ArrayList<>(postLinks);
    }

    @Override
    public GelbooruGallery getGalleryFromPost(String url) {

        Document document = this.getDocument(url);
        System.out.println(url);

        Map<String, String> queryMap = this.splitQuery(url);
        String postId = queryMap.get("id");

        Element tagListElement = document.select("section.aside").select("ul#tag-list").first();
        Elements tagTypeElements = tagListElement.select("li.tag-type-metadata");
        List<String> tags = new ArrayList<>();
        tagTypeElements.forEach(tagElement -> {
            tags.add(tagElement.select("a").get(1).ownText());
        });

        tags.forEach(System.out::println);
        GelbooruGallery gelbooruGallery;
        if (tags.contains("animated") && tags.contains("video") || tags.contains("webm")) {
            gelbooruGallery = this.getVideoFromPost(document);
            gelbooruGallery.setPostId(postId);
        } else {
            gelbooruGallery = this.getImageFromPost(document);
        }

        return gelbooruGallery;
    }

    private GelbooruGallery getImageFromPost(Document document) {

        Element element = document.select("div.mainBodyPadding").select("section").first();
        Element imageElement = document.select("div.mainBodyPadding").select("section").select("picture").first();

        String id = element.attr("data-id");
        List<String> tags = Arrays.stream(element.attr("data-tags").split(" ")).toList();
        Rating rating = Rating.toRating(element.attr("data-rating"));
        int height = Integer.parseInt(element.attr("data-height"));
        int width = Integer.parseInt(element.attr("data-width"));
        String sourceUrl = element.absUrl("data-source");
        String imageUrl = imageElement.select("img").first().absUrl("src");

        GelbooruGallery gelbooruGallery = new GelbooruGallery();
        gelbooruGallery.setImageUrl(imageUrl);
        gelbooruGallery.setPostId(id);
        gelbooruGallery.setTags(tags);
        gelbooruGallery.setRating(rating);
        gelbooruGallery.setWidth(width);
        gelbooruGallery.setHeight(height);
        gelbooruGallery.setSourceUrl(sourceUrl);

        return gelbooruGallery;
    }

    private GelbooruGallery getVideoFromPost(Document document) {
        Element element = document.select("div.mainBodyPadding").select("video").first();
        Element videoElement = document.select("div.mainBodyPadding").select("video").select("source").first();
        String videoUrl = videoElement.absUrl("src");

        GelbooruGallery gelbooruGallery = new GelbooruGallery();

        gelbooruGallery.setImageUrl(videoUrl);

        return gelbooruGallery;
    }

    @Override
    public List<GelbooruGallery> getGalleriesFromPosts(List<String> urls) {

        List<GelbooruGallery> galleries = new ArrayList<>();
        urls.forEach(url -> {
            GelbooruGallery gallery = this.getGalleryFromPost(url);
            galleries.add(gallery);
        });
        return galleries;
    }

    @Override
    public String getNextPage(String url) {
        Document document = this.getDocument(url);
        Elements elements = document.select("div.pagination").select("div#paginator").select("a");

        for (Element element : elements) {
            if (element.attr("alt").equals("next")) {
                return element.absUrl("href");
            }
        }

        return null;
    }

    @Override
    public String buildQueryUrl(Map<String, String> urlComponents) {
        return null;
    }

    private Map<String, String> splitQuery(String url) {
        try {
            URL link = new URL(url);
            String query = link.getQuery();
            String[] queries = query.split("&");
            Map<String, String> queryMap = new HashMap<>();

            Arrays.stream(queries).forEach(queryComponent -> {
                String[] keyValue = queryComponent.split("=");
                queryMap.put(keyValue[0], keyValue[1]);
            });
            return queryMap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

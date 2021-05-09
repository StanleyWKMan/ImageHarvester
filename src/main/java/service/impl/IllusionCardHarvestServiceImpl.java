package service.impl;

import model.IllusionCardAttribute;
import model.Rating;
import model.gallery.IllusionCardGallery;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.IllusionHarvestService;

import java.util.*;

public class IllusionCardHarvestServiceImpl implements IllusionHarvestService {


    @Override
    public List<String> getPostLinksFromSearchResult(String url) {

        Document document = this.getDocument(url);

        Set<String> postLinks = new HashSet<>();
        Elements elements = document.select("span.thumb").select("a");



        elements.forEach(element -> {
            postLinks.add(element.absUrl("href"));
            System.out.println(element.absUrl("href"));
        });

        return new ArrayList<>(postLinks);
    }

    @Override
    public List<String> getSearchResultUrlsFromQueries(String url) {

        Set<String> results = new HashSet<>();
        results = this.getAllResultUrls(results, url);

        results.forEach(System.out::println);

        return new ArrayList<>(results);

    }

    @Override
    public IllusionCardGallery getGalleryFromPost(String url) {

        Document document = this.getDocument(url);
        System.out.println(url);
        Element imageElement = document.select("div#note-container").select("img").first();

        IllusionCardGallery illusionCardGallery = this.buildGalleryFromDocument(url);
        illusionCardGallery.setImageUrl(imageElement.absUrl("src"));

        return illusionCardGallery;
    }

    @Override
    public List<IllusionCardGallery> getGalleriesFromPosts(List<String> postLinks) {

        List<IllusionCardGallery> galleries = new ArrayList<>();
        postLinks.forEach(link -> galleries.add(this.getGalleryFromPost(link)));

        return galleries;
    }

    public IllusionCardGallery buildGalleryFromDocument(String url) {
        return this.buildGalleryFromDocument(this.getDocument(url));
    }

    @Override
    public IllusionCardGallery buildGalleryFromDocument(Document document) {

        Element element = document.select("div#tag_list").select("ul").not("li").first();

        String[] statistic = element.html().split("\n");

        List<String> stats = new ArrayList<>();

        Arrays.stream(statistic).forEach(str -> {
            if (str.startsWith("<li>") || str.startsWith("<br><strong>")) {
//                System.out.println("Ignored: " + str);
            } else {
                stats.add(str.replace("<br>", "").trim());
            }
        });

        Map<IllusionCardAttribute, String> attributeStringMap = new HashMap<>();
        stats.forEach(attribute -> {

            String[] keyValue = attribute.split(":", 2);

            if (keyValue.length > 1)
                attributeStringMap.put(IllusionCardAttribute.fromString(keyValue[0]), keyValue[1].trim());
        });

        return this.buildGalleryFromMap(attributeStringMap);
    }

    private IllusionCardGallery buildGalleryFromMap(Map<IllusionCardAttribute, String> attributeStringMap) {

        IllusionCardGallery gallery = new IllusionCardGallery();

        attributeStringMap.forEach((k,v) -> {
            switch (k) {
                case ID -> gallery.setPostId(v);
                case SIZE -> {
                    String[] size = v.split("x");
                    gallery.setWidth(Integer.parseInt(size[0]));
                    gallery.setHeight(Integer.parseInt(size[1]));
                }
                case SCORE -> gallery.setScore(Integer.parseInt(v));
                case RATING -> gallery.setRating(Rating.toRating(v));
                case UPLOADER -> gallery.setUploader(v);

                //TODO: PARSE OFFSET_DATETIME
//                case UPLOAD_DATETIME -> gallery.setUploadDateTime(v);
            }
        });

        return gallery;
    }

    @Override
    public String buildQueryUrl(Map<String, String> urlComponents) {
        return null;
    }

    @Override
    public String getNextPage(String url) {

        Document document = this.getDocument(url);
        Element element = document.select("div#paginator").select("a[alt=next]").first();
        if (element != null)
            return element.absUrl("href");

        return null;
    }
}

package service.impl;

import model.EHentaiGalleryType;
import model.gallery.ExHentaiGallery;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import service.ExHentaiHarvestService;

import java.util.*;

public class ExHentaiHarvestServiceImpl implements ExHentaiHarvestService {

    List<String> extractedList = new ArrayList<>();

    @Override
    public String getNextPage(String url) {
        return null;
    }

    @Override
    public String buildQueryUrl(Map<String, String> urlComponents) {
        return null;
    }

    public List<String> getPostLinksFromSearchResult(String url) {
        Document document = this.getDocument(url);
        Elements elements = document.select("td.gl3c.glname").select("a");
        List<String> postLinks = new ArrayList<>();
        elements.forEach(element -> {
            postLinks.add(element.absUrl("href"));
        });
        return postLinks;
    }

    public List<ExHentaiGallery> getGalleriesFromPosts(List<String> postUrls) {
        List<ExHentaiGallery> galleries = new ArrayList<>();

        postUrls.forEach(postUrl -> {
            ExHentaiGallery gallery = this.getGalleryFromPost(postUrl);
            if (gallery != null) {
                galleries.add(gallery);
            }
        });
        return galleries;
    }

    public void getLimit() {

    }

    public ExHentaiGallery getGalleryFromPost(String url) {

        Document document = this.getDocument(url);
        String name = document.select("h1#gj").first().ownText();

        System.out.println("Gallery Name: " + name);
        if (extractedList.contains(name)) {
            System.out.println("Existed");
            return null;
        } else {
            extractedList.add(name);
        }
//        EHentaiGalleryType eHentaiGalleryType = EHentaiGalleryType.toType(document.select("div.cs.ct2").first().ownText());

        //TODO Enrich detail
//        Elements galleryInformationElement = document.select("div#gdd").select("td#gdt2");
//        String uploadDate = galleryInformationElement.get(0).ownText();
//        String length = galleryInformationElement.get(5).ownText().split(" ")[0];
//        String favourite = galleryInformationElement.get(6).ownText().split(" ")[0];

        String uploader = document.select("div#gdn").select("a").first().ownText();

        List<String> pageLinks = this.getPagesFromPost(new LinkedHashSet<>(), url);

        List<String> postLinks = new ArrayList<>();
        pageLinks.forEach(link -> {
            postLinks.addAll(this.getPostLinksFromPage(link));
        });

        List<String> imageUrls = new ArrayList<>();
        postLinks.forEach(link -> {
            imageUrls.add(this.getImageUrlFromPostLink(link));
        });


        ExHentaiGallery gallery = new ExHentaiGallery();
        gallery.setName(name);
//        gallery.setEHentaiGalleryType(eHentaiGalleryType);
        gallery.setImageUrls(imageUrls);
//        gallery.setLength(Integer.parseInt(length));
        gallery.setUploader(uploader);
        return gallery;
    }

    public List<String> getPostLinksFromPage(String url) {
        Document document = this.getDocument(url);
        Elements elements = document.select("div#gdt").select("div.gdtm").select("a");
        List<String> postLinks = new ArrayList<>();
        elements.forEach(element -> {
            postLinks.add(element.absUrl("href"));
        });
        return postLinks;
    }

    public List<String> getImageUrlsFromPostLinks(List<String> links) {
        Set<String> urls = new LinkedHashSet<>();
        links.forEach(link -> urls.add(this.getImageUrlFromPostLink(link)));
        return new ArrayList<>(urls);
    }

    public String getImageUrlFromPostLink(String link) {
        Document document = this.getDocument(link);
        String imageUrl = document.select("div#i3").select("img").first().absUrl("src");
        return imageUrl;
    }

    public String getNextPageFromGallery(String url) {
        Document document = this.getDocument(url);
        return document.select("table.ptb").select("a").last().absUrl("href");
    }

    public List<String> getPagesFromPost(Set<String> urls, String url) {
        urls.add(url);
        String nextPage = this.getNextPageFromGallery(url);
        if (!urls.contains(nextPage)) {
            return this.getPagesFromPost(urls, nextPage);
        } else {
            return new ArrayList<>(urls);
        }
    }

}

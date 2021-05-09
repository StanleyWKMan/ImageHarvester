package service.impl;

import dao.DanbooruGalleryDAOImpl;
import dao.DanbooruGalleryDTO;
import model.Rating;
import model.gallery.DanbooruGallery;
import service.DanbooruHarvestService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

public class DanbooruHarvestServiceImpl implements DanbooruHarvestService {

    @Override
    public List<String> getSearchResultUrlsFromQueries(String url) {

        Set<String> results = new HashSet<>();
        results = this.getAllResultUrls(results, url);

        results.forEach(System.out::println);

        return new ArrayList<>(results);
    }

    @Override
    public List<DanbooruGallery> getGalleriesFromSearchResult(String url) {

        DanbooruGalleryDAOImpl daoService = new DanbooruGalleryDAOImpl();

        Document document = this.getDocument(url);
        Elements galleryElements = document.select("div#posts-container").select("article");
        List<DanbooruGallery> galleries = new ArrayList<>();
        galleryElements.forEach(element -> {
            DanbooruGallery gallery = this.buildGalleryFromElement(element);
            galleries.add(gallery);
//            daoService.insertNewGallery(this.getDTOFromGallery(gallery));
        });

        return galleries;
    }

    @Override
    public DanbooruGallery buildGalleryFromElement(Element element) {

        DanbooruGallery gallery = new DanbooruGallery();

        String id = element.attr("data-id");
        String uploaderId = element.attr("data-uploader-id");
        Rating rating = Rating.toRating(element.attr("data-rating"));
        String sourceUrl = element.attr("data-source");
        String imageUrl = element.attr("data-file-url");
        String[] tags = element.attr("data-tags").split(" ");
        int width = Integer.parseInt(element.attr("data-width"));
        int height = Integer.parseInt(element.attr("data-height"));

        gallery.setPostId(id);
        gallery.setUploaderId(uploaderId);
        gallery.setRating(rating);
        gallery.setImageUrl(imageUrl);
        gallery.setSourceUrl(sourceUrl);
        gallery.setTags(Arrays.stream(tags).collect(Collectors.toList()));
        gallery.setWidth(width);
        gallery.setHeight(height);

        return gallery;
    }

    @Override
    public String getNextPage(String url) {
        Document document = this.getDocument(url);
        Element element = document.select("a.paginator-next").first();

        if (element == null) return null;
        else return element.absUrl("href");
    }

    @Override
    public String buildQueryUrl(Map<String, String> urlComponents) {
        return null;
    }

    public List<DanbooruGallery> getGalleriesFromDTOs(List<DanbooruGalleryDTO> dtos) {
        List<DanbooruGallery> results = new ArrayList<>();
        dtos.forEach(dto -> results.add(this.getGalleryFromDTO(dto)));
        return results;
    }

    @Override
    public DanbooruGallery getGalleryFromDTO(DanbooruGalleryDTO dto) {

        DanbooruGallery gallery = new DanbooruGallery();

        gallery.setPostId(dto.getId());
        gallery.setImageUrl(dto.getImageUrl());
        gallery.setRating(Rating.toRating(dto.getRating()));
        gallery.setHeight(dto.getHeight());
        gallery.setWidth(dto.getWidth());
        gallery.setTags(dto.getTags());
        gallery.setFavorite(dto.getFavourite());
        gallery.setScore(dto.getScore());
        gallery.setUploaderId(dto.getUploaderId());
        gallery.setUploadDateTime(null);
        gallery.setSourceUrl(dto.getSourceUrl());

        return gallery;
    }

    public List<DanbooruGalleryDTO> getDTOsFromGalleries(List<DanbooruGallery> galleries) {
        List<DanbooruGalleryDTO> dtos = new ArrayList<>();
        galleries.forEach(gallery -> dtos.add(this.getDTOFromGallery(gallery)));
        return dtos;
    }

    public DanbooruGalleryDTO getDTOFromGallery(DanbooruGallery gallery) {
        DanbooruGalleryDTO dto = new DanbooruGalleryDTO();

        dto.setId(gallery.getPostId());
        dto.setImageUrl(gallery.getImageUrl());
        dto.setRating(gallery.getRating().toString());
        dto.setHeight(gallery.getHeight());
        dto.setWidth(gallery.getWidth());
        dto.setTags(gallery.getTags());
        dto.setFavourite(gallery.getFavorite());
        dto.setScore(gallery.getScore());
        dto.setUploaderId(gallery.getUploaderId());
//        dto.setUploadDateTime(gallery.getUploadDateTime().toString());
        dto.setSourceUrl(gallery.getSourceUrl());

        return dto;
    }

}

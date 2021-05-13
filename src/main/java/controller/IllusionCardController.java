package controller;

import model.gallery.DanbooruGallery;
import model.gallery.IllusionCardGallery;
import service.DanbooruHarvestService;
import service.DownloadService;
import service.IllusionHarvestService;
import service.impl.DanbooruHarvestServiceImpl;
import service.impl.DownloadServiceImpl;
import service.impl.IllusionCardHarvestServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class IllusionCardController {

    private DownloadService downloadService;

    private IllusionHarvestService harvestService;

    public void setDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public void setHarvestService(IllusionHarvestService harvestService) {
        this.harvestService = harvestService;
    }

    public IllusionCardController() {
        this.downloadService = new DownloadServiceImpl();
        this.harvestService = new IllusionCardHarvestServiceImpl();
    }

    public void downloadFromQuery(String queryUrl) {

        List<IllusionCardGallery> galleries = new ArrayList<>();
        List<String> searchResults = this.harvestService.getSearchResultUrlsFromQueries(queryUrl);

        searchResults.forEach(result -> {
            List<String> postLinks = this.harvestService.getPostLinksFromSearchResult(result);
            galleries.addAll(this.harvestService.getGalleriesFromPosts(postLinks));
        });

        downloadService.downloadFromGalleries(galleries);

    }

}

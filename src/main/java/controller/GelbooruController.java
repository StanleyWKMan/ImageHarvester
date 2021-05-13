package controller;

import model.gallery.DanbooruGallery;
import model.gallery.GelbooruGallery;
import service.DanbooruHarvestService;
import service.DownloadService;
import service.GelbooruHarvestService;
import service.impl.DanbooruHarvestServiceImpl;
import service.impl.DownloadServiceImpl;
import service.impl.GelbooruHarvestServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class GelbooruController {

    private DownloadService downloadService;

    private GelbooruHarvestService harvestService;

    public void setDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public void setHarvestService(GelbooruHarvestService harvestService) {
        this.harvestService = harvestService;
    }

    public GelbooruController() {
        this.downloadService = new DownloadServiceImpl();
        this.harvestService = new GelbooruHarvestServiceImpl();
    }

    public void downloadFromQuery(String queryUrl) {
        List<GelbooruGallery> galleries = new ArrayList<>();
        List<String> urls = this.harvestService.getSearchResultUrlsFromQueries(queryUrl);
        urls.forEach(url -> galleries.addAll(this.harvestService.getGalleriesFromQueries(url)));
        this.downloadService.downloadFromGalleries(galleries);
    }

}

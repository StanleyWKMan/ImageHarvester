package controller;

import model.gallery.DanbooruGallery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import service.DanbooruHarvestService;
import service.DownloadService;
import service.impl.DanbooruHarvestServiceImpl;
import service.impl.DownloadServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DanbooruController {

    private DownloadService downloadService;

    private DanbooruHarvestService harvestService;

    Logger logger = LoggerFactory.getLogger(DanbooruController.class);

    public void setDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    public void setHarvestService(DanbooruHarvestService harvestService) {
        this.harvestService = harvestService;
    }

    public void downloadFromQuery(String queryUrl) {

        List<DanbooruGallery> galleries = new ArrayList<>();
        List<String> urls = this.harvestService.getSearchResultUrlsFromQueries(queryUrl);
        urls.forEach(url -> galleries.addAll(this.harvestService.getGalleriesFromSearchResult(url)));
        this.downloadService.downloadFromGalleries(galleries);
    }

    public String greeting() {
        return this.harvestService.getNextPage("https://danbooru.donmai.us/");
    }
}

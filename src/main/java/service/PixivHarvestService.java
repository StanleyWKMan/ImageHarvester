package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface PixivHarvestService extends HarvestService{

    @Override
    default Document getDocument(String url) {

        try {
            return Jsoup.connect(url)
                    .referrer("https://www.pixiv.net/artworks/83024343")
                    .ignoreContentType(true)
                    .timeout(HarvestService.TIMEOUT)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return HarvestService.super.getDocument(url);
    }

}

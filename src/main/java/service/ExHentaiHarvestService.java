package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface ExHentaiHarvestService extends HarvestService {

    @Override
    default Document getDocument(String url) {

        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36";

        Map<String, String> cookies = new HashMap<>();

        try {
            return Jsoup.connect(url).timeout(TIMEOUT)
                    .userAgent(userAgent)
                    .cookies(cookies)
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

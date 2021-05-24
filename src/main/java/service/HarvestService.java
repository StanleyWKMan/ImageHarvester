package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface HarvestService {

    int TIMEOUT = 100000;

    String getNextPage(String url);

    String buildQueryUrl(Map<String, String> urlComponents);

    default void login(String username, String password) {
        System.out.println("Login not Available");
    }

    default Document getDocument(String url) {

        try {
            return Jsoup.connect(url).timeout(TIMEOUT).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    default Set<String> getAllResultUrls(Set<String> urls, String url) {
        urls.add(url);

        String nextPage = this.getNextPage(url);
        if (nextPage != null) {
            return this.getAllResultUrls(urls, nextPage);
        } else {
            return urls;
        }
    }
}

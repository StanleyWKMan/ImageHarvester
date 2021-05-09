package service.impl;

import service.PixivHarvestService;

import java.util.Map;
import java.util.Set;

public class PixivHarvestServiceImpl implements PixivHarvestService {

    @Override
    public String getNextPage(String url) {
        return null;
    }

    @Override
    public String buildQueryUrl(Map<String, String> urlComponents) {
        return null;
    }

    @Override
    public Set<String> getAllResultUrls(Set<String> urls, String url) {
        return PixivHarvestService.super.getAllResultUrls(urls, url);
    }
}

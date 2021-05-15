package service;

import model.gallery.AbstractGallery;

import java.util.List;

public interface DownloadService {

    void downloadFromUrl(String url, String filename);

    void setRootpath(String rootPath);

    void downloadFromGalleries(List<? extends AbstractGallery> galleries);
}

package service.impl;

import model.DownloadHelper;
import model.GalleryType;
import model.gallery.AbstractGallery;
import org.springframework.stereotype.Component;
import service.DownloadService;
import util.FileUtils;
import util.FilenameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DownloadServiceImpl implements DownloadService {

    private String rootpath = "G:/test/ex";

    @Override
    public void setRootpath(String rootpath) {
        this.rootpath = rootpath;
    }

    @Override
    public void downloadFromGalleries(List<? extends AbstractGallery> galleries) {
        if (galleries.get(0).getGalleryType() == GalleryType.GALLERY) {
            this.downloadGalleries(galleries);
        } else {
            this.downloadImage(galleries);
        }
    }

    public void downloadImage(List<? extends AbstractGallery> galleries) {
        FileUtils.createDirectories(rootpath);
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            galleries.forEach(gallery -> {
                String imageUrl = gallery.getImageUrls().get(0);
                String filepath = FilenameUtils.buildPath(this.rootpath,
                        gallery.getName(),
                        FilenameUtils.getFileExtension(imageUrl));
                executorService.submit(new DownloadHelper(filepath, imageUrl));
            });
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void downloadGalleries(List<? extends AbstractGallery> galleries) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            for (AbstractGallery gallery : galleries) {
                String galleryName = gallery.getName().replaceAll("[\\\\/:*?\"<>|]", "-");
                String folderPath = FilenameUtils.buildPath(this.rootpath, galleryName);
                FileUtils.createDirectories(folderPath);
                int part = 1;
                for (String url : gallery.getImageUrls()) {
                    String filepath = FilenameUtils.buildPath(folderPath, part, FilenameUtils.getFileExtension(url));
                    executorService.submit(new DownloadHelper(filepath, url));
                    part++;
                }
                reDownload();
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static List<DownloadHelper> reDownloadList = new ArrayList<>();

    public static void reDownload() {
        reDownloadList.forEach(DownloadHelper::run);
        reDownloadList.clear();
    }

    public static void addReDownloadList(DownloadHelper downloadHelper) {
        reDownloadList.add(downloadHelper);
    }

    @Override
    public void downloadFromUrl(String url, String filename) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }
}

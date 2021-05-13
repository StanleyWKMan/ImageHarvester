package service.impl;

import model.DownloadHelper;
import model.gallery.AbstractGallery;
import org.springframework.stereotype.Component;
import service.DownloadService;
import util.FileUtils;
import util.FilenameUtils;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component("DownloadService")
public class DownloadServiceImpl implements DownloadService {

    private String rootpath = "G:/DLbackup/koikatsu/anime/onepiece";

    @Override
    public void setRootPath(String rootpath) {
        this.rootpath = rootpath;
    }

    @Override
    public void downloadFromGalleries(List<? extends AbstractGallery> galleries) {
        FileUtils.createDirectories(rootpath);
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            galleries.forEach(gallery -> {
                int part = 0;

                if (gallery.getImageUrls().size() > 1) {
                    gallery.getImageUrls().forEach(imageUrl -> {
                        String filepath = FilenameUtils.buildPath(this.rootpath,
                                gallery.getName(),
                                part,
                                FilenameUtils.getFileExtension(imageUrl));
                        executorService.submit(new DownloadHelper(filepath, imageUrl, gallery.getReferrer()));
                    });
                } else {
                    String imageUrl = gallery.getImageUrls().get(0);
                    String filepath = FilenameUtils.buildPath(this.rootpath,
                            gallery.getName(),
                            FilenameUtils.getFileExtension(imageUrl));
                    executorService.submit(new DownloadHelper(filepath, imageUrl, gallery.getReferrer()));
                }
            });
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFromUrl(String url, String filename) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

    }
}

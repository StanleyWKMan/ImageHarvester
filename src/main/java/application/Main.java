package application;

import model.gallery.DanbooruGallery;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.HarvestService;
import service.PixivHarvestService;
import service.impl.DanbooruHarvestServiceImpl;
import service.impl.DownloadServiceImpl;
import service.impl.IllusionCardHarvestServiceImpl;
import service.impl.PixivHarvestServiceImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {

        DownloadServiceImpl downloadService = new DownloadServiceImpl();
        DanbooruHarvestServiceImpl service = new DanbooruHarvestServiceImpl();

        IllusionCardHarvestServiceImpl illusionCardHarvestService = new IllusionCardHarvestServiceImpl();

        String rootpath = "G:/DLbackup/ImageHarvester/honey select 2/female/Pokemon";
        String rootpath2 = "G:/DLbackup/Danbooru/spread pussy";
        downloadService.setRootpath(rootpath2);


//        List<String> searchResults = illusionCardHarvestService
//                .getSearchResultUrlsFromQueries("https://illusioncards.booru.org/index.php?page=post&s=list&tags=studio_neo+honey_select_2");

//        searchResults.forEach(result -> {
//            List<String> postLinks = illusionCardHarvestService.getPostLinksFromSearchResult(result);
//            List<IllusionCardGallery> galleries = illusionCardHarvestService.getGalleriesFromPosts(postLinks);
//            downloadService.downloadFromGalleries(galleries);
//        });


//        DanbooruGalleryDAOImpl dao = new DanbooruGalleryDAOImpl();
//        dao.getAllGalleries();

        List<DanbooruGallery> galleries = new ArrayList<>();

        List<String> urls = service.getSearchResultUrlsFromQueries("https://danbooru.donmai.us/posts?tags=spread_pussy+age%3A40weeks...50weeks");

        urls.forEach(url -> {
            galleries.addAll(service.getGalleriesFromSearchResult(url));
            galleries.forEach(gallery -> downloadService.downloadFromUrl(gallery.getImageUrl(), gallery.getPostId()));
        });

        downloadService.downloadFromGalleries(galleries);

//        SpringApplication.run(Main.class);
    }

    static void test() {
        PixivHarvestService pixivHarvestService = new PixivHarvestServiceImpl();
        pixivHarvestService.getDocument("https://i.pximg.net/img-original/img/2020/07/17/09/52/14/83024343_p8.png");

        try {

            HttpURLConnection repsonse = (HttpURLConnection) new URL("https://i.pximg.net/img-original/img/2020/07/17/09/52/14/83024343_p8.png").openConnection();
            repsonse.setRequestMethod("GET");
            repsonse.setRequestProperty("referer", "https://www.pixiv.net/");

            repsonse.setConnectTimeout(10000);
            repsonse.setReadTimeout(10000);
            repsonse.connect();

            InputStream inputStream = repsonse.getInputStream();

            String filepath = "";

            System.out.println("Downloading: " + filepath);
            ReadableByteChannel readChannel = Channels.newChannel(inputStream);
            FileOutputStream fileOS = new FileOutputStream(filepath);
            FileChannel writeChannel = fileOS.getChannel();

            writeChannel
                    .transferFrom(readChannel, 0, Long.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

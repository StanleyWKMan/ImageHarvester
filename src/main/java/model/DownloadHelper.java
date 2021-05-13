package model;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class DownloadHelper implements Runnable {

    private final String filepath;

    private final String sourceUrl;

    private final String referrer;

    public DownloadHelper(String filepath, String sourceUrl, String referrer) {
        this.filepath = filepath;
        this.sourceUrl = sourceUrl;
        this.referrer = referrer;
    }

    @Override
    public void run() {
        try {

            HttpURLConnection repsonse = (HttpURLConnection) new URL(sourceUrl).openConnection();
            repsonse.setRequestMethod("GET");

            repsonse.setConnectTimeout(10000);
            repsonse.setReadTimeout(10000);
            repsonse.connect();

            InputStream inputStream = repsonse.getInputStream();

            System.out.println("Downloading: " + filepath);
            ReadableByteChannel readChannel = Channels.newChannel(inputStream);
            FileOutputStream fileOS = new FileOutputStream(this.filepath);
            FileChannel writeChannel = fileOS.getChannel();
            writeChannel
                    .transferFrom(readChannel, 0, Long.MAX_VALUE);
            repsonse.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

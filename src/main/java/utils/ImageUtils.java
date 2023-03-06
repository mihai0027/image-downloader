package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ImageUtils {

  public static List<String> getAnImage(String strURL, String filePath) throws IOException {

    List<String> listOfUrls = new ArrayList<>();

    Document document = Jsoup
        .connect(strURL)
        .userAgent("Mozilla/5.0")
        .timeout(10 * 1000)
        .get();

    // select img tags in png and jpg/jpeg format
    Elements imageElements = document.select("img[src~=(?i)\\.(png|jpe?g)]");

    ExecutorService executorService = Executors.newCachedThreadPool();

    // iterate over each image
    for (Element imageElement : imageElements) {

      String strImageURL = imageElement.attr("abs:src");
      listOfUrls.add(strImageURL);
      executorService.submit(new DownloadImageTask(strImageURL, filePath));

    }

    executorService.shutdown();

    return listOfUrls;

  }

  public static void downloadImage(String strImageURL, String filePath) {

    String strImageName = strImageURL.substring(strImageURL.lastIndexOf("/") + 1);

    try {

      URL urlImage = new URL(strImageURL);
      InputStream in = urlImage.openStream();

      byte[] buffer = new byte[4096];
      int n = -1;

      OutputStream os = new FileOutputStream(filePath + File.separator + strImageName);

      while ((n = in.read(buffer)) != -1) {
        os.write(buffer, 0, n);
      }

      os.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}

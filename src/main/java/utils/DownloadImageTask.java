package utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DownloadImageTask implements Runnable {

  private String strImageURL;
  private String filePath;

  @Override
  public void run() {
    ImageUtils.downloadImage(strImageURL, filePath);
  }

}

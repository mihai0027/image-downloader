package mp.imagedownloader.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mp.imagedownloader.models.Image;
import mp.imagedownloader.repositories.ImageRepository;
import utils.ImageUtils;

@Service
public class ImageServiceImpl implements ImageServices {

  @Autowired
  private ImageRepository repo;

  @Override
  public Long saveAllImages(Image image) {

    List<String> listOfUrls = new ArrayList<>();
    List<Image> listOfImages = new ArrayList<>();

    try {

      listOfUrls = ImageUtils.getAnImage(image.getStrURL(), image.getFilePath());

      // Check if provided file path is a valid one
      File fPath = new File(image.getFilePath());
      if (fPath.exists()) {
        for (int i = 0; i < listOfUrls.size(); i++) {
          listOfImages.add(new Image(listOfUrls.get(i), image.getFilePath() + File.separator
              + listOfUrls.get(i).substring(listOfUrls.get(i).lastIndexOf("/") + 1)));
        }

        repo.saveAll(listOfImages);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return (long) repo.saveAll(listOfImages).size();

  }

}

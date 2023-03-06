package mp.imagedownloader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mp.imagedownloader.models.Image;
import mp.imagedownloader.services.ImageServiceImpl;

@RestController
public class ImageController {

  @Autowired
  private ImageServiceImpl service;

  @PostMapping("/download")
  public ResponseEntity<String> downloadImages(@RequestBody Image image) {
    Long totalImages = service.saveAllImages(image);
    return totalImages == 0
        ? new ResponseEntity<>("Bad File Path. Please insert a valid File Path!", HttpStatus.BAD_REQUEST)
        : new ResponseEntity<>("Were saved in database a total of " + (totalImages) + " image(s)",
            HttpStatus.CREATED);

  }

}

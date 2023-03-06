package mp.imagedownloader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mp.imagedownloader.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}

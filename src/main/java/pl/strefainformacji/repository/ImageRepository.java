package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

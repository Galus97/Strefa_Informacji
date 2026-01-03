 package pl.strefainformacji.service;

 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Service;
 import pl.strefainformacji.repository.ImageRepository;

 @Service
 @RequiredArgsConstructor
public class ImageService {
     private final ImageRepository imageRepository;
}

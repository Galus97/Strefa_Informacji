 package pl.strefainformacji.service;

 import lombok.RequiredArgsConstructor;
 import org.springframework.stereotype.Service;
 import pl.strefainformacji.component.ErrorMessages;
 import pl.strefainformacji.component.MessageService;
 import pl.strefainformacji.repository.ImageRepository;

 @Service
 @RequiredArgsConstructor
public class ImageService {
     private final ImageRepository imageRepository;
     private final MessageService messageService;

     private void throwIfIdIsInvalid(Long imageId) {
      if(imageId == null || imageId <= 0) {
        throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_IMAGE_ID));
      }
     }
}

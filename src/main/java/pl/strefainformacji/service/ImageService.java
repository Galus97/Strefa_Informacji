package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.entity.Image;
import pl.strefainformacji.exception.ImageNotFoundException;
import pl.strefainformacji.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MessageService messageService;

    public ImageResponse getImageResponse(Long imageId) {
        throwIfIdIsInvalid(imageId);
        return ImageResponse.fromEntity(getImageOrThrowIfNotExist(imageId));
    }

    private void throwIfIdIsInvalid(Long imageId) {
        if (imageId == null || imageId <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_IMAGE_ID));
        }
    }

    private Image getImageOrThrowIfNotExist(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(
                () -> new ImageNotFoundException(messageService.getMessage(ErrorMessages.IMAGE_NOT_FOUND, imageId)));
    }
}

package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.entity.Image;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.exception.ImageNotFoundException;
import pl.strefainformacji.repository.ArticleRepository;
import pl.strefainformacji.repository.ImageRepository;
import pl.strefainformacji.util.ServiceValidator;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MessageService messageService;
    private final ArticleService articleService;
    private final ServiceValidator serviceValidator;

    public ImageResponse getImageResponse(Long imageId) {
        serviceValidator.throwIfIdIsNotValid(imageId, ErrorMessages.INVALID_IMAGE_ID);
        return ImageResponse.fromEntity(getImageOrThrowIfNotExist(imageId));
    }

    public ImageResponse saveImage(ImageRequest imageRequest) {
        serviceValidator.throwIfRequestIsNull(imageRequest, ErrorMessages.IMAGE_REQUEST_IS_NULL);
        return ImageResponse.fromEntity(imageRepository.save(buildImageFromRequest(imageRequest)));
    }

    public void deleteImage(Long imageId) {
        serviceValidator.throwIfIdIsNotValid(imageId, ErrorMessages.INVALID_IMAGE_ID);
        imageRepository.delete(getImageOrThrowIfNotExist(imageId));
    }

    private Image getImageOrThrowIfNotExist(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException(
                        messageService.getMessage(ErrorMessages.IMAGE_NOT_FOUND, imageId)));
    }

    private Image buildImageFromRequest(ImageRequest imageRequest) {
        return Image.builder()
                .srcImg(imageRequest.getSrcImg())
                .altImg(imageRequest.getAltImg())
                .article(articleService.getArticleOrThrowIfNotExist(imageRequest.getArticleId()))
                .build();
    }
}

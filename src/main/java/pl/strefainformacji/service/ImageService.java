package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.entity.Image;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.exception.ImageNotFoundException;
import pl.strefainformacji.repository.ArticleRepository;
import pl.strefainformacji.repository.ImageRepository;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MessageService messageService;
    private final ArticleRepository articleRepository;

    public ImageResponse saveImageResponse(ImageRequest imageRequest) {
        throwIfRequestIsNotValid(imageRequest);
        return ImageResponse.fromEntity(imageRepository.save(buildImageFromRequest(imageRequest)));
    }

    public ImageResponse getImageResponse(Long imageId) {
        throwIfIdIsInvalid(imageId);
        return ImageResponse.fromEntity(getImageOrThrowIfNotExist(imageId));
    }

    private void throwIfIdIsInvalid(Long imageId) {
        if (imageId == null || imageId <= 0) {
            throw new IllegalArgumentException(messageService.getMessage(ErrorMessages.INVALID_IMAGE_ID));
        }
    }

    public void throwIfRequestIsNotValid(ImageRequest imageRequest) {
        if(imageRequest == null) {
            throw new NullPointerException(messageService.getMessage(ErrorMessages.IMAGE_REQUEST_IS_NULL));
        }
    }

    private Image getImageOrThrowIfNotExist(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(
                () -> new ImageNotFoundException(messageService.getMessage(ErrorMessages.IMAGE_NOT_FOUND, imageId)));
    }

    private Image buildImageFromRequest(ImageRequest imageRequest) {
        return Image.builder()
                .srcImg(imageRequest.getSrcImg())
                .altImg(imageRequest.getAltImg())
                .article(articleRepository.findById(imageRequest.getArticleRequest().getArticleId())
                        .orElseThrow(() -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND))))
                .build();
    }
}

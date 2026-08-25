package pl.strefainformacji.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.model.Image;
import pl.strefainformacji.exception.ImageNotFoundException;
import pl.strefainformacji.mapper.ImageMapper;
import pl.strefainformacji.repository.ImageRepository;
import pl.strefainformacji.util.ServiceValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final MessageService messageService;
    private final ArticleService articleService;
    private final ServiceValidator serviceValidator;

    public ImageResponse getImage(Long imageId) {
        serviceValidator.throwIfIdIsNotValid(imageId, ErrorMessages.INVALID_IMAGE_ID);
        return ImageMapper.toImageResponse(getImageOrThrowIfNotExist(imageId));
    }

    public ImageResponse saveImage(ImageRequest imageRequest) {
        serviceValidator.throwIfRequestIsNull(imageRequest, ErrorMessages.IMAGE_REQUEST_IS_NULL);

        Image image = ImageMapper.toImageModel(imageRequest);
        image.setArticle(articleService.getArticleOrThrowIfNotExist(imageRequest.getArticleId()));

        return ImageMapper.toImageResponse(imageRepository.save(image));
    }

    public ImageResponse updateImage(ImageRequest imageRequest) {
        serviceValidator.throwIfRequestIsNull(imageRequest, ErrorMessages.IMAGE_REQUEST_IS_NULL);
        serviceValidator.throwIfIdIsNotValid(imageRequest.getImageId(), ErrorMessages.INVALID_IMAGE_ID);

        Image existingImage = getImageOrThrowIfNotExist(imageRequest.getImageId());
        existingImage.setSrcImg(imageRequest.getSrcImg());
        existingImage.setAltImg(imageRequest.getAltImg());
        existingImage.setArticle(articleService.getArticleOrThrowIfNotExist(imageRequest.getArticleId()));

        return ImageMapper.toImageResponse(imageRepository.save(existingImage));
    }

    public void deleteImage(Long imageId) {
        serviceValidator.throwIfIdIsNotValid(imageId, ErrorMessages.INVALID_IMAGE_ID);
        imageRepository.delete(getImageOrThrowIfNotExist(imageId));
    }

    public List<ImageResponse> getAllImagesByArticle(Long articleId) {
        Article article = articleService.getArticleOrThrowIfNotExist(articleId);
        return imageRepository.findAllByArticle(article)
                .stream()
                .map(ImageMapper::toImageResponse)
                .toList();
    }

    private Image getImageOrThrowIfNotExist(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ImageNotFoundException(
                        messageService.getMessage(ErrorMessages.IMAGE_NOT_FOUND, imageId)));
    }
}

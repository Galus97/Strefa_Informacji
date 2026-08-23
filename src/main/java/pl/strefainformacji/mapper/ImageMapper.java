package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
import pl.strefainformacji.dto.request.ImageRequest;
import pl.strefainformacji.dto.response.ImageResponse;
import pl.strefainformacji.entity.Image;

@Component
public class ImageMapper {

    public static ImageResponse toImageResponse(Image image) {
        return new ImageResponse(
                image.getImageId(),
                image.getSrcImg(),
                image.getAltImg(),
                image.getArticle().getArticleId()
        );
    }

    // Setup Article in service, because there I have access to articleService
    public static Image toImageModel(ImageRequest imageRequest) {
        return Image.builder()
                .srcImg(imageRequest.getSrcImg())
                .altImg(imageRequest.getAltImg())
                .build();
    }
}

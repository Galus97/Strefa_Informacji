package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
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
}

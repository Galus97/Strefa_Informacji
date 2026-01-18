package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.Image;

public record ImageResponse(Long imageId, String srcImg, String altImg, ArticleResponse articleResponse) {

    public static ImageResponse fromEntity(Image image) {
        return new ImageResponse(
                image.getImageId(),
                image.getSrcImg(),
                image.getAltImg(),
                ArticleResponse.fromEntity(image.getArticle())
        );
    }
}

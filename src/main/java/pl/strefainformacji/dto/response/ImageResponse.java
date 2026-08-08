package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.Image;

public record ImageResponse(
        Long imageId,
        String srcImg,
        String altImg,
        Long articleId) {

    public static ImageResponse fromEntity(Image image) {
        return new ImageResponse(
                image.getImageId(),
                image.getSrcImg(),
                image.getAltImg(),
                image.getArticle().getArticleId()
        );
    }
}

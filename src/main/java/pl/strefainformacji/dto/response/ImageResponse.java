package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.Image;

import java.util.List;

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

    public static List<ImageResponse> fromEntityList(List<Image> images) {
        return images.stream()
                .map(ImageResponse::fromEntity)
                .toList();
    }
}

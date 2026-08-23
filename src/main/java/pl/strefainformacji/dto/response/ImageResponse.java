package pl.strefainformacji.dto.response;

public record ImageResponse(
        Long imageId,
        String srcImg,
        String altImg,
        Long articleId) {
}

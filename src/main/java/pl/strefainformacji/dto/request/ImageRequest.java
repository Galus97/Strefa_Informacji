package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImageRequest {
    private Long imageId;
    private String srcImg;
    private String altImg;
    private ArticleRequest articleRequest;
}

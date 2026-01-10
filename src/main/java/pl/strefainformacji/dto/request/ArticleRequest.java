package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleRequest {
    private Long articleId;
    private String title;
    private String shortDescription;
    private String description;
}

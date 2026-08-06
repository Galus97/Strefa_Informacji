package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ArticleRequest {
    private Long articleId;
    private String title;
    private String shortDescription;
    private String description;
    private Boolean isSubscriber;
    private LocalDateTime createdAt;
}

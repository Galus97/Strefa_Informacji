package pl.strefainformacji.dto.request;

import lombok.Builder;
import lombok.Data;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ArticleRequest {
    private String title;
    private String shortDescription;
    private String description;
    private List<Category> categories;
    private List<Tag> tags;
    private LocalDateTime createdAt;
}

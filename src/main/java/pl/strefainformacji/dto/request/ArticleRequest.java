package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ArticleRequest {
    private Long articleId;
    @Size(min = 3)
    private String title;
    @Size(min = 10)
    private String shortDescription;
    @Size(min = 10)
    private String description;
    @NotNull
    private List<Category> categories;
    @NotNull
    private List<Tag> tags;
    private LocalDateTime createdAt;
}

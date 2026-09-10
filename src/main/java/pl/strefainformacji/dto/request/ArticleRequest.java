package pl.strefainformacji.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;

import java.util.List;

@Builder
@Data
public class ArticleRequest {
    private Long articleId;
    @Length(min = 3)
    private String title;
    @Length(min = 10)
    private String shortDescription;
    @Length(min = 10)
    private String description;
    @NotNull
    private List<Category> categories;
    @NotNull
    private List<Tag> tags;
}

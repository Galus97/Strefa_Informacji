package pl.strefainformacji.dto.response;

import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;
import pl.strefainformacji.entity.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        Long articleId,
        String title,
        String shortDescription,
        String description,
        List<Category> categories,
        List<Tag> tags,
        LocalDateTime createdAt) {


    public static List<ArticleResponse> fromEntityList(List<Article> articleList) {
        return articleList.stream()
                .map(ArticleResponse::fromEntity)
                .toList();
    }
}

package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        Long articleId,
        String title,
        String shortDescription,
        String description,
        LocalDateTime createdAt) {

    public static ArticleResponse fromEntity(Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getShortDescription(),
                article.getDescription(),
                article.getCreatedAt());
    }

    public static List<ArticleResponse> fromEntityList(List<Article> articleList) {
        return articleList.stream()
                .map(ArticleResponse::fromEntity)
                .toList();
    }
}

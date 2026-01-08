package pl.strefainformacji.dto.response;

import pl.strefainformacji.entity.Article;

public record ArticleResponse(Long articleId, String title, String shortDescription, String description) {

    public static ArticleResponse fromEntity(Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getShortDescription(),
                article.getDescription());
    }
}

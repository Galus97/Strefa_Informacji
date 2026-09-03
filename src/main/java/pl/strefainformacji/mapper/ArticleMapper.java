package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.model.Article;

@Component
public class ArticleMapper {

    public static ArticleResponse toArticleResponse(Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getShortDescription(),
                article.getDescription(),
                article.getCategories(),
                article.getTags(),
                article.getCreatedAt());
    }

    public static Article toArticleModel(ArticleRequest articleRequest) {
        return Article.builder()
                .title(articleRequest.getTitle())
                .shortDescription(articleRequest.getShortDescription())
                .description(articleRequest.getDescription())
                .categories(articleRequest.getCategories())
                .tags(articleRequest.getTags())
                .build();
    }
}

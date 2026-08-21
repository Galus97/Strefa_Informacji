package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.entity.Article;

import java.util.List;

@Component
public class ArticleMapper {

    public ArticleResponse toArticleResponse(Article article) {
        return new ArticleResponse(article.getArticleId(),
                article.getTitle(),
                article.getShortDescription(),
                article.getDescription(),
                article.getCategories(),
                article.getTags(),
                article.getCreatedAt());
    }

    public List<ArticleResponse> toArticleResponseList(List<Article> articleList) {
        return articleList.stream()
                .map(this::toArticleResponse)
                .toList();
    }

    public Article toArticleModel(ArticleRequest articleRequest) {
        return Article.builder()
                .title(articleRequest.getTitle())
                .shortDescription(articleRequest.getShortDescription())
                .description(articleRequest.getDescription())
                .categories(articleRequest.getCategories())
                .tags(articleRequest.getTags())
                .createdAt(articleRequest.getCreatedAt())
                .build();
    }
}

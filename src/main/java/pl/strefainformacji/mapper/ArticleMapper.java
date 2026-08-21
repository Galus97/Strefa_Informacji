package pl.strefainformacji.mapper;

import org.springframework.stereotype.Component;
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

}

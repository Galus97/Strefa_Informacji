package pl.strefainformacji.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.repository.ArticleRepository;
import pl.strefainformacji.util.ServiceValidator;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MessageService messageService;
    private final ServiceValidator serviceValidator;

    @Transactional(readOnly = true)
    public ArticleResponse getArticleResponse(Long articleId) {
        serviceValidator.throwIfIdIsNotValid(articleId, ErrorMessages.INVALID_ARTICLE_ID);
        return ArticleResponse.fromEntity(getArticleOrThrowIfNotExist(articleId));
    }

    @Transactional
    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        serviceValidator.throwIfRequestIsNull(articleRequest, ErrorMessages.ARTICLE_REQUEST_IS_NULL);
        return ArticleResponse.fromEntity(buildArticle(articleRequest));
    }

    @Transactional
    public void deleteArticle(Long articleId) {
        serviceValidator.throwIfIdIsNotValid(articleId, ErrorMessages.INVALID_ARTICLE_ID);
        articleRepository.delete(getArticleOrThrowIfNotExist(articleId));
    }

    @Transactional
    public ArticleResponse updateArticle(ArticleRequest articleRequest) {
        serviceValidator.throwIfRequestIsNull(articleRequest, ErrorMessages.ARTICLE_REQUEST_IS_NULL);
        serviceValidator.throwIfIdIsNotValid(articleRequest.getArticleId(), ErrorMessages.INVALID_ARTICLE_ID);

        Article article = getArticleOrThrowIfNotExist(articleRequest.getArticleId());
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        article.setShortDescription(articleRequest.getShortDescription());

        return ArticleResponse.fromEntity(articleRepository.save(article));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getAllArticles() {
        List<ArticleResponse>  articleResponses = new ArrayList<>();

        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> articleResponses.add(ArticleResponse.fromEntity(article)));

        return articleResponses;
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getArticlesByCategory(Category category) {
        return ArticleResponse.fromEntityList(
                articleRepository.findAllArticlesByCategory(category));
    }

    private Article buildArticle(ArticleRequest articleRequest) {
        serviceValidator.throwIfRequestIsNull(articleRequest, ErrorMessages.ARTICLE_REQUEST_IS_NULL);
        return Article.builder()
                .articleId(articleRequest.getArticleId())
                .title(articleRequest.getTitle())
                .shortDescription(articleRequest.getShortDescription())
                .description(articleRequest.getDescription())
                .build();
    }

    // Used in other classes
    public Article getArticleOrThrowIfNotExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND, articleId)));
    }
}

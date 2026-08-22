package pl.strefainformacji.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.component.Tag;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.mapper.ArticleMapper;
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
        return ArticleMapper.toArticleResponse(getArticleOrThrowIfNotExist(articleId));
    }

    @Transactional
    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        serviceValidator.throwIfRequestIsNull(articleRequest, ErrorMessages.ARTICLE_REQUEST_IS_NULL);
        return ArticleMapper.toArticleResponse(ArticleMapper.toArticleModel(articleRequest));
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

        return ArticleMapper.toArticleResponse(articleRepository.save(article));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getAllArticles() {
        List<ArticleResponse>  articleResponses = new ArrayList<>();

        List<Article> articles = articleRepository.findAll();
        articles.forEach(article -> articleResponses.add(ArticleMapper.toArticleResponse(article)));

        return articleResponses;
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getArticlesByCategory(List<Category> categories) {
        return articleRepository.findAllArticlesByCategories(categories)
                .stream()
                .map(ArticleMapper::toArticleResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getArticleByTags(List<Tag> tags) {
        return articleRepository.findArticlesByTags(tags)
                .stream()
                .map(ArticleMapper::toArticleResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getArticlesCreatedAtBetween(String from, String to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PARAMS);
        }

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);

            return articleRepository.findAllByCreatedAtBetween(fromDate, toDate)
                    .stream()
                    .map(ArticleMapper::toArticleResponse)
                    .toList();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_FORMAT_PARAMS);
        }

    }

    @Transactional(readOnly = true)
    public ArticleResponse getArticleByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PARAM);
        }
        return ArticleMapper.toArticleResponse(articleRepository.findByTitleContainingIgnoreCase(title));
    }

    // Used in other classes
    public Article getArticleOrThrowIfNotExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND, articleId)));
    }
}

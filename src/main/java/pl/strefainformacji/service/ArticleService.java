package pl.strefainformacji.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.ErrorMessages;
import pl.strefainformacji.component.MessageService;
import pl.strefainformacji.component.Tag;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.dto.search_param.ArticleSearchParameters;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.exception.ArticleNotFoundException;
import pl.strefainformacji.mapper.ArticleMapper;
import pl.strefainformacji.repository.article.ArticleRepository;
import pl.strefainformacji.repository.article.ArticleSpecificationBuilder;
import pl.strefainformacji.util.ServiceValidator;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MessageService messageService;
    private final ServiceValidator serviceValidator;
    private final ArticleSpecificationBuilder articleSpecificationBuilder;

    @Transactional(readOnly = true)
    public ArticleResponse getArticleResponse(Long articleId) {
        serviceValidator.throwIfIdIsNotValid(articleId, ErrorMessages.INVALID_ARTICLE_ID);
        return ArticleMapper.toArticleResponse(getArticleOrThrowIfNotExist(articleId));
    }

    @Transactional
    public ArticleResponse saveArticle(ArticleRequest articleRequest) {
        serviceValidator.throwIfRequestIsNull(articleRequest, ErrorMessages.ARTICLE_REQUEST_IS_NULL);
        Article article = articleRepository.save(ArticleMapper.toArticleModel(articleRequest));
        return ArticleMapper.toArticleResponse(article);
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
    public List<ArticleResponse> getAllArticles(Pageable pageable) {
         return articleRepository.findAll(pageable)
                 .stream()
                 .map(ArticleMapper::toArticleResponse)
                 .toList();
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> getArticlesByCategory(List<Category> categories, Pageable pageable) {
        return articleRepository.findAllArticlesByCategories(categories, pageable)
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
    public List<ArticleResponse> getArticlesCreatedAtBetween(String from, String to, Pageable pageable) {
        if (from == null || to == null) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PARAMS);
        }

        try {
            LocalDateTime fromDate = LocalDateTime.parse(from);
            LocalDateTime toDate = LocalDateTime.parse(to);

            return articleRepository.findAllByCreatedAtBetween(fromDate, toDate, pageable)
                    .stream()
                    .map(ArticleMapper::toArticleResponse)
                    .toList();
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_FORMAT_PARAMS);
        }
    }

    @Transactional(readOnly = true)
    public ArticleResponse getArticleByTitle(String title, Pageable pageable) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_PARAM);
        }
        return ArticleMapper.toArticleResponse(articleRepository.findByTitleContainingIgnoreCase(title, pageable));
    }

    @Transactional(readOnly = true)
    public List<ArticleResponse> search(ArticleSearchParameters articleSearchParameters) {
        Specification<Article> articleSpecification = articleSpecificationBuilder.build(articleSearchParameters);

        return articleRepository.findAll(articleSpecification)
                .stream()
                .map(ArticleMapper::toArticleResponse)
                .toList();
    }

    // Used in other classes
    public Article getArticleOrThrowIfNotExist(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(
                () -> new ArticleNotFoundException(messageService.getMessage(ErrorMessages.ARTICLE_NOT_FOUND, articleId)));
    }
}

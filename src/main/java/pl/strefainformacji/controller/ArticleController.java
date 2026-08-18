package pl.strefainformacji.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;
import pl.strefainformacji.dto.request.ArticleRequest;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.service.ArticleService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleResponse(id));
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> saveArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse savedArticle = articleService.saveArticle(articleRequest);
        return ResponseEntity.created(URI.create("/article/" + savedArticle.articleId()))
                .body(savedArticle);
    }

    @PutMapping
    public ResponseEntity<ArticleResponse> updateArticle(@Valid @RequestBody ArticleRequest articleRequest) {
        return ResponseEntity.ok(articleService.updateArticle(articleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleResponse> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-categories")
    public ResponseEntity<List<ArticleResponse>> getArticlesByCategories(@RequestParam List<Category> categories) {
        return ResponseEntity.ok(articleService.getArticlesByCategory(categories));
    }

    @GetMapping("/by-tags")
    public ResponseEntity<List<ArticleResponse>> getArticlesByTags(@RequestParam List<Tag> tags) {
        return ResponseEntity.ok(articleService.getArticleByTags(tags));
    }

    @GetMapping("time")
    public ResponseEntity<List<ArticleResponse>> getArticlesByDates(
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to) {
        if (from != null && to != null) {
            return ResponseEntity.ok(articleService.getArticlesCreatedAtBetween(from, to));
        }
        return ResponseEntity.ok(articleService.getAllArticles());
    }
}

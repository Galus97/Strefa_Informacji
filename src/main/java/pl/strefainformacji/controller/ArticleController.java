package pl.strefainformacji.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefainformacji.dto.response.ArticleResponse;
import pl.strefainformacji.service.ArticleService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleResponse(id));
    }
}

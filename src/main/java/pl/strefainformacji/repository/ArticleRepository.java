package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.component.Tag;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllArticlesByCategory(Category category);

    List<Article> findArticlesByTags(List<Tag> tags);

    List<Article> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}

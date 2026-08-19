package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.component.Tag;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllArticlesByCategories(List<Category> categories);

    List<Article> findArticlesByTags(List<Tag> tags);

    @Query("SELECT a FROM Article a WHERE a.createdAt BETWEEN :from AND :to")
    List<Article> findAllByCreatedAtBetween(
            @Param("from")LocalDateTime from,
            @Param("to") LocalDateTime to);

    Article findByTitleContainingIgnoreCase(String title);
}

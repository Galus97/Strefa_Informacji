package pl.strefainformacji.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.entity.Article;
import pl.strefainformacji.component.Tag;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByCategory(Category category);

    List<Article> findArticlesByTag(Tag tag);
}

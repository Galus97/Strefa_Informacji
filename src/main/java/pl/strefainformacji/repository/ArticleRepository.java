package pl.strefainformacji.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {


}

package pl.strefainformacji.repository.article.spec;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.repository.specification.SpecificationProvider;

import java.util.Arrays;
import java.util.List;

@Component
public class CategorySpecificationProvider implements SpecificationProvider<Article> {
    @Override
    public String getKey() {
        return "category";
    }

    @Override
    public Specification<Article> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            if (params == null || params.length == 0) {
                return null;
            }

            List<Category> categoryList = Arrays.stream(params)
                    .map(Category::valueOf)
                    .toList();

            Join<Article, Category> categoriesJoin = root.join("categories");

            query.distinct(true);
            return categoriesJoin.in(categoryList);
        };
    }
}

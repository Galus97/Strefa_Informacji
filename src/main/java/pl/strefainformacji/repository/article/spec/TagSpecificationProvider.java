package pl.strefainformacji.repository.article.spec;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import pl.strefainformacji.component.Tag;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.repository.specification.SpecificationProvider;

import java.util.Arrays;
import java.util.List;

public class TagSpecificationProvider implements SpecificationProvider<Article> {
    @Override
    public String getKey() {
        return "Tag";
    }

    @Override
    public Specification getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            if (params == null || params.length == 0) {
               return null;
            }

            List<Tag> tagList = Arrays.stream(params)
                    .map(Tag::valueOf)
                    .toList();

            Join<Article, Tag> tagsJoin = root.join("tags");

            query.distinct(true);
            return tagsJoin.in(tagList);
        };
    }
}

package pl.strefainformacji.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import pl.strefainformacji.dto.search_param.ArticleSearchParameters;

public interface SpecificationBuilder<T> {
    Specification<T> build(ArticleSearchParameters articleSearchParameters);
}

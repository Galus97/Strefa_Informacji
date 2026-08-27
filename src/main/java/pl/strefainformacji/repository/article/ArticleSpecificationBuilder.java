package pl.strefainformacji.repository.article;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import pl.strefainformacji.dto.search_param.ArticleSearchParameters;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.repository.specification.SpecificationBuilder;
import pl.strefainformacji.repository.specification.SpecificationProvider;
import pl.strefainformacji.repository.specification.SpecificationProviderManager;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleSpecificationBuilder implements SpecificationBuilder<Article> {
    private final SpecificationProviderManager<Article> articleSpecificationProviderManager;


    @Override
    public Specification<Article> build(ArticleSearchParameters articleSearchParameters) {
        Specification<Article> spec = Specification.unrestricted();

        if (articleSearchParameters.categories() != null && articleSearchParameters.categories().length == 0) {
            spec = spec.and(articleSpecificationProviderManager.getSpecification("category")
                    .getSpecification(articleSearchParameters.categories()));
        }
        if (articleSearchParameters.tags() != null && articleSearchParameters.tags().length == 0) {
            spec = spec.and(articleSpecificationProviderManager.getSpecification("tag")
                    .getSpecification(articleSearchParameters.tags()));
        }
        return spec;
    }
}

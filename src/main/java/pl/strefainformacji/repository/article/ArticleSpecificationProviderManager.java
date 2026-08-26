package pl.strefainformacji.repository.article;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.strefainformacji.model.Article;
import pl.strefainformacji.repository.specification.SpecificationProvider;
import pl.strefainformacji.repository.specification.SpecificationProviderManager;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleSpecificationProviderManager implements SpecificationProviderManager<Article> {
    private final List<SpecificationProvider<Article>> articleSpecificationProviders;

    @Override
    public SpecificationProvider<Article> getSpecification(String key) {
        return null;
    }
}

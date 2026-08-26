package pl.strefainformacji.repository.specification;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecification(String key);
}

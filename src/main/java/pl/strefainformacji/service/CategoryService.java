package pl.strefainformacji.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.strefainformacji.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
}

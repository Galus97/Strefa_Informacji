package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

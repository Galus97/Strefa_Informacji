package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import pl.strefainformacji.entity.Tag;

public interface TagRepository extends JpaRepositoryImplementation<Tag, Long> {
}

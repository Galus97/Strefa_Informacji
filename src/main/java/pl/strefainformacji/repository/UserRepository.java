package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.strefainformacji.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

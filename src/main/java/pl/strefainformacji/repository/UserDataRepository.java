package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.strefainformacji.entity.User;
import pl.strefainformacji.entity.UserData;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUser(User user);
}
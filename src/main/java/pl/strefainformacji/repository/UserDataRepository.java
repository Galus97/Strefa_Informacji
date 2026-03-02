package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.strefainformacji.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

}
package pl.strefainformacji.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.strefainformacji.entity.Ad;

public interface  AdRepository extends JpaRepository<Ad, Long>{

}

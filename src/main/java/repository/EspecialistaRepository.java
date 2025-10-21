package repository;

import model.EspecialistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspecialistaRepository extends JpaRepository<EspecialistaModel, Integer> {

}

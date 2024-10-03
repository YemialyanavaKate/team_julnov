package by.ita.je.repository;

import by.ita.je.models.Kettle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KettleJpa extends JpaRepository<Kettle, Integer> {

}

package by.ita.je.repository;

import by.ita.je.models.Kettle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KettleCrudRepository extends CrudRepository<Kettle, Integer> {
}

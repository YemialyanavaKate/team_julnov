package by.ita.je.repository;

import by.ita.je.models.Multicooker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MulticookerCrudRepository extends CrudRepository<Multicooker, Integer> {
}

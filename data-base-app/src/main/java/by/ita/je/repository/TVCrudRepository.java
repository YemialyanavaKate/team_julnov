package by.ita.je.repository;

import by.ita.je.models.TV;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVCrudRepository extends CrudRepository<TV, Integer> {


}
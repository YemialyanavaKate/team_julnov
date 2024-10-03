package by.ita.je.repository;

import by.ita.je.models.Fridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FridgeRepoCrud extends CrudRepository<Fridge, Integer> {

}

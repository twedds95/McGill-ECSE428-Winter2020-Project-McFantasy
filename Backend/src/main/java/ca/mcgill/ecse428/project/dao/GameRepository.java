package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.Game;

public interface GameRepository extends CrudRepository<Game, String> {

}


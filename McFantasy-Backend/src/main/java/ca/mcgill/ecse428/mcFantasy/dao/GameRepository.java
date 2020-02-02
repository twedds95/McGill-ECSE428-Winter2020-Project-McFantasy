package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.Game;

public interface GameRepository extends CrudRepository<Game, String> {

}


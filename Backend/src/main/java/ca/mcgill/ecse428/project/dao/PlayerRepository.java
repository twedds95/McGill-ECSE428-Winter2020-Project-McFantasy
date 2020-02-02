package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {

}


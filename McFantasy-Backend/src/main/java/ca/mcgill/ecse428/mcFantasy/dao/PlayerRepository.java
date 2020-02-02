package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {

}


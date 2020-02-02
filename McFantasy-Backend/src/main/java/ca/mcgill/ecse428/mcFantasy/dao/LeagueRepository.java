package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.League;

public interface LeagueRepository extends CrudRepository<League, String> {

}

package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.League;

public interface LeagueRepository extends CrudRepository<League, String> {

    League findByName(String name);

}

package ca.mcgill.ecse428.project.dao;

import ca.mcgill.ecse428.project.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.League;

import java.util.Set;

public interface LeagueRepository extends CrudRepository<League, String> {
	League findByName(String name);

    Set<League> findByUser(AppUser user);
}

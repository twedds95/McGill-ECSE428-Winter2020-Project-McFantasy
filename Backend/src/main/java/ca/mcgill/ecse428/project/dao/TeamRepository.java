  package ca.mcgill.ecse428.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.Team;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.League;


public interface TeamRepository extends CrudRepository<Team, String> {
        
       Team findByTeamID(Integer teamID);
       Team findByName(String name);
       List<Team> findByLeague (League league);

}

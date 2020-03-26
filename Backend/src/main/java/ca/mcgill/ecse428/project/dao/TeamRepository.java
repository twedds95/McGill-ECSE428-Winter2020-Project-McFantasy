  package ca.mcgill.ecse428.project.dao;

import ca.mcgill.ecse428.project.model.League;
import ca.mcgill.ecse428.project.model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface TeamRepository extends CrudRepository<Team, String> {

       Boolean existsByTeamID(Integer teamID);
       Team findByTeamID(Integer teamID);
       List<Team> findByName(String name);
       List<Team> findByLeague (League league);

}

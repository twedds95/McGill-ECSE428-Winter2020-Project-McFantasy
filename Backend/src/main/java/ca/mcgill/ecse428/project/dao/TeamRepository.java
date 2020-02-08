  package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.Team;
import ca.mcgill.ecse428.project.model.AppUser;

public interface TeamRepository extends CrudRepository<Team, String> {
        
       Team findByTeamID(Integer teamID);
       Team findByName(String name);
       Team findByAppUser(AppUser user);

}

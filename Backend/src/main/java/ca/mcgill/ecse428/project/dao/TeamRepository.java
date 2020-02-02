package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.Team;

public interface TeamRepository extends CrudRepository<Team, String> {

}


package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.Team;

public interface TeamRepository extends CrudRepository<Team, String> {

}


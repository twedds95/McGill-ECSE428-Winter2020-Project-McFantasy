package ca.mcgill.ecse428.project.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.project.model.SeasonStats;

public interface SeasonStatsRepository extends CrudRepository<SeasonStats, String> {

}

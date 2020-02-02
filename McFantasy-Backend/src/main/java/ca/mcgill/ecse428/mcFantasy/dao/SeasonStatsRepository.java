package ca.mcgill.ecse428.mcfantasy.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse428.mcfantasy.model.SeasonStats;

public interface SeasonStatsRepository extends CrudRepository<SeasonStats, String> {

}

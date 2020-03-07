package ca.mcgill.ecse428.project.features;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse428.project.dao.AppUserRepository;
import ca.mcgill.ecse428.project.dao.GameRepository;
import ca.mcgill.ecse428.project.dao.LeagueRepository;
import ca.mcgill.ecse428.project.dao.PlayerRepository;
import ca.mcgill.ecse428.project.dao.SeasonStatsRepository;
import ca.mcgill.ecse428.project.dao.TeamRepository;
import io.cucumber.java.After;

public class CucumberCleanUp {
	
	@Autowired
	private AppUserRepository appUserRepo;
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private LeagueRepository leagueRepo;
	@Autowired
	private PlayerRepository playerRepo;
	@Autowired
	private SeasonStatsRepository seasonStatsRepo;
	@Autowired
	private TeamRepository teamRepo;
	
	/**
	 * Tear Down
	 * 
	 */
	@After
	public void tearDown() {
		appUserRepo.deleteAll();
		leagueRepo.deleteAll();
		gameRepo.deleteAll();
		playerRepo.deleteAll();
		seasonStatsRepo.deleteAll();
		teamRepo.deleteAll();
	}
}

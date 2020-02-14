package ca.mcgill.ecse428.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse428.project.model.*;
import ca.mcgill.ecse428.project.dao.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMcFantasyService {
	@Autowired
	private McFantasyService service;
	
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
	
	@After
	public void clearDatabase() {
		appUserRepo.deleteAll();
		leagueRepo.deleteAll();
		gameRepo.deleteAll();
		playerRepo.deleteAll();
		seasonStatsRepo.deleteAll();
		teamRepo.deleteAll();
	}
	
}

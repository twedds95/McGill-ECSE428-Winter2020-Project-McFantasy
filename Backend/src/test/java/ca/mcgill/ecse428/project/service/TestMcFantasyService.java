package ca.mcgill.ecse428.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Arrays;
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
	
	private static String USER_NAME = "test_user";
	private static String USER_EMAIL = "test@mail.com";
	private static String USER_PASSWORD = "password123";
	private static byte[] USER_PICTURE = {'1'};
	private static String NON_EXISTING_EMAIL = "nonexist@mail.com";
	
	private static String LEAGUE_NAME ="LEAGUE";
	private static String TEAM_NAME ="Real Madrid";
	private static int TEAM_ID = 200; 
	private static int TEAM_PTS = 2;
	private static int TEAM_STANDING = 3;
	private static String TEAM1_NAME ="Arsenal";
	private static int TEAM1_ID = 300;
	private static int TEAM1_PTS = 4;
	private static int TEAM1_STANDING = 2;
	private static String TEAM2_NAME ="Impact";
	private static int TEAM2_ID = 400;
	private static int TEAM2_PTS = 10;
	private static int TEAM2_STANDING = 1;
	
	private static String PLAYER_NAME = "Burak Yilmaz";
	private static String PLAYER_POSITION = "ST";
	
	@After
	public void clearDatabase() {
		appUserRepo.deleteAll();
		leagueRepo.deleteAll();
		gameRepo.deleteAll();
		playerRepo.deleteAll();
		seasonStatsRepo.deleteAll();
		teamRepo.deleteAll();
	}
	
	@Test
	public void testCreateUser() {
		AppUser user = new AppUser();
		try {
			user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(USER_EMAIL, user.getEmail());
	}
	
	@Test
	public void testCreateUserNullEmail() {
		String error = "";
		try {
			service.createUser(null, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error, "User email cannot be empty!");
	}
	
	@Test
	public void testCreateUserExistingEmail() {
		String error = "";
		try {
			service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
			service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error, "User with this email has already been created!");
	}
	
	@Test
	public void testGetUserByExistingName() {
		service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		AppUser user = service.getUser(USER_EMAIL);
		assertEquals(USER_EMAIL, user.getEmail());
	}
	
	@Test
	public void testGetUserByNonExistingName() {
		AppUser user = service.getUser(NON_EXISTING_EMAIL);
		assertNull(user);
	}
	
	@Test
	public void testLogin() {
		service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		AppUser user = service.login(USER_EMAIL, USER_PASSWORD);
		assertEquals(USER_NAME, user.getName());
	}
	
	@Test
	public void failedLogin() {
		String error = "";
		service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		
		try {
			AppUser user = service.login(USER_EMAIL, "ggg");
		}
		catch (NullPointerException e) {
			error = e.getMessage();
		}
		assertEquals("Invalid Login Credidentials!", error);
	}
	@Test
	public void testCreateTeam() {
		assertEquals(0, service.getAllTeams().size());
		AppUser user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		service.createTeam(TEAM_ID, TEAM_NAME, user);
		assertEquals(TEAM_NAME, service.getTeam(TEAM_ID).getName());
	}
	
	@Test
	public void testCreateNullNameTeam() {
		String error = "";
		assertEquals(0, service.getAllTeams().size());
		AppUser user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		try {
			Team t = service.createTeam(TEAM_ID, null, user);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Team name cannot be empty!", error);
	}
	
	@Test
	public void testCreatePlayer() {
		assertEquals(0, service.getAllPlayers().size());
		Player player = service.createPlayer(PLAYER_NAME, PLAYER_POSITION);
		assertEquals(PLAYER_POSITION, service.getPlayer(PLAYER_NAME).getPosition());
		
	}
	
	@Test
	public void testCreateInvalidPlayer() {
		String error = null;
		assertEquals(0, service.getAllPlayers().size());
		try {
			Player player = service.createPlayer("", PLAYER_POSITION);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Player name cannot be empty!", error);
	}
	
	@Test
	public void addPlayersToATeam() {
		Player p1 = service.createPlayer(PLAYER_NAME, PLAYER_POSITION);
		Set<Player> players = new HashSet<Player>();
		players.add(p1);
		AppUser user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		Team team = service.createTeam(TEAM_ID, TEAM_NAME, user);
		service.addPlayer(players, team);
		assertEquals(0, service.getTeam(TEAM_ID).getPlayer().size());
	}
	
	@Test
	public void updateStandings() {
		AppUser user = service.createUser(USER_EMAIL, USER_NAME, USER_PASSWORD, USER_PICTURE);
		League LEAGUE = service.createLeague(LEAGUE_NAME, user);
		Team TEAM = service.createTeam(TEAM_ID, TEAM_NAME, user);
		Team TEAM1 = service.createTeam(TEAM1_ID, TEAM1_NAME, user);
		Team TEAM2 = service.createTeam(TEAM2_ID, TEAM2_NAME, user);
		service.getTeam(TEAM_ID).setPoints(TEAM_PTS);
		service.getTeam(TEAM1_ID).setPoints(TEAM1_PTS);
		service.getTeam(TEAM2_ID).setPoints(TEAM2_PTS);
		service.addTeam(TEAM, LEAGUE);
		service.addTeam(TEAM1, LEAGUE);
		service.addTeam(TEAM2, LEAGUE);
		List<Team> STANDINGS = service.updateStandings(LEAGUE);
		assertEquals(STANDINGS.get(TEAM_STANDING-1).getName(), TEAM.getName());
		assertEquals(STANDINGS.get(TEAM1_STANDING-1).getName(), TEAM1.getName());
		assertEquals(STANDINGS.get(TEAM2_STANDING-1).getName(), TEAM2.getName());
		
	}
	
	
	
	/*
	 * Limitations of testing include: not testing the REST API, and limited testing due to the nature of the update methods
	 * Additionally, this is commented out as it seems that the testing class doesn't have visibility to the other classes
	 @Test
	    public void TestUpdatesAppUser() {// MyClass is tested
	        byte[] bytes;
	        AppUser tester1 = service.createUser("testemail","testname","testpassword",bytes);
			
			try {
				AppUser tester = service.updateUser("","testname2","testpassword2",bytes);
			} catch (IllegalArgumentException e) {
				error = e.getMessage();
				}
	        assertEquals("User email cannot be empty!",error);
	        
	        try {
				AppUser tester = service.updateUser("testemail2","testname2","testpassword2",bytes);
			} catch (IllegalArgumentException e) {
				error1 = e.getMessage();
				}
	        assertEquals("No user with this email exists!",error1);
	    }*/
	
	
}

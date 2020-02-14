package ca.mcgill.ecse428.project.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
	
	private static String USER_NAME = "test_user";
	private static String USER_EMAIL = "test@mail.com";
	private static String USER_PASSWORD = "password123";
	private static byte[] USER_PICTURE = {'1'};
	private static String NON_EXISTING_EMAIL = "nonexist@mail.com";
	
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
		String error = null;
		try {
			service.createUser(null, USER_NAME, USER_PASSWORD, USER_PICTURE);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error, "User email cannot be empty!");
	}
	
	@Test
	public void testCreateUserExistingEmail() {
		String error = null;
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

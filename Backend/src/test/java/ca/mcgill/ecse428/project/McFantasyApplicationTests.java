package ca.mcgill.ecse428.project;

import ca.mcgill.ecse428.project.controller.McFantasyRestController;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.Player;
import ca.mcgill.ecse428.project.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
class McFantasyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void createPlayerTests() throws IOException, SQLException {
		// Create app
		McFantasyRestController tester = new McFantasyRestController();
		// Create player with dummy variables
		String testPlayerName = "CR7";
		String testPlayerPosition = "Striker";
		tester.createPlayer(testPlayerName, testPlayerPosition);

		// assert statements to validate
		assertEquals(tester.getPlayer(testPlayerName).getName(), testPlayerName, "Good name");
		assertEquals(tester.getPlayer(testPlayerName).getPosition(), testPlayerPosition, "Good striker");
	}


	@Test
	public void addingPlayersToTeam() throws IOException, SQLException {
		// Create app
		McFantasyRestController tester = new McFantasyRestController();
		// Create user
		String testEmailAddress = "foo@bar.com";
		String testUserName = "foo bar";
		String testUserPassword = "security";
		MockMultipartFile testPic = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
		AppUser testUser = tester.createUser(testEmailAddress, testUserName, testUserPassword, testPic);
		// Create player with dummy variables
		String testPlayerName = "CR7";
		String testPlayerPosition = "Striker";
		Player testPlayer = tester.createPlayer(testPlayerName, testPlayerPosition);
		// Create team with dummy variables
		Integer testTeamID = 007;
		String testTeamName = "PSG";
		Team testTeam = tester.createTeam(testTeamID, testTeamName, testUser);
		// Add player to team
		tester.addPlayers(Collections.singleton(testPlayer), testTeamID);

		// assert statements
		assertTrue("Set of players in Team contains dummy player", testTeam.getPlayer().contains(testPlayer));
	}

}

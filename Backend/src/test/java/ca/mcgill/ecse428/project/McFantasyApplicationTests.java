package ca.mcgill.ecse428.project;

import ca.mcgill.ecse428.project.controller.McFantasyRestController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class McFantasyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void createPlayerTests() throws IOException, SQLException {
		McFantasyRestController tester = new McFantasyRestController(); // MyClass is tested
		String player_name = "CR7";
		String player_position = "Striker";
		tester.createPlayer(player_name, "Striker");

		// assert statements
		assertEquals(tester.getPlayer(player_name).getName(), player_name, "Good name");
		assertEquals(tester.getPlayer(player_name).getPosition(), player_position, "Good striker");
	}

}

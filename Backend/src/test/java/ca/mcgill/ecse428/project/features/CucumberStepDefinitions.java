package ca.mcgill.ecse428.project.features;

import ca.mcgill.ecse428.project.controller.McFantasyRestController;
import ca.mcgill.ecse428.project.dao.LeagueRepository;
import ca.mcgill.ecse428.project.dao.TeamRepository;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.League;
import ca.mcgill.ecse428.project.model.Team;
import ca.mcgill.ecse428.project.service.McFantasyService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CucumberStepDefinitions {
	
	@Autowired
	private McFantasyRestController api;

	@Autowired
	private McFantasyService service;

	@Autowired
	private LeagueRepository leagueRepo;

	@Autowired
	private TeamRepository teamRepo;


	
	public static String error = "";
	
	public static String name = "bob";
	public static String email = "default@mail.com";
	public static String password = "123abc$";
	public static byte[] picture = {'1'};
	@Given("person {string} with email {string} and password {string}")
	public void person_with_email(String n, String e, String p) {
		name = n;
		email = e;
		password = p;
	}
	
	@When("they request to create a new account on McFantasy Sports")
	public void person_requests_create_account() throws IllegalArgumentException, SerialException, SQLException, IOException {
		api.createUser(email, name, password, null);
	}
	
	@Then("a new user with email {string} and password {string} is generated")
	public void a_new_account_with_email_and_password_is_generated(String email, String password) {
		AppUser user = new AppUser();
		try {
			user = api.getUser(email);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertEquals(email, user.getEmail());
		assertEquals(password, user.getPassword());
	}
	

	@Given("person {string} with email {string} is user of McFantasy Sports")
	public void person_with_email_is_user_of_McFantasy_Sports(String n, String e) {
		name = n;
		email = e;
	}

	@When("person requests to create a new account on McFantasy Sports")
	public void person_requests_to_create_a_new_account_with_username() throws SerialException, SQLException, IOException {
		try {
			api.createUser(email, name, password, null);
			api.createUser(email, name, password, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	}

	@Then("a {string} message is issued")
	public void an_message_is_issued(String string) {
		assertEquals(error, "User with this email has already been created!");
	}

	

	@Given("user with email {string} is logged in")
	public void user_with_email_is_logged_in(String string) throws IOException, SQLException {
		email = string;
	    api.createUser(string, name, password, null);
	}

	@When("the user attempts to change username with username {string}")
	public void the_user_attempts_to_change_username_with_username(String string) throws IOException, SQLException {
	    try {
	    	api.updateUser(email, name, password, null, string, password, null );
		}catch (Exception e){
	    	error = e.getMessage();
		}
	}

	@Then("the user Account with email {string} Username will be set to {string}")
	public void the_user_Account_with_email_Username_will_be_set_to(String string, String string2) {
	    assertEquals(string2, api.getUser(string).getName());
	}

	@Given("there exists a user with email {string} and username {string}")
	public void there_exists_a_user_with_email_and_username(String string, String string2) throws IOException, SQLException {
		api.createUser(string, string2, password, null);
	}

	@Given("the user {string} with current password {string} is logged in")
	public void the_user_with_current_password_is_logged_in(String string, String string2) throws IOException, SQLException {
		api.createUser(string, name, string2, null);
		email = string;
	}

	@Given("they have {int} failed attempts")
	public void they_have_failed_attempts(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		throw new cucumber.api.PendingException();
	}

	@When("the user initiates to change their password with current password {string} and new password {string} and confirmation password {string}")
	public void the_user_initiates_to_change_their_password_with_current_password_and_new_password_and_confirmation_password(String string, String string2, String string3) throws IOException, SQLException {
	    api.updateUser(email, name, string, null, name, string2, null);
	}

	@Then("their password is still {string}")
	public void their_password_is_still(String string) {
	    assertEquals(string, api.getUser(email).getPassword());
	}

	@Then("their failed attempts should increase to {int}")
	public void their_failed_attempts_should_increase_to(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("their account should be locked")
	public void their_account_should_be_locked() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user should be logged off")
	public void the_user_should_be_logged_off() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("a reset password email should be sent to the account holder")
	public void a_reset_password_email_should_be_sent_to_the_account_holder() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("they should receive {string}")
	public void they_should_receive(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("their password will be {string}")
	public void their_password_will_be(String string) {
		assertEquals(string, api.getUser(email).getPassword());
	}

	@Given("person’s email {string} and password {string} is an existing user")
	public void person_s_email_and_password_is_an_existing_user(String string, String string2) throws IOException, SQLException {
		email = string;
		api.createUser(string, name, string2, null);
	}

	@When("a person {string} requests for their account to be deleted with confirmation password {string}")
	public void a_person_requests_for_their_account_to_be_deleted_with_confirmation_password(String string, String string2) throws IOException, SQLException {
	    AppUser user = api.getUser(string);
		try {
	    	api.deleteUser(user, string2);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	}

	@Then("they’re information will be deleted from the database and their account will become invalid")
	public void they_re_information_will_be_deleted_from_the_database_and_their_account_will_become_invalid() {
	    assertNull(service.getUser(email));
	}

	@Then("a record of the attempt is recorded in the database to limit requests.")
	public void a_record_of_the_attempt_is_recorded_in_the_database_to_limit_requests() {

	}

	@Given("a league with {string} exists")
	public void a_league_with_exists(String string) throws IOException, SQLException {
		AppUser user = api.getUser(email);
	    api.createLeague(string, user);
	}

	@Given("user with email {string} has an existing team {string}")
	public void user_with_email_has_an_existing_team(String string, String string2) throws IOException, SQLException {
		AppUser user = api.createUser(string, name, password, null);
		email = string;
		int teamId = 1;
		while (teamRepo.findByTeamID(teamId) != null){
			teamId++;
		}
		api.createTeam(teamId, string2, user);


	}

	@Given("a league with name {string} exists")
	public void a_league_with_name_exists(String string) throws IOException, SQLException {
	    AppUser user = api.getUser(email);
	    api.createLeague(string, user);
	}
	
	@Given("user {string} is logged in")
	public void user_is_logged_in(String string) throws IOException, SQLException {
		api.createUser(string, name, password, null);
		email = string;
	}

	@When("the user inputs a valid league name {string} and clicks Create League button")
	public void the_user_inputs_a_valid_league_name_and_clicks_Create_League_button(String string) throws IOException, SQLException {
	    AppUser user = api.getUser(email);
		api.createLeague(string, user);
	}

	@Then("the new league with name {string} will be created")
	public void the_new_league_with_name_will_be_created(String string) {
	    assertNotNull(leagueRepo.findByName(string));
	}

	@When("the user attemps to join League {string} with their team {string}")
	public void the_user_attemps_to_join_League_with_their_team(String string, String string2) throws IOException, SQLException {
		try {
			Team team = api.getTeam(1);
			api.addTeamToLeague(team, string);
		}catch (Exception e){
			error = e.getMessage();
		}

	}

	@Then("the user's {string} will be added to the league {string}")
	public void the_user_s_will_be_added_to_the_league(String string, String string2) {
	    assertNotNull(api.getLeague(string2).getTeam());
	}

	@Given("a league with name {string} deos not exist")
	public void a_league_with_name_deos_not_exist(String string) {
	    if (leagueRepo.findByName(string) != null){
			League l = leagueRepo.findByName(string);
	    	leagueRepo.delete(l);
		}
	}

	@Then("an error message will appear {string}")
	public void an_error_message_will_appear(String string) {
	    assertEquals(string, error);
	}

	@Given("league {string} has a team {string} in the league")
	public void league_has_a_team_in_the_league(String string, String string2) throws IOException, SQLException {
		League league = leagueRepo.findByName(string);
		List<Team> teamsLeague = teamRepo.findByLeague(league);
		List<Team> teams = teamRepo.findByName(string2);
		boolean found = false;
		for (int i = 0; i < teamsLeague.size(); i++) {
				if (teamsLeague.get(i).getName().equals(string2)){
				found = true;
			}
		}
		if (!found){
			api.addTeamToLeague(teams.get(0), league.getName());
		}
	}

	@When("the user attemps to leave the League {string} with their team {string}")
	public void the_user_attemps_to_leave_the_League_with_their_team(String string, String string2) throws IOException, SQLException {
		League league = leagueRepo.findByName(string);
		List<Team> team = teamRepo.findByName(string2);
		api.leaveLeague(team.get(0), league.getName());
	}

	@Then("the user's {string} will be removed from the league {string}")
	public void the_user_s_will_be_removed_from_the_league(String string, String string2) {
		League league = leagueRepo.findByName(string2);
		List<Team> teams = teamRepo.findByLeague(league);
		boolean found = false;
		for (int i = 0; i < teams.size(); i++) {
			if (teams.get(i).getName().equals(string)){
				found = true;
			}
		}
		assertFalse(found);
	}

	@When("the user attemps to create a new team {string}")
	public void the_user_attemps_to_create_a_new_team(String string) throws IOException, SQLException {
		AppUser user = api.getUser(email);
		try {
			int teamId = 1;
			while (teamRepo.findByTeamID(teamId) != null){
				teamId++;
			}
			api.createTeam(teamId, string, user);
		}catch (Exception e){
			error = e.getMessage();
		}
	}

	@Then("the user's {string} will be created")
	public void the_user_s_will_be_created(String string) {
	    assertNotNull(teamRepo.findByName(string));
	}


	@When("the user inputs an invalid league name {string} and clicks Create League button")
	public void theUserInputsAnInvalidLeagueNameAndClicksCreateLeagueButton(String arg0) {
		try {
			AppUser user = api.getUser(email);
			api.createLeague(arg0, user);
		}catch (Exception e){
			error = e.getMessage();
		}
	}

	@Then("an {string} message is issued")
	public void anMessageIsIssued(String arg0) {
		assertEquals(arg0, error);
	}

	@Given("the league {string} has the following teams")
	public void the_league_has_the_following_teams(String string, io.cucumber.datatable.DataTable dataTable) throws IOException, SQLException {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size();i++) {
			AppUser user = api.createUser(list.get(i).get("userEmail"), list.get(i).get("userName"), password, null);
			int teamId = 1+i;
			while (teamRepo.findByTeamID(teamId) != null){
				teamId++;
			}
			api.createTeam(teamId, list.get(i).get("teamName"), user);
			api.joinLeague(teamId, string, user);
		}
	}

	List<String> teams = new ArrayList<>();
	@When("the user attemps to view the teams in the league {string}")
	public void the_user_attemps_to_view_the_teams_in_the_league(String string) {
		try {
			List<Team> teamsList = api.getTeamsInLeague(string);
			for (Team x : teamsList)
				teams.add(x.getName());
		}
		catch (Exception e){
			error = e.getMessage();
		}
	}

	@Then("the list of teams {string} in the league {string} is shown")
	public void the_list_of_teams_in_the_league_is_shown(String string, String string2) {
		List<String> list = new ArrayList<>();
		String[] ss = string.split(",");
		for (int i = 0; i < ss.length; i++) {
			ss[i] = ss[i].trim();
			list.add(ss[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			if (!teams.contains(list.get(i))){
				fail();
			}
		}
	}

	@Given("the league {string} has the following teams with the the followings statistics")
	public void the_league_has_the_following_teams_with_the_the_followings_statistics(String string, io.cucumber.datatable.DataTable dataTable) throws IOException, SQLException {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size();i++) {
			AppUser user = api.createUser(list.get(i).get("userEmail"), list.get(i).get("userName"), password, null);
			int teamId = 1+i;
			while (teamRepo.findByTeamID(teamId) != null){
				teamId++;
			}
			api.createTeam(teamId, list.get(i).get("teamName"), user);
			api.joinLeague(teamId, string, user);
			List<Team> team = teamRepo.findByName(list.get(i).get("teamName"));
			team.get(0).setPoints(Integer.valueOf(list.get(i).get("points")));
			if (list.get(i).get("wins") != null) {
				team.get(0).setWins(Integer.valueOf(list.get(i).get("wins")));
			}
			teamRepo.save(team.get(0));
		}
	}

	@When("the user attemps to view the standings of league {string}")
	public void the_user_attemps_to_view_the_standings_of_league(String string) throws IOException, SQLException {
		List<Team> teamsList = api.getLeagueStandings(string);
		for (Team t : teamsList) {
			teams.add(t.getName());
		}
	}

	@Then("the standings in the league {string} are shown to be the following")
	public void the_standings_in_the_league_are_shown_to_be_the_following(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		for (int i = 0; i < list.size();i++) {
			int ranking = Integer.valueOf(list.get(i).get("standing"));
			for (int j = 0; j < teams.size(); j++) {
				if (list.get(i).get("teamName").equals(teams.get(j))){
					assertEquals(ranking, j+1);
				}
			}
		}
	}


	@Then("the user {string} new team name is {string}")
	public void theUserNewTeamNameIs(String arg0, String arg1) {
		AppUser user = api.getUser(arg0);
		assertNotNull(api.getTeamByName(arg1, user));
	}

	@When("the user with email {string} attemps to change a team name from {string} to {string}")
	public void theUserWithEmailAttempsToChangeATeamNameFromTo(String arg0, String arg1, String arg2) {
		AppUser user = api.getUser(arg0);
		try {
			api.updateTeamName(user, arg1, arg2);
		}
		catch (Exception e){
			error = e.getMessage();
		}
	}
}

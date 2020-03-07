package ca.mcgill.ecse428.project.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse428.project.controller.McFantasyRestController;
import ca.mcgill.ecse428.project.dao.AppUserRepository;
import ca.mcgill.ecse428.project.dao.GameRepository;
import ca.mcgill.ecse428.project.dao.LeagueRepository;
import ca.mcgill.ecse428.project.dao.PlayerRepository;
import ca.mcgill.ecse428.project.dao.SeasonStatsRepository;
import ca.mcgill.ecse428.project.dao.TeamRepository;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.service.McFantasyService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CucumberStepDefinitions {
	
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
	
	public static String error = "";
	
	public static String name;
	public static String email;
	public static String password;
	public static byte[] picture = {'1'};
	@Given("person {string} with email {string} and password {string}")
	public void person_with_email(String n, String e, String p) {
		name = n;
		email = e;
		password = p;
	}
	
	@When("they request to create a new account on McFantasy Sports")
	public void person_requests_create_account() throws IllegalArgumentException, SerialException, SQLException, IOException {
		System.out.println(service);
		service.createUser(email, name, password, picture);
	}
	
	@Then("a new user with email {string} and password {string} is generated")
	public void a_new_account_with_email_and_password_is_generated(String email, String password) {
		AppUser user = new AppUser();
		try {
			user = service.getUser(email);
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
	public void person_requests_to_create_a_new_account_with_username() {
		try {
			service.createUser(email, name, password, picture);
			service.createUser(email, name, password, picture);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
	}

	@Then("a {string} message is issued")
	public void an_message_is_issued(String string) {
		assertEquals(error, "User with this email has already been created!");
	}

	

	@Given("user with email {string} is logged in")
	public void user_with_email_is_logged_in(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user attempts to change username with username {string}")
	public void the_user_attempts_to_change_username_with_username(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user Account with email {string} Username will be set to {string}")
	public void the_user_Account_with_email_Username_will_be_set_to(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user attempts to change username with username {string}{string}")
	public void the_user_attempts_to_change_username_with_username(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("there exists a user with email {string} and username {string}")
	public void there_exists_a_user_with_email_and_username(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("the user {string} with current password {string} is logged in")
	public void the_user_with_current_password_is_logged_in(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("they have {int} failed attempts")
	public void they_have_failed_attempts(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user initiates to change their password with current password {string} and new password {string} and confirmation password {string}")
	public void the_user_initiates_to_change_their_password_with_current_password_and_new_password_and_confirmation_password(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("their password is still {string}")
	public void their_password_is_still(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("person’s email {string} and password {string} is an existing user")
	public void person_s_email_and_password_is_an_existing_user(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("a person {string} requests for their account to be deleted with confirmation password {string}")
	public void a_person_requests_for_their_account_to_be_deleted_with_confirmation_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("they’re information will be deleted from the database and their account will become invalid")
	public void they_re_information_will_be_deleted_from_the_database_and_their_account_will_become_invalid() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("a record of the attempt is recorded in the database to limit requests.")
	public void a_record_of_the_attempt_is_recorded_in_the_database_to_limit_requests() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a league with {string} exists")
	public void a_league_with_exists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("user with email {string} has an existing team {string}")
	public void user_with_email_has_an_existing_team(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a league with name {string} exists")
	public void a_league_with_name_exists(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
	
	@Given("user {string} is logged in")
	public void user_is_logged_in(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user inputs a valid league name {string} and clicks Create League button")
	public void the_user_inputs_a_valid_league_name_and_clicks_Create_League_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the new league with name {string} will be created")
	public void the_new_league_with_name_will_be_created(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user inputs an invalid league name {string}{string} and clicks Create League button")
	public void the_user_inputs_an_invalid_league_name_and_clicks_Create_League_button(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user inputs an invalid league name {string} and clicks Create League button")
	public void the_user_inputs_an_invalid_league_name_and_clicks_Create_League_button(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user attemps to join League {string} with their team {string}")
	public void the_user_attemps_to_join_League_with_their_team(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user's {string} will be added to the league {string}")
	public void the_user_s_will_be_added_to_the_league(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("a league with name {string} deos not exist")
	public void a_league_with_name_deos_not_exist(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("an error message will appear {string}")
	public void an_error_message_will_appear(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("league {string} has a team {string} in the league")
	public void league_has_a_team_in_the_league(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user attemps to leave the League {string} with their team {string}")
	public void the_user_attemps_to_leave_the_League_with_their_team(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user's {string} will be removed from the league {string}")
	public void the_user_s_will_be_removed_from_the_league(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user attemps to create a new team {string}")
	public void the_user_attemps_to_create_a_new_team(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the user's {string} will be created")
	public void the_user_s_will_be_created(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	
}

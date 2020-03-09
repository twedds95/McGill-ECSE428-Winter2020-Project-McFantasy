package ca.mcgill.ecse428.project.controller;

import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.League;
import ca.mcgill.ecse428.project.model.Player;
import ca.mcgill.ecse428.project.model.Team;
import ca.mcgill.ecse428.project.service.McFantasyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



@CrossOrigin(origins = "*")
@RestController
public class McFantasyRestController {
	
	@Autowired
	private McFantasyService service;
	
	@PostMapping(value = { "/user/{email}", "/user/{email}/" })
	public AppUser createUser(@PathVariable("email") String email, 
			@RequestParam(name = "name") String name, 
			@RequestParam(name = "password") String password,
			@RequestParam(name = "picture") MultipartFile picfile) throws IllegalArgumentException, SerialException, SQLException, IOException {
		
		byte[] bytes = {'1'};
		if (picfile != null && picfile.getSize() > 0) {
			bytes = picfile.getBytes();
		}
		
		AppUser user = service.createUser(email, name, password, bytes);
		return user;
	}
	
	@PostMapping(value = { "/userupdate/{email}", "/userupdate/{email}/" })
	public AppUser updateUser(@PathVariable("email") String email, 
			@RequestParam(name = "name") String name, 
			@RequestParam(name = "password") String password,
			@RequestParam(name = "picture") MultipartFile picfile,
			@RequestParam(name = "newname") String newname, 
			@RequestParam(name = "newpassword") String newpassword,
			@RequestParam(name = "newpicture") MultipartFile newpicfile) throws IllegalArgumentException, SerialException, SQLException, IOException {

		byte[] bytes = {'1'};
		if (picfile != null && picfile.getSize() > 0) {
			bytes = picfile.getBytes();
		}
		
		byte[] newbytes = {'1'};
		if (newpicfile != null && newpicfile.getSize() > 0) {
			bytes = newpicfile.getBytes();
		}
	
		AppUser user = service.getUser(email);
		user = service.updateUser(email, name, password, bytes, newname, newpassword, newbytes);
		return user;
	}
	
	
	@GetMapping(value = { "/user/{email}", "/user/{email}/" })
	public AppUser getUser(@PathVariable("email") String email) {
		AppUser user = service.getUser(email);
	    return user;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@PostMapping(value = { "/login/{email}", "/login/{email}/" })
	public AppUser login(@PathVariable("email") String email, @RequestParam("password") String password) {
		List<AppUser> users = new ArrayList<>();
		for(AppUser user : service.getAllUsers()) {
			if(user.getEmail().equals(email)) {
				if(user.getPassword().equals(password)) {
					//users.add(convertToDto(user));
					return user;
				} else {
					throw new IllegalArgumentException("Incorrect password! Try again!");
				}
			}
		}
		
		if(users.isEmpty()) {
			throw new IllegalArgumentException("This email is not registered! Please use our web browser to sign up!");
			}
		
		return users.get(0);
		}
	
	/**
	 * @author Ali Tapan
	 */
	@PostMapping(value = { "/team/{id}", "/team/{id}/"})
	public Team createTeam(@PathVariable("id") Integer teamID,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "user") AppUser user) throws IllegalArgumentException, SerialException, SQLException, IOException {
		Team team = service.createTeam(teamID, name, user);
		return team;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@GetMapping(value = {"/team/{id}", "/team/{id}/"})
	public Team getTeam(@PathVariable("id") Integer teamID) {
		Team team = service.getTeam(teamID);
		return team;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@PostMapping(value = { "/player/{name}", "/player/{name}/"})
	public Player createPlayer(@PathVariable("name") String name,
			@RequestParam(name = "position") String position) throws IllegalArgumentException, SerialException, SQLException, IOException {
		Player player = service.createPlayer(name, position);
		return player;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@GetMapping(value = {"/player/{name}", "/player/{name}/"})
	public Player getPlayer(@PathVariable("name") String name) {
		Player player = service.getPlayer(name);
		return player;
	}
	
	/**
	 * 
	 * @author Ali Tapan
	 */
	@PostMapping(value = {"/team/{players}", "/team/{players}/"})
	public Team addPlayers(@PathVariable("players") Set<Player> players,
			@RequestParam(name = "teamID") Integer teamID) throws IllegalArgumentException, SerialException, SQLException, IOException{
		return service.addPlayer(players, service.getTeam(teamID));
	}
	
	/**
	 * @author Brad McBain
	 */
	@GetMapping(value = {"/league/{name}", "/league/{name}/"})
	public League getLeague(@PathVariable("name") String name) {
		League league = service.getLeague(name);
		return league;
	}

	/**
	 * @author Raphael Di Piazza
	 */
	@PostMapping(value = {"/league/{name}", "/league/{name}/"})
	public League createLeague(@PathVariable("name") String name,
							   @RequestParam(name = "user") AppUser user) throws IllegalArgumentException, SerialException, SQLException, IOException {
		League league = service.createLeague(name, user);
		return league;
	}
	
	/**
	 * @author Caleb Lim
	 */
	@DeleteMapping(value = {"/league/{team}", "/league/{team}/"})
	public League leaveLeague(@PathVariable("team") Team team, @RequestParam(name="leagueName") String leagueName)  throws IllegalArgumentException, SerialException, SQLException, IOException {
		League league = service.getLeague(leagueName);
		return service.leaveLeague(team, league);
	}
	
	
	/**
	 * @author Patrick Tweddell
	 */
	@PostMapping(value = {"/league/{team}", "/league/{team}/"})
	public League addTeamToLeague(@PathVariable("team") Team team,
			@RequestParam(name = "leagueName") String leagueName) throws IllegalArgumentException, SerialException, SQLException, IOException{
		return service.addTeam(team, service.getLeague(leagueName));
	}

	@DeleteMapping(value = {"/delete/{user}", "/delete/{user}/"})
	public void deleteUser(@PathVariable("user") AppUser user,
							   @RequestParam("password")String password
							  )  throws IllegalArgumentException, SerialException, SQLException, IOException {

		service.deleteUser(user, password);
	}
	
//	returns Ordered ArrayList of Team Standings
	@GetMapping(value = {"/leagueStandings/{name}", "/leagueStandings/{name}/"})
	public List<Team> getLeagueStandings(@PathVariable("name") String name) 
			throws IllegalArgumentException, SerialException, SQLException, IOException {
		League league = service.getLeague(name);
		List<Team> teamStandings = service.updateStandings(league);
		return teamStandings;
	}
	
	/*
	 * @author Ryan Arndtsen
	 */
	@PostMapping(value= {"/league/{teamID}", "/league/{teamID}/"})
	public League joinLeague(@PathVariable("teamID") int teamID, 
			@RequestParam(name="leagueName") String leagueName, 
			@RequestParam(name="user") AppUser user) throws IllegalArgumentException, SerialException, SQLException, IOException {
		return service.joinLeague(service.getLeague(leagueName),user,teamID);
	}
}

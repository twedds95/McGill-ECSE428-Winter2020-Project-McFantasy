package ca.mcgill.ecse428.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.Team;
import ca.mcgill.ecse428.project.model.Player;
import ca.mcgill.ecse428.project.service.McFantasyService;



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
		
		byte[] bytes;
		if (picfile.getSize() > 0) {
			bytes = picfile.getBytes();
		} else {
			bytes = null;
		}
	
		
		AppUser user = service.createUser(email, name, password, bytes);
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
	
	@PostMapping(value = {"/team/{players}", "/team/{players}/"})
	public Team addPlayers(@PathVariable("players") Set<Player> players,
			@RequestParam(name = "teamID") Integer teamID) throws IllegalArgumentException, SerialException, SQLException, IOException{
		return service.addPlayer(players, service.getTeam(teamID));
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
	
}

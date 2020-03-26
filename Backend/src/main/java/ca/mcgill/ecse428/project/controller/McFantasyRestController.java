package ca.mcgill.ecse428.project.controller;

import ca.mcgill.ecse428.project.dto.AppUserDto;
import ca.mcgill.ecse428.project.dto.LeagueDto;
import ca.mcgill.ecse428.project.dto.PlayerDto;
import ca.mcgill.ecse428.project.dto.TeamDto;
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
	public AppUserDto createUser(@PathVariable("email") String email,
			@RequestParam(name = "name") String name, 
			@RequestParam(name = "password") String password,
			@RequestParam(name = "picture") MultipartFile picfile) throws IllegalArgumentException, SerialException, SQLException, IOException {
		
		byte[] bytes = {'1'};
		if (picfile != null && picfile.getSize() > 0) {
			bytes = picfile.getBytes();
		}
		
		AppUser user = service.createUser(email, name, password, bytes);
		return convertAppUserToDTO(user);
	}
	
	@PostMapping(value = { "/userupdate/{email}", "/userupdate/{email}/" })
	public AppUserDto updateUser(@PathVariable("email") String email,
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
		return convertAppUserToDTO(user);
	}
	
	
	@GetMapping(value = { "/user/{email}", "/user/{email}/" })
	public AppUserDto getUser(@PathVariable("email") String email) {
		AppUser user = service.getUser(email);
	    return convertAppUserToDTO(user);
	}
	
	/**
	 * @author Ali Tapan
	 */
	@PostMapping(value = { "/login/{email}", "/login/{email}/" })
	public AppUserDto login(@PathVariable("email") String email, @RequestParam("password") String password) {
		List<AppUser> users = new ArrayList<>();
		for(AppUser user : service.getAllUsers()) {
			if(user.getEmail().equals(email)) {
				if(user.getPassword().equals(password)) {
					//users.add(convertToDto(user));
					return convertAppUserToDTO(user);
				} else {
					throw new IllegalArgumentException("Incorrect password! Try again!");
				}
			}
		}
		
		if(users.isEmpty()) {
			throw new IllegalArgumentException("This email is not registered! Please use our web browser to sign up!");
			}
		
		return convertAppUserToDTO(users.get(0));
		}
	
	/**
	 * @author Ali Tapan
	 * @return
	 */
	@PostMapping(value = { "/newTeam/{name}", "/newTeam/{name}/"})
	public TeamDto createTeam(@PathVariable("name") String name,
							  @RequestParam(name = "user") String email) throws IllegalArgumentException, SerialException, SQLException, IOException {
		AppUser user = service.getUser(email);
		Team team = service.createTeam(name, user);
		return convertTeamToDTO(team);
	}
	
	/**
	 * @author Ali Tapan
	 * @return
	 */
	@GetMapping(value = {"/team/{id}", "/team/{id}/"})
	public TeamDto getTeam(@PathVariable("id") Integer teamID) {
		Team team = service.getTeam(teamID);
		return convertTeamToDTO(team);
	}
	
	/**
	 * @author Ali Tapan
	 * @return
	 */
	@PostMapping(value = { "/player/{name}", "/player/{name}/"})
	public PlayerDto createPlayer(@PathVariable("name") String name,
								  @RequestParam(name = "position") String position,
								  @RequestParam(name = "rating") int rating) throws IllegalArgumentException, SerialException, SQLException, IOException {
		Player player = service.createPlayer(name, position, rating);
		// rating is out of 10 for each player
		return convertPlayerToDTO(player);
	}
	
	/**
	 * @author Ali Tapan
	 */
	@GetMapping(value = {"/player/{name}", "/player/{name}/"})
	public PlayerDto getPlayer(@PathVariable("name") String name) {
		Player player = service.getPlayer(name);
		return convertPlayerToDTO(player);
	}
	
	/**
	 * 
	 * @author Ali Tapan
	 */
	@PostMapping(value = {"/team/addPlayer/{player}", "/team/addPlayer/{player}/"})
	public TeamDto addPlayers(@PathVariable("player") String playerName,
			@RequestParam(name = "teamID") Integer teamID) throws IllegalArgumentException, SerialException, SQLException, IOException{
		Player player = service.getPlayer(playerName);
		return convertTeamToDTO(service.addPlayer(player, service.getTeam(teamID)));
	}
	
	/**
	 * @author Brad McBain
	 * @return
	 */
	@GetMapping(value = {"/league/{name}", "/league/{name}/"})
	public LeagueDto getLeague(@PathVariable("name") String name) {
		League league = service.getLeague(name);
		return convertLeagueToDTO(league);
	}

	/**
	 * @author Raphael Di Piazza
	 * @return
	 */
	@PostMapping(value = {"/newLeague/{name}", "/newLeague/{name}/"})
	public LeagueDto createLeague(@PathVariable("name") String name,
								  @RequestParam(name = "user") String userEmail) throws IllegalArgumentException, SerialException, SQLException, IOException {
		AppUser user = service.getUser(userEmail);
		League league = service.createLeague(name, user);
		return convertLeagueToDTO(league);
	}
	
	/**
	 * @author Caleb Lim
	 */
	@DeleteMapping(value = {"/leaveLeague/{team}", "/leaveLeague/{team}/"})
	public League leaveLeague(@PathVariable("team") Team team, @RequestParam(name="leagueName") String leagueName)  throws IllegalArgumentException, SerialException, SQLException, IOException {
		League league = service.getLeague(leagueName);
		return service.leaveLeague(team, league);
	}
	
	
	/**
	 * @author Patrick Tweddell
	 * @return
	 */
	@PostMapping(value = {"/league/addTeam/{team}", "/league/addTeam/{team}/"})
	public LeagueDto addTeamToLeague(@PathVariable("team") String teamName,
									 @RequestParam(name = "leagueName") String leagueName,
									 @RequestParam(name = "userEmail") String userEmail) throws IllegalArgumentException, SerialException, SQLException, IOException{
		Team team = service.getTeamByName(teamName, service.getUser(userEmail));
		return convertLeagueToDTO(service.addTeam(team, service.getLeague(leagueName)));
	}

	@DeleteMapping(value = {"/delete/{user}", "/delete/{user}/"})
	public void deleteUser(@PathVariable("user") AppUser user,
							   @RequestParam("password")String password
							  )  throws IllegalArgumentException, SerialException, SQLException, IOException {

		service.deleteUser(user, password);
	}

	/**
	 * @author Patrick Tweddell
	 * @param name
	 * @return Ordered ArrayList of Team Standings
	 * @throws IllegalArgumentException
	 * @throws SerialException
	 * @throws SQLException
	 * @throws IOException
	 */
	@GetMapping(value = {"/leagueStandings/{name}", "/leagueStandings/{name}/"})
	public List<TeamDto> getLeagueStandings(@PathVariable("name") String name)
			throws IllegalArgumentException, SerialException, SQLException, IOException {
		League league = service.getLeague(name);
		List<Team> teamStandings = service.updateStandings(league);
		List<TeamDto> teamDtos = new ArrayList<>();
		for (Team t : teamStandings) {
			teamDtos.add(convertTeamToDTO(t));
		}
		return teamDtos;
	}

	/**
	 * @author Patrick Tweddell
	 * @return
	 */
	@GetMapping(value = {"/teamByName/{teamName}", "/teamByName/{teamName}/"})
	public TeamDto getTeamByName(@PathVariable("teamName") String name,
								 @RequestParam("userEmail") String userEmail) {
		AppUser user = service.getUser(userEmail);
		Team team = service.getTeamByName(name, user);
		return convertTeamToDTO(team);
	}

	/**
	 * @author Patrick Tweddell
	 * @param userEmail
	 * @return
	 */
	@GetMapping(value = {"/teamsForUser/", "/teamsForUser/"})
	public List<TeamDto> getTeamsForUser(@RequestParam("userEmail") String userEmail){
		AppUser user = service.getUser(userEmail);
		Set<Team> teams = user.getTeam();
		List<TeamDto> teamDtos = new ArrayList<>();
		for (Team t : teams) {
			teamDtos.add(convertTeamToDTO(t));
		}
		return teamDtos;
	}

	/**
	 * @author Patrick Tweddell
	 * @param userEmail
	 * @return List of leagues created by the user, and leagues in which the user has a team
	 */
	@GetMapping(value = {"/leaguesForUser/", "/leaguesForUser/"})
	public List<LeagueDto> getLeaguesForUser(@RequestParam("userEmail") String userEmail){
		AppUser user = service.getUser(userEmail);
		Set<Team> teams = user.getTeam();
		Set<League> leagues = user.getLeague();
		List<LeagueDto> leagueDtos = new ArrayList<>();
		for (League l : leagues) {
			leagueDtos.add(convertLeagueToDTO(l));
		}
		for (Team t : teams) {
			for (League l: t.getLeague()) {
				if (!leagues.contains(l)){
					leagueDtos.add(convertLeagueToDTO(l));
					leagues.add(l);
				}
			}
		}
		return leagueDtos;
	}

	/**
	 * @author Patrick Tweddell
	 * @param teamName
	 * @param userEmail
	 * @return
	 */
	@GetMapping(value = {"/playersOnTeam/{teamName}", "/playersOnTeam/{teamName}"})
	public List<PlayerDto> getPlayersOnTeam(@PathVariable("teamName") String teamName,
											@RequestParam("userEmail") String userEmail){
		AppUser user = service.getUser(userEmail);
		Team team = service.getTeamByName(teamName, user);
		Set<Player> players = team.getPlayer();
		List<PlayerDto> playerDtos = new ArrayList<>();
		for (Player p : players) {
			playerDtos.add(convertPlayerToDTO(p));
		}
		return playerDtos;
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

	@GetMapping(value= {"/league/teams", "/league/teams/"})
	public List<TeamDto> getTeamsInLeague(@RequestParam(name="leagueName") String name) {
		League league = service.getLeague(name);
		Set<Team> teamsSet = league.getTeam();
		List<TeamDto> teams = new ArrayList<>();
		for (Team x:teamsSet) {
			teams.add(convertTeamToDTO(x));
		}
		return teams;
	}

	/**
	 * @author Patrick Tweddell
	 * @param userEmail
	 * @param oldName
	 * @param newName
	 * @return Team
	 */
	@PostMapping(value = {"/team/updateName", "/team/updateName/"})
	public TeamDto updateTeamName(@RequestParam(name="user") String userEmail,
							   @RequestParam(name="oldName") String oldName,
							   @RequestParam(name="newName") String newName) {
		AppUser user = service.getUser(userEmail);
		Team team = service.updateTeamName(user, oldName, newName);
		return convertTeamToDTO(team);
	}

	/*
	////////////////////////////////////////////
	////////////////CONVERT TO DTO//////////////
	////////////////////////////////////////////
	 */
	private AppUserDto convertAppUserToDTO (AppUser appUser){
		return new AppUserDto(appUser.getName(), appUser.getEmail(), appUser.getProfilePicture());
	}

	private LeagueDto convertLeagueToDTO (League league){
		return new LeagueDto(league.getName(), convertAppUserToDTO(league.getUser()));
	}

	private PlayerDto convertPlayerToDTO (Player player){
		return new PlayerDto(player.getPosition(), player.getName(), player.getJerseyNumber(), player.getRating(),
				player.getTotalGoals(), player.getTotalAssists());
	}

	private TeamDto convertTeamToDTO (Team team){
		return new TeamDto(team.getTeamID(), team.getName(), team.getWins(), team.getLoses(), team.getTies(),
				team.getPoints(), team.getTotalRating(), convertAppUserToDTO(team.getUser()));
	}
}

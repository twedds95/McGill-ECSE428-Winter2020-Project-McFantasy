
package ca.mcgill.ecse428.project.service;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse428.project.dao.AppUserRepository;
import ca.mcgill.ecse428.project.dao.GameRepository;
import ca.mcgill.ecse428.project.dao.LeagueRepository;
import ca.mcgill.ecse428.project.dao.PlayerRepository;
import ca.mcgill.ecse428.project.dao.SeasonStatsRepository;
import ca.mcgill.ecse428.project.dao.TeamRepository;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.Player;
import ca.mcgill.ecse428.project.model.Team;
@Service
public class McFantasyService {
	
	@Autowired
	AppUserRepository userRepo;
	
	@Autowired
	GameRepository gameRepo;
	
	@Autowired
	LeagueRepository leagueRepo;
	
	@Autowired
	PlayerRepository playerRepo;
	
	@Autowired
	SeasonStatsRepository seasonStatsRepo;
	
	@Autowired
	TeamRepository teamRepo;
	
	
	@Transactional
	public AppUser createUser(String email, String name, String password, byte[] bytes) {
		
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("User email cannot be empty!");
		} else if (userRepo.existsById(email)) {
			throw new IllegalArgumentException("User with this email has already been created!");
		}
		
		AppUser user = new AppUser();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setProfilePicture(bytes);
		userRepo.save(user);
		
		return user;
		
	}
	
	@Transactional
	public AppUser getUser(String email) {
		AppUser user = userRepo.findByEmail(email);
		return user;
		
	}
	
	/**
	 * @author Ali Tapan
	 */
	@Transactional 
	public Team createTeam(Integer teamID, String name, AppUser user) {
		// Check if the inputs are wrong
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "Team name cannot be empty!";
		} 
		if (teamID >= 0) {
			error += "Team ID cannot be zero or less than zero!";
		}
		
		// Need to fix this at some point:
//		if (teamRepo.existsById(teamID) {
//			error += "Team with this name has already been created!";
//		} 
		
		if (user == null) {
			error += "Application user is null!";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		// Initialize the team
		Team team = new Team();
		team.setTeamID(teamID);
		team.setName(name);
		team.setWins(0);
		team.setLoses(0);
		team.setTies(0);
		team.setPoints(0);
		team.setTotalRating(0);
		// team.setMaxRating(0); // Did not initialize max rating
		team.setUser(user);
		teamRepo.save(team);
		return team;
	}	
		
	/**
	 * @author Ali Tapan
	 */	
	@Transactional 
	public Team getTeam(Integer teamID) {
		Team team = teamRepo.findByTeamID(teamID);
		return team;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@Transactional
	public Player createPlayer(String name, String position) {  // Pls note that you cant create players with same name!
		// Check if the inputs are wrong
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "Player name cannot be empty!";
		} 
		if (position == null || position.trim().length() == 0) {
			error += "Player position cannot be empty!";
		}
		if (playerRepo.existsById(name)) {
			error += "Player with this name has already been created!";
		} 
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		// Initialize the player
		Player player = new Player();
		player.setName(name);
		player.setPosition(position);
		player.setJerseyNumber(0);
		player.setRating(0);
		player.setSeasonsPlayed(0);
		player.setStilPlaying(true);
		player.setTotalAssists(0);
		player.setTotalGoals(0);
		playerRepo.save(player);
		return player;
	}
	
	/**
	 * @author Ali Tapan
	 */
	@Transactional
	public Player getPlayer(String name) {
		Player player = playerRepo.findByName(name);
		return player;
	}
	
	
	@Transactional
	public Team addPlayer(Set<Player> players, Team team) {
		Team t = teamRepo.findByName(team.getName());
		t.setPlayer(players);
		// Will also update player's team in the future
		teamRepo.save(t);
		return t;
	}

	/**
	 * @author Raphael Di Piazza
	 */
	@Transactional
	public League createLeague(String name, AppUser user) {
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "League name cannot be empty!";
		}

		//need to check if league name already exists
		//will fix this later

		if (leagueID >= 0) {
			error += "League ID cannot be zero or less!";
		}
		if (user == null) {
			error += "Application user is null!";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		League league = new League();
		league.setName(name);
		league.setUser(user);
		leagueRepo.save(league);
		return league;
	}
	
}
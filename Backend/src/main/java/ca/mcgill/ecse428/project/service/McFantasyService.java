
package ca.mcgill.ecse428.project.service;


import java.util.Optional;

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
	 * <p>
	 * Create a Team
	 * <p>
	 * <p>
	 * Initialize a team with no players, no league and no games
	 * <p>
	 * 
	 * @param teamID, name, user
	 * @return Team object
	 * 
	 * @author Ali Tapan
	 * @version 1.0
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
	 * <p>
	 * Get Team
	 * <p>
	 * <p>
	 * Get a team with a given teamID
	 * <p>
	 * 
	 * @param teamID
	 * @return Team object
	 * 
	 * @author Ali Tapan
	 * @version 1.0
	 */	
	@Transactional 
	public Team getTeam(Integer teamID) {
		Team team = teamRepo.findByTeamID(teamID);
		return team;
	}
	
}

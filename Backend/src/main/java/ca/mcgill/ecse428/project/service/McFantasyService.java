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
		

}

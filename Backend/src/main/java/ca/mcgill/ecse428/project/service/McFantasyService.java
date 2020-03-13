
package ca.mcgill.ecse428.project.service;


import ca.mcgill.ecse428.project.dao.*;
import ca.mcgill.ecse428.project.model.AppUser;
import ca.mcgill.ecse428.project.model.League;
import ca.mcgill.ecse428.project.model.Player;
import ca.mcgill.ecse428.project.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

	/**
	 * @author David Whiteside
	 */

	@Transactional
	public AppUser updateUser(String email, String name, String password, byte[] bytes, String newname, String newpassword, byte[] newbytes) {

		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("User email cannot be empty!");
		} else if (!userRepo.existsById(email)) {
			throw new IllegalArgumentException("No user with this email exists!");
		} else if (!name.equals(newname) && userRepo.existsAppUserByName(newname)) {
			throw new IllegalArgumentException("User with this username already exists!");
		}


		AppUser user = userRepo.findByEmail(email);
		if (newname == null || newname.length() == 0) {throw new IllegalArgumentException("Please input all required parameters");}
		if (newpassword == null || newpassword.length() == 0) {throw new IllegalArgumentException("Please input all required parameters");}
		if (newbytes == null ) {throw new IllegalArgumentException("Please input all required parameters");}

		userRepo.delete(user);
		AppUser updateduser = createUser(email,newname,newpassword,newbytes);
		userRepo.save(updateduser);
		
		return updateduser;

	}

	@Transactional
	public AppUser getUser(String email) {
		AppUser user = userRepo.findByEmail(email);
		return user;

	}
	
	@Transactional
	public List<AppUser> getAllUsers() {
		return toList(userRepo.findAll());
	}
	
	/**
	 * @author Ali Tapan
	 */
    @Transactional
    public AppUser login(String email, String password) {
    	AppUser user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new NullPointerException("Invalid Login Credidentials!");
    }
	

	/**
	 * @author Ali Tapan
	 */
	@Transactional
	public Team createTeam(Integer teamID, String name, AppUser user) {
		// Check if the inputs are wrong
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "Team name is not valid";
		}
		if (teamID <= 0) {
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

	@Transactional
	public List<Team> getAllTeams() {
		return toList(teamRepo.findAll());
	}

	@Transactional
	public List<Player> getAllPlayers(){
		return toList(playerRepo.findAll());
	}
	
	@Transactional
	public List<Team> getAllLeagues() {
		return toList(teamRepo.findAll());
	}
	
	/**
	 * @author Ali Tapan
	 */
	@Transactional
	public Player createPlayer(String name, String position, int rating) {  // Pls note that you cant create players with same name!
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
		player.setRating(rating);
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


	/**
	 * @author Patrick Tweddell
	 * @param player
	 * @param team
	 * @return
	 */
	@Transactional
	public Team addPlayer(Player player, Team team) {
		String error = "";
		Team t = teamRepo.findByTeamID(team.getTeamID());
		Player p = playerRepo.findByName(player.getName());
		Set<Player> players;
		if (team.getPlayer() != null) {
			players = team.getPlayer();
			for (Player play : players) {
				if (p.getName().equals(play.getName())) {
					error += "This player is already on your team.";
				}
			}
		}
		else {
			players = new HashSet<>();
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		if (t.getTotalRating() + p.getRating() <= 77) {
			players.add(p);
			t.setPlayer(players);
			t.setTotalRating(t.getTotalRating() + p.getRating());
			teamRepo.save(t);

			Set<Team> teams = p.getTeam();
			teams.add(t);
			p.setTeam(teams);
			playerRepo.save(p);
		}else {
			error += "Cannot add player to team, exceeds the max rating of 77.";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		return t;
	}

	/**
	 * @author Brad McBain
	 */
	@Transactional
	public League getLeague(String name) {
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "League name cannot be empty!";
		}
		if (!leagueRepo.existsById(name)) {
			error += "League does not exist";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		League league =	leagueRepo.findByName(name);
		return league;
	}


	/**
	 * @author Raphael Di Piazza
	 */
	@Transactional
	public League createLeague(String name, AppUser user) {
		String error ="";
		if (name == null || name.trim().length() == 0) {
			error += "League name is not valid";
		}else if (leagueRepo.existsById(name)) {
			throw new IllegalArgumentException("League name is already used");
		}

		//need to check if league name already exists
		//will fix this later

//		if (leagueID >= 0) {
//			error += "League ID cannot be zero or less!";
//		}
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

	private <T> List<T> toList(Iterable<T> iterable){
		if(iterable == null){
			throw new IllegalArgumentException("Iterable cannot be null! ");
		}
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	/**
	 * @author Caleb Lim
	 */
	@Transactional
	public League leaveLeague(Team team, League league) {
		
		String error = "";
		if (team == null) {
			error += "Team is null!";
		} else if (league == null) {
			error += "League is null!";
		}
		if (error.length()>0) {
			throw new IllegalArgumentException(error);
		}
		
		League l = leagueRepo.findByName(league.getName());
		Team t = teamRepo.findByTeamID(team.getTeamID());
		AppUser u = t.getUser();
		
		Set<Team> lTeams = l.getTeam();
		lTeams.remove(t);
		l.setTeam(lTeams);
		leagueRepo.save(l);
		
		Set<Team> uTeams = u.getTeam();
		uTeams.remove(t);
		u.setTeam(uTeams);
		Set<League> uLeagues = u.getLeague();
		uLeagues.remove(l);
		u.setLeague(uLeagues);
		userRepo.save(u);
		
		teamRepo.delete(t);
		
		return l;
	}

	/**
	 * @author Patrick Tweddell
	 */
	@Transactional
	public League addTeam(Team team, League league){
		String error = "";
		if (!leagueRepo.existsById(league.getName())){
			error += "League does not exist";
		}
		Set<Team> teamsInLeague = leagueRepo.findByName(league.getName()).getTeam();
		for (Team t: teamsInLeague) {
			if(t.getName().equals(team.getName())){
				error += "Team name is already used in this league, please modify your team name before joining.";
			}
		}
//		if (teamRepo.findByName(team.getName()) == null){
//			error += "Team does not exist";
//		}
		if (error.length() > 0){
			throw new IllegalArgumentException(error);
		}
		League l = leagueRepo.findByName(league.getName());
		Team foundTeam = teamRepo.findByTeamID(team.getTeamID());
		
		Set<Team> teams = l.getTeam();
		teams.add(foundTeam);
		l.setTeam(teams);
		leagueRepo.save(l);
		
		Set<League> leagues = foundTeam.getLeague();
		leagues.add(l);
		foundTeam.setLeague(leagues);
		teamRepo.save(foundTeam);
		return l;
	}
	
	@Transactional
	public List<Team> updateStandings(League league) {
		String error = "";
		List<Team> teamsInLeague = teamRepo.findByLeague(league);
		if (teamsInLeague.size()==1) {
			return teamsInLeague;
		}
		if (teamsInLeague.size()==0) {
			error += "League has no Teams!";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		int n = teamsInLeague.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (teamsInLeague.get(j).getPoints() <= teamsInLeague.get(j+1).getPoints()) {
                	if (teamsInLeague.get(j).getPoints() == teamsInLeague.get(j+1).getPoints()) {
                		if (teamsInLeague.get(j).getWins() > teamsInLeague.get(j+1).getWins()) {
                			break;
                		}
                	}
                    Team temp = teamsInLeague.get(j);
                    teamsInLeague.set(j,teamsInLeague.get(j+1));
                    teamsInLeague.set(j+1,temp);
                }

		return teamsInLeague;
	}
	
	/**
	 * @author Ryan Arndtsen
	 */
	
	@Transactional
	public League joinLeague(League league, AppUser appUser, int teamId) {
		String error = "";
		
		if (league == null) {
			error += "League is null!";
		}
		if (appUser == null) {
			error += "User is null!";
		}
		if (teamId == 0) {
			error += "TeamID cannot have value 0!";
		}
		if (error.length() > 0 ){
			throw new IllegalArgumentException(error);
		}
		
		League l = leagueRepo.findByName(league.getName());
		AppUser au = userRepo.findByEmail(appUser.getEmail());
		Team t = teamRepo.findByTeamID(teamId);
		
		t.getLeague().add(l);
		t.setUser(au);
		teamRepo.save(t);
		Set<Team> teams = l.getTeam();
		teams.add(t);
		l.setTeam(teams);
		leagueRepo.save(l);
		
		return l;
	}

	/**
	 * @author Patrick Tweddell
	 * @param user
	 * @param password
	 */
	@Transactional
	public void deleteUser(AppUser user, String password) {
		AppUser appUser = userRepo.findByEmail(user.getEmail());
		if (appUser.getPassword().equals(password)){
			userRepo.delete(user);
		}
		else {
			throw new IllegalArgumentException("Unauthorized request");
		}
	}

	/**
	 * @author Patrick Tweddell
	 * @param name
	 * @return Team
	 */
	@Transactional
	public Team getTeamByName(String name, AppUser user) {
		String error = "";
		List<Team> teams = teamRepo.findByName(name);
		if(teams.size() == 0 || teams == null){
			error += "Team does not exist";
		}
		if (error.length() > 0){
			throw new IllegalArgumentException();
		}
		Team foundTeam = null;
		for (Team t : teams) {
			if (t.getUser().getEmail().equals(user.getEmail())){
				foundTeam = t;
			}
		}
		return  foundTeam;
	}

	@Transactional
	public Team updateTeamName(AppUser user, String oldName, String newName) {
		Team team = getTeamByName(oldName, user);
		String error = "";
		if (team.getLeague() != null) {
			Set<League> leagueSet = team.getLeague();
			for (League l:leagueSet) {
				for (Team t:l.getTeam()) {
					if (t.getName().equals(newName)){
						error += "Team name is already used in one of your leagues";
					}
				}
			}
		}
		if (error.length() > 0){
			throw new IllegalArgumentException(error);
		}
		team.setName(newName);
		teamRepo.save(team);
		return team;
	}

}

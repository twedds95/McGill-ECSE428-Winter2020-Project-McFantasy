package ca.mcgill.ecse428.mcfantasy.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Team{
   private Integer teamID;

public void setTeamID(Integer value) {
    this.teamID = value;
}
@Id
public Integer getTeamID() {
    return this.teamID;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private Integer wins;

public void setWins(Integer value) {
    this.wins = value;
}
public Integer getWins() {
    return this.wins;
}
private Integer loses;

public void setLoses(Integer value) {
    this.loses = value;
}
public Integer getLoses() {
    return this.loses;
}
private Integer ties;

public void setTies(Integer value) {
    this.ties = value;
}
public Integer getTies() {
    return this.ties;
}
private Integer points;

public void setPoints(Integer value) {
    this.points = value;
}
public Integer getPoints() {
    return this.points;
}
private Integer totalRating;

public void setTotalRating(Integer value) {
    this.totalRating = value;
}
public Integer getTotalRating() {
    return this.totalRating;
}
private Integer MaxRating;

public void setMaxRating(Integer value) {
    this.MaxRating = value;
}
public Integer getMaxRating() {
    return this.MaxRating;
}
   private AppUser user;
   
   @ManyToOne(optional=false)
   public AppUser getUser() {
      return this.user;
   }
   
   public void setUser(AppUser user) {
      this.user = user;
   }
   
   private Set<League> league;
   
   @ManyToMany
   public Set<League> getLeague() {
      return this.league;
   }
   
   public void setLeague(Set<League> leagues) {
      this.league = leagues;
   }
   
   private Set<Player> player;
   
   @ManyToMany(mappedBy="team" )
   public Set<Player> getPlayer() {
      return this.player;
   }
   
   public void setPlayer(Set<Player> players) {
      this.player = players;
   }
   
   private Set<Game> game;
   
   @ManyToMany(mappedBy="team" )
   public Set<Game> getGame() {
      return this.game;
   }
   
   public void setGame(Set<Game> games) {
      this.game = games;
   }
   
   }

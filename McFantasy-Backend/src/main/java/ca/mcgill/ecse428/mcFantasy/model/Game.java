package ca.mcgill.ecse428.mcfantasy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Game{
   private Integer gameId;

public void setGameId(Integer value) {
    this.gameId = value;
}
@Id
public Integer getGameId() {
    return this.gameId;
}
private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Integer team1Score;

public void setTeam1Score(Integer value) {
    this.team1Score = value;
}
public Integer getTeam1Score() {
    return this.team1Score;
}
private Integer team2Score;

public void setTeam2Score(Integer value) {
    this.team2Score = value;
}
public Integer getTeam2Score() {
    return this.team2Score;
}
   private Set<Team> team;
   
   @ManyToMany
   public Set<Team> getTeam() {
      return this.team;
   }
   
   public void setTeam(Set<Team> teams) {
      this.team = teams;
   }
   
   }

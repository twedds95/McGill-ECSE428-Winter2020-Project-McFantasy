package ca.mcgill.ecse428.mcfantasy.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Player{
private String position;

public void setPosition(String value) {
this.position = value;
}
public String getPosition() {
return this.position;
}
   private String name;

public void setName(String value) {
    this.name = value;
}
@Id
public String getName() {
    return this.name;
}
private Integer jerseyNumber;

public void setJerseyNumber(Integer value) {
    this.jerseyNumber = value;
}
public Integer getJerseyNumber() {
    return this.jerseyNumber;
}
private Integer rating;

public void setRating(Integer value) {
    this.rating = value;
}
public Integer getRating() {
    return this.rating;
}
private Integer seasonsPlayed;

public void setSeasonsPlayed(Integer value) {
    this.seasonsPlayed = value;
}
public Integer getSeasonsPlayed() {
    return this.seasonsPlayed;
}
private Integer totalGoals;

public void setTotalGoals(Integer value) {
    this.totalGoals = value;
}
public Integer getTotalGoals() {
    return this.totalGoals;
}
private Integer totalAssists;

public void setTotalAssists(Integer value) {
    this.totalAssists = value;
}
public Integer getTotalAssists() {
    return this.totalAssists;
}
private Set<SeasonStats> seasonStats;

@OneToMany
public Set<SeasonStats> getSeasonStats() {
   return this.seasonStats;
}

public void setSeasonStats(Set<SeasonStats> seasonStatss) {
   this.seasonStats = seasonStatss;
}

private Set<Team> team;

@ManyToMany
public Set<Team> getTeam() {
   return this.team;
}

public void setTeam(Set<Team> teams) {
   this.team = teams;
}

private Boolean stilPlaying;

public void setStilPlaying(Boolean value) {
    this.stilPlaying = value;
}
public Boolean getStilPlaying() {
    return this.stilPlaying;
}
}

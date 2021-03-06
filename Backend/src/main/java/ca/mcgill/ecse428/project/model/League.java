package ca.mcgill.ecse428.project.model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class League{
   private String name;

public void setName(String value) {
    this.name = value;
}
@Id
public String getName() {
    return this.name;
}
   private AppUser user;
   
   @ManyToOne(optional=false)
   public AppUser getUser() {
      return this.user;
   }
   
   public void setUser(AppUser user) {

      this.user = user;
   }
   
   private Set<Team> team;
   
   @ManyToMany(mappedBy="league", fetch=FetchType.EAGER)
   public Set<Team> getTeam() {
      return this.team;
   }
   
   public void setTeam(Set<Team> teams) {
      this.team = teams;
   }
   
   private Set<Game> game;
   
   @OneToMany
   public Set<Game> getGame() {
      return this.game;
   }
   
   public void setGame(Set<Game> games) {
      this.game = games;
   }
   
   }

package ca.mcgill.ecse428.project.model;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class UserAccount{
private Blob picture;

public void setPicture(Blob value) {
this.picture = value;
}
public Blob getPicture() {
return this.picture;
}
   private String email;

public void setEmail(String value) {
    this.email = value;
}
@Id
public String getEmail() {
    return this.email;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private String password;

public void setPassword(String value) {
    this.password = value;
}
public String getPassword() {
    return this.password;
}
   private Set<Team> team;
   
   @OneToMany(mappedBy="user" , cascade={CascadeType.ALL})
   public Set<Team> getTeam() {
      return this.team;
   }
   
   public void setTeam(Set<Team> teams) {
      this.team = teams;
   }
   
   private Set<League> league;
   
   @OneToMany(mappedBy="user" , cascade={CascadeType.ALL})
   public Set<League> getLeague() {
      return this.league;
   }
   
   public void setLeague(Set<League> leagues) {
      this.league = leagues;
   }
   
   }

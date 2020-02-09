package ca.mcgill.ecse428.project.model;


import javax.persistence.Entity;
import javax.persistence.Id;

import java.sql.Blob;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class AppUser{
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
	private byte[] profilePicture;

	public void setProfilePicture(byte[] value) {
		this.profilePicture = value;
	}
	public byte[] getProfilePicture() {
		return this.profilePicture;
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
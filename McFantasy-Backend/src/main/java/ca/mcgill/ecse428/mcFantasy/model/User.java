/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 5 "McFantasyModel.ump"
public class User
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Picture { Link1, Link2, Link3, Link4, Link5 }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String name;
  private String password;
  private Picture profilePicture;

  //User Associations
  private List<Team> teams;
  private List<League> leagues;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aName, String aPassword, Picture aProfilePicture)
  {
    email = aEmail;
    name = aName;
    password = aPassword;
    profilePicture = aProfilePicture;
    teams = new ArrayList<Team>();
    leagues = new ArrayList<League>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setProfilePicture(Picture aProfilePicture)
  {
    boolean wasSet = false;
    profilePicture = aProfilePicture;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public Picture getProfilePicture()
  {
    return profilePicture;
  }
  /* Code from template association_GetMany */
  public Team getTeam(int index)
  {
    Team aTeam = teams.get(index);
    return aTeam;
  }

  public List<Team> getTeams()
  {
    List<Team> newTeams = Collections.unmodifiableList(teams);
    return newTeams;
  }

  public int numberOfTeams()
  {
    int number = teams.size();
    return number;
  }

  public boolean hasTeams()
  {
    boolean has = teams.size() > 0;
    return has;
  }

  public int indexOfTeam(Team aTeam)
  {
    int index = teams.indexOf(aTeam);
    return index;
  }
  /* Code from template association_GetMany */
  public League getLeague(int index)
  {
    League aLeague = leagues.get(index);
    return aLeague;
  }

  public List<League> getLeagues()
  {
    List<League> newLeagues = Collections.unmodifiableList(leagues);
    return newLeagues;
  }

  public int numberOfLeagues()
  {
    int number = leagues.size();
    return number;
  }

  public boolean hasLeagues()
  {
    boolean has = leagues.size() > 0;
    return has;
  }

  public int indexOfLeague(League aLeague)
  {
    int index = leagues.indexOf(aLeague);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTeams()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Team addTeam(int aTeamID, String aName, int aWins, int aLoses, int aTies, int aPoints, int aTotalRating)
  {
    return new Team(aTeamID, aName, aWins, aLoses, aTies, aPoints, aTotalRating, this);
  }

  public boolean addTeam(Team aTeam)
  {
    boolean wasAdded = false;
    if (teams.contains(aTeam)) { return false; }
    User existingUser = aTeam.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aTeam.setUser(this);
    }
    else
    {
      teams.add(aTeam);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTeam(Team aTeam)
  {
    boolean wasRemoved = false;
    //Unable to remove aTeam, as it must always have a user
    if (!this.equals(aTeam.getUser()))
    {
      teams.remove(aTeam);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTeamAt(Team aTeam, int index)
  {  
    boolean wasAdded = false;
    if(addTeam(aTeam))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeams()) { index = numberOfTeams() - 1; }
      teams.remove(aTeam);
      teams.add(index, aTeam);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTeamAt(Team aTeam, int index)
  {
    boolean wasAdded = false;
    if(teams.contains(aTeam))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTeams()) { index = numberOfTeams() - 1; }
      teams.remove(aTeam);
      teams.add(index, aTeam);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTeamAt(aTeam, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLeagues()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public League addLeague(String aName)
  {
    return new League(aName, this);
  }

  public boolean addLeague(League aLeague)
  {
    boolean wasAdded = false;
    if (leagues.contains(aLeague)) { return false; }
    User existingUser = aLeague.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aLeague.setUser(this);
    }
    else
    {
      leagues.add(aLeague);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLeague(League aLeague)
  {
    boolean wasRemoved = false;
    //Unable to remove aLeague, as it must always have a user
    if (!this.equals(aLeague.getUser()))
    {
      leagues.remove(aLeague);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLeagueAt(League aLeague, int index)
  {  
    boolean wasAdded = false;
    if(addLeague(aLeague))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLeagues()) { index = numberOfLeagues() - 1; }
      leagues.remove(aLeague);
      leagues.add(index, aLeague);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLeagueAt(League aLeague, int index)
  {
    boolean wasAdded = false;
    if(leagues.contains(aLeague))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLeagues()) { index = numberOfLeagues() - 1; }
      leagues.remove(aLeague);
      leagues.add(index, aLeague);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLeagueAt(aLeague, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (teams.size() > 0)
    {
      Team aTeam = teams.get(teams.size() - 1);
      aTeam.delete();
      teams.remove(aTeam);
    }
    
    while (leagues.size() > 0)
    {
      League aLeague = leagues.get(leagues.size() - 1);
      aLeague.delete();
      leagues.remove(aLeague);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "name" + ":" + getName()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "profilePicture" + "=" + (getProfilePicture() != null ? !getProfilePicture().equals(this)  ? getProfilePicture().toString().replaceAll("  ","    ") : "this" : "null");
  }
}
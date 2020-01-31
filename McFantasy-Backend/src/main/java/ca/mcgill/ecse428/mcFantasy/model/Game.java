/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.sql.Date;
import java.util.*;

// line 55 "McFantasyModel.ump"
public class Game
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int gameID;
  private Date date;
  private int team1Score;

  //Game Associations
  private League league;
  private List<Team> teams;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aGameID, Date aDate, int aTeam1Score, League aLeague, Team... allTeams)
  {
    gameID = aGameID;
    date = aDate;
    team1Score = aTeam1Score;
    boolean didAddLeague = setLeague(aLeague);
    if (!didAddLeague)
    {
      throw new RuntimeException("Unable to create game due to league");
    }
    teams = new ArrayList<Team>();
    boolean didAddTeams = setTeams(allTeams);
    if (!didAddTeams)
    {
      throw new RuntimeException("Unable to create Game, must have 2 teams");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGameID(int aGameID)
  {
    boolean wasSet = false;
    gameID = aGameID;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setTeam1Score(int aTeam1Score)
  {
    boolean wasSet = false;
    team1Score = aTeam1Score;
    wasSet = true;
    return wasSet;
  }

  public int getGameID()
  {
    return gameID;
  }

  public Date getDate()
  {
    return date;
  }

  public int getTeam1Score()
  {
    return team1Score;
  }
  /* Code from template association_GetOne */
  public League getLeague()
  {
    return league;
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
  /* Code from template association_SetOneToMany */
  public boolean setLeague(League aLeague)
  {
    boolean wasSet = false;
    if (aLeague == null)
    {
      return wasSet;
    }

    League existingLeague = league;
    league = aLeague;
    if (existingLeague != null && !existingLeague.equals(aLeague))
    {
      existingLeague.removeGame(this);
    }
    league.addGame(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfTeamsValid()
  {
    boolean isValid = numberOfTeams() >= minimumNumberOfTeams() && numberOfTeams() <= maximumNumberOfTeams();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfTeams()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTeams()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfTeams()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addTeam(Team aTeam)
  {
    boolean wasAdded = false;
    if (teams.contains(aTeam)) { return false; }
    if (numberOfTeams() >= maximumNumberOfTeams())
    {
      return wasAdded;
    }

    teams.add(aTeam);
    if (aTeam.indexOfGame(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTeam.addGame(this);
      if (!wasAdded)
      {
        teams.remove(aTeam);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMNToMany */
  public boolean removeTeam(Team aTeam)
  {
    boolean wasRemoved = false;
    if (!teams.contains(aTeam))
    {
      return wasRemoved;
    }

    if (numberOfTeams() <= minimumNumberOfTeams())
    {
      return wasRemoved;
    }

    int oldIndex = teams.indexOf(aTeam);
    teams.remove(oldIndex);
    if (aTeam.indexOfGame(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTeam.removeGame(this);
      if (!wasRemoved)
      {
        teams.add(oldIndex,aTeam);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMNToMany */
  public boolean setTeams(Team... newTeams)
  {
    boolean wasSet = false;
    ArrayList<Team> verifiedTeams = new ArrayList<Team>();
    for (Team aTeam : newTeams)
    {
      if (verifiedTeams.contains(aTeam))
      {
        continue;
      }
      verifiedTeams.add(aTeam);
    }

    if (verifiedTeams.size() != newTeams.length || verifiedTeams.size() < minimumNumberOfTeams() || verifiedTeams.size() > maximumNumberOfTeams())
    {
      return wasSet;
    }

    ArrayList<Team> oldTeams = new ArrayList<Team>(teams);
    teams.clear();
    for (Team aNewTeam : verifiedTeams)
    {
      teams.add(aNewTeam);
      if (oldTeams.contains(aNewTeam))
      {
        oldTeams.remove(aNewTeam);
      }
      else
      {
        aNewTeam.addGame(this);
      }
    }

    for (Team anOldTeam : oldTeams)
    {
      anOldTeam.removeGame(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    League placeholderLeague = league;
    this.league = null;
    if(placeholderLeague != null)
    {
      placeholderLeague.removeGame(this);
    }
    ArrayList<Team> copyOfTeams = new ArrayList<Team>(teams);
    teams.clear();
    for(Team aTeam : copyOfTeams)
    {
      aTeam.removeGame(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gameID" + ":" + getGameID()+ "," +
            "team1Score" + ":" + getTeam1Score()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "league = "+(getLeague()!=null?Integer.toHexString(System.identityHashCode(getLeague())):"null");
  }
}
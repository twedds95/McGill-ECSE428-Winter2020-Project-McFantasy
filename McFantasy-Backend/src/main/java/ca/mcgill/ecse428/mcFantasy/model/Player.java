/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 29 "McFantasyModel.ump"
public class Player
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private String name;
  private int jerseyNumber;
  private int rating;
  private String position;
  private int seasonsPlayed;
  private int totalGoals;
  private int totalAssists;

  //Player Associations
  private List<Team> teams;
  private List<SeasonStats> seasonStats;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String aName, int aJerseyNumber, int aRating, String aPosition, int aSeasonsPlayed, int aTotalGoals, int aTotalAssists)
  {
    name = aName;
    jerseyNumber = aJerseyNumber;
    rating = aRating;
    position = aPosition;
    seasonsPlayed = aSeasonsPlayed;
    totalGoals = aTotalGoals;
    totalAssists = aTotalAssists;
    teams = new ArrayList<Team>();
    seasonStats = new ArrayList<SeasonStats>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setJerseyNumber(int aJerseyNumber)
  {
    boolean wasSet = false;
    jerseyNumber = aJerseyNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setRating(int aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setPosition(String aPosition)
  {
    boolean wasSet = false;
    position = aPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setSeasonsPlayed(int aSeasonsPlayed)
  {
    boolean wasSet = false;
    seasonsPlayed = aSeasonsPlayed;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalGoals(int aTotalGoals)
  {
    boolean wasSet = false;
    totalGoals = aTotalGoals;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalAssists(int aTotalAssists)
  {
    boolean wasSet = false;
    totalAssists = aTotalAssists;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getJerseyNumber()
  {
    return jerseyNumber;
  }

  public int getRating()
  {
    return rating;
  }

  public String getPosition()
  {
    return position;
  }

  public int getSeasonsPlayed()
  {
    return seasonsPlayed;
  }

  public int getTotalGoals()
  {
    return totalGoals;
  }

  public int getTotalAssists()
  {
    return totalAssists;
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
  public SeasonStats getSeasonStat(int index)
  {
    SeasonStats aSeasonStat = seasonStats.get(index);
    return aSeasonStat;
  }

  public List<SeasonStats> getSeasonStats()
  {
    List<SeasonStats> newSeasonStats = Collections.unmodifiableList(seasonStats);
    return newSeasonStats;
  }

  public int numberOfSeasonStats()
  {
    int number = seasonStats.size();
    return number;
  }

  public boolean hasSeasonStats()
  {
    boolean has = seasonStats.size() > 0;
    return has;
  }

  public int indexOfSeasonStat(SeasonStats aSeasonStat)
  {
    int index = seasonStats.indexOf(aSeasonStat);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTeams()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addTeam(Team aTeam)
  {
    boolean wasAdded = false;
    if (teams.contains(aTeam)) { return false; }
    teams.add(aTeam);
    if (aTeam.indexOfPlayer(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTeam.addPlayer(this);
      if (!wasAdded)
      {
        teams.remove(aTeam);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeTeam(Team aTeam)
  {
    boolean wasRemoved = false;
    if (!teams.contains(aTeam))
    {
      return wasRemoved;
    }

    int oldIndex = teams.indexOf(aTeam);
    teams.remove(oldIndex);
    if (aTeam.indexOfPlayer(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTeam.removePlayer(this);
      if (!wasRemoved)
      {
        teams.add(oldIndex,aTeam);
      }
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
  public static int minimumNumberOfSeasonStats()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SeasonStats addSeasonStat(int aYear, int aGoals, int aAssists)
  {
    return new SeasonStats(aYear, aGoals, aAssists, this);
  }

  public boolean addSeasonStat(SeasonStats aSeasonStat)
  {
    boolean wasAdded = false;
    if (seasonStats.contains(aSeasonStat)) { return false; }
    Player existingPlayer = aSeasonStat.getPlayer();
    boolean isNewPlayer = existingPlayer != null && !this.equals(existingPlayer);
    if (isNewPlayer)
    {
      aSeasonStat.setPlayer(this);
    }
    else
    {
      seasonStats.add(aSeasonStat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeasonStat(SeasonStats aSeasonStat)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeasonStat, as it must always have a player
    if (!this.equals(aSeasonStat.getPlayer()))
    {
      seasonStats.remove(aSeasonStat);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeasonStatAt(SeasonStats aSeasonStat, int index)
  {  
    boolean wasAdded = false;
    if(addSeasonStat(aSeasonStat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasonStats()) { index = numberOfSeasonStats() - 1; }
      seasonStats.remove(aSeasonStat);
      seasonStats.add(index, aSeasonStat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeasonStatAt(SeasonStats aSeasonStat, int index)
  {
    boolean wasAdded = false;
    if(seasonStats.contains(aSeasonStat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeasonStats()) { index = numberOfSeasonStats() - 1; }
      seasonStats.remove(aSeasonStat);
      seasonStats.add(index, aSeasonStat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeasonStatAt(aSeasonStat, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Team> copyOfTeams = new ArrayList<Team>(teams);
    teams.clear();
    for(Team aTeam : copyOfTeams)
    {
      aTeam.removePlayer(this);
    }
    for(int i=seasonStats.size(); i > 0; i--)
    {
      SeasonStats aSeasonStat = seasonStats.get(i - 1);
      aSeasonStat.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "jerseyNumber" + ":" + getJerseyNumber()+ "," +
            "rating" + ":" + getRating()+ "," +
            "position" + ":" + getPosition()+ "," +
            "seasonsPlayed" + ":" + getSeasonsPlayed()+ "," +
            "totalGoals" + ":" + getTotalGoals()+ "," +
            "totalAssists" + ":" + getTotalAssists()+ "]";
  }
}
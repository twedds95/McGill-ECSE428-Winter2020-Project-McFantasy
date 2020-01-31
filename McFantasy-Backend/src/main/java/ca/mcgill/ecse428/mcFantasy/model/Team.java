/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/


import java.util.*;

// line 17 "McFantasyModel.ump"
public class Team
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int MaxRating = 77;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Team Attributes
  private int teamID;
  private String name;
  private int wins;
  private int loses;
  private int ties;
  private int points;
  private int totalRating;

  //Team Associations
  private User user;
  private List<Player> players;
  private List<League> leagues;
  private List<Game> games;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Team(int aTeamID, String aName, int aWins, int aLoses, int aTies, int aPoints, int aTotalRating, User aUser)
  {
    teamID = aTeamID;
    name = aName;
    wins = aWins;
    loses = aLoses;
    ties = aTies;
    points = aPoints;
    totalRating = aTotalRating;
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create team due to user");
    }
    players = new ArrayList<Player>();
    leagues = new ArrayList<League>();
    games = new ArrayList<Game>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTeamID(int aTeamID)
  {
    boolean wasSet = false;
    teamID = aTeamID;
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

  public boolean setWins(int aWins)
  {
    boolean wasSet = false;
    wins = aWins;
    wasSet = true;
    return wasSet;
  }

  public boolean setLoses(int aLoses)
  {
    boolean wasSet = false;
    loses = aLoses;
    wasSet = true;
    return wasSet;
  }

  public boolean setTies(int aTies)
  {
    boolean wasSet = false;
    ties = aTies;
    wasSet = true;
    return wasSet;
  }

  public boolean setPoints(int aPoints)
  {
    boolean wasSet = false;
    points = aPoints;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalRating(int aTotalRating)
  {
    boolean wasSet = false;
    totalRating = aTotalRating;
    wasSet = true;
    return wasSet;
  }

  public int getTeamID()
  {
    return teamID;
  }

  public String getName()
  {
    return name;
  }

  public int getWins()
  {
    return wins;
  }

  public int getLoses()
  {
    return loses;
  }

  public int getTies()
  {
    return ties;
  }

  public int getPoints()
  {
    return points;
  }

  public int getTotalRating()
  {
    return totalRating;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetMany */
  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
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
  /* Code from template association_GetMany */
  public Game getGame(int index)
  {
    Game aGame = games.get(index);
    return aGame;
  }

  public List<Game> getGames()
  {
    List<Game> newGames = Collections.unmodifiableList(games);
    return newGames;
  }

  public int numberOfGames()
  {
    int number = games.size();
    return number;
  }

  public boolean hasGames()
  {
    boolean has = games.size() > 0;
    return has;
  }

  public int indexOfGame(Game aGame)
  {
    int index = games.indexOf(aGame);
    return index;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeTeam(this);
    }
    user.addTeam(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPlayers()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    players.add(aPlayer);
    if (aPlayer.indexOfTeam(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPlayer.addTeam(this);
      if (!wasAdded)
      {
        players.remove(aPlayer);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    if (!players.contains(aPlayer))
    {
      return wasRemoved;
    }

    int oldIndex = players.indexOf(aPlayer);
    players.remove(oldIndex);
    if (aPlayer.indexOfTeam(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPlayer.removeTeam(this);
      if (!wasRemoved)
      {
        players.add(oldIndex,aPlayer);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLeagues()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addLeague(League aLeague)
  {
    boolean wasAdded = false;
    if (leagues.contains(aLeague)) { return false; }
    leagues.add(aLeague);
    if (aLeague.indexOfTeam(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aLeague.addTeam(this);
      if (!wasAdded)
      {
        leagues.remove(aLeague);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeLeague(League aLeague)
  {
    boolean wasRemoved = false;
    if (!leagues.contains(aLeague))
    {
      return wasRemoved;
    }

    int oldIndex = leagues.indexOf(aLeague);
    leagues.remove(oldIndex);
    if (aLeague.indexOfTeam(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aLeague.removeTeam(this);
      if (!wasRemoved)
      {
        leagues.add(oldIndex,aLeague);
      }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGames()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addGame(Game aGame)
  {
    boolean wasAdded = false;
    if (games.contains(aGame)) { return false; }
    games.add(aGame);
    if (aGame.indexOfTeam(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aGame.addTeam(this);
      if (!wasAdded)
      {
        games.remove(aGame);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeGame(Game aGame)
  {
    boolean wasRemoved = false;
    if (!games.contains(aGame))
    {
      return wasRemoved;
    }

    int oldIndex = games.indexOf(aGame);
    games.remove(oldIndex);
    if (aGame.indexOfTeam(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aGame.removeTeam(this);
      if (!wasRemoved)
      {
        games.add(oldIndex,aGame);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGameAt(Game aGame, int index)
  {  
    boolean wasAdded = false;
    if(addGame(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGameAt(Game aGame, int index)
  {
    boolean wasAdded = false;
    if(games.contains(aGame))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGames()) { index = numberOfGames() - 1; }
      games.remove(aGame);
      games.add(index, aGame);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGameAt(aGame, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
    ArrayList<Player> copyOfPlayers = new ArrayList<Player>(players);
    players.clear();
    for(Player aPlayer : copyOfPlayers)
    {
      aPlayer.removeTeam(this);
    }
    ArrayList<League> copyOfLeagues = new ArrayList<League>(leagues);
    leagues.clear();
    for(League aLeague : copyOfLeagues)
    {
      aLeague.removeTeam(this);
    }
    ArrayList<Game> copyOfGames = new ArrayList<Game>(games);
    games.clear();
    for(Game aGame : copyOfGames)
    {
      if (aGame.numberOfTeams() <= Game.minimumNumberOfTeams())
      {
        aGame.delete();
      }
      else
      {
        aGame.removeTeam(this);
      }
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "teamID" + ":" + getTeamID()+ "," +
            "name" + ":" + getName()+ "," +
            "wins" + ":" + getWins()+ "," +
            "loses" + ":" + getLoses()+ "," +
            "ties" + ":" + getTies()+ "," +
            "points" + ":" + getPoints()+ "," +
            "totalRating" + ":" + getTotalRating()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}
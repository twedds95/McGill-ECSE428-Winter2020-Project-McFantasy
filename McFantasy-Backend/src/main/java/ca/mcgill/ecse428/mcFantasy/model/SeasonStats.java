/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/



// line 42 "McFantasyModel.ump"
public class SeasonStats
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SeasonStats Attributes
  private int year;
  private int goals;
  private int assists;

  //SeasonStats Associations
  private Player player;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SeasonStats(int aYear, int aGoals, int aAssists, Player aPlayer)
  {
    year = aYear;
    goals = aGoals;
    assists = aAssists;
    boolean didAddPlayer = setPlayer(aPlayer);
    if (!didAddPlayer)
    {
      throw new RuntimeException("Unable to create seasonStat due to player");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setYear(int aYear)
  {
    boolean wasSet = false;
    year = aYear;
    wasSet = true;
    return wasSet;
  }

  public boolean setGoals(int aGoals)
  {
    boolean wasSet = false;
    goals = aGoals;
    wasSet = true;
    return wasSet;
  }

  public boolean setAssists(int aAssists)
  {
    boolean wasSet = false;
    assists = aAssists;
    wasSet = true;
    return wasSet;
  }

  public int getYear()
  {
    return year;
  }

  public int getGoals()
  {
    return goals;
  }

  public int getAssists()
  {
    return assists;
  }
  /* Code from template association_GetOne */
  public Player getPlayer()
  {
    return player;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPlayer(Player aPlayer)
  {
    boolean wasSet = false;
    if (aPlayer == null)
    {
      return wasSet;
    }

    Player existingPlayer = player;
    player = aPlayer;
    if (existingPlayer != null && !existingPlayer.equals(aPlayer))
    {
      existingPlayer.removeSeasonStat(this);
    }
    player.addSeasonStat(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Player placeholderPlayer = player;
    this.player = null;
    if(placeholderPlayer != null)
    {
      placeholderPlayer.removeSeasonStat(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "year" + ":" + getYear()+ "," +
            "goals" + ":" + getGoals()+ "," +
            "assists" + ":" + getAssists()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "player = "+(getPlayer()!=null?Integer.toHexString(System.identityHashCode(getPlayer())):"null");
  }
}
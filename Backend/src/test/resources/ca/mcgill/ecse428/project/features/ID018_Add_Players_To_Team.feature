Feature: User Adds Players to their Team

As an AppUser
I would like to add players to my team roster

Scenario Outline: User adds players to his team, and is still below the max rating (Normal Flow)

Given user with email "<email>" has an existing team "<team>"
Given the players with the following information exist
  |     name  |    position  |   rating   |  stillPlaying  |
  |  Player1  |  forward     |      7     |    yes         |
  |  Player2  |  goalie      |    7       |    yes         |
  |  Player3  |  wing        |      7     |    yes         |
  |  Player4  |  defense     |    7       |    yes         |
  |  Player5  |  middle      |      7     |    yes         |
  |  Player6  |  attacker    |    7       |    yes         |
  |  Player7  |  middle      |      7     |    yes         |
  |  Player8  |  defender    |    7       |    yes         |
  |  Player9  |  forward     |      7     |    yes         |
  |  Player10 |  defender    |    7       |    yes         |
  |  Player11 |  defender    |      7     |    yes         |
When the user "<email>" adds the following players to his team "<team>"
  |     name  |    position  |   rating   |  stillPlaying  |
  |  Player1  |  forward     |      7     |    yes         |
  |  Player2  |  goalie      |    7       |    yes         |
  |  Player3  |  wing        |      7     |    yes         |
  |  Player4  |  defense     |    7       |    yes         |
  |  Player5  |  middle      |      7     |    yes         |
  |  Player6  |  attacker    |    7       |    yes         |
  |  Player7  |  middle      |      7     |    yes         |
  |  Player8  |  defender    |    7       |    yes         |
  |  Player9  |  forward     |      7     |    yes         |
  |  Player10 |  defender    |    7       |    yes         |
  |  Player11 |  defender    |      7     |    yes         |
Then the user's "<team>" will have the all the players and total rating of 77

Examples:

|     email     |    team      |
|  doe@mail.com |  Rascals     |
|  doe@mail.com |  Baracuda    |

  Scenario Outline: User adds players to his team, but exceeds the max rating (Error Flow)

    Given user with email "<email>" has an existing team "<team>"
    Given the players with the following information exist
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player1  |  forward     |      7     |    yes         |
      |  Player2  |  goalie      |    7       |    yes         |
      |  Player3  |  wing        |      7     |    yes         |
      |  Player4  |  defense     |    7       |    yes         |
      |  Player5  |  middle      |      7     |    yes         |
      |  Player6  |  attacker    |    7       |    yes         |
      |  Player7  |  middle      |      7     |    yes         |
      |  Player8  |  defender    |    7       |    yes         |
      |  Player9  |  forward     |      7     |    yes         |
      |  Player10 |  defender    |    7       |    yes         |
      |  Player11 |  defender    |      7     |    yes         |
    Given  the user "<email>" has the following players on his team "<team>"
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player1  |  forward     |      7     |    yes         |
      |  Player2  |  goalie      |    7       |    yes         |
      |  Player3  |  wing        |      7     |    yes         |
      |  Player4  |  defense     |    7       |    yes         |
      |  Player5  |  middle      |      7     |    yes         |
      |  Player6  |  attacker    |    7       |    yes         |
      |  Player7  |  middle      |      7     |    yes         |
      |  Player8  |  defender    |    7       |    yes         |
      |  Player9  |  forward     |      7     |    yes         |
      |  Player10 |  defender    |    7       |    yes         |
    When the user "<email>" adds the following players to his team "<team>"
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player11 |  defender    |      10     |    yes         |
    Then an "Cannot add player to team, exceeds the max rating of 77." message is issued

    Examples:

      |     email     |    team      |
      |  doe@mail.com |  Rascals     |
      |  doe@mail.com |  Baracuda    |

  Scenario Outline: User tries to add a player already on his team (Error Flow)

    Given user with email "<email>" has an existing team "<team>"
    Given the players with the following information exist
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player1  |  forward     |      7     |    yes         |
      |  Player2  |  goalie      |    7       |    yes         |
      |  Player3  |  wing        |      7     |    yes         |
      |  Player4  |  defense     |    7       |    yes         |
      |  Player5  |  middle      |      7     |    yes         |
    Given  the user "<email>" has the following players on his team "<team>"
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player1  |  forward     |      7     |    yes         |
      |  Player2  |  goalie      |    7       |    yes         |
      |  Player3  |  wing        |      7     |    yes         |
      |  Player4  |  defense     |    7       |    yes         |
      |  Player5  |  middle      |      7     |    yes         |
    When the user "<email>" adds the following players to his team "<team>"
      |     name  |    position  |   rating   |  stillPlaying  |
      |  Player3  |  wing        |      7     |    yes         |
    Then an "This player is already on your team." message is issued

    Examples:

      |     email     |    team      |
      |  doe@mail.com |  Rascals     |
      |  doe@mail.com |  Baracuda    |
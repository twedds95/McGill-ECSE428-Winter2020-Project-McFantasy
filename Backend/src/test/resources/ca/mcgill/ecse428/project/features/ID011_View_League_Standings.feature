Feature: View League Standings

As a user of McFantasy Sports Web App
I would like to be able to view the standings of a league

Scenario Outline: View Standings of an existing League (Normal Flow)

Given user with email "<email>" is logged in
  Given a league with name "<league>" exists
  Given  the league "<league>" has the following teams with the the followings statistics
  | teamName  |      userEmail  |   userName  |   points    |
  |   team1   | user1@mail.com  |  userName1  |    21       |
  |   team2   | user2@mail.com  |  userName2  |    32       |
  |   team3   | user3@mail.com  |  userName3  |    23       |
  |   team4   | user4@mail.com  |  userName4  |    27       |
  |   team5   | user5@mail.com  |  userName5  |    25       |
When the user attemps to view the standings of league "<league>"
Then the standings in the league "<league>" are shown to be the following
  | teamName  |standing|
  |team1      |5       |
  | team2     |1       |
  | team3     |4       |
  |team 4     |2       |
  | team5     |3       |

Examples:

|     email     |   league   |
|  doe@mail.com |  Champs    |
|  doe@mail.com |    Mtl     |

  Scenario Outline: View Standings of an existing League, with teams with same number of points (ties)
  but different number of wins (Alternate Flow)

    Given user with email "<email>" is logged in
    Given a league with name "<league>" exists
    Given  the league "<league>" has the following teams with the the followings statistics
      | teamName  |      userEmail  |   userName  |   points    |  wins  |
      |   team1   | user1@mail.com  |  userName1  |    22       |  6     |
      |   team2   | user2@mail.com  |  userName2  |    22       |  7     |
      |   team3   | user3@mail.com  |  userName3  |    25       |  12    |
      |   team4   | user4@mail.com  |  userName4  |    25       | 11     |
      |   team5   | user5@mail.com  |  userName5  |    23       |  9     |
    When the user attemps to view the standings of league "<league>"
    Then the standings in the league "<league>" are shown to be the following
      | teamName  |standing|
      |team1      |5       |
      | team2     |4       |
      | team3     |1       |
      |team 4     |2       |
      | team5     |3       |

    Examples:

      |     email     |   league   |
      |  doe@mail.com |  Champs    |
      |  doe@mail.com |    Mtl     |


Scenario Outline: Attempt to View Standings With Invalid League Name (Error Flow)

  Given user with email "<email>" is logged in
  Given a league with name "<league>" deos not exist
  When the user attemps to view the teams in the league "<league>"
  Then an error message will appear "League does not exist"

Examples:

|     email     |    league      |
|  doe@mail.com |      Champs    |
|  doe@mail.com |       Mtl      |
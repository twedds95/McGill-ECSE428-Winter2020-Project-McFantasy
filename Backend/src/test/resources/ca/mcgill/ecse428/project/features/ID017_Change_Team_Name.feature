Feature: Change Team Name

As a user of McFantasy Sports Web App
I would like to change the name of an existing team

Scenario Outline: Change Team Name (Normal Flow)

  Given user with email "<email>" has an existing team "<team>"
  When the user with email "<email>" attemps to change a team name from "<team>" to "<newTeamName>"
  Then the user "<email>" new team name is "<newTeamName>"

Examples:

|     email     |    team      |    newTeamName |
|  doe@mail.com |  Rascals     |    Rascals2    |
|  doe@mail.com |  Baracuda    |McGillSportsTeam|


Scenario Outline: Attempt to Change Team Name to an Invalid Name (Error Flow)

  Given user with email "<email>" has an existing team "<team>"
  When the user with email "<email>" attemps to change a team name from "<team>" to "<newTeamName>"
  Then an "Team name is not valid" message is issued

Examples:

  |     email     |    team      |    newTeamName |
  |  doe@mail.com |  Rascals     |                |
  |  doe@mail.com |  Baracuda    |         1      |

  Scenario Outline: Attempt to Change Team Name to a Name that is already used in a League that the Team is in (Error Flow)

    Given user with email "<email>" has an existing team "<team>"
    Given user with email "<email2>" has an existing team "<newTeamName>"
    Given a league with name "<league>" exists
    Given league "<league>" has a team "<team>" in the league
    Given league "<league>" has a team "<newTeamName>" in the league
    When the user with email "<email>" attemps to change a team name from "<team>" to "<newTeamName>"
    Then an "Team name is already used in one of your leagues" message is issued

    Examples:

      |     email     | email2     |    team      |    newTeamName |      league    |
      |  doe@mail.com |jad@mail.com|  Rascals     |    Rascals2    |     LosLeague  |
      |  doe@mail.com |jad@mail.com|  Baracuda    |McGillSportsTeam|    Matata      |
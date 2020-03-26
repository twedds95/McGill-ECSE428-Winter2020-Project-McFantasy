Feature: Join Existing League

As an AppUser
I would like to join an existing fantasy league
So that I can access all services and see all data from the given league

Scenario Outline: User inputs a valid league name (Normal Flow)

Given user with email "<email>" has an existing team "<team>"
Given a league with name "<leagueName>" exists
When the user attemps to join League "<leagueName>" with their team "<team>"
Then the user's "<team>" will be added to the league "<leagueName>" 

Examples:

|     email     |    team      |       leagueName      |
|  doe@mail.com |  Rascals     |      Champs           |
|  doe@mail.com |  Baracuda    |    McGill Sports Team |

Scenario Outline: User inputs an invalid league name (Error Flow)

Given user with email "<email>" has an existing team "<team>"
Given a league with name "<leagueName>" deos not exist
When the user attemps to join League "<leagueName>" with their team "<team>"
Then an error message will appear "League does not exist"

Examples:

|     email     |    team      |       leagueName      |
|  doe@mail.com |  Rascals     |      Champs           |
|  doe@mail.com |  Baracuda    |    McGill Sports Team |

  Scenario Outline: Attempt to Join a League with a Team Name that is already used in the same League (Error Flow)

    Given user with email "<doe@mail.com >" has an existing team "<team>"
    Given user with email "jad@mail.com" has an existing team "<team>"
    Given a league with name "<league>" exists
    Given league "<league>" has a team "<team>" in the league
    When the user attemps to join League "<league>" with their team "<team>"
    Then an "Team name is already used in this league, please modify your team name before joining." message is issued

    Examples:

      |     email     |    team      |    newTeamName |      league    |
      |  doe@mail.com |  Rascals     |    Rascals2    |     LosLeague  |
      |  doe@mail.com |  Baracuda    |McGillSportsTeam|    Matata      |
Feature: View Teams in a League

As a user of McFantasy Sports Web App
I would like to be able to view the teams that are already part of a league

Scenario Outline: View Teams of an existing League (Normal Flow)

Given user with email "<email>" is logged in
  Given a league with name "<league>" exists
  Given  the league "<league>" has the following teams
  | teamName  |      userEmail  |   userName  |
  |   team1   | user1@mail.com  |  userName1  |
  |   team2   | user2@mail.com  |  userName2  |
  |   team3   | user3@mail.com  |  userName3  |
  |   team4   | user4@mail.com  |  userName4  |
  |   team5   | user5@mail.com  |  userName5  |
When the user attemps to view the teams in the league "<league>"
Then the list of teams "<teams>" in the league "<league>" is shown

Examples:

|     email     |                teams               |   league   |
|  doe@mail.com |  team1,team2,team3,team4,team5     |   Champs   |
|  doe@mail.com |  team1,team2,team3,team4,team5     |    Mtl     |


Scenario Outline: Attempt to View Teams With Invalid League Name (Error Flow)

  Given user with email "<email>" is logged in
  Given a league with name "<league>" deos not exist
  When the user attemps to view the teams in the league "<league>"
  Then an error message will appear "League does not exist"

Examples:

|     email     |    league      |
|  doe@mail.com |      Champs    |
|  doe@mail.com |       Mtl      |
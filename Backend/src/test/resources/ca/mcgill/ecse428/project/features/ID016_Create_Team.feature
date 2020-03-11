Feature: Create Team

As a user of McFantasy Sports Web App
I would like to create a team
So that this team can be added to the league

Scenario Outline: Create Team (Normal Flow)

Given user with email "<email>" is logged in
When the user attemps to create a new team "<team>"
Then the user's "<team>" will be created 

Examples:

|     email     |    team      |
|  doe@mail.com |  Rascals     |
|  doe@mail.com |  Baracuda    |


Scenario Outline: Attempt to Create Team With Invalid Name (Error Flow)

  Given user with email "<email>" is logged in
  When the user attemps to create a new team "<team>"
  Then an "Team name is not valid" message is issued

Examples:

|     email     |    team      |
|  doe@mail.com |              |
|  doe@mail.com |     1        |
Feature: Create League

As a user of McFantasy Sports Web App
I would like to create a league
So that teams can join this league

Scenario Outline: Create League (Normal Flow)

Given user "<email>" is logged in
When the user inputs a valid league name "<league_name>" and clicks Create League button
Then the new league with name "<league_name>" will be created

Examples:
|   email      |    league_name  |
| jo@mail.com  |  DucksWorth     |

Scenario Outline: Attempt to Create League With Invalid Name (Error Flow)

Given user "<email>" is logged in
When the user inputs an invalid league name "<league_name>" and clicks Create League button
Then an "League name is not valid" message is issued

Examples: 

|   email      |    league_name  |
| jo@mail.com  |                 |
| jo@mail.com  |      1          |

Scenario Outline: Attempt to Create League With a Name that is Already Used (Error Flow)

Given user "<email>" is logged in
Given a league with "<league_name>" exists
When the user inputs an invalid league name "<league_name>" and clicks Create League button
Then an "League name is already used" message is issued

Examples:

|   email      |    league_name  |
| jo@mail.com  |    DucksWorth   |
| jo@mail.com  |    The Champs   |
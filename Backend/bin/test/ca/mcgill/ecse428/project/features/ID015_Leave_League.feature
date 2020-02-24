Feature: Leave League

As a user of McFantasy Sports Web App
I would like to leave a leave that I am currently part of
So that I would no longer be able to access the services in this league

Scenario Outline: Leave League (Normal Flow)

Given user with email "<email>" has an existing team "<team>"
Given a league with name "<leagueName>" exists
Given league "<leagueName>" has a team "<team>" in the league
When the user attemps to leave the League "<leagueName>" with their team "<team>"
Then the user's "<team>" will be removed from the league "<leagueName>" 

Examples:

|     email     |    team      |       leagueName      |
|  doe@mail.com |  Rascals     |      Champs           |
|  doe@mail.com |  Baracuda    |    McGill Sports Team |

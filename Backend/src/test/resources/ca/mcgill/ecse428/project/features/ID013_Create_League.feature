Feature: Create League

As a user of McFantasy Sports Web App
I would like to create a league
So that teams can join this league

Scenario Outline: Create League (Normal Flow)

Given user<email> is logged in
And is on the Create League page
When the user inputs a valid <league_name>
And clicks Create League button
Then the new league will be created
And the user should be presented with a success message

Scenario Outline: Attempt to Create League With Invalid Name (Error Flow)

Given user<email> is logged in
And is on the Create League page
When the user inputs "" as a <league_name>
And clicks Create League button
Then the user should be presented with a failure message
And the league will not be created
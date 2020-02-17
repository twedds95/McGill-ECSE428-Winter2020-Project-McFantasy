Feature: Leave League

As a user of McFantasy Sports Web App
I would like to leave a leave that I am currently part of
So that I would no longer be able to access the services in this league

Scenario Outline: Leave League (Normal Flow)

Given user<email> is logged in
And the user is on the League Settings page of league<leagueName>
When the user clicks the "Leave League" button
And the user clicks a confirmation button
Then the user will no longer be part of this league
And the user should be presented with a success message
Feature: Create Team

As a user of McFantasy Sports Web App
I would like to create a team
So that this team can be added to the league

Scenario Outline: Create Team (Normal Flow)

Given user<email> is logged in
And is on the Create Team page
When the user inputs a valid <team_name>
And clicks Create Team button
Then the new team will be created
And the user should be presented with a success message
And should be directed to the Add Players page

Scenario Outline: Attempt to Create Team With Invalid Name (Error Flow)

Given user<email> is logged in
And is on the Create Team page
When the user inputs "" as a <team_name>
And clicks Create Team button
Then the user should be presented with a failure message
And the team will not be created
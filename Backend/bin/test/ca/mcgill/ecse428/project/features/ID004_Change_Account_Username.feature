Feature: Change Account Username

As a user of McFantasy Sports Web App
I would like to change my username on an existing account
So that I can set it as my new Account Username

Scenario Outline: Change Account Username (Normal Flow)

Given user<email> is logged in
And is on the Edit Profile page
When the user clicks the Change Username button
And inputs a valid <username>
And clicks the Confirm button
Then the new Account Username will be set
And the user should be presented with a success message
And should be redirected back to the Edit Profile page

Scenario Outline: The user changes account username to an invalid format username (Error Flow)

Given user<email> is logged in
And is on the Edit Profile page
When the user clicks the Change Username button
And inputs ! as a new username
And clicks the Confirm button
Then the user should be presented with a failure message
And their username should not change
And should be redirected back to the Edit Profile page

Scenario Outline: The user changes account username to a pre-existing username (Error Flow)
 
Given user <username> is logged in
And is on the Edit Profile page
When the user<username> selects to change username option
And they enter a pre-existing username
Then the user<username>should be presented with a failure message
And their username should not change
And the user<username> should be redirected back to the Edit Profile page




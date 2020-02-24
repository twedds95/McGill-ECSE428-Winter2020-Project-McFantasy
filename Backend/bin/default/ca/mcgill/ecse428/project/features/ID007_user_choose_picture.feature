Feature: Choose a Profile Picture

As a user of McFantasy Sports Web App
I would like to choose a picture from my local photo library
So that I can set it as my Profile Picture

Scenario Outline: Pick a compatible profile picture (Normal Flow)

Given user<email> is logged in
And is on the Edit Profile page
When the user clicks the Change Profile Picture button
And chooses a compatible picture from their local photo library
And clicks the Confirm button
Then the new Profile Picture will be set
And the user should be presented with a success message
And should be redirected back to the Edit Profile page

Scenario Outline: Pick an incompatible profile picture (Error Flow)

Given user<email> is logged in
And is on the Edit Profile page
When the user clicks the Change Profile Picture button
And chooses an incompatible picture from their local photo library
And clicks the Confirm button
Then the user should be presented with a failure message
And should be redirected back to the Edit Profile page


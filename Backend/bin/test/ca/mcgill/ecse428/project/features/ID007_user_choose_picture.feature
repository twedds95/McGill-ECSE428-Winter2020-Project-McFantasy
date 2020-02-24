Feature: Choose a Profile Picture

As a user of McFantasy Sports Web App
I would like to choose a picture from my local photo library
So that I can set it as my Profile Picture

Scenario Outline: Pick a compatible profile picture (Normal Flow)

Given user with email "<email>" is logged in
When the user clicks the Change Profile Picture button
And chooses a compatible picture from their local photo library
Then the new Profile Picture will be set
And the user should be presented with a success message


Scenario Outline: Pick an incompatible profile picture (Error Flow)

Given user with email "<email>" is logged in
When the user clicks the Change Profile Picture button
And chooses a incompatible picture from their local photo library
Then an "Picture format is not compatible" message is issued


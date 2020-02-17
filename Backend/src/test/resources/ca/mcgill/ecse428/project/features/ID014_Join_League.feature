Story: Join Existing League

As an AppUser
I would like to join an existing fantasy league
So that I can access all services and see all data from the given league

Scenario Outline: User inputs a valid league name (Normal Flow)

Given user<email> is logged in
And is on the User Home Page
When the user clicks the Join League button
And inputs a valid league name<leagueName>
And clicks the Confirm button
Then the player will be added to the league<leagueName>
And a team will be created for them in the league<leagueName>
And the user should be presented with a success message
And the League page will appear for league<leagueName>

Scenario Outline: User inputs an invalid league name (Error Flow)

Given user<email> is logged in
And is on the User Home Page
When the user clicks the Join League button
And inputs an invalid league name<leagueName>
And clicks the Confirm button
Then an error message will appear "League with <leagueName> does not exist"
Amd the User Home Page will reappear
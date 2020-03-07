Feature: Create New Account

I would like to become a user of the McFantasy Sports Web App
So that I can create or join fantasy sports leagues 

Scenario Outline: People with different unique emails (Normal Flow)

Given person "<name>" with email "<email>" and password "<password>"
When they request to create a new account on McFantasy Sports
Then a new user with email "<email>" and password "<password>" is generated

Examples:

| name              | email         	     | password     | confirmedPassword |
| Archie Andrews    | arch@gmail.com	     |Operator      |Operator           |
| Betty Cooper      | betty@hotmail.com    |12345abcde      |12345abcde    	    |
| Jughead Jones     | jonesy@mail.com      |67890$asdf      |67890$asdf    	    |
| Veronica Lodge    | vLodge@gmail.com     |zxcvb1234       |zxcvb1234    	    |

Scenario Outline: Existing user attempts to become a user (Error Flow)

Given person "<name>" with email "<email>" is user of McFantasy Sports
When person requests to create a new account on McFantasy Sports
Then a "User with this email has already been created!" message is issued

Examples:

| name              | email         	     |
| Archie Andrews    | arch@gmail.com	     |
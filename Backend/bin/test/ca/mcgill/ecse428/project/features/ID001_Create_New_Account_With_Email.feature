Feature: Create New Account

I would like to become a user of the McFantasy Sports Web App
So that I can create or join fantasy sports leagues 

Scenario Outline: People with different unique emails (Normal Flow)

Given person <name> with email <email> and password <password> and matching confirmed password <confirmedPassword>
When person <name> requests to create a new account on McFantasy Sports
Then a new account with <email> and <password> is generated

| name                  | email         	     | password      | confirmedPassword |
| Archie Andrews  | arch@gmail.com	     |Operator        |Operator       	    |
| Betty Cooper      | betty@hotmail.com  |12345abcde   |12345abcde    	    |
| Jughead Jones   | jonesy@mail.com    |67890$asdf    |67890$asdf    	    |
| Veronica Lodge  | vLodge@gmail.com |zxcvb1234      |zxcvb1234    	    |

Scenario Outline: Existing user attempts to become a user (Error Flow)

Given Bill Jones with email billy@gmail.com is user of McFantasy Sports
When Bill Jones with email billy@gmail.com requests to create a new account 
Then an "Already registered" message is issued


Feature: Create New Username

I would like to create a username 
When creating a new account on the McFantasy Sports Web App

Scenario Outline: Person with valid username tries to change an account (Normal Flow)

Given person <name> with email <email>
When person <name> requests to create a valid <username> when creating a new account on McFantasy Sports
Then a new account with <email> and <username> is generated

Scenario Outline: Person with an invalid username tries to create an account (Error Flow)

Given Fred Smith uses username ! to create a new account
When Fred Smith requests to create a new account
Then a “Invalid Username” message is issued


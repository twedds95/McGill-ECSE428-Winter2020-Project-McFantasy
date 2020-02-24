Feature: Create New Password

I would like to create a password 
When creating a new account on the McFantasy Sports Web App

Scenario Outline: People with matching valid passwords tries to create an account (Normal Flow)

Given person <name> with email <email>
When person <name> requests to create a valid <password> when creating a new account on McFantasy Sports
Then a new account with <email> and <password> is generated if <password> and <confirmedPassword> match

Scenario Outline: Person with unmatching passwords tries to create an account (Error Flow)

Given Fred Smith uses password 12345qwerty and confirmedPassword 433121qwert to create a new account
When Fred Smith requests to create a new account
Then a "Passwords Do Not Match" message is issued

Scenario Outline: Person with an invalid password tries to create an account (Error Flow)

Given Fred Smith uses password ! to create a new account
When Fred Smith requests to create a new account
Then a “Invalid Password” message is issued

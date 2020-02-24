Feature: Delete Account

I would like to delete my account as a user of the McFantasy Sports Web App
So that my information will no longer be stored in the system

Scenario Outline: Valid account and correct password (Normal Flow)

Given person’s email "<email>" and password "<password>" is an existing user
When a person "<email>" requests for their account to be deleted with confirmation password "<confirmPW>" 
Then they’re information will be deleted from the database and their account will become invalid

Examples:

|     email    |   password   |    confirmPW  |
|  jo@mail.com |   CurrentPW1 |    CurrentPW1 |


Scenario Outline: Incorrect password given for account to be deleted (Error Flow)
Given person’s email "<email>" and password "<password>" is an existing user
When a person "<email>" requests for their account to be deleted with confirmation password "<ConfirmPW>" 
Then an "Unauthorized request" message is issued
And a record of the attempt is recorded in the database to limit requests. 

Examples:

|     email    |   password   |    confirmPW  |
|  jo@mail.com |   CurrentPW1 |    CurrentPW1 |
|  jo@mail.com |   CurrentPW1 |    alphasjs1J |
|  jo@mail.com |   CurrentPW1 |    hshjajf12VG|
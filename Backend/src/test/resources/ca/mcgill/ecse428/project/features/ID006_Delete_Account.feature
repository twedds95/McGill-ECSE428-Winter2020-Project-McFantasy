Feature: Delete Account

I would like to delete my account as a user of the McFantasy Sports Web App
So that my information will no longer be stored in the system

Scenario Outline: Valid account and correct password (Normal Flow)

Given person’s email <email> and password <password>, both matching a user recorded in the database
When a person <email> requests for their account to be deleted, they’re information will be deleted from the database and their account will become invalid
Then they will be logged out automatically and redirected to the Login Page

Scenario Outline: Incorrect password given for account to be deleted (Error Flow)
Given Fred Smith uses password PASSWORD to request his account to be deleted
When user Fred Smith requests account deletion from the McFantasy Sports Web App
Then an “Unauthorized request” message is issued
And a record of the attempt is recorded in the database to limit requests. 

Feature: Change Account Username

As a user of McFantasy Sports Web App
I would like to change my username on an existing account
So that I can set it as my new Account Username

Scenario Outline: Change Account Username (Normal Flow)

Given user with email "<email>" is logged in
When the user attempts to change username with username "<username>"
Then the user Account with email "<email>" Username will be set to "<username>"

Examples:
|     email  |   username |
| jd@mail.com|    JDoe    |

Scenario Outline: The user changes account username to an invalid format username (Error Flow)

Given user with email "<email>" is logged in
When the user attempts to change username with username "<username>"
Then an "Please input all required parameters" message is issued

Examples:
|     email  |   username |
| jd@mail.com|            |

Scenario Outline: The user changes account username to a pre-existing username (Error Flow)

Given there exists a user with email "<email1>" and username "<username>"
Given user with email "<email>" is logged in
When the user attempts to change username with username "<username>"
Then an "User with this username already exists!" message is issued

Examples:
|     email  |   username |    email1    |
| jd@mail.com|    JDoe    |  doe@mail.com|




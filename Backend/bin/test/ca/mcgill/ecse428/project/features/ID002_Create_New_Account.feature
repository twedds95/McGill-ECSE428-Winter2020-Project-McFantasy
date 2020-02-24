Feature: Create New Username

I would like to create a username when creating a new account on the McFantasy Sports Web App

Scenario Outline: Person with valid username tries to change an account (Normal Flow)

Given person "<name>" with email "<email>"
When person "<name>" requests to create a new account with username "<username>"
Then a new account with "<email>" and "<username>" is generated

Examples:
| name   |     email  |   username |
|John Doe| jd@mail.com|    JDoe    |

Scenario Outline: Person with an invalid username tries to create an account (Error Flow)

Given person "<name>" with email "<email>"
When person "<name>" requests to create a new account with username "<username>"
Then an "Invalid username" message is issued

Examples:
| name   |     email  |username|
|John Doe| jd@mail.com|    !   |


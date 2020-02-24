Feature: Create New Password

I would like to create a password when creating a new account on the McFantasy Sports Web App

Scenario Outline: People with matching valid passwords tries to create an account (Normal Flow)

Given person "<name>" with email "<email>"
When person "<name>" requests to create a valid "<password>" and confirmation "<confirmedPassword>" when creating a new account 
Then a new account with "<email>" and "<password>" is generated

Examples:

| name              | email         	     | password     | confirmedPassword |
| Archie Andrews    | arch@gmail.com	     |Operator      |Operator           |
| Betty Cooper      | betty@hotmail.com    |12345abcde      |12345abcde    	    |
| Jughead Jones     | jonesy@mail.com      |67890$asdf      |67890$asdf    	    |
| Veronica Lodge    | vLodge@gmail.com     |zxcvb1234       |zxcvb1234    	    |

Scenario Outline: Person with unmatching passwords tries to create an account (Error Flow)

Given person "<name>" with email "<email>"
When person "<name>" requests to create a valid "<password>" and confirmation "<confirmedPassword>" when creating a new account 
Then a "Passwords Do Not Match" message is issued

Examples:

| name              | email         	     | password     | confirmedPassword |
| Archie Andrews    | arch@gmail.com	     |Operator      |    jineo          |
| Betty Cooper      | betty@hotmail.com    |12345abcde      |alphabravo   	    |

Scenario Outline: Person with an invalid password tries to create an account (Error Flow)

Given person "<name>" with email "<email>"
When person "<name>" requests to create a valid "<password>" and confirmation "<confirmedPassword>" when creating a new account 
Then an "Invalid Password" message is issued

Examples:

| name              | email         	     | password     | confirmedPassword |
| Archie Andrews    | arch@gmail.com	     |        !     |    !              |
| Betty Cooper      | betty@hotmail.com      |123           |123                |
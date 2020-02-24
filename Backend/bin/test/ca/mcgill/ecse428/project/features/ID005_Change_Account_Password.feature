
  Feature: Change password
  
  Scenario Outline: The user enters in the wrong current password for the first time 
    Given the user "<user>" with current password "<currentPW>" is logged in
    And they have <numAttempts> failed attempts 
    When the user initiates to change their password with current password "<currPWAttempt>" and new password "<newPW>" and confirmation password "<newPWConf>"
    Then an "Wrong current password" message is issued 
    And their password is still "<currentPW>"
    And their failed attempts should increase to <newNumAttemps>

  Examples: 

|   user    |  currentPW    |    numAttempts     |  currPWAttempt   |   newPW     |   newPWConf    |  newNumAttemps  |
|   joey    |  kiDdo234b    |        2           |     kixxo234b    |   newPW21   |       newPW21  |  3              |
|   john    |  losVeg89v    |        1           |     kiddo234b    |   newPW21   |   newPW21      |  2              |
 
 Scenario Outline: The user enters the wrong current password for the fifth time
    Given the user "<user>" with current password "<currentPW>" is logged in
    And they have <numAttempts> failed attempts 
    When the user initiates to change their password with current password "<currPWAttempt>" and new password "<newPW>" and confirmation password "<newPWConf>"
    Then an "Wrong current password" message is issued 
    And their password is still "<currentPW>"
    And their failed attempts should increase to <newNumAttemps>
    And their account should be locked
    And the user should be logged off
    And a reset password email should be sent to the account holder
     
 Examples: 

|   user    |  currentPW    |    numAttempts     |  currPWAttempt   |   newPW     |   newPWConf    |  newNumAttemps  |
|   joey    |  kiDdo234b    |        4           |     kixxo234b    |   newPW21   |       newPW21  |  5              |
|   john    |  losVeg89v    |        4           |     kiddo234b    |   newPW21   |   newPW21      |  5              |
         
 Scenario Outline: The user enters their password
    Given the user "<user>" with current password "<currentPW>" is logged in
    When the user initiates to change their password with current password "<currPWAttempt>" and new password "<newPW>" and confirmation password "<newPWConf>"
    Then they should receive "<message>"
    And their password will be "<password>" 

Examples: 

|   user    |  currentPW    |  currPWAttempt   |   newPW        |   newPWConf    |   message                                                               |  password    |
|   joey    |  kiDdo234b    |     kiDdo234b    |   CoolCat_123  | CoolCat_123    | Password Changed Successfully!                                          | CoolCat_123  | 
|   john    |  kiDdo234b    |     kiDdo234b    |   Cool123      |   Cool123      | Password should be at least 8 characters!                               | kiDdo234b    |
|   john    |  kiDdo234b    |     kiDdo234b    |   cool1234     |   cool1234     | Password should have a mix of uppercase and lowercase characters!       | kiDdo234b    | 
|   john    |  kiDdo234b    |     kiDdo234b    |   coolKitten   |   coolKitten   | Password should have at least one digit or special character! (@!%$#*^_)| kiDdo234b    | 
|   john    |  kiDdo234b    |     kiDdo234b    |   CoolCat_123  |   CoolCat_213  | Passwors don't match!                                                   | kiDdo234b    |     
      
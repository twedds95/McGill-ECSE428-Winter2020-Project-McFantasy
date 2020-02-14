
  Feature: Change password
  
  Scenario Outline: The user enters in the wrong current password for the first time 
    Given the user is logged in
    And they have less than 4 failed attempts 
     When the user initiates to change their password
     And they enter the wrong current password
     Then they will be asked to re-enter their password or receive an email 
      And their password should not change
      And their failed attempts should increase
  
 Scenario Outline: The user enters the wrong current password for the fifth time
    Given the user is logged in
      And they have entered their current password incorrectly four times
     When the user initiates to change their password
     And they enter the wrong current password
      Then their failed attempts should increase
      And their password should not change
      And their account should be locked
      And the user should be logged off
      And a reset password email should be sent to the account holder
      
 Scenario Outline: The user forgot their password
    Given the user is not logged in
     When the user initiates to change their password
      Then an email should be sent with the password change form
      
 Scenario Outline: The user tries to change their password twice with the same email
    Given the user changed their password with an email
     When the user initiates to change their password with the same email
     Then they should not be allowed to change their password 
      
 Scenario Outline: The user enters their password
    Given the user is changing their password
    And their old password is oldPassword1
     When they enter in $<currentPassword>
     And they enter in $<repeatedPassword>
     Then they should receive $<message>
     And their password will be $<password> 
     
      | currentPassword | repeatedPassword | message                                                                 | password     | 
      | CoolCat_123     | CoolCat_123      | Password Changed Successfully!                                          | CoolCat_123  | 
      | Cool123         | Cool123          | Password should be at least 8 characters!                               | oldPassword1 |
      | cool1234        | cool1234         | Password should have a mix of uppercase and lowercase characters!       | oldPassword1 | 
      | coolKitten      | coolKitten       | Password should have at least one digit or special character! (@!%$#*^_)| oldPassword1 | 
      | CoolCat_123     | CoolCat_12       | Passwors don't match!                                                   | oldPassword1 |
      


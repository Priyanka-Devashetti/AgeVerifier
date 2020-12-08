## AgeVerifier
AgeVerifier is a simple  Android App which verifies user age and responds with text message whether user is allowed to drink or not. 
App is hosted in https://age-verifier.herokuapp.com/ . 
Objective of this test is to evaluate candidate ability to design the test cases and automate the test execution. Scope include validating the Android UI, backend API Service and its integration.

## High Level Design of the App
<img width="839" alt="Screenshot 2020-12-07 at 9 43 45 PM" src="https://user-images.githubusercontent.com/58664897/101546701-192f9a00-39a1-11eb-9fa2-51df7da9f7b9.png">


## Tech Stack
| Programming Language | Kotlin |
|----------------------|--------|
| API                  | Rest Based API Hosted in Heroku |
| Test Automation      | Espresso, Rest Assured, JUnit |
| Build                | Gradle |
| IDE                  | Android Studio |



## Test Case Design
<img width="696" alt="Screenshot 2020-12-08 at 10 16 49 PM" src="https://user-images.githubusercontent.com/58664897/101548021-1df54d80-39a3-11eb-92b8-1e689143ff55.png">


## Test Cases
### UI Test Cases
| Test case ID | Description                                                                                                                                                                                                                                                                                                                                                                      | Test case Type    |
| ------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------ |
| UITC001      | Scenario Outline:Verify the user aged above 18 is allowed to drink<br>Given:Emulator launched successfully<br>When entered "<Age>" is above 18 and Clicked on the button<br>Then user should see message syaing "you are allowed to Drink"<br>\*Examples\*<br>\*Age\*<br>\*28,35 etc\*                                                                                           | Positive       |
| UITC002      | Scenario Outline:Verify the user aged below 18 is not allowed to drink<br>Given:Emulator launched successfully<br>When entered "<Age>" is below 18 and Clicked on the button<br>Then user should see message syaing "you are not allowed to Drink yet"<br>\*Examples\*<br>\*Age\*<br>\*10,15 etc\*                                                                               | Positive       |
| UITC003      | Scenario Outline:Verify user should experience server error when nagative age values entered in to text box<br>Given:Emulator launched successfully<br>When Nagative  "<Age>" entered in text box<br>Then user should see message syaing "Interal 500 server error"<br>\*Examples\*<br>\*Age\*<br>\*10,15 etc\*<br>                                                              | Nagative       |
| UITC004      | Sceanrio Outline:Verify label text displayed on App UI and text filed should accept only numeric values.<br>Given Emulator launched successfully<br>When user tries to enter apart from numeric values<br>Then text filed should not accept any other charcters apart from numeric and user should be able to see the lable text "insert the age and press the button" on App UI | Positive       |
| UITC005      | Scenario Outline:Verify button is clickable on App UI<br>Given:Emulator launched successfully<br>When user enters a"<Age>" and try to click on button<br>Then button should be clickable for user<br>\*Examples\*<br>\*Age\*<br>\*10,15 etc\*<br>                                                                                                                                | Positive       |
  
  
### API -Service Test Cases
| Test case ID | Description                                                                                                                                                                                                                                                                                                                                             | Test case Type |
| ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------- |
| APITC001     | Scenario Outline:Verify Age verifier API returing status response code<br>Given End point must be accessible<br>When user hits "<End point>" and enters "<Age>" in request body<br>Then user must recive the "<Status\_code>"<br>Examples:<br>\*End point\*\*Age\*Status\_code\*<br>\*https://age-verifier.herokuapp.com/age/verifier\*\*20\*200\*      | Positive       |
| APITC002     | "Scenario Outline:Verify Age verifier API returing true when age input between 18 -50<br>Given End point must be accessible<br>When user hits ""<End point>"" and enters ""<Age>"" in request body<br>Then user must recive the {"isValid":true} text<br>Examples:<br>\*End point\*\*Age\*<br>\*https://age-verifier.herokuapp.com/age/verifier\*\*20\* | Positive       |
| APITC003     | "Scenario Outline:Verify Age verifier API returing false when age input below 18<br>Given End point must be accessible<br>When user hits ""<End point>"" and enters ""<Age>"" in request body<br>Then user must recive the {"isValid":false} text<br>Examples:<br>\*End point\*\*Age\*<br>\*https://age-verifier.herokuapp.com/age/verifier\*\*10\*     | Positive       |
| APITC004     | "Scenario Outline:Verify Age verifier API returing null when age input above 50<br>Given End point must be accessible<br>When user hits ""<End point>"" and enters ""<Age>"" in request body<br>Then user must recive the<br>Examples:<br>\*End point\*\*Age\*<br>\*https://age-verifier.herokuapp.com/age/verifier\*\*55\*                             | Positive       |
| APITC004     | "Scenario Outline:Verify Age verifier API when user enters nagative age<br>Given End point must be accessible<br>When user hits ""<End point>"" and enters ""<Age>"" in request body<br>Then user must recive Internal server error<br>Examples:<br>\*End point\*\*Age\*<br>\*https://age-verifier.herokuapp.com/age/verifier\*\*-100\*                 | Nagative       |


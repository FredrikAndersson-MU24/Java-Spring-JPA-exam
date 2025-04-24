# Testing

SETUP
application-test.properties

| Name             | 
|------------------|
| `MYSQL_TEST_URL` |
| `MYSQL_USERNAME` |
| `MYSQL_PASSWORD` |

## UserControllerComponentTest - Component tests for the UserController

### The purpose of the component tests

The purpose of the component tests is to verify the functionality between layers in the application. In the case of these
specific component tests, the interaction between

### Test strategy

The POST request should contain a UserCreationDTO request body, with the fields `username`, `firstName` and
`lastName`.  
First is a positive test, checking that the expected response is sent if a valid request is received.  
The later tests
are negative tests, to test if the correct exception is thrown if the request body is not valid.

- ### Positive test to verify the response if a valid request is received

When receiving a POST request with a valid UserCreationDTO request body to the "/users" end-point, the addUser() method
should respond with HTTP status 200 and a UserMinimalDTO.

| Test                                                                       | What part of the request body is tested?      |
|----------------------------------------------------------------------------|-----------------------------------------------|
| addUserShouldRespondWithUserMinimalDTOAndStatusOk()                        | Valid UserCreationDTO                         |
| addUserShouldRespondWithUserMinimalDTOAndStatusOkWhenUsernameIsMaxLength() | Valid UserCreationDTO, w username of 32 chars |

- ### A series of tests to verify that a BadRequestException is thrown if the POST request body does not meet the validation criteria-

They should all respond with HTTP Status code 400 Bad Request and the exception message.

| Test                                                             | Scenario                                 |
|------------------------------------------------------------------|------------------------------------------|
| addUserShouldThrowExceptionWhenBlankUsernameField()              | `username` blank.                        |
| addUserShouldThrowExceptionWhenBlankFirstNameField()             | `firstName` blank.                       |
| addUserShouldThrowExceptionWhenBlankLastNameField()              | `lastName` blank.                        |
| addUserShouldThrowExceptionWhenBlankFirstNAmeAndLastNameFields() | `firstName` and `lastName` fields blank. |
| addUserShouldThrowExceptionWhenAllFieldsBlank()                  | All fields blank.                        |
| addUserShouldThrowExceptionWhenAllFieldsNull()                   | All fields `null`.                       |
| addUserShouldThrowExceptionWhenUsernameLongerThan32Chars()       | `username` longer than max 32 chars.     |

## UserControllerIntegrationTest - Testing from controller layer to database

### The purpose of the integration test

To test all the parts from an HTTP request all the way to the database, verifying the correct response
status and bodies.

### Test strategy

Utilizing SpringBootTest to set up a temporary server and using TestRestTemplate to interact with it, I can simulate the
behavior of the actual server, but in an isolated environment. The tests are done with a live database, that is separate
from the production database, to make sure the test data is isolated.

These tests use the application-test.properties configuration file (See setup at the top of the page). When running a
test, the database is cleared using the create-drop keyword, to make sure there is no data interfering with the tests.

When sending a POST request with a valid request body, HTTP status 200 and a UserMinimalDTO is expected.  
When sending a GET request with a valid user ID, HTTP status 200 and a UserDetailedDTO is expected.  
When sending a DELETE request with a valid user ID, HTTP status 200 is expected.

| Test                                | Scenario                                   |
|-------------------------------------|--------------------------------------------|
| testSaveAndGetUserFromDatabase()    | Save user to DB, then get user from DB.    |
| testSaveAndDeleteUserFromDatabase() | Save user to DB, then delete user from DB. |

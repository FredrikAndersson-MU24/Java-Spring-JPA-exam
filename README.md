# Testing

***(For instructions on setup see the main branch readme)***

## UserControllerTest - Component tests for the UserController

The purpose of these tests is to verify the functionality of the UserController PostMapping end-point, creating a new
user object.
The POST request should contain a UserCreationDTO request body, with the fields `username`, `firstName` and
`lastName`.  
First is a positive test, checking that the expected response is sent if a valid request is received.  
The later tests
are negative tests, to test if the correct exception is thrown if the request body is not valid.

- ### Positive test to verify the response if a valid request is received

When receiving a POST request with a valid UserCreationDTO request body to the "/users" end-point, the addUser() method
should respond with HTTP status 200 and a UserMinimalDTO.

| Test                                                                                                                                 | What part of the request body is tested?      |
|--------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------|
| addUserShouldReturnResponseWithUserMinimalDTOAndStatusOkWhenPostingUserCreationDTOHttpRequest()                                      | Valid UserCreationDTO                         |
| addUserShouldReturnResponseWithUserMinimalDTOAndStatusOkWhenPostingUserCreationDTOHttpRequestWithUsernameOfMaxAllowedLength32Chars() | Valid UserCreationDTO, w username of 32 chars |

- ### A series of tests to verify that a BadRequestException is thrown if the POST request body does not meet the validation criteria-

They should all respond with HTTP Status code 400 Bad Request and the exception message.

| Test                                                                                                  | What part of the request body is tested? |
|-------------------------------------------------------------------------------------------------------|------------------------------------------|
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankUsernameField()              | `username` blank.                        |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankFirstNameField()             | `firstName` blank.                       |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankLastNameField()              | `lastName` blank.                        |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithBlankFirstNAmeAndLastNameFields() | `firstName` and `lastName` fields blank. |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithAllFieldsBlank()                  | All fields blank.                        |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithAllFieldsNull()                   | All fields `null`.                       |
| addUserShouldThrowExceptionWhenPostingUserCreationDTOHttpRequestWithUsernameLongerThan32Chars()       | `username` longer than max 32 chars.     |
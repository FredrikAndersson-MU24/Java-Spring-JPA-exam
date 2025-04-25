# Testing

<!-- TOC -->
* [Testing](#testing)
  * [Assigment](#assigment)
  * [Project description](#project-description)
  * [Setup](#setup)
    * [Environmental variables](#environmental-variables)
  * [UserServiceUnitTest - Testing the service layer](#userserviceunittest---testing-the-service-layer)
    * [The purpose of the tests](#the-purpose-of-the-tests)
    * [Test strategy](#test-strategy)
  * [UserControllerUnitAndComponentTest - Unit and component tests for the controller](#usercontrollerunitandcomponenttest---unit-and-component-tests-for-the-controller)
    * [The purpose of the tests](#the-purpose-of-the-tests-1)
    * [Test strategy](#test-strategy-1)
      * [Component tests](#component-tests)
      * [Unit tests](#unit-tests)
  * [UserControllerIntegrationTest - Testing controller layer to database](#usercontrollerintegrationtest---testing-controller-layer-to-database)
    * [The purpose of the tests](#the-purpose-of-the-tests-2)
    * [Test strategy](#test-strategy-2)
  * [Abilities used for this assignment](#abilities-used-for-this-assignment)
    * [Technical](#technical)
    * [Soft](#soft)
<!-- TOC -->

---

## Assigment

Using a Spring Boot project, demonstrate your understanding of and ability to implement various types of tests.
The assignment required covering three types of tests:
- unit tests.
- component tests.
- integration tests.

- [Assigment in full (Swedish)](https://gist.github.com/nz-bill/9bc3d6b7146cb68fa41c5d5ffc9c17cf)
---
## Project description

The base project is a Spring Boot application from a previous assigment. It's a REST API for a message board service where 
users can register and post messages in channels. As the user is the core of the service, I decided to focus the tests 
around the functionality for creating new users.

I have set up sections below, with the name of each test class, to describe the purpose and strategy of each test. 

---
## Setup

The application-test.properties config file is used throughout these tests, specifying a separate test database.
This is in part to isolate test data from production data. But also because the standard config file tries to write
example data to the database each time it runs, which will affect the generated IDs and the tests should not be
allowed to affect that. `create-drop` is used in the config file to clear the database before each test run, to make 
sure there is no old data interfering with the tests. 
---
### Environmental variables

These environmental variables are used in application-test.properties. Set them up according to your MySQL preferences
in each of the test class configurations in your IDE. If you want to run individual test, you also need to set up the 
configuration for that specific test, otherwise the standard application.properties is used and you will get an error.

| Name             | 
|------------------|
| `MYSQL_TEST_URL` |
| `MYSQL_USERNAME` |
| `MYSQL_PASSWORD` |

---

## UserServiceUnitTest - Testing the service layer

### The purpose of the tests

Unit tests are used to test individual layers. One or more methods can be tested, but the test should be isolated to one
layer. Any interaction with other layers should be mocked.

### Test strategy

I have used Mockito to create a mocked instance of the repository, then injecting it into the service class.
Using the when().thenReturn() pattern I control what is returned from the mocked repository when the save() method is
invoked.

| Test method                                                      | Scenario                                               |
|------------------------------------------------------------------|--------------------------------------------------------|
| addUserShouldReturnUserMinimalDTOIfUsernameDoesNotAlreadyExist() | Create a new valid user                                |
| addUserShouldThrowDuplicateKeyExceptionIfUsernameAlreadyExist()  | Create a new user with a username that already exists. |

---

## UserControllerUnitAndComponentTest - Unit and component tests for the controller

### The purpose of the tests

The purpose of the component test is to verify actual interaction between layers in the application. In the case of
these specific component tests, the interaction between the controller and service layers are tested.  
Unit tests are used to test individual layers. One or more methods can be tested, but the test should be isolated to one
layer. Here I test that a Bad Request Exception is thrown if the request body are invalid.

### Test strategy

I chose to use the AutoConfigureMockMvc annotation to use MockMvc for making HTTP requests.  
SpringbootTest is used to load the application context.  
The controller and service layers interact as they should.  
The repository layer is mocked using the MockitoBean annotation, to not interact with the database.

I consider the component tests to be positive tests, as they test that the expected response is received if a valid 
request is sent.  
The unit test on the other hand are negative tests, since they test the response if the request is invalid.

The POST request should contain a UserCreationDTO request body, with the fields `username`, `firstName` and
`lastName`.

#### Component tests

When receiving a POST request with a valid UserCreationDTO request body to the "/users" end-point, the addUser() method
should respond with HTTP status 200 and a UserMinimalDTO.

| Test method                                                                | Scenario                                      |
|----------------------------------------------------------------------------|-----------------------------------------------|
| addUserShouldRespondWithUserMinimalDTOAndStatusOk()                        | Valid UserCreationDTO                         |
| addUserShouldRespondWithUserMinimalDTOAndStatusOkWhenUsernameIsMaxLength() | Valid UserCreationDTO, w username of 32 chars |

#### Unit tests

Expected response: HTTP Status code 400 Bad Request and exception message.

| Test method                                                      | Scenario                                 |
|------------------------------------------------------------------|------------------------------------------|
| addUserShouldThrowExceptionWhenBlankUsernameField()              | `username` blank.                        |
| addUserShouldThrowExceptionWhenBlankFirstNameField()             | `firstName` blank.                       |
| addUserShouldThrowExceptionWhenBlankLastNameField()              | `lastName` blank.                        |
| addUserShouldThrowExceptionWhenBlankFirstNameAndLastNameFields() | `firstName` and `lastName` fields blank. |
| addUserShouldThrowExceptionWhenAllFieldsBlank()                  | All fields blank.                        |
| addUserShouldThrowExceptionWhenAllFieldsNull()                   | All fields `null`.                       |
| addUserShouldThrowExceptionWhenUsernameLongerThan32Chars()       | `username` longer than max 32 chars.     |

---

## UserControllerIntegrationTest - Testing controller layer to database

### The purpose of the tests

The purpose of these integration tests is to test all the parts from an HTTP request all the way to the database, 
verifying the correct response status and bodies. 

### Test strategy

Utilizing SpringBootTest WebEnvironment to set up a temporary server and using TestRestTemplate to interact with it, I
simulate the behavior of the actual server, but in an isolated environment.  

When sending a POST request with a valid request body, HTTP status 200 and a UserMinimalDTO is expected.  
When sending a GET request with a valid user ID, HTTP status 200 and a UserDetailedDTO is expected.  
When sending a DELETE request with a valid user ID, HTTP status 200 is expected.

| Test method                         | Scenario                                   |
|-------------------------------------|--------------------------------------------|
| testSaveAndGetUserFromDatabase()    | Save user to DB, then get user from DB.    |
| testSaveAndDeleteUserFromDatabase() | Save user to DB, then delete user from DB. |

---

## Abilities used for this assignment
### Technical
- Java
  - Spring Boot
  - Mockito
  - Junit
  - SpringBootTest
- Documentation
- IntelliJ Idea
- Git
- GitHub
- Jira
### Soft
- Problem solving
- Structuring
- Adaptability 
- Creativity

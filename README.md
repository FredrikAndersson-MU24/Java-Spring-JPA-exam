# Java-Spring-JPA-exam

## Assignment in short
Build an API for a forum/message board service.
The client should be able to perform CRUD-operations and the data should be stored persistently using JPA.
- [Assignment in full(in swedish)](https://docs.google.com/document/d/1iW2rs-U7Hc4sxsPhKNWb4A_8MLo-i8MYQ0Kn1zClKDA/edit?usp=sharing)
---

## Project description
A REST API written in Java. The API utilizes separated layers to expose as little business logic as possible to the client. 
It is divided into Repository, Service and Controller layers for each entity to maintain separation of concerns.

Through Dependency Injection each layer can access other parts of the layers either horizontally or vertically.  

Data Transfer Objects(DTO) are used to control what is is sent from/to the client. In this project there is 
no particularly sensitive data, but DTOs are used for training purposes and to send specif responses.

The client can create, read, update and delete data. There are three different entities; Channel, User and Post.
The client can create channels and users. They can then create posts that relate to a user and a channel.  
If a user or channel is deleted all their related posts are deleted through the use of cascading.
---

## Getting started

### Setting up environmental variables
Set up environmental variables for your database:  
MYSQL_URL - URL to the database. For example jdbc:mysql://localhost:3306/message_board  
MYSQL_USERNAME - Your server username  
MYSQL_PASSWORD - Your server password  

Run the Java-application to let the application create neccessary tables.
### Preparing the MySQL database
(This is optional but preloads the database with some data to have something to start working with)
Running the SQL-script in MySQL Workbench to preload mock data.

Request are sent to the API to interact with the database. 
---

## Interacting with the API

Interacting is done by using the endpoints stated below. When using POST commands the provided code syntax should be used 
in the request body. 


### Channels ("/channels")
Syntax for creating a channel
````json
{
   "name": "{name}"
}
````

| Command | Operation                                | Endpoint             | Returns                |
|---------|------------------------------------------|----------------------|------------------------|
| POST    | Create a new channel                     |                      | `ChannelDTO`           |
| GET     | Get all channels                         |                      | `List<ChannelDTO>`     |
|         | Get a channel by ID                      | `/{id}`              | `ChannelDTO`           |
|         | Get all posts in a channel by channel ID | `/{channelId}/posts` | `List<PostMinimalDTO>` |
|         | Get channels by a search query           | `/name/{searchTerm}` | `List<ChannelDTO>`     |
| PUT     | Update a channel by ID                   | `/{id}`              | `ChannelDTO`           |
| DELETE  | Delete a channel by ID                   | `/{id}`              | `void`                 |

### Users ("/users")
Syntax for creating a user
````json
{
   "name": "{name}"
}
````
Syntax for creating a post
````json
{
  "title": "The title can be 6-32 characters",
  "body": "This will be the body of the post. It can be a bit longer, 6-160 characters."
}
````

| Command | Operation                                   | Endpoint                      | Returns          |
|---------|---------------------------------------------|-------------------------------|------------------|
| POST    | Create a new user                           |                               | `UserDTO`        |
|         | Create a new post on user ID and channel ID | `/{userId}/posts/{channelID}` | `PostMinimalDTO` |
| GET     | Get all users                               |                               | `List<UserDTO>`  |
|         | Get a user by ID                            | `/{id}`                       | `UserDTO`        |
|         | Get a users posts by user ID                | `/{userId}/posts`             | `List<UserDTO>`  |
|         | Get users by a search query                 | `/name/{searchTerm}`          | `List<UserDTO>`  |
| PUT     | Update a user by ID                         | `/{id}`                       | `UserDTO`        |
| DELETE  | Delete a user by ID                         | `/{id}`                       | `void`           |


### Posts ("/posts")

| Command | Operation                        | Endpoint   | Returns                 |
|---------|----------------------------------|------------|-------------------------|
| GET     | Get all posts with minimal info  |            | `List<PostMinimalDTO>`  |
|         | Get all posts with detailed info | `/details` | `List<PostDetailedDTO>` |
| PUT     | Update a single post by ID       | `/{id}`    | `PostDetailedDTO`       |
| DELETE  | Delete a single post by ID       | `/{id}`    | `void`                  |

---
## Project documentation
- [Jira board  ](https://fredande.atlassian.net/jira/software/projects/JSJE/boards/4)
- UML
- ERD
---
## Abilities used for this assignment
### Technical
- Java
- Spring 
   - Spring Data JPA
   - Spring Validation
   - Spring Web
- Maven
  - Mapstruct 
- MySQL
- Documentation

### Soft
- Problem solving
- Structuring
- Adaptability
- Creativity
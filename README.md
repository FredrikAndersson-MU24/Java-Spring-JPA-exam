# Java-Spring-JPA-exam

## Assignment in short
Build an API for a forum/message board service.
The client should be able to perform CRUD-operations and the data should be stored persistently using JPA.
- [Assignment in full (swedish)](https://docs.google.com/document/d/1iW2rs-U7Hc4sxsPhKNWb4A_8MLo-i8MYQ0Kn1zClKDA/edit?usp=sharing)

---

## Project description
A REST API written in Java using Spring Boot. It is divided into Repository, Service and Controller layers for each
entity, to maintain separation of concerns.

Through Dependency Injection each layer can access other parts of the layers either horizontally or vertically.  

Data Transfer Objects(DTO) are used to control what is is sent from/to the client. In this project there is 
no particularly sensitive data, but DTOs are used for training purposes and to send specific responses.

The client can create, read, update and delete data. There are three different entities; Channel, User and Post.
The client can create channels and users. They can then create posts that relate to a user and a channel.  
If a user or channel is deleted all their related posts are deleted through the use of cascading, minimizing the risk of
orphaned data.

---

## Getting started

### Preparing the MySQL database
Create a MySQL database, for example message_board.

### Setting up environmental variables
Set up environmental variables for your database:  
MYSQL_URL - URL to the database. For example jdbc:mysql://localhost:3306/message_board  
MYSQL_USERNAME - Your server username  
MYSQL_PASSWORD - Your server password  

Run the Java-application. It creates the neccessary tables and mock data is preloaded using data.sql.

---

## Interacting with the API

Interacting is done using the endpoints stated below. When using POST commands the provided code syntax should be entered 
in the request body as JSON. 


### Channels ("/channels")
Syntax for creating/updating a channel
````json
{
   "name": "{4-16 characters}"
}
````

| Command | Operation                                | Endpoint                    | Restrictions    | Returns                |
|---------|------------------------------------------|-----------------------------|-----------------|------------------------|
| POST    | Create a new channel                     |                             | 4-16 characters | `ChannelDTO`           |
| GET     | Get all channels                         |                             |                 | `List<ChannelDTO>`     |
|         | Get a channel by ID                      | `/{id}`                     |                 | `ChannelDTO`           |
|         | Get all posts in a channel by channel ID | `/{channelId}/posts`        |                 | `List<PostMinimalDTO>` |
|         | Get channels by a search query           | `/find/byName/{searchTerm}` |                 | `List<ChannelDTO>`     |
| PUT     | Update a channel by ID                   | `/{id}`                     | 4-16 characters | `ChannelDTO`           |
| DELETE  | Delete a channel by ID                   | `/{id}`                     |                 | `void`                 |

### Users ("/users")
Syntax for creating/updating a user
````json
{
   "username": "{name, max 32 chars}",
   "firstName": "{firstName, max 32 chars}",
   "lastName": "{lastName, max 32 chars}"
}
````
Syntax for creating a post
````json
{
  "title": "The title can be 6-32 characters",
  "body": "This will be the body of the post. It can be a bit longer, 6-160 characters."
}
````

| Command | Operation                                   | Endpoint                        | Restrictions                                                        | Returns                 |
|---------|---------------------------------------------|---------------------------------|---------------------------------------------------------------------|-------------------------|
| POST    | Create a new user                           |                                 | Username must be unique, no null values                             | `UserMinimalDTO`        |
|         | Create a new post on userID and channelID   | `/{userId}/channel/{channelID}` | Title 6-32 chars, Body 6-160 chars, valid user ID, valid channel ID | `PostMinimalDTO`        |
| GET     | Get all users                               |                                 |                                                                     | `List<UserMinimalDTO>`  |
|         | Find a user by ID                           | `/{id}`                         |                                                                     | `UserDetailedDTO`       |
|         | Find a users posts by user ID               | `/{userId}/posts`               |                                                                     | `List<PostDetailedDTO>` |
|         | Find users by username                      | `/find/byUsername/{searchTerm}` |                                                                     | `List<UserMinimalDTO>`  |
|         | Find users by username, first and last name | `/find/byAll/{searchTerm}`      |                                                                     | `List<UserDetailedDTO>` |
| PUT     | Update a user by ID                         | `/{id}`                         | Username must be unique, no null values                             | `UserMinimalDTO`        |
| DELETE  | Delete a user by ID                         | `/{id}`                         |                                                                     | `void`                  |


### Posts ("/posts")
Syntax for updating a post
````json
{
  "title": "The title can be 6-32 characters",
  "body": "This will be the body of the post. It can be a bit longer, 6-160 characters."
}
````

| Command | Operation                        | Endpoint   | Restrictions                       | Returns                 |
|---------|----------------------------------|------------|------------------------------------|-------------------------|
| GET     | Get all posts with minimal info  |            |                                    | `List<PostMinimalDTO>`  |
|         | Get all posts with detailed info | `/details` |                                    | `List<PostDetailedDTO>` |
| PUT     | Update a single post by ID       | `/{id}`    | Title 6-32 chars, Body 6-160 chars | `PostDetailedDTO`       |
| DELETE  | Delete a single post by ID       | `/{id}`    |                                    | `void`                  |

---

## Project documentation
- [Jira board  ](https://fredande.atlassian.net/jira/software/projects/JSJE/boards/4)
- [UML diagram on Lucid](https://lucid.app/lucidchart/5b5c5dbb-9a6d-4e08-8056-e7ad4ee5ecbd/edit?viewport_loc=-818%2C-874%2C5098%2C2864%2CHWEp-vi-RSFO&invitationId=inv_53fa96bc-78f0-4c3c-9c04-a78bc610d52f)
- [Entity Relationship Diagram(ERD) on Lucid](https://lucid.app/lucidspark/5dfd1bc5-ff0f-4def-931f-bf4ee588e0fb/edit?viewport_loc=-1383%2C-304%2C1501%2C879%2C0_0&invitationId=inv_d5ed5517-53ee-4ce6-a2ac-6ea2b64925c8)

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
- IntelliJ Idea
- MySQL Workbench
- Postman

### Soft
- Problem solving
- Structuring
- Adaptability
- Creativity
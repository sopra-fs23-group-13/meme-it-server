# Meme It - Backend

## Introduction
Meme-It is the ultimate meme creation application, guaranteed to bring high entertainment value to a night with friends and family, or make waiting in the dentist office alone just a bit more bearable. Meme it can be played with friends by joining the game with a specific code, or against strangers by joining one of the public lobbies.

The rules are simple: a group of players receive randomly chosen meme templates to add their own text to and create a unique meme through their sense of humor. After a time limit, the memes are collected and voted on, with the best meme winning the round. The collaborative chat feature allows users to chat with their opponents while waiting for the game to start, as well as during the game. 

## Technologies Used
Meme-It is developed using the following main technologies:

- [React.js](https://reactjs.org/docs/getting-started.html): Used for building the front-end of the application.
- [Springboot](https://spring.io/): Used to develop the backend server. A rest api is used for real-time data transfer between the client and the server.
- [H2](https://www.h2database.com/html/main.html): Used to managed the database. 
- [Bootstrap](https://react-bootstrap.github.io/): Used for designing responsive UI.
- [Github Projects](https://github.com/explore): Project Management
- [Sonarcube](https://sonarcloud.io/): Used for code quality and test metrics.
- [Google Cloud](https://cloud.google.com/): Used for the online deployment.

## High-level Components
The backend of Meme-It is responsible for processing user requests and managing the application’s data. It includes a server that handles incoming requests, a database to store meme data, and a restful API that communicates with the frontend.

## High-Level Components
Meme-It comprises four main backend components:

### Lobby: 
This is the entry point for users to join or create a game. The lobby owner can customize different parameters of the game which are then stored in the database. It consistes of three standard parts; [LobbyController](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/controller/LobbyController.java), [LobbyService](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/service/LobbyService.java) and [LobbyRepository](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/repository/LobbyRepository.java).

### Game: 
This is the heart of Meme-It. It handles the creation of a game, submitting memes, voting and getting the results. All of these changes are stored in the H2 database. The game component is also responsible for allowing users to query state information about their game. The component consistes of three standard parts; [GameController](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/controller/GameController.java), [GameService](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/service/GameService.java) and [GameRepository](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/repository/GameRepository.java).

### Game Job:
This is the brain of Meme-It, it manages the state transitions and ensures rounds start and end when they should. The game job runs within a thread and is spawned right after a game is created. This allows for the non-blocking contiuation of the GameController. Due to its nature it can be considered as a background job. It runs throughout the duration of a game every second to make sure all users as synced up perfectly. The game job can be found [here](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/job/GameJob.java). 

### Chat
The Chat component facilitates real-time communication between players in a game. It enhances the multiplayer aspect of Meme-It, allowing players to comment, cheer, and react to the ongoing game. It works parallel to the Lobby and the Game, so that all the players can always be in touch with each other no matter where they are during their journey. The component consistes of three standard parts; [ChatController](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/controller/ChatController.java), [ChatService](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/service/ChatService.java) and [ChatRepository](https://github.com/sopra-fs23-group-13/meme-it-server/tree/main/src/main/java/ch/uzh/ifi/hase/soprafs23/repository/ChatRepository.java).

## Launch and Deployment Guide
This guide provides instructions on how to launch and deploy the project using IntelliJ and VS Code. Please follow the steps below:

### IntelliJ
1. Open IntelliJ and navigate to File -> Open.... Select the Meme It project.
2. When prompted, choose to import the project as a `gradle project`.
3. To build the project, right-click on the `build.gradle` file and select `Run Build`.

### VS Code
To facilitate the setup process in VS Code, consider installing the following extensions:
-   `vmware.vscode-spring-boot`
-   `vscjava.vscode-spring-initializr`
-   `vscjava.vscode-spring-boot-dashboard`
-   `vscjava.vscode-java-pack`

**Note:** Before proceeding, ensure that you have built the project with Gradle. You can do this by clicking on the `build` command in the  _Gradle Tasks_ extension. Once the project is built, check the _Spring Boot Dashboard_ extension. If it already displays the project, you can click the play button to start the server. If the project does not appear, restart VS Code and check again.

## Building with Gradle
You can use the local Gradle Wrapper to build the application.
-   macOS: `./gradlew`
-   Linux: `./gradlew`
-   Windows: `./gradlew.bat`

### Build

```bash
./gradlew build
```

### Run

```bash
./gradlew bootRun
```

You can verify that the server is running by visiting `localhost:8080` in your browser.

### Test

```bash
./gradlew test
```

## Code Quality

### SonarQube

To view the sonarqube reports click [here](https://sonarcloud.io/summary/overall?id=sopra-fs23-group-13_meme-it-server).

### Test coverage

This is the latest status of our test coverage:
<img width="1512" alt="Screenshot 2023-05-25 at 18 29 00" src="https://github.com/sopra-fs23-group-13/meme-it-server/assets/91025733/e02ac3d5-1986-4e6a-bde4-415d347df088">


## Roadmap
For developers wanting to contribute to Meme-It backend, here are a few potential ideas:
- Seperate database from application to allow for vertical scalability
- Allow users to retain their victory history using accounts
- Provide option for messaging users of the current game privately
- Improve test coverage

## Cloud Issues on cold starts
One of the improvements is to switch the cloud provider. Google Cloud does not seem to function properly with the application. The loading time of the application is rather slow. This is due to cold starts. Once the Cloud VM shuts down, the start up is slow. Users can join a lobby, even though the instance is not yet properly started up. This leads to some crashes right once the game should start. We try to prevent this by having an always on instance, however, this does not properly fix the issue.  

## Authors and Acknowledgment
This project is maintained by Sopra Group 13:
- Daniel Lutziger [@danielLutziger](https://github.com/danielLutziger)
- Pablo Tanner [@pabloTanner](https://github.com/pabloTanner)
- Henrik Nordgren [@cNordg](https://github.com/cnordg)
- Linda Weber [@weberlii](https://github.com/weberlii)
- Marc Schurr [@M-Schurr](https://github.com/m-schurr)

We would like to thank Mete Polat for his support and guidance during the development of this project.

## License
Meme-It is licensed under the [Apache 2.0 License](https://github.com/sopra-fs23-group-13/meme-it-server/blob/main/LICENSE). The Apache 2.0 license grants users the freedom to use, modify, and distribute software under its terms, while offering patent protection, making it a popular choice for open-source projects such as the Apache software itself.

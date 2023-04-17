# Team Contributions Log

This file serves as a log of individual contributions made by each team member throughout the course. Before each meeting, team members are required to manually append two development tasks they completed during the previous week to this markdown file. Each entry should include the week date, name, links to the GitHub issues they worked on, and optionally a short description of their work.

## Contributions

| Week Date | Name | Links to GitHub Issues | Description |
| --- | --- | --- | --- |
| 03/27/2023-04/03/2023 | Marc Schurr |[User Story 70,](https://github.com/sopra-fs23-group-13/meme-it-server/issues/70) [User Story 30](https://github.com/sopra-fs23-group-13/meme-it-client/issues/30)| If a player that hasn't set a name yet tries to join a lobby, they should be prompted with the name setting window (message that indicates that the username was missing). / If the player enters an invalid lobby code, an error message should be displayed. |
| 03/27/2023-04/03/2023 | Marc Schurr | [User Story 7](https://github.com/sopra-fs23-group-13/meme-it-client/issues/7) | This task is to create a lobbyview in the frontend area within which the settings and an active player list is displayed. Also the admin of the lobby should be highlighted visually. Other necessary elements like the copy field of the lobby code as well as the buttons to remove players from the lobby have to be added in a coherent overall picture.|
| 03/27/2023-04/03/2023 | Linda Weber | [User Story 69](https://github.com/sopra-fs23-group-13/meme-it-server/issues/69) | The player should only be able to join lobbies that aren't full yet and should receive an error message otherwise. To achieve this, join was implemented, as well as the logic to prevent a kicked user from joining.|
| 03/27/2023-04/03/2023 | Linda Weber | [User Story 73](https://github.com/sopra-fs23-group-13/meme-it-server/issues/73) | I added the methods kick (needed for #77), join (needed for #69), leave, exit for better lobby management. I converted the @Column annotations to JPA annotations, so the relationships between entities are more accurately represented. Additionally, some fields were converted to data structures that make working with collections more convenient.|
| 03/27/2023-04/03/2023 | Daniel Lutziger |[User Story 26](https://github.com/sopra-fs23-group-13/meme-it-client/issues/26), [User Story 23](https://github.com/sopra-fs23-group-13/meme-it-client/issues/23)| I have added the frontend implementation of the lobby creation and the lobby list showing all the running games. The data is currently mocked and thus not fully functioning. The next action is to send the api calls to the backend. Thus, one of the obstacles is the implementation speed. As a next task I am planning on implementing the game screen of the user. |
| 03/27/2023-04/03/2023 | Daniel Lutziger |-| Major refactoring of the template so that we can easily use bootstrap elements to fit the page and not spend hours in styling. |
| 03/27/2023-04/03/2023 | Henrik Nordgren | [6](https://github.com/sopra-fs23-group-13/meme-it-client/issues/6), [7](https://github.com/sopra-fs23-group-13/meme-it-client/issues/7), [63](https://github.com/sopra-fs23-group-13/meme-it-server/issues/65), [65](https://github.com/sopra-fs23-group-13/meme-it-server/issues/65) | I added logic to the backend  to create and view a lobby / lobbies (incl. success tests of controllers). This includes setting up the JPA entities (and their relations) using the class diagrams and manageing internal/external versions of those entities. |
| 03/27/2023-04/03/2023 | Pablo Tanner | [User Story 44](https://github.com/sopra-fs23-group-13/meme-it-client/issues/44) [Dev Task 2](https://github.com/sopra-fs23-group-13/meme-it-client/issues/2) [Dev Task 4](https://github.com/sopra-fs23-group-13/meme-it-client/issues/4) [Dev Task 5](https://github.com/sopra-fs23-group-13/meme-it-client/issues/5) [Dev Task 41](https://github.com/sopra-fs23-group-13/meme-it-client/issues/41) [Dev Task 40](https://github.com/sopra-fs23-group-13/meme-it-client/issues/40) [Dev Task 70](https://github.com/sopra-fs23-group-13/meme-it-server/issues/70) [Dev Task 83](https://github.com/sopra-fs23-group-13/meme-it-server/issues/83)|Created the first version of a semi working home screen before we refactored it to use bootstrap. Built a working home screen with every relevant feature (every button does what it should do in the end) together with Daniel|
| 03/27/2023-04/03/2023 | Pablo Tanner | [Dev Task 52](https://github.com/sopra-fs23-group-13/meme-it-client/issues/52) [Dev Task 51](https://github.com/sopra-fs23-group-13/meme-it-client/issues/51)|Worked on the lobby screen with Marc|
| 04/04/2023-04/17/2023 | Linda Weber | [Dev Task 54](https://github.com/sopra-fs23-group-13/meme-it-client/issues/54) [Dev Task 21](https://github.com/sopra-fs23-group-13/meme-it-client/issues/21) |I have added the frontend implementation for the meme creation and meme rating phase, including the timer progress bar and draggable and editable meme text boxes.|

## Weekly Update

During the meeting, each team member should provide a brief 3-minute update answering the following questions:

1. What did I do last week?
2. What will I do this week?
3. What are the obstacles to progress?

Please update this section with your answers during the meeting.

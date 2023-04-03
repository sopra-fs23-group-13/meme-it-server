# Team Contributions Log

This file serves as a log of individual contributions made by each team member throughout the course. Before each meeting, team members are required to manually append two development tasks they completed during the previous week to this markdown file. Each entry should include the week date, name, links to the GitHub issues they worked on, and optionally a short description of their work.

## Contributions

| Week Date | Name | Links to GitHub Issues | Description |
| --- | --- | --- | --- |
| 03/27/2023-04/03/2023 | Marc Schurr |[User Story 70,](https://github.com/sopra-fs23-group-13/meme-it-server/issues/70) [User Story 30](https://github.com/sopra-fs23-group-13/meme-it-client/issues/30)| If a player that hasn't set a name yet tries to join a lobby, they should be prompted with the name setting window (message that indicates that the username was missing). / If the player enters an invalid lobby code, an error message should be displayed. |
| 03/27/2023-04/03/2023 | Marc Schurr | [User Story 7](https://github.com/sopra-fs23-group-13/meme-it-client/issues/7) | This task is to create a lobbyview in the frontend area within which the settings and an active player list is displayed. Also the admin of the lobby should be highlighted visually. Other necessary elements like the copy field of the lobby code as well as the buttons to remove players from the lobby have to be added in a coherent overall picture.|
| 03/27/2023-04/03/2023 | Linda Weber | [User Story 69](https://github.com/sopra-fs23-group-13/meme-it-server/issues/69) | The player should only be able to join lobbies that aren't full yet and should receive an error message otherwise. To achieve this, join was implemented, as well as the logic to prevent a kicked user from joining.|
| 03/27/2023-04/03/2023 | Linda Weber | [User Story 73](https://github.com/sopra-fs23-group-13/meme-it-server/issues/73) | Henrik wrote most of the code. I added the methods kick, join, leave for better lobby management. I converted the @Column annotations to JPA annotations, so the relationships between entities are more accurately represented. Additionally, some fields were convertes to data structures that make working with collections more convenient.|

## Weekly Update

During the meeting, each team member should provide a brief 3-minute update answering the following questions:

1. What did I do last week?
2. What will I do this week?
3. What are the obstacles to progress?

Please update this section with your answers during the meeting.

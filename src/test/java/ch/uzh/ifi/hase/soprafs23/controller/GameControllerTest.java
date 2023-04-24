// package ch.uzh.ifi.hase.soprafs23.controller;

// import ch.uzh.ifi.hase.soprafs23.entity.Game;
// import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
// import ch.uzh.ifi.hase.soprafs23.entity.Meme;
// import ch.uzh.ifi.hase.soprafs23.entity.Template;
// import ch.uzh.ifi.hase.soprafs23.entity.User;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemePostDTO;
// import ch.uzh.ifi.hase.soprafs23.rest.mapper.meme.MemeMapper;
// import ch.uzh.ifi.hase.soprafs23.service.GameService;
// import ch.uzh.ifi.hase.soprafs23.service.LobbyService;
// import ch.uzh.ifi.hase.soprafs23.service.UserService;

// import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.actuate.endpoint.SecurityContext;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
// import org.springframework.http.MediaType;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import java.util.UUID;

// @WebMvcTest(GameController.class)
// public class GameControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private GameService gameService;

//     @MockBean
//     private LobbyService lobbyService;

//     @MockBean
//     private UserService userService;

//     @MockBean
//     private UserDetailsService userDetailsService;

//     @Test
//     public void givenLobbyCode_whenCreateGame_thenReturnCreatedGame() throws Exception {
//         String lobbyCode = "testCode";
//         Lobby lobby = new Lobby();
//         Game game = new Game();

//         when(lobbyService.getLobbyByCode(lobbyCode)).thenReturn(lobby);
//         when(gameService.createGame(lobbyCode)).thenReturn(game);

//         mockMvc.perform(post("/games/{lobbyCode}", lobbyCode)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isCreated());
//     }

//     @Test
//     public void givenGameId_whenGetGame_thenReturnGame() throws Exception {
//         String gameId = UUID.randomUUID().toString();
//         Game game = new Game();

//         when(gameService.getGame(gameId)).thenReturn(game);

//         mockMvc.perform(get("/games/{gameId}", gameId)
//                 .contentType(MediaType.APPLICATION_JSON))
//                 .andExpect(status().isOk());
//     }

//     // Add more tests for the other GameController methods
// }


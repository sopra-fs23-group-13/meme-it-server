// package ch.uzh.ifi.hase.soprafs23.controller;

// import ch.uzh.ifi.hase.soprafs23.JwtRequestFilter;
// import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
// import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
// import ch.uzh.ifi.hase.soprafs23.entity.Message;
// import ch.uzh.ifi.hase.soprafs23.entity.User;
// import ch.uzh.ifi.hase.soprafs23.repository.UserRepository;
// import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyPostDTO;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyPutDTO;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.user.UserPostDTO;
// import ch.uzh.ifi.hase.soprafs23.service.LobbyService;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
// import org.springframework.web.server.ResponseStatusException;
// import ch.uzh.ifi.hase.soprafs23.rest.mapper.user.UserMapper;


// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// import static org.hamcrest.Matchers.hasSize;
// import static org.hamcrest.Matchers.is;
// import static org.mockito.BDDMockito.given;
// import static org.mockito.Mockito.doNothing;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


// // /**
// // * LobbyControllerTest
// // * This is a WebMvcTest which allows to test the UserController i.e. GET/POST
// // * request without actually sending them over the network.
// // * This tests if the LobbyController works.
// // */

// //@ExtendWith(SpringExtension.class)
// //@SpringBootTest(classes = LobbyController.class)


// @WebMvcTest(LobbyController.class)
// public class LobbyControllerTest {

//         @Autowired
//         private MockMvc mockMvc;

//         @MockBean
//         private LobbyService lobbyService;

//         @Mock
//         private LobbyRepository lobbyRepository;

//         @Test
//         public void givenLobbies_whenGetLobbies_thenReturnJsonArray() throws Exception {
//                 // given

//                 User user = new User(); //creates owner of the lobby
//                 user.setName("owner name");

//                 Lobby lobby = new Lobby();

//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);

//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 List<Lobby> allLobbies = Collections.singletonList(lobby);

//                 // this mocks the lobbyService -> we define above what the lobbyService should
//                 // return when getLobbies() is called
//                 given(lobbyService.getLobbies()).willReturn(allLobbies);

//                 // when
//                 MockHttpServletRequestBuilder getRequest = get("/lobbies").contentType(MediaType.APPLICATION_JSON);

//                 // then
//                 mockMvc.perform(getRequest).andExpect(status().isOk())
//                                 .andExpect(jsonPath("$", hasSize(1)))
//                                 .andExpect(jsonPath("$[0].name", is(lobby.getName())))
//                                 .andExpect(jsonPath("$[0].owner", is(lobby.getOwner())))
//                                 .andExpect(jsonPath("$[0].lobbySetting.isPublic",
//                                                 is(lobby.getLobbySetting().getIsPublic())))
//                                 .andExpect(jsonPath("$[0].lobbySetting.maxPlayers",
//                                                 is(lobby.getLobbySetting().getMaxPlayers())))
//                                 .andExpect(jsonPath("$[0].lobbySetting.maxRounds",
//                                                 is(lobby.getLobbySetting().getMaxRounds())))
//                                 .andExpect(
//                                                 jsonPath("$[0].lobbySetting.memeChangeLimit",
//                                                                 is(lobby.getLobbySetting().getMemeChangeLimit())))
//                                 .andExpect(
//                                                 jsonPath("$[0].lobbySetting.superLikeLimit",
//                                                                 is(lobby.getLobbySetting().getSuperLikeLimit())))
//                                 .andExpect(jsonPath("$[0].lobbySetting.superDislikeLimit",
//                                                 is(lobby.getLobbySetting().getSuperDislikeLimit())))
//                                 .andExpect(
//                                                 jsonPath("$[0].lobbySetting.timeRoundLimit",
//                                                                 is(lobby.getLobbySetting().getRoundDuration())))
//                                 .andExpect(jsonPath("$[0].lobbySetting.timeVoteLimit",
//                                                 is(lobby.getLobbySetting().getRatingDuration())));
//         }

//         @Test
//         public void createLobby_validInput_userCreated() throws Exception {
//                 // given
//                 User user = new User(); //creates owner of the lobby
//                 user.setName("owner name");

//                 Lobby lobby = new Lobby();

//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);

//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 LobbyPostDTO lobbyPostDTO = new LobbyPostDTO();
//                 lobbyPostDTO.setName("lobby name");
//                 lobbyPostDTO.setIsPublic(false);
//                 lobbyPostDTO.setMaxPlayers(1);
//                 lobbyPostDTO.setMaxRounds(2);
//                 lobbyPostDTO.setMemeChangeLimit(3);
//                 lobbyPostDTO.setSuperLikeLimit(4);
//                 lobbyPostDTO.setSuperDislikeLimit(5);
//                 lobbyPostDTO.setRoundDuration(6);
//                 lobbyPostDTO.setRatingDuration(7);

//                 given(lobbyService.createLobby(Mockito.any(), Mockito.any())).willReturn(lobby);

//                 // when/then -> do the request + validate the result
//                 MockHttpServletRequestBuilder postRequest = post("/lobbies")
//                                 .contentType(MediaType.APPLICATION_JSON)
//                                 .content(asJsonString(lobbyPostDTO));

//                 // then
//                 mockMvc.perform(postRequest)
//                                 .andExpect(status().isCreated())
//                                 .andExpect(jsonPath("$.name", is(lobby.getName())))
//                                 .andExpect(jsonPath("$.owner", is(lobby.getOwner())))
//                                 .andExpect(jsonPath("$.lobbySetting.isPublic",
//                                                 is(lobby.getLobbySetting().getIsPublic())))
//                                 .andExpect(jsonPath("$.lobbySetting.maxPlayers",
//                                                 is(lobby.getLobbySetting().getMaxPlayers())))
//                                 .andExpect(jsonPath("$.lobbySetting.maxRounds",
//                                                 is(lobby.getLobbySetting().getMaxRounds())))
//                                 .andExpect(jsonPath("$.lobbySetting.memeChangeLimit",
//                                                 is(lobby.getLobbySetting().getMemeChangeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.superLikeLimit",
//                                                 is(lobby.getLobbySetting().getSuperLikeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.superDislikeLimit",
//                                                 is(lobby.getLobbySetting().getSuperDislikeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.timeRoundLimit",
//                                                 is(lobby.getLobbySetting().getRoundDuration())))
//                                 .andExpect(jsonPath("$.lobbySetting.timeVoteLimit",
//                                                 is(lobby.getLobbySetting().getRatingDuration())));

//         }

//         @Test
//         public void givenLobbies_whenGetLobby_thenReturnJsonArray() throws Exception {
//                 // given
//                 User user = new User(); //creates owner of the lobby
//                 user.setName("owner name");

//                 Lobby lobby = new Lobby();

//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);
//                 lobby.setId(1L);
//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 // this mocks the lobbyService -> we define above what the lobbyService should
//                 // return when getLobbies() is called
//                 given(lobbyService.getLobbyByCode(Mockito.any())).willReturn(lobby);

//                 // when
//                 MockHttpServletRequestBuilder getRequest = get("/lobbies/1").contentType(MediaType.APPLICATION_JSON);

//                 // then
//                 mockMvc.perform(getRequest).andExpect(status().isOk())
//                                 .andExpect(jsonPath("$.name", is(lobby.getName())))
//                                 .andExpect(jsonPath("$.owner", is(lobby.getOwner())))
//                                 .andExpect(jsonPath("$.lobbySetting.isPublic",
//                                                 is(lobby.getLobbySetting().getIsPublic())))
//                                 .andExpect(jsonPath("$.lobbySetting.maxPlayers",
//                                                 is(lobby.getLobbySetting().getMaxPlayers())))
//                                 .andExpect(jsonPath("$.lobbySetting.maxRounds",
//                                                 is(lobby.getLobbySetting().getMaxRounds())))
//                                 .andExpect(jsonPath("$.lobbySetting.memeChangeLimit",
//                                                 is(lobby.getLobbySetting().getMemeChangeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.superLikeLimit",
//                                                 is(lobby.getLobbySetting().getSuperLikeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.superDislikeLimit",
//                                                 is(lobby.getLobbySetting().getSuperDislikeLimit())))
//                                 .andExpect(jsonPath("$.lobbySetting.timeRoundLimit",
//                                                 is(lobby.getLobbySetting().getRoundDuration())))
//                                 .andExpect(jsonPath("$.lobbySetting.timeVoteLimit",
//                                                 is(lobby.getLobbySetting().getRatingDuration())));

//         }


//         @Test
//         public void givenLobbies_whenJoinLobby_thenReturnJsonArray() throws Exception {
//                 // given

//                 //creates owner of the lobby, needed to create the Lobby instance
//                 User user = new User();
//                 user.setName("owner name");
//                 user.setUuid("1");

//                 Lobby lobby = new Lobby();

//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);
//                 lobby.setId(1L);
//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 //creates another User object representing the user who wants to join the lobby
//                 User userPostDTO = new User(); 
//                 userPostDTO.setId(2L);
//                 userPostDTO.setName("example");

//                 // this mocks the lobbyService -> we define above what the lobbyService should
//                 // return when getLobbies() is called
//                 given(lobbyRepository.findByCode(Mockito.anyString())).willReturn(lobby);
//                 given(lobbyService.joinLobby(Mockito.anyString(), Mockito.any())).willReturn(true);

//                 // when
//                 MockHttpServletRequestBuilder postRequest = post("lobbies/1/players") //1 meaning lobby with id=1
//                                 .contentType(MediaType.APPLICATION_JSON)
//                                 .content(asJsonString(userPostDTO));

//                 // then
//                 mockMvc.perform(postRequest).andExpect(status().isOk());
        
//         }



//         @Test
//         public void givenLobbies_whenJoinLobbyFails_thenReturnForbidden() throws Exception {
//                 // given

//                 //creates owner of the lobby, needed to create the Lobby instance
//                 User user = new User();
//                 user.setName("owner name");
//                 user.setUuid("1");

//                 Lobby lobby = new Lobby();
        
//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);
//                 lobby.setId(1L);
//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(false);  //false, Lobby not joinable

//                 //creates another User object representing the user who wants to join the lobby
//                 User userPostDTO = new User();
//                 userPostDTO.setId(2L);
//                 userPostDTO.setName("example");
        
//                 // this mocks the UserService -> we define above what the userService should
//                 // return when getUsers() is called
//                 given(lobbyRepository.findByCode(Mockito.anyString())).willReturn(lobby);
//                 given(lobbyService.joinLobby(Mockito.anyString(), Mockito.any())).willReturn(false);
        
//                 // when
//                 MockHttpServletRequestBuilder postRequest = post("lobbies/1/players")
//                                 .contentType(MediaType.APPLICATION_JSON)
//                                 .content(asJsonString(userPostDTO));
        
//                 // then
//                 mockMvc.perform(postRequest).andExpect(status().isForbidden());
//             }


            

//         @Test
//         public void givenLobbies_whenLeaveLobby_thenReturnJsonArray() throws Exception {
//                 // given

//                 //creates owner of the lobby, needed to create the Lobby instance
//                 User user = new User();
//                 user.setName("owner name");
//                 user.setUuid("1");

//                 Lobby lobby = new Lobby();

//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);
//                 lobby.setId(1L);
//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 //creates another User object representing the user who wants to leave the lobby
//                 User userPostDTO = new User();
//                 userPostDTO.setId(2L);
//                 userPostDTO.setName("example");

//                 // this mocks the UserService -> we define above what the userService should
//                 // return when getUsers() is called
//                 given(lobbyRepository.findByCode(Mockito.anyString())).willReturn(lobby);
//                 doNothing().when(lobbyService).leaveLobby(Mockito.anyString(), Mockito.any());


//                 // when
//                 MockHttpServletRequestBuilder deleteRequest = delete("/lobbies/1/players")
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .content(asJsonString(userPostDTO));

//                 // then
//                 mockMvc.perform(deleteRequest).andExpect(status().isOk());
                        
//         }


//         @Test
//         public void givenLobbies_whenKickLobby_thenReturnUpdatedLobby() throws Exception {
//                 // given

//                 //creates owner of the lobby, needed to create the Lobby instance
//                 User user = new User();
//                 user.setName("owner name");
//                 user.setUuid("1");

//                 Lobby lobby = new Lobby();
            
//                 LobbySetting lobbySetting = new LobbySetting(
//                                 false,
//                                 1,
//                                 2,
//                                 3,
//                                 4,
//                                 5,
//                                 6,
//                                 7);
//                 lobby.setId(1L);
//                 lobby.setName("lobby name");
//                 lobby.setOwner(user);
//                 lobby.setLobbySetting(lobbySetting);
//                 lobby.setPlayers(new ArrayList<User>());
//                 lobby.setKickedPlayers(new ArrayList<User>());
//                 lobby.setMessages(new ArrayList<Message>());
//                 lobby.setIsJoinable(true);

//                 //creates another User object representing the user to be kicked from the lobby
//                 User userToKick = new User();
//                 userToKick.setId(2L);
//                 userToKick.setName("userToKick");
            
//                 UserPostDTO userPostDTO = UserMapper.INSTANCE.convertEntityToUserPostDTO(userToKick);
            
//                 given(lobbyRepository.findByCode(Mockito.anyString())).willReturn(lobby);
//                 given(lobbyService.kickPlayer(Mockito.anyString(), Mockito.any(), Mockito.any())).willReturn(lobby);
            
//                 // when
//                 MockHttpServletRequestBuilder putRequest = put("/lobbies/1/players")
//                                 .contentType(MediaType.APPLICATION_JSON)
//                                 .content(asJsonString(userPostDTO));
            
//                 // then
//                 mockMvc.perform(putRequest)
//                        .andExpect(status().isOk())
//                        .andExpect(jsonPath("$.id", is(1)))
//                        .andExpect(jsonPath("$.name", is("lobby name")));
//             }


//         @Test
//         public void givenLobbies_whenUpdateLobby_thenReturnStatusOk() throws Exception {
//                 // given

//                 LobbyPutDTO lobbyPutDTO = new LobbyPutDTO();
//                 // set the necessary attributes for lobbyPutDTO, e.g.,
//                 lobbyPutDTO.setIsPublic(true);
//                 lobbyPutDTO.setMaxPlayers(8);
//                 lobbyPutDTO.setMaxRounds(10);
//                 lobbyPutDTO.setMemeChangeLimit(3);
//                 lobbyPutDTO.setSuperLikeLimit(1);
//                 lobbyPutDTO.setSuperDislikeLimit(1);
//                 lobbyPutDTO.setTimeRoundLimit(120);
//                 lobbyPutDTO.setTimeVoteLimit(60);
            
            
//                 doNothing().when(lobbyService).updateLobby(Mockito.anyString(), Mockito.any(), Mockito.any());
            
//                 // when
//                 MockHttpServletRequestBuilder putRequest = put("/lobbies/1")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(asJsonString(lobbyPutDTO));
            
//                 // then
//                 mockMvc.perform(putRequest).andExpect(status().isOk());
//             }




//         /**
//          * Helper Method to convert userPostDTO into a JSON string such that the input
//          * can be processed
//          * Input will look like this: {"name": "Test User", "username":
//          * "testUsername"}
//          *
//          * @param object
//          * @return string
//          */
//         private String asJsonString(final Object object) {
//                 try {
//                         return new ObjectMapper().writeValueAsString(object);
//                 } catch (JsonProcessingException e) {
//                         throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                                         String.format("The request body could not be created.%s", e.toString()));
//                 }
//         }
// }
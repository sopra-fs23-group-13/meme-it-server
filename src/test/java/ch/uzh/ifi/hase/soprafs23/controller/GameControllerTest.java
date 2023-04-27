package ch.uzh.ifi.hase.soprafs23.controller;

import ch.uzh.ifi.hase.soprafs23.entity.*;
import ch.uzh.ifi.hase.soprafs23.job.GameJob;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemePostDTO;
import ch.uzh.ifi.hase.soprafs23.security.JwtSecurityConfig;
import ch.uzh.ifi.hase.soprafs23.service.GameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;


import java.util.Collections;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.mockito.Mockito;


@WebMvcTest(controllers = GameController.class, excludeAutoConfiguration = {
        SecurityAutoConfiguration.class,
        JwtSecurityConfig.class
})
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private GameJob gameJob;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    // @Test
    // public void createGameTest() throws Exception {
    //     // Given
    //     String lobbyCode = "ABCD";
    //     Game game = new Game();
    //     game.setId("1234");

    //     when(gameService.createGame(lobbyCode)).thenReturn(game);

    //     // When
    //     mockMvc.perform(post("/games/{lobbyCode}", lobbyCode))
    //             // Then
    //             .andExpect(status().isCreated())
    //             .andExpect(jsonPath("$.id").value("1234"));
    // }

    // @Test
    // public void getGameTest() throws Exception {
    //     // Given
    //     String gameId = "1234";
    //     Game game = new Game();
    //     game.setId(gameId);

    //     when(gameService.getGame(gameId)).thenReturn(game);

    //     // When
    //     mockMvc.perform(get("/games/{gameId}", gameId))
    //             // Then
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.id").value("1234"));
    // }

    @Test
    @WithMockUser(username = "testUser")    
    public void testCreateMeme() throws Exception {
        MemePostDTO memePostDTO = new MemePostDTO();
        TextBox textBox = new TextBox();
        textBox.setText("Caption");
        textBox.setX(10);
        textBox.setY(20);
        memePostDTO.setTextBoxes(Collections.singletonList(textBox));
        memePostDTO.setColor("color");
        memePostDTO.setCurrentRound(1);
        memePostDTO.setFontSize(15);

        mockMvc.perform(post("/games/{gameId}/meme/{templateId}", "gameId-123", "templateId-123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memePostDTO)))
                .andExpect(status().isCreated());
    }



    // Add more test methods for other GameController methods here, such as
    // getTemplate, createMeme, getMemes, createRating, getRoundRatings, and getWinners

    /**
     * Helper Method to convert an object into a JSON string such that the input
     * can be processed
     *
     * @param object
     * @return string
     */
    private String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("The request body could not be created.%s", e.toString()));
        }
    }
}

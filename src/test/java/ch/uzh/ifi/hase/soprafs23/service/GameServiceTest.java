package ch.uzh.ifi.hase.soprafs23.service;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;

import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.repository.GameRepository;
import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;

public class GameServiceTest {
    @Mock
    private GameRepository gameRepository;

    @Mock
    private LobbyService lobbyService;

    @Mock
    private LobbyRepository lobbyRepository;

    @InjectMocks
    private GameService gameService;

    private Game testGame;

    private Lobby testLobby;

    private LobbySetting lobbySetting;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // given
        testGame = new Game();

        // Initialize templates and currentRound for the testGame
        ArrayList<Template> templates = new ArrayList<>();
        Template template = new Template();
        templates.add(template);
        testGame.setTemplates(templates);
        testGame.setCurrentRound(1);

        testLobby = new Lobby();
        testLobby.setCode("123");

        // Set up LobbySetting for the testLobby
        lobbySetting = new LobbySetting();
        lobbySetting.setMaxRounds(5);
        lobbySetting.setRoundDuration(60);
        lobbySetting.setRatingDuration(30);
        lobbySetting.setMemeChangeLimit(3);
        testLobby.setLobbySetting(lobbySetting);
        testLobby.setPlayers(new ArrayList<>());

        // Set the id of testGame using reflection
        try {
            Field idField = Game.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(testGame, UUID.randomUUID().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // when -> any object is being save in the lobbyRepository -> return the dummy
        // testGame
        Mockito.when(gameRepository.save(any())).thenReturn(testGame);

    }

    @Test
    public void createGame_validInputs_success() {
        // given
        String testLobbyCode = "testLobbyCode";

        Mockito.when(lobbyService.getLobbyByCode(testLobbyCode)).thenReturn(testLobby);

        // when
        Game createdGame = gameService.createGame(testLobbyCode);

        // then
        assertEquals(lobbySetting.getMaxRounds(), createdGame.getGameSetting().getMaxRounds());
        assertEquals(lobbySetting.getRoundDuration(), createdGame.getGameSetting().getRoundDuration());
        assertEquals(lobbySetting.getRatingDuration(), createdGame.getGameSetting().getRatingDuration());
        assertEquals(lobbySetting.getMemeChangeLimit(), createdGame.getGameSetting().getTemplateSwapLimit());
        verify(gameRepository, times(1)).save(any());
        verify(lobbyService, times(1)).getLobbyByCode(testLobbyCode);
    }

    @Test
    public void getGame_validGameId_success() {
        when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

        // when
        Game fetchedGame = gameService.getGame(testGame.getId());

        // then
        assertEquals(testGame, fetchedGame);
        verify(gameRepository, times(1)).findById(testGame.getId());
    }

    @Test
    public void getTemplate_validGameId_success() {
        when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

        User testUser = new User();
        testUser.setName("TestUser");

        // when
        Template fetchedTemplate = gameService.getTemplate(testGame.getId(), testUser);

        // then
        assertNotNull(fetchedTemplate);
        verify(gameRepository, times(1)).findById(testGame.getId());
    }

    // @Test
    // public void createMeme_validInputs_success() {
    // when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

    // User testUser = new User();
    // testUser.setName("TestUser");

    // String testTemplateId = "testTemplateId";
    // Meme testMeme = new Meme();

    // // when
    // gameService.createMeme(testGame.getId(), testTemplateId, testMeme, testUser);

    // // then
    // verify(gameRepository, times(1)).findById(testGame.getId());
    // verify(gameRepository, times(1)).save(any());
    // }

    // @Test
    // public void getMemes_validGameId_success() {
    // when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

    // // when
    // List<Meme> fetchedMemes = gameService.getMemes(testGame.getId());

    // // then
    // assertNotNull(fetchedMemes);
    // verify(gameRepository, times(1)).findById(testGame.getId());
    // }

    // @Test
    // public void createRating_validInputs_success() {
    // when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

    // User testUser = new User();
    // testUser.setName("TestUser");

    // UUID testMemeId = UUID.randomUUID();
    // Rating testRating = new Rating();

    // // when
    // gameService.createRating(testGame.getId(), testMemeId, testRating, testUser);

    // // then
    // verify(gameRepository, times(1)).findById(testGame.getId());
    // verify(gameRepository, times(1)).save(any());
    // }

    // @Test
    // public void getRatingsFromRound_validGameId_success() {
    // when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

    // // when
    // List<Rating> fetchedRatings =
    // gameService.getRatingsFromRound(testGame.getId());

    // // then
    // assertNotNull(fetchedRatings);
    // verify(gameRepository, times(1)).findById(testGame.getId());
    // }

    // @Test
    // public void getAllRatings_validGameId_success() {
    // when(gameRepository.findById(testGame.getId())).thenReturn(java.util.Optional.ofNullable(testGame));

    // // when
    // List<Rating> fetchedAllRatings = gameService.getAllRatings(testGame.getId());

    // // then
    // assertNotNull(fetchedAllRatings);
    // verify(gameRepository, times(1)).findById(testGame.getId());
    // }

}

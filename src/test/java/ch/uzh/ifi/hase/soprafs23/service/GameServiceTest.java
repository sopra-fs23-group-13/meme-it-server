package ch.uzh.ifi.hase.soprafs23.service;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

}

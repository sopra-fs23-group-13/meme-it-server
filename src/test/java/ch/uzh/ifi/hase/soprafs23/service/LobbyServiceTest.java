package ch.uzh.ifi.hase.soprafs23.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.apache.catalina.User;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;

public class LobbyServiceTest {
    @Mock
    private LobbyRepository lobbyRepository;

    @InjectMocks
    private LobbyService lobbyService;

    private Lobby lobby;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // given
        lobby = new Lobby();
        lobby.setId(1L);
                lobby.setName("lobby name");
                lobby.setOwner("owner name");
                lobby.setIsJoinable(true);


        // when -> any object is being save in the userRepository -> return the dummy
        // testUser
        Mockito.when(lobbyRepository.save(Mockito.any())).thenReturn(lobby);
    }

    @Test
    public void createLobby_validInputs_success() {
        // when -> any object is being save in the userRepository -> return the dummy
        // testUser
        Lobby createdLobby = lobbyService.createLobby(lobby);

        // then
        Mockito.verify(lobbyRepository, Mockito.times(1)).save(Mockito.any());

        assertEquals(lobby.getName(), createdLobby.getName());
        assertEquals(lobby.getOwner(), createdLobby.getOwner());

    }

    
}

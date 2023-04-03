package ch.uzh.ifi.hase.soprafs23.service;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;
import ch.uzh.ifi.hase.soprafs23.repository.UserRepository;
import ch.uzh.ifi.hase.soprafs23.utility.NameGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Lobby Service
 * This class is the "worker" and responsible for all functionality related to
 * the lobby
 * (e.g., it creates, modifies, finds, kicks, closes). The result will be passed
 * back
 * to the caller.
 */
@Service
@Transactional
public class LobbyService {
    private final Logger log = LoggerFactory.getLogger(LobbyService.class);

    private final LobbyRepository lobbyRepository;

    private final NameGenerator nameGenerator = new NameGenerator();

    @Autowired
    public LobbyService(@Qualifier("lobbyRepository") LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    public List<Lobby> getLobbies() {
        return this.lobbyRepository.findAll();
    }

    public Lobby createLobby(Lobby newLobby) {
        String code = nameGenerator.getReadableId();
        newLobby.setCode(code);

        checkIfLobbyExists(newLobby);
        // saves the given entity but data is only persisted in the database once
        // flush() is called
        newLobby = lobbyRepository.save(newLobby);
        lobbyRepository.flush();

        System.out.println("LobbyService: " + newLobby.getId());

        log.debug("Created Information for Lobby: {}", newLobby);
        return newLobby;
    }

    public Lobby updateLobby(String lobbyId, LobbySetting newSettings) {
        Lobby lobbyToUpdate = getLobbyById(Long.parseLong(lobbyId));
        lobbyToUpdate.setLobbySetting(newSettings);
        lobbyRepository.save(lobbyToUpdate);
        return lobbyToUpdate;
    }

    public Lobby getLobbyById(Long id) {
        return lobbyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby not found"));
    }

    private void checkIfLobbyExists(Lobby lobby) {
        if (lobbyRepository.findByCode(lobby.getCode()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Lobby already exists");
        }
    }

    public void joinLobby(Long lobbyId, Long userId) {
        Lobby lobby = checkLobbyExist(lobbyId);
        User user = checkUserExist(userId);

      if(lobby.isFull()){
         throw new ResponseStatusException( HttpStatus.CONFLICT, "Lobby is full");
      }

      if(lobby.getKickedPlayers().contains(user)){
          throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "You can not join again, already kicked out!");
      }

        lobby.join(user);

      if(lobby.isFull()){
          lobby.setIsJoinable(false);
          lobbyRepository.save(lobby);
      }
    }

    public void leaveLobby(Long lobbyId, Long userId) {
        Lobby lobby =  checkLobbyExist(lobbyId);
        User user = checkUserExist(userId);
        checkUserIsAPlayer(lobby, user);

        lobby.exit(user);
       lobbyRepository.save(lobby);
    }

    public void kickFromLobby(Long lobbyId, Long userId) {
        Lobby lobby =  checkLobbyExist(lobbyId);
        User user = checkUserExist(userId);
        checkUserIsAPlayer(lobby, user);

        lobby.kick(user);
        lobbyRepository.save(lobby);
    }

    private Lobby checkLobbyExist(Long lobbyId) {
       return lobbyRepository.findById(lobbyId)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,"Lobby does not exist"));
    }

    //create UserRepository 
    private User checkUserExist(Long userId) {
      return UserRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "User does not exist"));
    }


    private static void checkUserIsAPlayer(Lobby lobby, User user) {
        boolean isPlayer = lobby.getPlayers().contains(user);
        if(!isPlayer){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not a player");
        }
    }
}

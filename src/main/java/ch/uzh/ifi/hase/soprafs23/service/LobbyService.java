package ch.uzh.ifi.hase.soprafs23.service;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;
import ch.uzh.ifi.hase.soprafs23.utility.NameGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
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

    // private final UserRepository usersRepository;

    private final NameGenerator nameGenerator = new NameGenerator();

    @Autowired
    public LobbyService(@Qualifier("lobbyRepository") LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
        // this.usersRepository = usersRepository;
    }

    public List<Lobby> getLobbies() {
        return this.lobbyRepository.findAll();
    }

    public Lobby createLobby(Lobby newLobby, User user) {
        String code = nameGenerator.getReadableId();
        newLobby.setCode(code);
        newLobby.setOwner(user);

        checkIfLobbyExists(newLobby);
        // saves the given entity but data is only persisted in the database once
        // flush() is called
        newLobby = lobbyRepository.save(newLobby);
        lobbyRepository.flush();

        System.out.println("LobbyService: " + newLobby.getId());

        log.debug("Created Information for Lobby: {}", newLobby);
        return newLobby;
    }

    public Lobby updateLobby(String lobbyCode, Lobby newLobby, User owner) {
        Lobby lobbyToUpdate = getLobbyByCode(lobbyCode);

        if (!lobbyToUpdate.getOwner().equals(owner)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the owner of this lobby.");
        }

        newLobby.getLobbySetting();
        LobbySetting newSettings = newLobby.getLobbySetting();
        // Check which values are provided so that no null values are saved

        // Weird checking for getIsPublic, because "if(getIsPublic == null)" throws
        // error for some reason
        if (newSettings.getIsPublic() == true) {
            newSettings.setIsPublic(true);
        } else if (newSettings.getIsPublic() == false) {
            newSettings.setIsPublic(false);
        } else {
            newSettings.setIsPublic(lobbyToUpdate.getLobbySetting().getIsPublic());
        }

        if (newSettings.getMaxPlayers() == null) {
            newSettings.setMaxPlayers(lobbyToUpdate.getLobbySetting().getMaxPlayers());
        }

        if (newSettings.getMaxRounds() == null) {
            newSettings.setMaxRounds((lobbyToUpdate.getLobbySetting().getMaxRounds()));
        }

        if (newSettings.getMemeChangeLimit() == null) {
            newSettings.setMemeChangeLimit((lobbyToUpdate.getLobbySetting().getMemeChangeLimit()));
        }

        if (newSettings.getSuperLikeLimit() == null) {
            newSettings.setSuperLikeLimit((lobbyToUpdate.getLobbySetting().getSuperLikeLimit()));
        }

        if (newSettings.getSuperDislikeLimit() == null) {
            newSettings.setSuperDislikeLimit((lobbyToUpdate.getLobbySetting().getSuperDislikeLimit()));
        }

        if (newSettings.getRoundDuration() == null) {
            newSettings.setRoundDuration((lobbyToUpdate.getLobbySetting().getRoundDuration()));
        }

        if (newSettings.getRatingDuration() == null) {
            newSettings.setRatingDuration((lobbyToUpdate.getLobbySetting().getRatingDuration()));
        }

        if (newLobby.getName() != null && !newLobby.getName().equals("")) {
            lobbyToUpdate.setName(newLobby.getName());
        }
        if (newLobby.getOwner() != null) {
            lobbyToUpdate.setOwner(newLobby.getOwner());
        }
        lobbyToUpdate.setLobbySetting(newSettings);
        lobbyRepository.save(lobbyToUpdate);
        return lobbyToUpdate;
    }

    public Lobby getLobbyByCode(String code) {
        Lobby lobby = lobbyRepository.findByCode(code);

        if (lobby == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby not found");
        }
        return lobby;
    }

    private void checkIfLobbyExists(Lobby lobby) {
        if (lobbyRepository.findByCode(lobby.getCode()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Lobby already exists");
        }
    }

    public Lobby joinLobby(String lobbyCode, User user) {
        Lobby lobby = getLobbyByCode(lobbyCode);

        // * joinable is set if game is started
        if (!lobby.isJoinable()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Lobby is not joinable");
        }

        if (lobby.isFull()) {
            lobby.setIsJoinable(false);
            lobbyRepository.save(lobby);
            lobbyRepository.flush();
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Lobby is full.");
        }

        if (lobby.getKickedPlayers().stream().anyMatch(user1 -> user1.equals(user))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot join again, you've been kicked.");
        }

        // add user
        lobby.addPlayer(user);

        // persist changes
        lobbyRepository.save(lobby);
        lobbyRepository.flush();

        return lobby;
    }

    public void leaveLobby(String lobbyCode, User user) {
        Lobby lobby = getLobbyByCode(lobbyCode);

        lobby.removePlayer(user);

        // persist changes
        lobbyRepository.save(lobby);
        lobbyRepository.flush();
    }

    public Lobby kickPlayer(String lobbyCode, User owner, User userKick) {
        Lobby lobby = getLobbyByCode(lobbyCode);

        if (!lobby.getOwner().equals(owner)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not the owner of this lobby.");
        }

        lobby.removePlayer(userKick);
        lobby.addKickedPlayer(userKick);

        // persist changes
        lobbyRepository.save(lobby);
        lobbyRepository.flush();

        return lobby;
    }

    /**
     * Sets the game id and start time of the lobby
     * 
     * @param lobbyCode
     * @param gameId
     * @param startTime
     */
    public void setGameStarted(String lobbyCode, String gameId, Date startTime) {
        Lobby lobby = getLobbyByCode(lobbyCode);

        lobby.setGameStartedAT(startTime);
        lobby.setGameId(gameId);

        // persist changes
        lobbyRepository.save(lobby);
        lobbyRepository.flush();
    }

}
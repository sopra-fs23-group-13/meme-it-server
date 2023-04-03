package ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby;

import org.mapstruct.ObjectFactory;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PostDTO;
//import java.util.HashSet;
import java.util.ArrayList;

public class DTOFactory {

    @ObjectFactory
    public Lobby convertLobbyPostDTOtoEntity(PostDTO lobbyPostDTO) {

        Lobby lobby = new Lobby();

    LobbySetting lobbySetting = new LobbySetting();
    lobbySetting.setIsPublic(lobbyPostDTO.getIsPublic());
    lobbySetting.setMaxPlayers(lobbyPostDTO.getMaxPlayers());
    lobbySetting.setMaxRounds(lobbyPostDTO.getMaxRounds());
    lobbySetting.setMemeChangeLimit(lobbyPostDTO.getMemeChangeLimit());
    lobbySetting.setSuperLikeLimit(lobbyPostDTO.getSuperLikeLimit());
    lobbySetting.setSuperDislikeLimit(lobbyPostDTO.getSuperDislikeLimit());
    lobbySetting.setTimeRoundLimit(lobbyPostDTO.getTimeRoundLimit());
    lobbySetting.setTimeVoteLimit(lobbyPostDTO.getTimeVoteLimit());
    lobbySetting.setLobbyName(lobbyPostDTO.getLobbyName());

    lobby.setName(lobbyPostDTO.getName());
    //lobby.setOwner(lobbyPostDTO.getOwner());
    lobby.setLobbySetting(lobbySetting);
    //lobby.setPlayers(new HashSet<>());
    //lobby.setKickedPlayers(new HashSet<>());
    lobby.setMessages(new ArrayList<>());
    lobby.setIsJoinable(true);

    return lobby;
    }
}
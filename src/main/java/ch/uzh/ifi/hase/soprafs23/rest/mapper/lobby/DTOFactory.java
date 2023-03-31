package ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby;

import org.mapstruct.ObjectFactory;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.entity.Messages;
import ch.uzh.ifi.hase.soprafs23.entity.Users;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PostDTO;

public class DTOFactory {

    @ObjectFactory
    public Lobby convertLobbyPostDTOtoEntity(PostDTO lobbyPostDTO) {

        Lobby lobby = new Lobby();

        LobbySetting lobbySetting = new LobbySetting(
                lobbyPostDTO.getIsPublic(),
                lobbyPostDTO.getMaxPlayers(),
                lobbyPostDTO.getMaxRounds(),
                lobbyPostDTO.getMemeChangeLimit(),
                lobbyPostDTO.getSuperLikeLimit(),
                lobbyPostDTO.getSuperDislikeLimit(),
                lobbyPostDTO.getTimeRoundLimit(),
                lobbyPostDTO.getTimeVoteLimit());

        lobby.setName(lobbyPostDTO.getName());
        lobby.setOwner(lobbyPostDTO.getOwner());
        lobby.setLobbySetting(lobbySetting);
        lobby.setPlayers(new Users());
        lobby.setKickedPlayers(new Users());
        lobby.setMessages(new Messages());
        lobby.setIsJoinable(true);

        return lobby;
    }
}
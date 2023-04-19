package ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.ObjectFactory;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.entity.Message;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyPostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.UserGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.user.UserMapper;

public class DTOFactory {

    @ObjectFactory
    public Lobby convertLobbyPostDTOtoEntity(LobbyPostDTO lobbyPostDTO) {

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
        lobby.setLobbySetting(lobbySetting);
        lobby.setPlayers(new ArrayList<User>());
        lobby.setKickedPlayers(new ArrayList<User>());
        lobby.setMessages(new ArrayList<Message>());
        lobby.setIsJoinable(true);

        return lobby;
    }

    @ObjectFactory
    public LobbyGetDTO convertEntityToLobbyGetDTO(Lobby lobby) {

        LobbyGetDTO lobbyGetDTO = new LobbyGetDTO();

        lobbyGetDTO.setCode(lobby.getCode());
        lobbyGetDTO.setName(lobby.getName());
        lobbyGetDTO.setOwner(UserMapper.INSTANCE.convertEntityToUserGetDTO(lobby.getOwner()));
        lobbyGetDTO.setLobbySetting(lobby.getLobbySetting());

        List<UserGetDTO> playerDTOs = new ArrayList<UserGetDTO>();
        for (User player : lobby.getPlayers()) {
            playerDTOs.add(UserMapper.INSTANCE.convertEntityToUserGetDTO(player));
        }
        lobbyGetDTO.setPlayers(playerDTOs);
        lobbyGetDTO.setMessages(lobby.getMessages());

        return lobbyGetDTO;
    }
}
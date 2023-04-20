package ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby;

import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
// import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;

import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyPostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyPutDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.LobbyGetDTO;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PutDTO;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * DTOMapper.
 * This class is responsible for generating classes that will automatically
 * transform/map the internal representation
 * of an entity (e.g., the Lobby) to the external/API representation (e.g.,
 * LobbyGetDTO for getting, LobbyPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for
 * creating information (POST).
 */
@Mapper(uses = { DTOFactory.class, /* EntityFactory.class */ })
public interface LobbyMapper {

  LobbyMapper INSTANCE = Mappers.getMapper(LobbyMapper.class);

  Lobby convertLobbyPostDTOtoEntity(LobbyPostDTO lobbyPostDTO);

  Lobby convertLobbyPutDTOtoEntity(LobbyPutDTO lobbyPutDTO);

  // @Mapping(source = "isPublic", target = "isPublic")
  // @Mapping(source = "maxPlayers", target = "maxPlayers")
  // @Mapping(source = "maxRounds", target = "maxRounds")
  // @Mapping(source = "memeChangeLimit", target = "memeChangeLimit")
  // @Mapping(source = "superLikeLimit", target = "superLikeLimit")
  // @Mapping(source = "superDislikeLimit", target = "superDislikeLimit")
  // @Mapping(source = "timeRoundLimit", target = "timeRoundLimit")
  // @Mapping(source = "timeVoteLimit", target = "timeVoteLimit")
  // LobbySetting convertLobbyPutDTOtoEntity(PutDTO lobbyPutDTO);

  LobbyGetDTO convertEntityToLobbyGetDTO(Lobby lobby);

}

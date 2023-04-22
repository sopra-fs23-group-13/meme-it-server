package ch.uzh.ifi.hase.soprafs23.rest.mapper.game;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;

@Mapper(uses = { DTOFactory.class, /* EntityFactory.class */ })
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    GameGetDTO convertEntityToGameGetDTO(Game game);

}
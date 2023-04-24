package ch.uzh.ifi.hase.soprafs23.rest.mapper.game;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;

@Mapper()
public interface GameMapper {

    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    @Mapping(source = "players", target = "players")
    @Mapping(source = "state", target = "gameState")
    @Mapping(source = "gameSetting.roundDuration", target = "roundDuration")
    @Mapping(source = "gameSetting.ratingDuration", target = "votingDuration")
    @Mapping(source = "currentRound", target = "currentRound")
    @Mapping(source = "gameSetting.maxRounds", target = "totalRounds")
    @Mapping(source = "startedAt", target = "startedAt")
    @Mapping(source = "id", target = "id")
    GameGetDTO convertEntityToGameGetDTO(Game game);

}
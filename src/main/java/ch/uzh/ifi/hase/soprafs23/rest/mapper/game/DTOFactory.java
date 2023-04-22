package ch.uzh.ifi.hase.soprafs23.rest.mapper.game;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;
import org.mapstruct.ObjectFactory;

public class DTOFactory {

    @ObjectFactory
    public GameGetDTO convertEntityToGameGetDTO(Game game) {

        GameGetDTO gameGetDTO = new GameGetDTO();

        gameGetDTO.setId(game.getId());
        gameGetDTO.setGameState(game.getState());
        gameGetDTO.setRoundDuration(game.getGameSetting().getRoundDuration());
        gameGetDTO.setVotingDuration(game.getGameSetting().getRatingDuration());
        gameGetDTO.setTotalRounds(game.getGameSetting().getMaxRounds());
        gameGetDTO.setCurrentRound(game.getCurrentRound());
        gameGetDTO.setStartedAt(game.getStartedAt());

        return gameGetDTO;
    }

}
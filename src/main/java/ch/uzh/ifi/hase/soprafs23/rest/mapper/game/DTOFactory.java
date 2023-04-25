package ch.uzh.ifi.hase.soprafs23.rest.mapper.game;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.Player;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.player.PlayerGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.player.PlayerMapper;
import org.mapstruct.ObjectFactory;

import java.util.ArrayList;
import java.util.List;

public class DTOFactory {

    @ObjectFactory
    public GameGetDTO convertEntityToGameGetDTO(Game game) {

        GameGetDTO gameGetDTO = new GameGetDTO();

        gameGetDTO.setId(game.getId());
        gameGetDTO.setGameState(game.getState());
        gameGetDTO.setRoundDuration(game.getGameSetting().getRoundDuration());
        gameGetDTO.setVotingDuration(game.getGameSetting().getRatingDuration());
        gameGetDTO.setRoundResultDuration(game.getGameSetting().getRoundResultDuration());
        gameGetDTO.setTotalRounds(game.getGameSetting().getMaxRounds());
        gameGetDTO.setCurrentRound(game.getCurrentRound());
        gameGetDTO.setStartedAt(game.getStartedAt());
        List<PlayerGetDTO> playerDTOs = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            playerDTOs.add(PlayerMapper.INSTANCE.convertEntityToPlayerGetDTO(player));
        }
        gameGetDTO.setPlayers(playerDTOs);

        return gameGetDTO;
    }

}
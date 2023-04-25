package ch.uzh.ifi.hase.soprafs23.job;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.GameState;
import ch.uzh.ifi.hase.soprafs23.entity.Round;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.service.GameService;

@Component
@Transactional
public class GameJob {
    private final Logger log = LoggerFactory.getLogger(GameJob.class);

    private final GameService gameService;

    public GameJob(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Executes the job which will manage the state throughout the lifetime of the
     * game
     * 
     * @param gameId
     */
    public void exectue(String gameId) {
        while (true) {
            Game game = gameService.getGame(gameId);
            // get game players
            List<User> players = game.getPlayers();
            // get current round
            Round round = game.getRound();

            // Calculate different phase start times
            Long roundStart = round.getStartedAt().getTime();
            Long ratingStart = roundStart + game.getGameSetting().getRoundDuration();
            Long roundResultsStart = ratingStart + game.getGameSetting().getRatingDuration();
            Long nextRoundStart = roundResultsStart + game.getGameSetting().getRoundResultDuration();

            // check if game is finished
            if (game.getGameSetting().getMaxRounds() == game.getCurrentRound()) {
                game.setState(GameState.GAME_RESULTS);
                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase GAME_RESULTS");
                return;
            }
            // check if everyone submited or creation phase is over
            else if (round.getSubmitedMemes().size() == players.size() || game.getState() == GameState.CREATION
                    && ratingStart <= Calendar.getInstance().getTime().getTime()) {
                // close round
                round.setOpen(false);
                // start voting phase
                game.setState(GameState.RATING);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase RATING");
            }
            // check if everyone rated or rating phase is over
            else if (round.getRatings().size() == players.size() || game.getState() == GameState.RATING
                    && roundResultsStart <= Calendar.getInstance().getTime().getTime()) {
                // start round result phase
                game.setState(GameState.ROUND_RESULTS);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase ROUND_RESULTS");
            }
            // game not finished yet, next round
            // check if round_result phase is over
            else if (game.getState() == GameState.ROUND_RESULTS
                    && nextRoundStart <= Calendar.getInstance().getTime().getTime()) {

                // start creation phase
                game.setState(GameState.CREATION);
                // increment round
                game.setCurrentRound(game.getCurrentRound() + 1);
                // initialize new round
                Round nextRound = game.getRound();
                nextRound.setOpen(true);
                nextRound.setRoundNumber(null);
                nextRound.setStartedAt(Calendar.getInstance().getTime());
                game.setRound(nextRound);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase CREATION");
            }

            // persist changes
            gameService.save(game);

            // sleep for 1 second
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isRatingPhrase(GameState state, List<User> players, Round round, Long ratingStart) {
        return round.getSubmitedMemes().size() == players.size() || state == GameState.CREATION
                && ratingStart <= Calendar.getInstance().getTime().getTime();
    }

    private boolean isRoundResultPshase(GameState state, List<User> players, Round round, Long roundResultsStart) {
        return round.getRatings().size() == players.size() || state == GameState.RATING
                && roundResultsStart <= Calendar.getInstance().getTime().getTime();
    }

    private boolean isGameResultsPhase(Game game) {
        return game.getGameSetting().getMaxRounds() == game.getCurrentRound();
    }

}

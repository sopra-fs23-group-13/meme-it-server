package ch.uzh.ifi.hase.soprafs23.job;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.GameState;
import ch.uzh.ifi.hase.soprafs23.entity.Meme;
import ch.uzh.ifi.hase.soprafs23.entity.Rating;
import ch.uzh.ifi.hase.soprafs23.entity.Round;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.service.GameService;

// TODO: make testable by seperating components into smaller functions 

/**
 * Contains the code for the acutal game server
 * 
 * Game server updates every second
 */
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
            Long ratingStart = roundStart + (game.getGameSetting().getRatingDuration() * 1000);
            Long roundResultsStart = ratingStart + (game.getGameSetting().getRoundDuration() * 1000);
            Long nextRoundStart = roundResultsStart + (game.getGameSetting().getRoundResultDuration() * 1000);

            Long timeNow = Calendar.getInstance().getTime().getTime();

            Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
            log.info("\nRound started at: " + format.format(new Date(roundStart)));
            log.info("Rating started at: " + format.format(new Date(ratingStart)));
            log.info("Round results started at: " + format.format(new Date(roundResultsStart)));
            log.info("Next round started at: " + format.format(new Date(nextRoundStart)));
            log.info("\n");

            // check if game is finished
            if (game.getState() == GameState.RATING && game.getGameSetting().getMaxRounds() == game.getCurrentRound()) {
                game.setState(GameState.GAME_RESULTS);
                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase GAME_RESULTS");
                return;
            }
            // game not finished yet, next round
            // check if round_result phase is over
            else if (game.getState() == GameState.ROUND_RESULTS
                    && nextRoundStart <= timeNow) {

                // start creation phase
                game.setState(GameState.CREATION);
                // increment round
                game.setCurrentRound(game.getCurrentRound() + 1);
                // initialize new round
                Round nextRound = new Round();
                List<Meme> memes = new ArrayList<Meme>(players.size());
                List<Rating> ratings = new ArrayList<Rating>(players.size() * (players.size() - 1));
                nextRound.setMemes(memes);
                nextRound.setRatings(ratings);
                nextRound.setOpen(true);
                nextRound.setRoundNumber(round.getRoundNumber() + 1);
                nextRound.setStartedAt(Calendar.getInstance().getTime());
                game.addRound(nextRound);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase CREATION");
            }
            // check if everyone rated or rating phase is over
            else if (round.getRatings().size() == players.size() || game.getState() == GameState.RATING
                    && roundResultsStart <= timeNow) {
                // start round result phase
                game.setState(GameState.ROUND_RESULTS);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase ROUND_RESULTS");

            }
            // check if everyone submited or creation phase is over
            else if (round.getSubmitedMemes().size() == players.size() || game.getState() == GameState.CREATION
                    && ratingStart <= timeNow) {
                // close round
                round.setOpen(false);
                // start voting phase
                game.setState(GameState.RATING);

                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase RATING");
            } else {
                log.info("GameId: " + gameId + " - Round " + game.getCurrentRound() + " Phase "
                        + game.getState().toString().toUpperCase());
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

}

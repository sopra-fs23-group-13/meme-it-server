package ch.uzh.ifi.hase.soprafs23.service;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.GameSetting;
import ch.uzh.ifi.hase.soprafs23.entity.GameState;
import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.Meme;

import ch.uzh.ifi.hase.soprafs23.entity.Rating;
import ch.uzh.ifi.hase.soprafs23.entity.Round;
import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.repository.GameRepository;
import ch.uzh.ifi.hase.soprafs23.utility.memeapi.IMemeApi;
import ch.uzh.ifi.hase.soprafs23.utility.memeapi.ImgflipClient;
import ch.uzh.ifi.hase.soprafs23.utility.memeapi.ImgflipClient.ApiResponse;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.UUID;

import org.jobrunr.scheduling.JobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class GameService {
    private final Logger log = LoggerFactory.getLogger(LobbyService.class);

    private final IMemeApi memeApi = new ImgflipClient();

    private final LobbyService lobbyService;

    private final GameRepository gameRepository;

    public GameService(@Qualifier("gameRepository") GameRepository gameRepository,
            LobbyService lobbyService) {
        this.gameRepository = gameRepository;
        this.lobbyService = lobbyService;
    }

    /**
     * Creates a new game
     * 
     * @param lobbyId
     * @return
     */
    public Game createGame(String lobbyCode) {
        Game newGame = new Game();
        Lobby lobby = lobbyService.getLobbyByCode(lobbyCode);

        // set templates
        ApiResponse apiResponse = memeApi.getTemplates();
        List<Template> templates = new ArrayList<Template>(apiResponse.data.memes.size());
        for (var meme : apiResponse.data.memes) {
            Template template = new Template();
            template.setImageUrl(meme.url);
            templates.add(template);
        }
        newGame.setTemplates(templates);
        newGame.setState(GameState.CREATION);

        // set game settings
        GameSetting gameSetting = new GameSetting();
        gameSetting.setMaxRounds(lobby.getLobbySetting().getMaxRounds());
        gameSetting.setRoundDuration(lobby.getLobbySetting().getRoundDuration());
        gameSetting.setRatingDuration(lobby.getLobbySetting().getRatingDuration());
        gameSetting.setTemplateSwapLimit(lobby.getLobbySetting().getMemeChangeLimit());
        gameSetting.setRoundResultDuration(20); // ! default 20 seconds
        newGame.setGameSetting(gameSetting);

        // set round
        newGame.setCurrentRound(1);

        // initialise players
        List<User> users = lobby.getPlayers();
        newGame.setPlayers(users);

        // Add 2 seconds to the current time
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        newGame.setStartedAt(calendar.getTime());

        // initialise first round
        List<Round> rounds = new ArrayList<Round>(lobby.getLobbySetting().getMaxRounds());
        Round round = new Round();
        round.setOpen(true);
        round.setStartedAt(Calendar.getInstance().getTime());
        round.setRoundNumber(1);
        rounds.add(0, round);

        newGame.setRounds(rounds);

        save(newGame);

        lobbyService.setGameStarted(lobbyCode, newGame.getId(), newGame.getStartedAt());

        return newGame;
    }

    /**
     * Returns a game by id
     * 
     * @param gameId
     * @return
     */
    public Game getGame(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        return game;
    }

    /**
     * Returns the template of a meme
     * 
     * @param gameId
     * @return
     */
    public Template getTemplate(String gameId, User user) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        // TODO: check user has can get more templates depending on lobby rules
        // TODO: update user has gotten template

        return game.getTemplate();
    }

    /**
     * Submits a meme of a user
     * 
     * @param gameId
     * @param meme
     * @param user
     */
    public void createMeme(String gameId, String templateId, Meme meme, User user) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        meme.setUser(user);

        Round round = game.getRound();

        // check if round still open
        if (!round.isOpen()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Round is not open");
        }

        // set user chosen template
        Template template = game.getTemplateById(templateId);
        meme.setTemplate(template);

        // add meme to the round
        round.addMeme(meme);

        // update round
        game.setRound(round);

        // perist changes
        save(game);

    }

    public List<Meme> getMemes(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        Round round = game.getRound();

        return round.getMemes();
    }

    /**
     * Submits a rating for a meme by a user
     * 
     * @param gameId
     * @param memeId
     * @param rating
     * @param user
     */
    public void createRating(String gameId, UUID memeId, Rating rating, User user) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        // TODO: check user actually part of game
        // TODO: check user has not already rated meme
        // TODO: user is not meme owner

        Round round = game.getRound();
        Meme meme = round.getMemeById(memeId);
        if (meme == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meme not found");
        }

        rating.setUser(user);
        rating.setMeme(meme);
        round.addRating(rating);

        // update round
        game.setRound(round);

        // perist changes
        save(game);
    }

    /**
     * Sets a player state to ready, meaning they are ready to start the next round
     * 
     * @param gameId
     * @param user
     */
    // public void setPlayerReady(String gameId, User user) {
    // Game game = gameRepository.findById(gameId)
    // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game
    // not found"));

    // List<Player> players = game.getPlayers();

    // for (Player player : players) {
    // if (user.getId() == player.getUser().getId()) {
    // player.setState(PlayerState.READY);
    // }
    // }

    // game.setPlayers(players);

    // // perist changes
    // save(game);
    // }

    /**
     * Returns a list of ratings from the past round
     * 
     * @param gameId
     * @return
     */
    public List<Rating> getRatingsFromRound(String gameId) {
        // Return a list of players, their score and the meme of the round
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        Round round = game.getRound();

        return round.getRatings();
    }

    /**
     * Returns all ratings from the game
     * 
     * @param gameId
     * @return
     */
    public List<Rating> getAllRatings(String gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        List<Round> rounds = game.getRounds();

        List<Rating> allRatings = new ArrayList<Rating>();

        for (Round round : rounds) {
            allRatings.addAll(round.getRatings());
        }
        return allRatings;
    }

    /**
     * Perists game changes
     * 
     * @param game
     */
    public void save(Game game) {
        gameRepository.save(game);
        gameRepository.flush();
    }

}

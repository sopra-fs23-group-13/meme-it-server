
package ch.uzh.ifi.hase.soprafs23.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PutDTO;

import org.springframework.web.bind.annotation.*;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.Meme;
import ch.uzh.ifi.hase.soprafs23.entity.Rating;
import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemeGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.meme.MemePostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.rating.RatingGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.rating.RatingPostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.template.TemplateGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.game.GameMapper;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.meme.MemeMapper;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.rating.RatingMapper;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.template.TemplateMapper;
import ch.uzh.ifi.hase.soprafs23.service.GameService;

/**
 * Game Controller
 * This class is responsible for handling all REST request that are related to
 * the game.
 * The controller will receive the request and delegate the execution to the
 * GameService and finally return the result.
 */
@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games/{lobbyCode}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GameGetDTO createGame(@PathVariable String lobbyCode) {

        // create game
        Game game = gameService.createGame(lobbyCode);

        return GameMapper.INSTANCE.convertEntityToGameGetDTO(game);
    }

    /**
     * Gets the current game state
     * 
     * @param gameId
     * @param memeId
     * @return
     */
    @GetMapping("/games/{gameId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GameGetDTO getGame(@PathVariable String gameId) {

        Game game = gameService.getGame(gameId);

        return GameMapper.INSTANCE.convertEntityToGameGetDTO(game);
    }

    /** Get a template. Should also be used for swapping memes */
    @GetMapping("/games/{gameId}/template")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TemplateGetDTO getTemplate(@PathVariable String gameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Template template = gameService.getTemplate(gameId, user);

        return TemplateMapper.INSTANCE.convertEntityToGetDTO(template);
    }

    /**
     * Submits a meme to the game
     * 
     * @param gameId
     * @param memePostDTO
     */
    @PostMapping("/games/{gameId}/meme/{templateId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMeme(@PathVariable String gameId, @PathVariable String templateId,
            @RequestBody MemePostDTO memePostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Meme meme = MemeMapper.INSTANCE.convertMemePostDTOtoEntity(memePostDTO);

        gameService.createMeme(gameId, templateId, meme, user);
    }

    /**
     * Gets memes from current round in game
     * 
     * @param gameId
     */
    @GetMapping("/games/{gameId}/meme")
    @ResponseStatus(HttpStatus.CREATED)
    public List<MemeGetDTO> getMemes(@PathVariable String gameId) {

        List<Meme> memes = gameService.getMemes(gameId);

        List<MemeGetDTO> memeGetDTOs = new ArrayList<>();
        for (Meme meme : memes) {
            memeGetDTOs.add(MemeMapper.INSTANCE.convertEntityToGetDTO(meme));
        }

        return memeGetDTOs;
    }

    /**
     * Submits a rating of a meme to the game
     * 
     * @param gameId
     * @param memeId
     * @param ratingPostDTO
     */
    @PostMapping("/games/{gameId}/rating/{memeId}")
    @ResponseStatus(HttpStatus.OK)
    public void createRating(@PathVariable String gameId, @PathVariable UUID memeId,
            @RequestBody RatingPostDTO ratingPostDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Rating rating = RatingMapper.INSTANCE.convertRatingPostDTOtoEntity(ratingPostDTO);
        gameService.createRating(gameId, memeId, rating, user);
    }

    /**
     * Sets a player as ready for next round
     * 
     * @param gameId
     * @param memeId
     * @param ratingPostDTO
     */
    @PostMapping("/games/{gameId}/ready")
    @ResponseStatus(HttpStatus.OK)
    public void setPlayerReady(@PathVariable String gameId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        gameService.setPlayerReady(gameId, user);
    }

    /**
     * Get all ratings in a round
     * 
     * @param gameId
     * @param memeId
     * @return
     */
    @GetMapping("/games/{gameId}/rating")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RatingGetDTO> getRoundRatings(@PathVariable String gameId) {

        List<Rating> ratings = gameService.getRatingsFromRound(gameId);

        List<RatingGetDTO> ratingGetDTOs = new ArrayList<RatingGetDTO>();
        for (Rating rating : ratings) {
            ratingGetDTOs.add(RatingMapper.INSTANCE.convertEntityToRatingGetDTO(rating));
        }
        return ratingGetDTOs;
    }

    /**
     * Get all ratings in a game
     * 
     * @param gameId
     * @param memeId
     * @return
     */
    @GetMapping("/games/{gameId}/winner")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<RatingGetDTO> getWinners(@PathVariable String gameId) {

        List<Rating> ratings = gameService.getAllRatings(gameId);

        List<RatingGetDTO> ratingGetDTOs = new ArrayList<RatingGetDTO>();
        for (Rating rating : ratings) {
            ratingGetDTOs.add(RatingMapper.INSTANCE.convertEntityToRatingGetDTO(rating));
        }

        return ratingGetDTOs;
    }
}


package ch.uzh.ifi.hase.soprafs23.controller;

import org.springframework.http.HttpStatus;

// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PutDTO;

import org.springframework.web.bind.annotation.*;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.Meme;
import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.repository.GameRepository;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.GameGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.game.MemeGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.dto.template.TemplateGetDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.game.GameMapper;
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

    @PostMapping("/game/{lobbyId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GameGetDTO createGame(@PathVariable Long lobbyId) {
        // create a game
        Game game = gameService.createGame(lobbyId);

        return GameMapper.INSTANCE.convertEntityToGameGetDTO(game);
    }

    @GetMapping("/game/{id}/template")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TemplateGetDTO getTemplate(@PathVariable Long id) {

        Template template = gameService.getTemplate(id);

        return TemplateMapper.INSTANCE.convertEntityToGetDTO(template);
    }
}

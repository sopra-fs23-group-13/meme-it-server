package ch.uzh.ifi.hase.soprafs23.service;

import ch.uzh.ifi.hase.soprafs23.entity.Game;
import ch.uzh.ifi.hase.soprafs23.entity.Template;
import ch.uzh.ifi.hase.soprafs23.repository.GameRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Service
@Transactional
public class GameService {
    private final Logger log = LoggerFactory.getLogger(LobbyService.class);

    private final TemplateService templateService = new TemplateService();

    private final GameRepository gameRepository;

    @Autowired
    public GameService(@Qualifier("gameRepository") GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(Long lobbyId) {
        Game newGame = new Game();
        // TODO: set lobby is joinable to false
        // set templates
        newGame.setTemplates(templateService.getTemplates());
        // set lobby
        newGame.setLobbyId(lobbyId);

        log.info("Created new game: " + newGame);

        // newGame.setLobbyId(lobbyId);
        gameRepository.save(newGame);
        return newGame;
    }

    public Template getTemplate(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));

        return game.getTemplate();
    }
}

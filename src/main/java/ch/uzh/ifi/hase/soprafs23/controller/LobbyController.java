
package ch.uzh.ifi.hase.soprafs23.controller;

import ch.uzh.ifi.hase.soprafs23.service.LobbyService;
import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby.LobbyMapper;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.GetDTO;
import ch.uzh.ifi.hase.soprafs23.repository.LobbyRepository;
import ch.uzh.ifi.hase.soprafs23.entity.User;
// import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PutDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

/**
 * Lobby Controller
 * This class is responsible for handling all REST request that are related to
 * the lobby.
 * The controller will receive the request and delegate the execution to the
 * LobbyService and finally return the result.
 */
@RestController
public class LobbyController {

    private final LobbyService lobbyService;
    private LobbyRepository lobbyRepository;

    public LobbyController(LobbyService lobbyService) {
        this.lobbyService = lobbyService;
    }

    @GetMapping("/lobbies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<GetDTO> getLobbies() {
        // get all lobbies
        List<Lobby> createdLobby = lobbyService.getLobbies();

        System.out.println(createdLobby);
        // convert internal representation of lobby back to API
        List<GetDTO> getDTOs = new ArrayList<GetDTO>();
        for (Lobby lobby : createdLobby) {
            getDTOs.add(LobbyMapper.INSTANCE.convertEntityToLobbyGetDTO(lobby));
        }

        return getDTOs;
    }

    @PostMapping("/lobbies")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GetDTO createLobby(@RequestBody PostDTO lobbyPostDTO) {
        // convert API user to internal representation
        Lobby lobbyInput = LobbyMapper.INSTANCE.convertLobbyPostDTOtoEntity(lobbyPostDTO);

        // create lobby
        Lobby createdLobby = lobbyService.createLobby(lobbyInput);

        // convert internal representation of lobby back to API
        return LobbyMapper.INSTANCE.convertEntityToLobbyGetDTO(createdLobby);
    }

    // @PutMapping("/lobby/{lobbyId}")
    // @ResponseStatus(HttpStatus.OK)
    // @ResponseBody
    // public void updateLobby(@PathVariable String lobbyId, @RequestBody PutDTO
    // lobbyPutDTO) {
    // // convert API user to internal representation
    // LobbySetting lobbySettingInput =
    // DTOMapper.INSTANCE.convertLobbyPutDTOtoEntity(lobbyPutDTO);

    // // update lobby
    // lobbyService.updateLobby(lobbyId, lobbySettingInput);
    // }

    @GetMapping("/lobbies/{lobbyId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetDTO getLobby(@PathVariable Long lobbyId) {
        Lobby getLobby = lobbyService.getLobbyById(lobbyId);
        return LobbyMapper.INSTANCE.convertEntityToLobbyGetDTO(getLobby);
    }

    // If the user is not in a lobby, they can join a lobby by entering the lobby
    // code
    // If the code provided is incorrect an error message gets displayed

    @PostMapping("/join/lobbies/{code}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Object> joinLobby(@PathVariable String code,
            @RequestBody User user) {
        // TODO: change user is not sent in body but in header (but only user token
        // sent)
        Lobby lobby = lobbyRepository.findByCode(code);

        if (lobby == null) {
            return ResponseEntity.notFound().build();
        }

        // Add the player to the lobby
        boolean success = lobbyService.joinLobby(code, user);

        if (!success) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }

}

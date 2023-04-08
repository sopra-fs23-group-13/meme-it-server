
package ch.uzh.ifi.hase.soprafs23.controller;

import ch.uzh.ifi.hase.soprafs23.service.LobbyService;
import ch.uzh.ifi.hase.soprafs23.entity.Lobby;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.lobby.PostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby.LobbyMapper;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.lobby.UserMapper;
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

    // If the user is not in a lobby, they can join a lobby by entering the lobby code
    // If the code provided is incorrect an error message gets displayed
    @PostMapping("/{code}/players")
    public ResponseEntity<Object> joinLobby(@PathVariable String code, @RequestBody User user) {
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


    @GetMapping("/lobby")
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

    @PostMapping("/lobby")
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

    @GetMapping("/lobby/{lobbyId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetDTO getLobby(@PathVariable Long lobbyId) {
        Lobby getLobby = lobbyService.getLobbyById(lobbyId);
        return LobbyMapper.INSTANCE.convertEntityToLobbyGetDTO(getLobby);
    }
    @PostMapping("/lobby/{lobbyId}/join")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public GetDTO joinLobby(@PathVariable("lobbyId") Long lobbyId, @RequestBody PostDTO userPostDTO) {
        // convert API user to internal representation
        User userInput = UserMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        // join lobby
        Lobby joinedLobby = lobbyService.joinLobby(lobbyId, userInput);

        // convert internal representation of lobby back to API
        return LobbyMapper.INSTANCE.convertEntityToLobbyGetDTO(joinedLobby);
    }



}

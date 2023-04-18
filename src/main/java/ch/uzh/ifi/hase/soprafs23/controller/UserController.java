package ch.uzh.ifi.hase.soprafs23.controller;

import ch.uzh.ifi.hase.soprafs23.service.UserService;
import ch.uzh.ifi.hase.soprafs23.entity.User;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.PostDTO;
import ch.uzh.ifi.hase.soprafs23.rest.mapper.user.UserMapper;
import ch.uzh.ifi.hase.soprafs23.rest.dto.user.GetDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public GetDTO createLobby(@RequestBody PostDTO userPostDTO) {
        // convert API user to internal representation
        User userInput = UserMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        // create lobby
        User createdUser = userService.createUser(userInput);

        // convert internal representation of lobby back to API
        return UserMapper.INSTANCE.convertEntityToUserGetDTO(createdUser);
    }

}

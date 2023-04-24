package ch.uzh.ifi.hase.soprafs23.rest.dto.player;

import ch.uzh.ifi.hase.soprafs23.rest.dto.user.UserGetDTO;

public class PlayerGetDTO {

    private String name;
    private String id;
    private UserGetDTO user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
        this.id = uuid;
    }

    public UserGetDTO getUser() {
        return user;
    }

    public void setUser(UserGetDTO user) {
        this.user = user;
    }
}

package ch.uzh.ifi.hase.soprafs23.rest.dto.lobby;

import ch.uzh.ifi.hase.soprafs23.entity.LobbySetting;
import ch.uzh.ifi.hase.soprafs23.entity.Messages;
import ch.uzh.ifi.hase.soprafs23.entity.Users;

public class GetDTO {
    private long id;
    private String code;

    private String name;

    private String owner;

    private LobbySetting lobbySetting;

    private Users players;

    private Messages messages;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public LobbySetting getLobbySetting() {
        return lobbySetting;
    }

    public void setLobbySetting(LobbySetting lobbySetting) {
        this.lobbySetting = lobbySetting;
    }

    public Users getPlayers() {
        return players;
    }

    public void setPlayers(Users players) {
        this.players = players;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

}

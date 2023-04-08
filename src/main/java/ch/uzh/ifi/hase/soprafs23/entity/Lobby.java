package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.stream.StreamSupport;

@Entity
@Table(name = "LOBBY")
public class Lobby implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Creates default lobby */
    // public Lobby(String name, String owner, LobbySetting lobbySetting) {
    // this.owner = owner;
    // this.lobbySetting = new LobbySetting();
    // this.players = new Users();
    // this.kickedPlayers = new Users();
    // this.messages = new Messages();
    // this.isJoinable = true;
    // }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private LobbySetting lobbySetting;

    @Column(nullable = false)
    private Users players;

    @Column(nullable = false)
    private Users kickedPlayers;

    @Column(nullable = false)
    private Messages messages;

    @Column(nullable = false)
    private boolean isJoinable;

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

    //for joining
    public void addPlayer(User player) {
        // TODO: check if player is already in lobby
        // TODO: check if player is kicked
        this.players.addUser(player);
    }

    public Users getKickedPlayers() {
        return kickedPlayers;
    }

    public void setKickedPlayers(Users kickedPlayers) {
        this.kickedPlayers = kickedPlayers;
    }

    public void addKickedPlayer(User kickedPlayer) {
        this.kickedPlayers.addUser(kickedPlayer);
    }

    public Messages getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        this.messages.addMessage(message);
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public boolean isJoinable() {
        return isJoinable;
    }

    public void setIsJoinable(boolean isJoinable) {
        this.isJoinable = isJoinable;
    }

    public boolean isFull() {


       Long totalUsers = StreamSupport.stream(this.players.getUsers().spliterator(), true).count();

     return this.lobbySetting.getMaxPlayers() == totalUsers.intValue();

    }


}

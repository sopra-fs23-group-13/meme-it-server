package ch.uzh.ifi.hase.soprafs23.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Lobby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "lobby_players",
            joinColumns = @JoinColumn(
                    name = "lobby_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            )
    )
    private Set<User> players = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "lobby_kicked_players",
            joinColumns = @JoinColumn(
                    name = "lobby_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            )
    )
    private Set<User> kickedPlayers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private LobbySetting settings;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "lobby_messages",
            joinColumns = @JoinColumn(
                    name = "lobby_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "message_id", referencedColumnName = "id"
            )
    )
    private List<Message> messages = new ArrayList<>();
    private boolean isJoinable;


    public Lobby() {
    }
    public Lobby(User user, LobbySetting settings) {
        this.user = user;
        this.settings = settings;
    }

    public void join(User user){
        this.players.add(user);
    }

    public void exit(User user){
        this.players.remove(user);
        this.setIsJoinable(true);
    }

    public Set<User> getKickedPlayers() {
        return kickedPlayers;
    }

    public void kick(User user){
        this.kickedPlayers.add(user);

        // Also removes from players
        this.exit(user);
    }

    public void closeLobby(){
       this.isJoinable = false;
    }

    public void sendMessage(Message message){
        this.messages.add(message);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LobbySetting getLobbySettings() {
        return settings;
    }

    public void setLobbySetting(LobbySetting settings) {
        this.settings = settings;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Set<User> getPlayers() {
        return players;
    }

    public boolean getIsJoinable() {
        return isJoinable;
    }

    public void setIsJoinable(boolean isJoinable) {
        this.isJoinable = isJoinable;
    }
    @JsonIgnore
    public boolean isFull(){
        return this.settings.getMaxPlayers() == this.players.size();
    }
    public void setCode(String code) {
    }
    public String getCode() {
        return null;
    }
}
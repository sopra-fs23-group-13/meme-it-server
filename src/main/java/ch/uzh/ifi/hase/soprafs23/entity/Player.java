package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.*;

@Entity
@Table(name = "PLAYER")
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @Column(nullable = false)
    private PlayerState state;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}

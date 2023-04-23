package ch.uzh.ifi.hase.soprafs23.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    // Message(String message, LocalDateTime created_at, User user) {
    // this.message = message;
    // this.createdAt = created_at;
    // this.user = user;
    // }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column
    private LocalDateTime createdAt;

    // ! no "@Column(nullable = false)" for foreign keys!
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
package ch.uzh.ifi.hase.soprafs23.entity;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    // Message(String message, LocalDateTime created_at, User user) {
    // this.message = message;
    // this.createdAt = created_at;
    // this.user = user;
    // }

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private User user;

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
}

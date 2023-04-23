package ch.uzh.ifi.hase.soprafs23.rest.dto.chat;

import java.time.LocalDateTime;

public class ChatGetDTO {
    private String author;
    private String message;
    private LocalDateTime time;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
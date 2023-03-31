package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Embeddable
public class Messages implements Serializable {
    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue
    // private Long id;

    // @OneToMany(mappedBy = "messages", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @Column(nullable = false)
    @ElementCollection
    private List<Message> messages;

    public Messages() {
        this.messages = Arrays.asList(new Message[] {});
    }

    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public Iterable<Message> getMessages() {
        return this.messages;
    }

}

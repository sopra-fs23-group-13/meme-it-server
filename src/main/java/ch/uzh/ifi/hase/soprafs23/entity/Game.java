package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @ElementCollection
    private List<Template> templates;

    @Column(nullable = false)
    @ElementCollection
    private List<Meme> submitedMemes;

    @Column(nullable = false)
    // @OneToOne
    private Long lobbyId;

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public Template getTemplate() {
        return templates.remove(0);
    }

    public void setSubmitedMemes(List<Meme> submitedMemes) {
        this.submitedMemes = submitedMemes;
    }

    public List<Meme> getSubmitedMemes() {
        return submitedMemes;
    }

    public void setLobbyId(Long lobbyId) {
        this.lobbyId = lobbyId;
    }

    public Long getLobbyId() {
        return lobbyId;
    }
}

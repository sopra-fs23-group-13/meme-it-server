package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

public class Meme implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Template template;

    @Column(nullable = false)
    @ElementCollection
    private List<TextBox> textBoxes;

    @Column(nullable = false)
    private User user;

    public Meme() {
    }

    public Meme(Template template, List<TextBox> textBoxes, User user) {
        this.template = template;
        this.textBoxes = textBoxes;
        this.user = user;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<TextBox> getTextBoxes() {
        return textBoxes;
    }

    public void setTextBoxes(List<TextBox> textBoxes) {
        this.textBoxes = textBoxes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

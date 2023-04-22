package ch.uzh.ifi.hase.soprafs23.rest.dto.meme;

import java.util.List;
import java.util.UUID;

import ch.uzh.ifi.hase.soprafs23.entity.TextBox;
import ch.uzh.ifi.hase.soprafs23.entity.User;

public class MemeGetDTO {

    private UUID id;

    private String imageUrl;

    private List<TextBox> textBoxes;

    // private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<TextBox> getTextBoxes() {
        return textBoxes;
    }

    public void setTextBoxes(List<TextBox> textBoxes) {
        this.textBoxes = textBoxes;
    }

    // public User getUser() {
    // return user;
    // }

    // public void setUser(User user) {
    // this.user = user;
    // }

}

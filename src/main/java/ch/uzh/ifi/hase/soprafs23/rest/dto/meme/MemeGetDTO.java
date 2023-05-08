package ch.uzh.ifi.hase.soprafs23.rest.dto.meme;

import java.awt.*;
import java.util.List;
import java.util.UUID;

import ch.uzh.ifi.hase.soprafs23.entity.TextBox;
import ch.uzh.ifi.hase.soprafs23.entity.User;

public class MemeGetDTO {

    private String id;

    private String imageUrl;

    private List<TextBox> textBoxes;

    private int fontSize;
    private String color;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String uuid) {
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

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

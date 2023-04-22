package ch.uzh.ifi.hase.soprafs23.rest.dto.meme;

import java.util.List;

import ch.uzh.ifi.hase.soprafs23.entity.TextBox;

public class MemePostDTO {

    private List<TextBox> textBoxes;

    public List<TextBox> getTextBoxes() {
        return textBoxes;
    }

    public void setTextBoxes(List<TextBox> textBoxes) {
        this.textBoxes = textBoxes;
    }

}

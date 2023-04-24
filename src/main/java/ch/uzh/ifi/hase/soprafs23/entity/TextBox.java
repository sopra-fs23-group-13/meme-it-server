package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.Embeddable;

@Embeddable
public class TextBox {
    public String text;
    public Integer x;
    public Integer y;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}

package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

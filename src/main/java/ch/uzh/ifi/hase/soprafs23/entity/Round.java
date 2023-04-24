package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "ROUND")
public class Round implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Meme> memes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @Column(nullable = false)
    private Integer roundNumber;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDateTime startedAt;

    public Long getId() {
        return id;
    }

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Rating rating) {
        if (ratings == null) {
            ratings = new ArrayList<Rating>();
        }
        ratings.add(rating);
    }

    public void setSubmitedMemes(List<Meme> memes) {
        this.memes = memes;
    }

    public void addMeme(Meme meme) {
        if (memes == null) {
            memes = new ArrayList<Meme>();
        }
        memes.add(meme);
    }

    public Meme getMemeById(UUID id) {
        for (Meme meme : memes) {
            if (meme.getId().equals(id)) {
                return meme;
            }
        }
        return null;
    }

    public List<Meme> getSubmitedMemes() {
        return memes;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(Integer roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

}

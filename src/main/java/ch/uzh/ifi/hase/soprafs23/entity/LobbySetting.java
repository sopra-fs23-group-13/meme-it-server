package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class LobbySetting implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean isPublic;
    private Integer maxPlayers;
    private Integer maxRounds;
    private Integer memeChangeLimit;
    private Integer superLikeLimit;
    private Integer superDislikeLimit;
    private Integer roundDuration;
    private Integer ratingDuration;

    /** Default lobby settings */
    public LobbySetting() {
        this.isPublic = false;
        this.maxPlayers = 10;
        this.maxRounds = 5;
        this.memeChangeLimit = 3;
        this.superLikeLimit = 3;
        this.superDislikeLimit = 3;
        this.roundDuration = 120;
        this.ratingDuration = 120;
    }

    /** Custom lobby settings */
    public LobbySetting(boolean isPublic, Integer maxPlayers, Integer maxRounds, Integer memeChangeLimit,
            Integer superLikeLimit, Integer superDislikeLimit, Integer timeRoundLimit, Integer timeVoteLimit) {
        this.isPublic = isPublic;
        this.maxPlayers = maxPlayers;
        this.maxRounds = maxRounds;
        this.memeChangeLimit = memeChangeLimit;
        this.superLikeLimit = superLikeLimit;
        this.superDislikeLimit = superDislikeLimit;
        this.roundDuration = timeRoundLimit;
        this.ratingDuration = timeVoteLimit;

    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(Integer maxRounds) {
        this.maxRounds = maxRounds;
    }

    public Integer getMemeChangeLimit() {
        return memeChangeLimit;
    }

    public void setMemeChangeLimit(Integer memeChangeLimit) {
        this.memeChangeLimit = memeChangeLimit;
    }

    public Integer getSuperLikeLimit() {
        return superLikeLimit;
    }

    public void setSuperLikeLimit(Integer superLikeLimit) {
        this.superLikeLimit = superLikeLimit;
    }

    public Integer getSuperDislikeLimit() {
        return superDislikeLimit;
    }

    public void setSuperDislikeLimit(Integer superDislikeLimit) {
        this.superDislikeLimit = superDislikeLimit;
    }

    public Integer getRoundDuration() {
        return roundDuration;
    }

    public void setRoundDuration(Integer timeRoundLimit) {
        this.roundDuration = timeRoundLimit;
    }

    public Integer getRatingDuration() {
        return ratingDuration;
    }

    public void setRatingDuration(Integer timeVoteLimit) {
        this.ratingDuration = timeVoteLimit;
    }

}

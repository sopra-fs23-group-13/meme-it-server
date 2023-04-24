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

    public boolean setIsPublic(boolean isPublic) {
        return this.isPublic = isPublic;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Integer setMaxPlayers(Integer maxPlayers) {
        return this.maxPlayers = maxPlayers;
    }

    public Integer getMaxRounds() {
        return maxRounds;
    }

    public Integer setMaxRounds(Integer maxRounds) {
        return this.maxRounds = maxRounds;
    }

    public Integer getMemeChangeLimit() {
        return memeChangeLimit;
    }

    public Integer setMemeChangeLimit(Integer memeChangeLimit) {
        return this.memeChangeLimit = memeChangeLimit;
    }

    public Integer getSuperLikeLimit() {
        return superLikeLimit;
    }

    public Integer setSuperLikeLimit(Integer superLikeLimit) {
        return this.superLikeLimit = superLikeLimit;
    }

    public Integer getSuperDislikeLimit() {
        return superDislikeLimit;
    }

    public Integer setSuperDislikeLimit(Integer superDislikeLimit) {
        return this.superDislikeLimit = superDislikeLimit;
    }

    public Integer getRoundDuration() {
        return roundDuration;
    }

    public Integer setRoundDuration(Integer timeRoundLimit) {
        return this.roundDuration = timeRoundLimit;
    }

    public Integer getRatingDuration() {
        return ratingDuration;
    }

    public Integer setRatingDuration(Integer timeVoteLimit) {
        return this.ratingDuration = timeVoteLimit;
    }

}

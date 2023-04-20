package ch.uzh.ifi.hase.soprafs23.entity;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class LobbySetting implements Serializable {
    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue
    // private Long id;

    private boolean isPublic;
    private Integer maxPlayers;
    private Integer maxRounds;
    private Integer memeChangeLimit;
    private Integer superLikeLimit;
    private Integer superDislikeLimit;
    private Integer timeRoundLimit;
    private Integer timeVoteLimit;

    /** Default lobby settings */
    public LobbySetting() {
        this.isPublic = false;
        this.maxPlayers = 10;
        this.maxRounds = 5;
        this.memeChangeLimit = 3;
        this.superLikeLimit = 3;
        this.superDislikeLimit = 3;
        this.timeRoundLimit = 120;
        this.timeVoteLimit = 120;
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
        this.timeRoundLimit = timeRoundLimit;
        this.timeVoteLimit = timeVoteLimit;

    }

    // public Long getId() {
    // return id;
    // }

    // public void setId(Long id) {
    // this.id = id;
    // }

    public boolean getIsPublic() {
        return isPublic;
    }

    public boolean setIsPublic(boolean isPublic) {
        return this.isPublic = isPublic;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Integer setMaxPlayers(int maxPlayers) {
        return this.maxPlayers = maxPlayers;
    }

    public Integer getMaxRounds() {
        return maxRounds;
    }

    public Integer setMaxRounds(int maxRounds) {
        return this.maxRounds = maxRounds;
    }

    public Integer getMemeChangeLimit() {
        return memeChangeLimit;
    }

    public Integer setMemeChangeLimit(int memeChangeLimit) {
        return this.memeChangeLimit = memeChangeLimit;
    }

    public Integer getSuperLikeLimit() {
        return superLikeLimit;
    }

    public Integer setSuperLikeLimit(int superLikeLimit) {
        return this.superLikeLimit = superLikeLimit;
    }

    public Integer getSuperDislikeLimit() {
        return superDislikeLimit;
    }

    public Integer setSuperDislikeLimit(int superDislikeLimit) {
        return this.superDislikeLimit = superDislikeLimit;
    }

    public Integer getTimeRoundLimit() {
        return timeRoundLimit;
    }

    public Integer setTimeRoundLimit(int timeRoundLimit) {
        return this.timeRoundLimit = timeRoundLimit;
    }

    public Integer getTimeVoteLimit() {
        return timeVoteLimit;
    }

    public Integer setTimeVoteLimit(int timeVoteLimit) {
        return this.timeVoteLimit = timeVoteLimit;
    }

}

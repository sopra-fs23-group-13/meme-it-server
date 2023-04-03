package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class LobbySetting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isPublic;
    private int maxPlayers;
    private int maxRounds;
    private int memeChangeLimit;
    private int superLikeLimit;
    private int superDislikeLimit;
    private int timeRoundLimit;
    private int timeVoteLimit;

    @OneToOne(mappedBy = "settings")
    private Lobby lobby;

    public boolean isPublic() {
        return isPublic;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getRounds() {
        return maxRounds;
    }

    public int getMemeChangeLimit() {
        return memeChangeLimit;
    }

    public int getSuperLikeLimit() {
        return superLikeLimit;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setMaxRounds(int maxRounds) {
        this.maxRounds = maxRounds;
    }

    public void setMemeChangeLimit(int memeChangeLimit) {
        this.memeChangeLimit = memeChangeLimit;
    }

    public void setSuperLikeLimit(int superLikeLimit) {
        this.superLikeLimit = superLikeLimit;
    }

    public void setSuperDislikeLimit(int superDislikeLimit) {
        this.superDislikeLimit = superDislikeLimit;
    }

    public void setTimeRoundLimit(int timeRoundLimit) {
        this.timeRoundLimit = timeRoundLimit;
    }

    public void setTimeVoteLimit(int timeVoteLimit) {
        this.timeVoteLimit = timeVoteLimit;
    }

    public int getTimeRoundLimit() {
        return timeRoundLimit;
    }

    public int getTimeVoteLimit() {
        return timeVoteLimit;
    }
}
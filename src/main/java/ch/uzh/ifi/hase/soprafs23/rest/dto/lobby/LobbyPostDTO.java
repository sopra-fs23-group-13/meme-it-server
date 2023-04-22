package ch.uzh.ifi.hase.soprafs23.rest.dto.lobby;

public class LobbyPostDTO {

    private String name;
    private boolean isPublic;
    private Integer maxPlayers;
    private Integer maxRounds;
    private Integer memeChangeLimit;
    private Integer superLikeLimit;
    private Integer superDislikeLimit;
    private Integer roundDuration;
    private Integer ratingDuration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

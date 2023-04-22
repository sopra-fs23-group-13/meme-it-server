package ch.uzh.ifi.hase.soprafs23.entity;

import javax.persistence.Embeddable;

@Embeddable
public class GameSetting {

    private Integer maxRounds;
    private Integer templateSwapLimit;
    // private Integer superLikeLimit;
    // private Integer superDislikeLimit;
    private Integer roundDuration;
    private Integer ratingDuration;

    public Integer getMaxRounds() {
        return maxRounds;
    }

    public void setMaxRounds(Integer maxRounds) {
        this.maxRounds = maxRounds;
    }

    public Integer getTemplateSwapLimit() {
        return templateSwapLimit;
    }

    public void setTemplateSwapLimit(Integer memeChangeLimit) {
        this.templateSwapLimit = memeChangeLimit;
    }

    // public Integer getSuperLikeLimit() {
    // return superLikeLimit;
    // }

    // public void setSuperLikeLimit(Integer superLikeLimit) {
    // this.superLikeLimit = superLikeLimit;
    // }

    // public Integer getSuperDislikeLimit() {
    // return superDislikeLimit;
    // }

    // public void setSuperDislikeLimit(Integer superDislikeLimit) {
    // this.superDislikeLimit = superDislikeLimit;
    // }

    public Integer getRoundDuration() {
        return roundDuration;
    }

    public void setRoundDuration(Integer roundDuration) {
        this.roundDuration = roundDuration;
    }

    public Integer getRatingDuration() {
        return ratingDuration;
    }

    public void setRatingDuration(Integer ratingDuration) {
        this.ratingDuration = ratingDuration;
    }
}

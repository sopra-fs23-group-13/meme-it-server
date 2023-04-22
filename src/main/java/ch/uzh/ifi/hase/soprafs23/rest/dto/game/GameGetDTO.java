package ch.uzh.ifi.hase.soprafs23.rest.dto.game;

import java.time.LocalDateTime;

import ch.uzh.ifi.hase.soprafs23.entity.GameState;

public class GameGetDTO {

    private String id;
    private GameState gameState;

    private Integer roundDuration;
    private Integer votingDuration;

    private Integer currentRound;

    private Integer totalRounds;

    private LocalDateTime startedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Integer getRoundDuration() {
        return roundDuration;
    }

    public void setRoundDuration(Integer roundDuration) {
        this.roundDuration = roundDuration;
    }

    public Integer getVotingDuration() {
        return votingDuration;
    }

    public void setVotingDuration(Integer votingDuration) {
        this.votingDuration = votingDuration;
    }

    public Integer getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(Integer currentRound) {
        this.currentRound = currentRound;
    }

    public Integer getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(Integer totalRounds) {
        this.totalRounds = totalRounds;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

}

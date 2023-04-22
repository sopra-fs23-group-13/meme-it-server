package ch.uzh.ifi.hase.soprafs23.rest.dto.game;

import java.util.Date;

public class GameGetDTO {
    private Long id;
    private int memeChangeLimit;
    private int timeRoundLimit;
    private Date startTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMemeChangeLimit() {
        return memeChangeLimit;
    }

    public void setMemeChangeLimit(int memeChangeLimit) {
        this.memeChangeLimit = memeChangeLimit;
    }

    public int getTimeRoundLimit() {
        return timeRoundLimit;
    }

    public void setTimeRoundLimit(int timeRoundLimit) {
        this.timeRoundLimit = timeRoundLimit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}

package ua.com.foxminded.racer;

import java.time.Duration;
import java.time.LocalDateTime;

public class Racer {
    private String id;
    private String name;
    private String team;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration lapTime;
    
    public Racer(String id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    public void setLapTime(Duration lapTime) {
        this.lapTime = lapTime;
    }

    @Override
    public String toString() {
        return "Racer [id=" + id + ", name=" + name + ", team=" + team
                + ", startTime=" + startTime + ", endTime=" + endTime
                + ", lapTime=" + lapTime + "]";
    }
    
}

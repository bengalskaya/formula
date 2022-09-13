package ua.foxminded.formula;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RacerData {

    private String fullName;
    private String team;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;


    public RacerData(String fullName, String team, LocalDateTime startTime, LocalDateTime finishTime) {
        super();
        this.fullName = fullName;
        this.team = team;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Long calculateLapTime() {
        return ChronoUnit.MILLIS.between(startTime, finishTime);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        RacerData rd = (RacerData)obj;
        
        boolean equalFullName = getFullName() != null && getFullName().equals(rd.getFullName());
        boolean equalTeam = getTeam() != null && getTeam().equals(rd.getTeam()); 
        
        return equalFullName && equalTeam;
    }
}

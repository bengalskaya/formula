package ua.foxminded.formula;

import java.time.LocalDateTime;
import java.util.*;

public class RacerDataValidator {

    
    public List<RacerData> createOnlyValidRacers(Map<String, LocalDateTime> racersStartTime,
            Map<String, LocalDateTime> racersEndTime, Map<String, String> racersDetails) {
        List<RacerData> validRacers = new ArrayList<>();
        for (String racerCode : racersEndTime.keySet()) {
            LocalDateTime startTime = racersStartTime.get(racerCode);
            LocalDateTime finishTime = racersEndTime.get(racerCode);
            String detailInfo = racersDetails.get(racerCode);
            if (isValid(detailInfo, startTime, finishTime)) {
                String[] details = detailInfo.split("_");
                validRacers.add(new RacerData(details[0], details[1], startTime, finishTime));
            } else {
                System.out.println("Racer with code " + racerCode + " has incorrect data.");
            }    
        }
        return validRacers;
    }
    
    private boolean isValid(String racerDetails, LocalDateTime startTime, LocalDateTime finishTime) {
        return isDetailValid(racerDetails) && isDateValid(startTime) && isDateValid(finishTime);
    }
    
    private boolean isDetailValid(String racerDetails) {
        return racerDetails != null && racerDetails.contains("_") && (racerDetails.split("_").length > 1);
    }
    
    private boolean isDateValid(LocalDateTime time) {
        return time != null;
    }
}

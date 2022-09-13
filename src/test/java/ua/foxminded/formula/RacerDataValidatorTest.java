package ua.foxminded.formula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class RacerDataValidatorTest {
    @Test
    void testCreateOnlyValidRacers_SingleValidAdded_RacersPartlyValid() {
        Map<String, LocalDateTime> racersEndTime = new HashMap<>();
        LocalDate date = LocalDate.of(2022, Month.MARCH, 11);
        LocalTime startTime = LocalTime.of(5, 15, 10, 100);
        LocalTime finishTime = LocalTime.of(5, 15, 10, 120);
        LocalDateTime startDateTime = LocalDateTime.of(date, startTime);
        LocalDateTime finishDateTime = LocalDateTime.of(date, finishTime);
        
        Map<String, LocalDateTime> racersStartTime = new HashMap<>();
        racersStartTime.put("VLD", startDateTime);
        racersStartTime.put("VST", LocalDateTime.now());
        racersStartTime.put("XXX", startDateTime);
        
        racersEndTime.put("VLD", finishDateTime);
        racersEndTime.put("XXX", finishDateTime);
        racersEndTime.put("VET", LocalDateTime.now());
        
        Map<String, String> racersDetails = new HashMap<>();
        racersDetails.put("VLD", "Olga Chernenko_RED BULL RACING TAG HEUER");
        racersDetails.put("VET", "Kimi Raikkonen_FERRARI");
        racersDetails.put("XXX", "Kimi Raikkonen_");
        
        List<RacerData> expectedRacers = new ArrayList<>();
        expectedRacers.add(new RacerData("Olga Chernenko", "RED BULL RACING TAG HEUER", startDateTime, finishDateTime));

        List<RacerData> actualRacers = new RacerDataValidator().createOnlyValidRacers(racersStartTime, racersEndTime,
                racersDetails);
        assertEquals(expectedRacers, actualRacers);
    }
    
    @Test
    void testCreateOnlyValidRacers_NoneDataAdded_AllParsedDataEmpty() {
        List<RacerData> expectedRacers = new ArrayList<>();

        List<RacerData> actualRacers = new RacerDataValidator().createOnlyValidRacers(new HashMap<>(), new HashMap<>(),
                new HashMap<>());
        assertEquals(expectedRacers, actualRacers);
    } 
    
}

package ua.foxminded.formula;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.*;
import java.util.*;

import org.junit.jupiter.api.Test;

public class RacerDataFormatterTest {
    
    @Test
    void testMaxLength_Success_FindMaxLengthOfBoth() {
        List<RacerData> racers = new ArrayList<>();

        RacerData racerData1 = new RacerData("aa", "team", LocalDateTime.now(), LocalDateTime.now());
        racers.add(racerData1);
        RacerData racerData2 = new RacerData("aaa", "team", LocalDateTime.now(), LocalDateTime.now());
       
        racers.add(racerData2);
        Optional<Integer> maxFullNameLenght = racers.stream().map(r -> r.getFullName().length()).max((fn1, fn2) -> fn1-fn2);
        assertEquals(Integer.valueOf(3), maxFullNameLenght.get());
    }

    @Test
    void testFormatRacersData_ShowDaysAndHours_ValidAndInvalidDetails() {
        
        List<String> expectedFormattedRacers = new LinkedList<>();
        expectedFormattedRacers.add("1. Minutes Available|Foxminded |1:20.0");
        expectedFormattedRacers.add("2. Hours Available  |Foxminded |1:0:0.0");
        expectedFormattedRacers.add("3. Days Available   |Foxminded |1 day(s) 1:0:0.0");
        
        LocalDate date = LocalDate.of(2022, Month.MARCH, 11);
        LocalTime time = LocalTime.of(5, 15, 10, 100);
        LocalDate datePlusDay = LocalDate.of(2022, Month.MARCH, 12);
        LocalTime timePlusHour = LocalTime.of(6, 15, 10, 120);
        LocalTime timePlusMinutes = LocalTime.of(5, 16, 30, 100);

       
        List<RacerData> racers = new ArrayList<>();
        racers.add(new RacerData("Days Available", "Foxminded", LocalDateTime.of(date, time), LocalDateTime.of(datePlusDay, timePlusHour)));
        racers.add(new RacerData("Hours Available", "Foxminded", LocalDateTime.of(date, time), LocalDateTime.of(date, timePlusHour)));
        racers.add(new RacerData("Minutes Available", "Foxminded", LocalDateTime.of(date, time), LocalDateTime.of(date, timePlusMinutes)));
       
        List<String> actualFormattedRacers = new RacerDataFormatter().formatRacersData(racers);
        
        
        assertEquals(expectedFormattedRacers, actualFormattedRacers);
    }

}

package ua.foxminded.formula;

import java.time.LocalDateTime;
import java.util.*;


public class Application {

    public static void main(String[] args) {
        RacerDataParser parser = new RacerDataParser();
        Map<String, LocalDateTime> racersStartTime = parser.parseRacerTime("start.log");
        Map<String, LocalDateTime> racersEndTime = parser.parseRacerTime("end.log");
        Map<String, String> racersDetails = parser.parseRacersDetails("abbreviations.txt");

        List<RacerData> racers = new RacerDataValidator().createOnlyValidRacers(racersStartTime, racersEndTime, racersDetails);
        new RacerDataFormatter().formatRacersData(racers).forEach(System.out::println);
    }
}

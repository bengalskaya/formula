package ua.foxminded.formula;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class RacerDataParser {

    static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static <T> BiConsumer<T, String> lambdaTimeParserWrapper(BiConsumer<T, String> consumer) {
        return (map, raserData) -> {
            try {
                consumer.accept(map, raserData);
            } catch (Exception e) {
                System.err.println(
                  "Row will be skipped. Exception occured : " + e.getClass() + " " + e.getMessage());
            }
        };
    }
   
    public Map<String, LocalDateTime> parseRacerTime(String fileName) {
        Map<String, LocalDateTime> racersStartTime = new HashMap<>();
        racersStartTime.putAll(racersStartTime);
     
        try (Stream<String> racersData = Files.lines(getFilePath(fileName))) {
            
            racersStartTime = racersData.collect(HashMap::new,
                    lambdaTimeParserWrapper((map, racerData) -> map.put(racerData.substring(0, 3),
                            LocalDateTime.parse(racerData.substring(3), DATE_TIME_FORMAT))),
                    HashMap::putAll);
                    
        } catch (IOException e) {
            System.out.println("Failed to load racers' data.");
        }
        return racersStartTime;
    }
    
    protected Map<String, String> parseRacersDetails(String fileName) {
        Map<String, String> racerAbbreviations = new HashMap<>();
        
        try (Stream<String> racerAbbreviationData = Files.lines(getFilePath(fileName))) {
            racerAbbreviations = racerAbbreviationData.collect(HashMap::new,
                    lambdaTimeParserWrapper((map, abbreviationData) -> map.put(abbreviationData.substring(0, 3), abbreviationData.substring(4))),
                    HashMap::putAll);
        } catch (IOException e) {
            System.out.println("Failed to load racers' abbreviations.");
        }
           
        return racerAbbreviations;
    }

    protected Path getFilePath(String fileName) {
        ClassLoader loader = RacerDataParser.class.getClassLoader();
        File file = new File(loader.getResource(fileName).getFile());
        return file.toPath();
    }
}

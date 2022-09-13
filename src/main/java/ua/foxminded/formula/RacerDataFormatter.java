package ua.foxminded.formula;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class RacerDataFormatter {

    public static final String SPACE = " ";
    public static final int TOP_RESULT_AMOUNT = 15;
    public static final String COLON = ":";
    public static final String DOT = ".";
    public static final String VERTICAL_LINE = "|";
    public static final String DAYS = "day(s)";

    /*
     * TODO: Create separate LINE
     */
    public List<String> formatRacersData(List<RacerData> racerData) {
        Optional<Integer> maxFullNameLenght = getMaxLength(racerData, RacerData::getFullName);
        Optional<Integer> maxTeamLenght = getMaxLength(racerData, RacerData::getTeam);
        int racersAmountLength = String.valueOf(racerData.size()).length();

        List<RacerData> oderedRacersData = descendingOrderRacersByLapTime(racerData);
        int i = 1;
        List<String> formattedResult = new LinkedList<>();
        for (RacerData oderedRacerData : oderedRacersData) {
            formattedResult.add(
                    formatLine(oderedRacerData, i++, maxFullNameLenght, racersAmountLength, maxTeamLenght));
        }
        return formattedResult;
    }

    protected List<RacerData> descendingOrderRacersByLapTime(List<RacerData> racers) {
        racers.sort((r1, r2) -> r1.calculateLapTime().compareTo(r2.calculateLapTime()));
        return racers;
    }

    private Optional<Integer> getMaxLength(List<RacerData> racersData, Function<RacerData, String> function) {
        return racersData.stream().map(function).map(String::length).max((fn1, fn2) -> fn1 - fn2);
    }

    private String formatLine(RacerData racerData, int number, Optional<Integer> maxFullNameLenght, int racersAmountLength,
            Optional<Integer> maxTeamLenght) {
        StringBuilder formattedLine = new StringBuilder();
        formattedLine.append(number).append(DOT).append(SPACE)
                .append(formatFullName(maxFullNameLenght, racerData.getFullName(), racersAmountLength, number))
                .append(formatTeam(maxTeamLenght, racerData.getTeam()))
                .append(formatDurationPart(Duration.ofMillis(racerData.calculateLapTime())));

        return formattedLine.toString();
    }

    private String formatTeam(Optional<Integer> maxTeamLenght, String team) {
        StringBuilder formattedTeam = new StringBuilder();
        formattedTeam.append(team).append(rightPadSpaces(maxTeamLenght.get() - team.length()))
                    .append(SPACE).append(VERTICAL_LINE);
        return formattedTeam.toString();
    }
    
    private String formatFullName(Optional<Integer> maxFullNameLenght, String fullName, int racersAmountLength, int number) {
        StringBuilder formattedFullName = new StringBuilder();
        formattedFullName.append(fullName)
                            .append(rightPadSpaces(maxFullNameLenght.get() - fullName.length()))
                            .append(rightPadSpaces(racersAmountLength - String.valueOf(number).length()))
                            .append(VERTICAL_LINE);
        return formattedFullName.toString();
    }
    
    private String formatDurationPart(Duration duration) {
        StringBuilder formattedDuration = new StringBuilder();
        long days = duration.toDaysPart();
        int hours = duration.toHoursPart();

        if (days > 0) {
            formattedDuration.append(days).append(SPACE).append(DAYS).append(SPACE);
        }
        if (hours > 0) {
            formattedDuration.append(hours).append(COLON);
        }
        formattedDuration.append(duration.toMinutesPart()).append(COLON)
                            .append(duration.toSecondsPart()).append(DOT)
                            .append(duration.toMillisPart());
        return formattedDuration.toString();
    }
    
    private StringBuilder rightPadSpaces(int spaceAmount) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < spaceAmount; i++) {
            spaces.append(SPACE);
        }
        return spaces;
    }
}

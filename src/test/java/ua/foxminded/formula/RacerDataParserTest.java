package ua.foxminded.formula;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.*;

public class RacerDataParserTest {
    
    RacerDataParser parser = null;
    @BeforeEach
    void init() {
        parser = new RacerDataParser();
    }

    @Test
    void testParseRacerTime_RacerDataNotAdded_RacerDataLessThenCode() {
        Map<String, LocalDateTime> racersStartTime = parser.parseRacerTime("test/start_RacerDataValidAndInvalid.log");
        assertEquals(1, racersStartTime.size());
        assertEquals(LocalDateTime.parse("2018-05-24_12:02:49.914", RacerDataParser.DATE_TIME_FORMAT), racersStartTime.get("NHR"));
    }

    @Test
    void testParseRacerTime_AddOnlyValidDetails_ValidAndInvalidDetails() {
        Map<String, String> racersDetails = parser.parseRacersDetails("test/abbreviations_DetailsIncorrectFormat.txt");
        assertEquals(2, racersDetails.size());
        assertEquals("Lewis Hamilton_MERCEDES", racersDetails.get("LHM"));
        assertEquals("", racersDetails.get("DRR"));
    }

    
//TODO: check empty file
  
    @Test
    void testParseRacerTime_RacerDataNotAdded_RacerTimeEmpty() {
        
    }

    @Test
    void testParseRacerTime_RacerDetailsNotAdded_RacerTimeEmpty() {
        
    }

}

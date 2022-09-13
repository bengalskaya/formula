package ua.foxminded.mockito.formula;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

import ua.foxminded.formula.RacerDataFormatter;

public class RacerDataFormatterMoks {

    private RacerDataFormatter formatter;
    
    @BeforeEach
    private void setup() {
        formatter = mock(RacerDataFormatter.class);
    }
    
    
}

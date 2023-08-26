package com.example.sahibinden.common;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class RandomizerTest {

    private final Randomizer randomizer=new Randomizer();
    @Test
    public void testGenerateWithDefaultLength() {
        String result = randomizer.generate();

        assertNotNull(result);
        assertEquals(Randomizer.DEFAULT_LENGTH, result.length());
    }

    @Test
    public void testGenerateWithCustomLength() {
        int customLength = 10;
        String result = randomizer.generate(customLength);

        assertNotNull(result);
        assertEquals(customLength, result.length());
    }

    @Test
    public void testGenerateWithNullLength() {

        String result = randomizer.generate(null);

        assertNotNull(result);
        assertEquals(Randomizer.DEFAULT_LENGTH, result.length());
    }

    @Test
    public void testGenerateWithZeroLength() {
        int zeroLength = 0;
        String result = randomizer.generate(zeroLength);

        assertNotNull(result);
        assertEquals(Randomizer.DEFAULT_LENGTH, result.length());
    }
}

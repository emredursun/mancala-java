package mancala.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlTest {
    private Bowl bowl;

    @BeforeEach
    public void setUp() {
        bowl = new Bowl();
    }

    @Test
    public void testGetStones() {
        Assertions.assertEquals(4, bowl.getStones());
    }

}

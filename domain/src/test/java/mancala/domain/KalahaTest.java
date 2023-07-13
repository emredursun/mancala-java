package mancala.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KalahaTest {
    private Kalaha kalaha;

    @BeforeEach
    public void setUp() {
        kalaha = new Kalaha();
    }

    @Test
    public void testGetStones() {
        Assertions.assertEquals(0, kalaha.getStones());
    }

}

package org.JavaCar;

import org.junit.Test;
import static org.junit.Assert.*;

public class MotorTest {

    @Test
    public void testCreacioMotor() {
        Motor motor = new Motor("Gasolina", 120);

        assertEquals("Gasolina", motor.getTipus());
        assertEquals(120, motor.getPotencia());
    }

    @Test
    public void testMotorAmbPotenciaZero() {
        Motor motor = new Motor("Gasolina", 0);

        assertEquals("Gasolina", motor.getTipus());
        assertEquals(0, motor.getPotencia());
    }
}

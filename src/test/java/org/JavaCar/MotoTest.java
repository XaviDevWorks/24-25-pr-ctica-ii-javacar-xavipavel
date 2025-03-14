package org.JavaCar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class MotoTest {

    @Test
    public void testCreacioMoto() {
        Moto moto = new Moto("5678DEF", "Yamaha", "R3", 25, 300, null, null);
        
        assertEquals("5678DEF", moto.getMatricula());
        assertEquals("Yamaha", moto.getMarca());
        assertEquals("R3", moto.getModel());
        assertEquals(25, moto.getPreuBase(), 0.01);
        assertEquals(300, moto.getCilindrada());
    }

    @Test
    public void testCalculPreuMotoNormal() {
        Moto moto = new Moto("1234XYZ", "Honda", "CB500", 20, 400, null, null);
        assertEquals(60, moto.calcularPreu(3), 0.01); // 20 * 3 = 60
    }

    @Test
    public void testCalculPreuMotoGran() {
        Moto moto = new Moto("7890LMN", "Ducati", "Panigale", 30, 600, null, null);
        assertEquals(105, moto.calcularPreu(3), 0.01); // 30 * 3 + 5 * 3 = 105
    }

    @Test
    public void testAtributPrivat() throws NoSuchFieldException {
        // Reflexió per accedir als atributs i verificar que són privats
        Field cilindradaField = Moto.class.getDeclaredField("cilindrada");


        assertTrue("L'atribut 'cilindrada' hauria de ser privat", 
                   java.lang.reflect.Modifier.isPrivate(cilindradaField.getModifiers()));
    }
}

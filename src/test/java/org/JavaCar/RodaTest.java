package org.JavaCar;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;


public class RodaTest {

    @Test
    public void testCreacioRoda() {
        Roda roda = new Roda("Michelin", 17);

        assertEquals("Michelin", roda.getMarca());
        assertEquals(17, roda.getDiametre(), 0.01);
    }

    @Test
    public void testAtributsPrivats() throws NoSuchFieldException {
        // Reflexió per accedir als atributs i verificar que són privats
        Field marcaField = Roda.class.getDeclaredField("marca");
        Field diametreField = Roda.class.getDeclaredField("diametre");

        assertTrue("L'atribut 'marca' hauria de ser privat", 
                   java.lang.reflect.Modifier.isPrivate(marcaField.getModifiers()));
        assertTrue("L'atribut 'diametre' hauria de ser privat", 
                   java.lang.reflect.Modifier.isPrivate(diametreField.getModifiers()));
    }
}

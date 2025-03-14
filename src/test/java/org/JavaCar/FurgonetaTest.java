package org.JavaCar;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class FurgonetaTest {

    @Test
    public void testCreacioFurgoneta() {
        Furgoneta furgoneta = new Furgoneta("1122JKL", "Ford", "Transit", 40, 800, null, null);
        
        assertEquals("1122JKL", furgoneta.getMatricula());
        assertEquals("Ford", furgoneta.getMarca());
        assertEquals("Transit", furgoneta.getModel());
        assertEquals(40, furgoneta.getPreuBase(), 0.01);
        assertEquals(800, furgoneta.getCapacitatCarga(), 0.01);
    }

    @Test
    public void testCalculPreuFurgonetaPetita() {
        Furgoneta furgoneta = new Furgoneta("3344MNO", "Mercedes", "Vito", 50, 900, null, null);
        assertEquals(150, furgoneta.calcularPreu(3), 0.01); // 50 * 3 = 150
    }

    @Test
    public void testCalculPreuFurgonetaGran() {
        Furgoneta furgoneta = new Furgoneta("5566PQR", "Iveco", "Daily", 55, 1200, null, null);
        assertEquals(195, furgoneta.calcularPreu(3), 0.01); // 55 * 3 + 10 * 3 = 195
    }

    @Test
    public void testAtributPrivat() throws NoSuchFieldException {
        // Reflexió per accedir als atributs i verificar que són privats
        Field capacitatCargaField = Moto.class.getDeclaredField("cilindrada");


        assertTrue("L'atribut 'capacitatCarga' hauria de ser privat", 
                   java.lang.reflect.Modifier.isPrivate(capacitatCargaField.getModifiers()));
    }
}

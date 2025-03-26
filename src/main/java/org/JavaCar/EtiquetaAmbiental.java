package org.JavaCar;

public class EtiquetaAmbiental {
    public enum TipoEtiqueta {
        SIN_ETIQUETA,
        B,
        C,
        ECO,
        CERO
    }

    public static TipoEtiqueta obtenerEtiqueta(Vehicle vehiculo) {
        Motor motor = vehiculo.getMotor();
        tipusVehicle tipoCombustible = tipusVehicle.valueOf(motor.getTipus().toUpperCase());
        
        // Si el vehículo es eléctrico o híbrido enchufable (más de 40 km de autonomía en modo eléctrico)
        if (vehiculo.esElectrico() || motor.esHibridoEnchufable40km()) {
            return TipoEtiqueta.CERO;
        } 
        // Si es híbrido o usa gas
        else if (motor.esHibrido() || motor.usaGas()) {
            return TipoEtiqueta.ECO;
        } 
        // Para gasolina o diésel dependiendo de las normativas Euro
        else if ((tipoCombustible == tipusVehicle.GASOLINA || tipoCombustible == tipusVehicle.HIBRID_GASOLINA) 
                && vehiculo.getAñoMatriculacion() >= 2006 && motor.cumpleNormativaEuro(4)) {
            return TipoEtiqueta.C;
        } 
        else if ((tipoCombustible == tipusVehicle.DIESEL || tipoCombustible == tipusVehicle.HIBRID_DIESEL) 
                && vehiculo.getAñoMatriculacion() >= 2014 && motor.cumpleNormativaEuro(6)) {
            return TipoEtiqueta.C;
        } 
        // Normativa Euro 3 para gasolina y Euro 4 para diésel
        else if ((tipoCombustible == tipusVehicle.GASOLINA && vehiculo.getAñoMatriculacion() >= 2000 && motor.cumpleNormativaEuro(3)) ||
                   (tipoCombustible == tipusVehicle.DIESEL && vehiculo.getAñoMatriculacion() >= 2006 && motor.cumpleNormativaEuro(4))) {
            return TipoEtiqueta.B;
        } 
        // Si no cumple con ninguna de las normativas, no tiene etiqueta
        else {
            return TipoEtiqueta.SIN_ETIQUETA;
        }
    }
}

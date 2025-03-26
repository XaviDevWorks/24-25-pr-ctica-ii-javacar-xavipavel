public class EtiquetaAmbiental {
    public enum TipoEtiqueta {
        SIN_ETIQUETA,
        B,
        C,
        ECO,
        CERO
    }
    public static TipoEtiqueta obtenerEtiqueta(Vehiculo vehiculo) {
        Motor motor = vehiculo.getMotor();
        
        if (vehiculo.esElectrico() || motor.esHibridoEnchufable40km()) {
            return TipoEtiqueta.CERO;
        } 
        else if (motor.esHibrido() || motor.usaGas()) {
            return TipoEtiqueta.ECO;
        } 
        else if (vehiculo.esGasolina() && vehiculo.añoMatriculacion >= 2006 && motor.cumpleNormativaEuro(4)) {
            return TipoEtiqueta.C;
        } 
        else if (vehiculo.esDiesel() && vehiculo.añoMatriculacion >= 2014 && motor.cumpleNormativaEuro(6)) {
            return TipoEtiqueta.C;
        } 
        else if ((vehiculo.esGasolina() && vehiculo.añoMatriculacion >= 2000 && motor.cumpleNormativaEuro(3)) ||
                   (vehiculo.esDiesel() && vehiculo.añoMatriculacion >= 2006 && motor.cumpleNormativaEuro(4))) {
            return TipoEtiqueta.B;
        } 
        else {
            return TipoEtiqueta.SIN_ETIQUETA;
        }
    }
}
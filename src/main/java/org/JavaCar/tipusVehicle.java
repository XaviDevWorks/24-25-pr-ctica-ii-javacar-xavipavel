package org.JavaCar;

public enum tipusVehicle {
    GASOLINA("Gasolina"), ELECTRIC("Electric"), HIBRID_DIESEL("HibridDiesel"), DIESEL("Diesel"),HIBRID_GASOLINA("HibridGasolina");
    private String atr;

    tipusVehicle(String tipo) {
        this.atr = tipo;
    }
}

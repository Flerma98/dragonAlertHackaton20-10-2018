package com.itlm.fernandoalberto.firedragonalerthackaton;

public class Datos {
        private Integer ID;
        private Integer Fuego;
        private Integer Cantidad;
        private Double GradosC;
        private Double GradosF;
        private Double Humedad;
        private String Fecha;
        private String Hora;

    public Datos() {
    }

    public Datos(Integer ID, Integer fuego, Integer cantidad, Double gradosC, Double gradosF, Double humedad, String fecha, String hora) {
        this.ID = ID;
        Fuego = fuego;
        Cantidad = cantidad;
        GradosC = gradosC;
        GradosF = gradosF;
        Humedad = humedad;
        Fecha = fecha;
        Hora = hora;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getFuego() {
        return Fuego;
    }

    public void setFuego(Integer fuego) {
        Fuego = fuego;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer cantidad) {
        Cantidad = cantidad;
    }

    public Double getGradosC() {
        return GradosC;
    }

    public void setGradosC(Double gradosC) {
        GradosC = gradosC;
    }

    public Double getGradosF() {
        return GradosF;
    }

    public void setGradosF(Double gradosF) {
        GradosF = gradosF;
    }

    public Double getHumedad() {
        return Humedad;
    }

    public void setHumedad(Double humedad) {
        Humedad = humedad;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }
}

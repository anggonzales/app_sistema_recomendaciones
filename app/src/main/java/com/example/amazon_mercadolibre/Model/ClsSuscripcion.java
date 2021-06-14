package com.example.amazon_mercadolibre.Model;

public class ClsSuscripcion {
    private String id;
    private String IdSuscripcion;
    private String IdUsuario;
    private Double Precio;
    private String NombreSuscripcion;
    private String Fecha;
    private String Estado;

    public ClsSuscripcion() {
    }

    public ClsSuscripcion(String idUsuario, Double precio, String nombreSuscripcion, String fecha, String estado) {
        IdUsuario = idUsuario;
        Precio = precio;
        NombreSuscripcion = nombreSuscripcion;
        Fecha = fecha;
        Estado = estado;
    }

    public ClsSuscripcion(Double precio, String nombreSuscripcion, String fecha, String estado) {
        Precio = precio;
        NombreSuscripcion = nombreSuscripcion;
        Fecha = fecha;
        Estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSuscripcion() {
        return IdSuscripcion;
    }

    public void setIdSuscripcion(String idSuscripcion) {
        IdSuscripcion = idSuscripcion;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getNombreSuscripcion() {
        return NombreSuscripcion;
    }

    public void setNombreSuscripcion(String nombreSuscripcion) {
        NombreSuscripcion = nombreSuscripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}

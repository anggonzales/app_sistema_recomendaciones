package com.example.amazon_mercadolibre.Model;

public class ClsBusqueda {

    private String key;
    private String busqueda;

    public ClsBusqueda(){

    }

    public ClsBusqueda(String key, String busqueda) {
        this.key = key;
        this.busqueda = busqueda;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

}

package com.example.amazon_mercadolibre.Model;

public class ClsArticulo {
    private String asin;
    private String titulo;
    private String URL;
    private String precio;
    private String imagen;
    private String currency_id;
    private String condition;

public  ClsArticulo(){

}
    public ClsArticulo(String asin, String titulo, String URL, String precio, String imagen,String currency_id,String condition) {
        this.asin = asin;
        this.titulo = titulo;
        this.URL = URL;
        this.precio = precio;
        this.imagen = imagen;
        this.currency_id =currency_id;
        this.condition=condition;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}

package com.example.amazon_mercadolibre.Model;

public class ClsArticulo {
    private String asin;
    private String titulo;
    private String URL;
    private String precio;
    private String imagen;

    public ClsArticulo(String asin, String titulo, String URL, String precio, String imagen) {
        this.asin = asin;
        this.titulo = titulo;
        this.URL = URL;
        this.precio = precio;
        this.imagen = imagen;
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
}

package com.example.amazon_mercadolibre.Model;

public class ClsCategoria {
    private String IdCategoria;
    private String id;
    private String Nombre;
    private String Descripcion;
    private boolean isSelected;
    private  String idcategory;

    public ClsCategoria() {
    }


    public ClsCategoria(String nombre) {
        Nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public String getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(String idcategory) {
        this.idcategory = idcategory;
    }

    @Override
    public String toString(){
        return this.getNombre();
    }
}

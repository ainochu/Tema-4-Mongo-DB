package com.ainochu.gestor_facturas.base;


import org.bson.types.ObjectId;

/**
 * Created by Ainoa on 27/01/2016.
 */
public class Producto {

    private ObjectId id;

    private String nombre;

    private String descripcion;

    private float precio;

    private boolean descuento;



    public Producto(){

    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

}

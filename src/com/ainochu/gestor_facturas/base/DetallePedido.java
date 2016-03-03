package com.ainochu.gestor_facturas.base;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Ainoa on 14/01/2016.
 */

public class DetallePedido {

    private ObjectId id;
    private float precio;
    private String nombre_producto;
    private String destinatario_compra;
    private Date fecha_factura;


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDestinatario_compra() {
        return destinatario_compra;
    }

    public void setDestinatario_compra(String destinatario_compra) {
        this.destinatario_compra = destinatario_compra;
    }

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}

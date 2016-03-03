package com.ainochu.gestor_facturas.base;

import org.bson.types.ObjectId;

import java.util.Date;


/**
 * Created by Ainoa on 14/01/2016.
 */

public class Factura {

    private ObjectId id;
    private String domicilio;
    private Date fecha;
    private Date fechaEntrega;
    private String cliente;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

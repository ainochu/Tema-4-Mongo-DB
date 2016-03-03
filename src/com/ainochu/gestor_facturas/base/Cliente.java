package com.ainochu.gestor_facturas.base;

import org.bson.types.ObjectId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ainoa on 24/01/2016.
 */

public class Cliente {

    private ObjectId id;
    private String nombre;
    private String apellidos;
    private Date fecha_nacimiento;
    private String domicilio;
    private String tipo_cliente;
    private List<Factura> listaFacturas;

    public Cliente(){
        this.listaFacturas = new ArrayList<>();
    }


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

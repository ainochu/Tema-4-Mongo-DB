package com.ainochu.gestor_facturas.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ainoa on 27/01/2016.
 */
@Entity
@Table(name="secciones")
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="fecha_creacion")
    private Date fecha_creacion;

    @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "listaSecciones")
    private List<Producto>listaProductos;

    public Seccion(){
        this.listaProductos = new ArrayList<>();
    }


    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return nombre;
    }
    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}

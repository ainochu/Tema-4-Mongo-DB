package com.ainochu.gestor_facturas.bean;


import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;

/**
 * JTable que se encarga de rellenar la tabla de los articulos
 */
public class TableArticulos extends JTable {

    private DefaultTableModel modelo;
    private List<Producto> listaProductos;

    public TableArticulos(){
        modelo = new DefaultTableModel();
        anadirColumnas();
    }

    public void anadirColumnas(){
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Precio");
        modelo.addColumn("Descuento");
        this.setModel(modelo);
    }


    public void rellenarTabla(MongoDB db){
        modelo.setNumRows(0);
        try {
            listaProductos = db.getProductos();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(listaProductos.size()==0){
            return;
        }
        for (Producto producto: listaProductos) {
            Object[] fila = new Object[]{producto.getId(),producto.getNombre(),producto.getDescripcion(),
                    producto.getPrecio(),producto.isDescuento()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }


    public void rellenarTabla(List<Producto>listaProductosB){
        modelo.setNumRows(0);
        if(listaProductosB.size()==0){
            return;
        }
        for (Producto producto: listaProductosB) {
            Object[] fila = new Object[]{producto.getId(),producto.getNombre(),producto.getDescripcion(),
                    producto.getPrecio(),producto.isDescuento()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }




    public Producto cargarProducto(MongoDB db){
        int filaSeleccionada = 0;

        filaSeleccionada = getSelectedRow();
        if (filaSeleccionada == -1)
            return null;

        String nombre = (String) getValueAt(filaSeleccionada, 1);

        Producto producto = null;
        try {
            producto = db.getProducto(nombre);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return producto;
    }




}

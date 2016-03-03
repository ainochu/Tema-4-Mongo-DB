package com.ainochu.gestor_facturas.bean;


import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * JTable que almacena las facturas de cada cliente
 */
public class TableCarrito extends JTable{

    private DefaultTableModel modelo;
    private List<DetallePedido> listaProductos;
    private DetallePedido detallePedido;
    private int filaSeleccionada;

    public TableCarrito(){
        modelo = new DefaultTableModel();
        anadirColumnas();
    }

    public void anadirColumnas(){
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio/U");
        modelo.addColumn("Destinatario factura");
        modelo.addColumn("Fecha_factura");
        this.setModel(modelo);
    }

    public void rellenarTablaPedido(DetallePedido detallePedido){
        modelo.setNumRows(0);
        Object[] fila = new Object[]{detallePedido.getId(),detallePedido.getNombre_producto(),detallePedido.getPrecio(),
        detallePedido.getDestinatario_compra(),detallePedido.getFecha_factura()};
        modelo.addRow(fila);
        setModel(modelo);
    }

    public void limpiarcarrito() {
        modelo.setRowCount(0);
    }

    public void cargarCarrito(Factura f) {
        /*
        modelo.setNumRows(0);
        listaProductos = new ArrayList<>();
        for(DetallePedido detallePedido: f.getListaPedidos()) {
            Object[] fila = new Object[]{detallePedido.getId(),detallePedido.getProducto().getNombre(), detallePedido.getUnidades(),
                   detallePedido.getProducto().getPrecio(), detallePedido.getPrecio()};
            modelo.addRow(fila);
            setModel(modelo);
        }
        */

    }

    public void cargarCarrito(List<DetallePedido>listaProductos){
        modelo.setNumRows(0);
        for(DetallePedido detallePedido: listaProductos) {
            Object[] fila = new Object[]{detallePedido.getId(),detallePedido.getNombre_producto(),detallePedido.getPrecio(),
                    detallePedido.getDestinatario_compra(),detallePedido.getFecha_factura()};
            modelo.addRow(fila);
            setModel(modelo);
        }
    }



   public DetallePedido devolverProductoCesta(MongoDB db){
       filaSeleccionada = 0;

       filaSeleccionada = getSelectedRow();
       if (filaSeleccionada == -1)
           return null;

       String nombre_producto = (String) getValueAt(filaSeleccionada, 1);

       try {
           detallePedido = db.getElementoCarrito(nombre_producto);
       } catch (ParseException e) {
           e.printStackTrace();
       }
       return detallePedido;
   }




/*
    public void modificarProducto(DetallePedido detallePedido,MongoDB db){
        float precioU = (Float.parseFloat(String.valueOf(this.getValueAt(filaSeleccionada, 3))));
        int unidades = (Integer.parseInt(String.valueOf(this.getValueAt(filaSeleccionada, 2))));
        String nombreProducto = (String) this.getValueAt(filaSeleccionada, 1);
        Producto producto = r.devolverProduto(nombreProducto);
        precioU = producto.getPrecio();
        detallePedido.getProducto().setNombre(nombreProducto);
        detallePedido.getProducto().setPrecio(precioU);
        detallePedido.setUnidades(unidades);
        detallePedido.setPrecio(precioU*unidades);
        r.modificarDetalleFactura(detallePedido);
    }
    */


}

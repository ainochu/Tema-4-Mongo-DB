package com.ainochu.gestor_facturas.bean;


import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Ainoa on 06/12/2015.
 */
public class TableFacturas extends JTable {

    private DefaultTableModel modelo;
    private List<Factura> listaFacturas;

    public TableFacturas(){
        modelo = new DefaultTableModel();
        anadirColumnas();
    }

    public void anadirColumnas(){
        modelo.addColumn("ID factura");
        modelo.addColumn("Domicilio");
        modelo.addColumn("F.Factura");
        modelo.addColumn("F.Entrega");
        modelo.addColumn("Destinatario factura");
        this.setModel(modelo);
    }



    public void rellenarTabla(List<Factura>listaFacturas){
        modelo.setNumRows(0);
        if(listaFacturas.size()==0){
            return;
        }
        for (Factura factura: listaFacturas) {
            Object[] fila = new Object[]{factura.getId(),factura.getDomicilio(),factura.getFecha(),
                    factura.getFechaEntrega(),factura.getCliente()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }

    public void rellenarTabla(MongoDB r){
        modelo.setNumRows(0);
        try {
            listaFacturas = r.getFacturas();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(listaFacturas.size()==0){
            return;
        }
        for (Factura factura: listaFacturas) {
            Object[] fila = new Object[]{factura.getId(),factura.getDomicilio(),factura.getFecha(),
                    factura.getFechaEntrega(),factura.getCliente()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }



    public Factura cargarFactura(MongoDB db){
        int filaSeleccionada = 0;

        filaSeleccionada = getSelectedRow();
        if (filaSeleccionada == -1)
            return null;

        String cliente = (String) getValueAt(filaSeleccionada, 4);

        Factura factura= null;
        try {
            factura = db.getFactura(cliente);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return factura;
    }



}

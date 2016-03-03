package com.ainochu.gestor_facturas.bean;


import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Ainoa on 03/12/2015.
 */
public class TableClientes extends JTable{

    private DefaultTableModel modelo;
    private MongoDB mongoDB;
    private List<Cliente>listaClientes;

    public TableClientes(){
        modelo = new DefaultTableModel();
        mongoDB = new MongoDB();
        anadirColumnas();
    }

    public void anadirColumnas(){
        modelo.addColumn("id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Domicilio");
        modelo.addColumn("F.Nacimiento");
        modelo.addColumn("Tipo Cliente");
        this.setModel(modelo);
    }


    public void rellenarTabla(){
        modelo.setNumRows(0);
        try {
            listaClientes = mongoDB.getClientes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(listaClientes.size()==0){
            return;
        }
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[]{cliente.getId(),cliente.getNombre(),cliente.getApellidos(),
                    cliente.getDomicilio(),cliente.getFecha_nacimiento(),cliente.getTipo_cliente()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }

    public void rellenarTabla(List<Cliente>listaClientes){
        modelo.setNumRows(0);
        if(listaClientes.size()==0){
            return;
        }
        for (Cliente cliente : listaClientes) {
            Object[] fila = new Object[]{cliente.getId(),cliente.getNombre(),cliente.getApellidos(),
                    cliente.getDomicilio(),cliente.getFecha_nacimiento(),cliente.getTipo_cliente()};
            modelo.addRow(fila);
        }
        this.setModel(modelo);
    }



    public Cliente cargarCliente(MongoDB db){
        int filaSeleccionada = 0;

        filaSeleccionada = getSelectedRow();
        if (filaSeleccionada == -1)
            return null;

        String nombre = (String) getValueAt(filaSeleccionada, 1);

        Cliente cliente = null;
        try {
            cliente = db.getCliente(nombre);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cliente;
    }




}

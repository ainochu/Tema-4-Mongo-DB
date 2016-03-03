package com.ainochu.gestor_facturas.bean;

import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.Cliente;

import javax.swing.*;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Ainoa on 26/01/2016.
 */
public class ComboC extends JComboBox<String> {

    public MongoDB r;
    private List<Cliente> listaClientes;

    public ComboC(){
        super();
        r = new MongoDB();
    }

    public void rellenarCombo(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.removeAllElements();
        try {
            listaClientes = r.getClientes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("SIZE " + listaClientes.size());
        if(listaClientes.size()==0){
            return;
        }
        for(int i=0; i<listaClientes.size(); i++){
            modelo.addElement(listaClientes.get(i).getNombre());
        }
        this.setModel(modelo);
    }



    public Cliente devolverCliente(MongoDB db){
        String nombre = this.getSelectedItem().toString();
        //HACER CONSULTA
        Cliente cliente = null;
        try {
            cliente = db.getCliente(nombre);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cliente;
    }

}

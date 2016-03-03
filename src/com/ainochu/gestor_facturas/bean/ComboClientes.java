package com.ainochu.gestor_facturas.bean;

import com.ainochu.gestor_facturas.base.Cliente;
import org.hibernate.Query;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Combo que almacena los clientes del array ListaClientes
 */
public class ComboClientes extends JComboBox<String> {

    public ComboClientes(){
        super();
    }

    public void rellenarCombo(ArrayList<Cliente>listaClientes){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.removeAllElements();
        if(listaClientes.size()==0){
            return;
        }
        for(int i=0; i<listaClientes.size(); i++){
            //modelo.addElement(listaClientes.get(i).getNombre());
        }
        this.setModel(modelo);
    }

    /*
    public Cliente devolverCliente(){
        String nombre = this.getSelectedItem().toString();
        //HACER CONSULTA
        Query query = HibernateUtil.getCurrentSession().createQuery("SELECT c FROM Cliente c WHERE c.nombre = :nombre");
        query.setParameter("nombre", nombre);
        Cliente cliente = (Cliente) query.uniqueResult();
        return cliente;
    }
    */
}

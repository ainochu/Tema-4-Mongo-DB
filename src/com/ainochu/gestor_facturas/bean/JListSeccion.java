package com.ainochu.gestor_facturas.bean;

import com.ainochu.gestor_facturas.base.Seccion;

import javax.swing.*;
import java.util.List;

/**
 * Created by Ainoa on 26/01/2016.
 */
public class JListSeccion extends JList<Seccion> {


    private List<Seccion> secciones;
    private DefaultListModel modelo;

    public JListSeccion(){
        super();
        modelo = new DefaultListModel();
    }



    /*
    public void rellenarListaDpto(Relaciones r) {
        modelo.removeAllElements();
        secciones = r.devolverSecciones();
        for(Seccion seccion: secciones){
            modelo.addElement(seccion);
        }
        this.setModel(modelo);
    }

    */


}

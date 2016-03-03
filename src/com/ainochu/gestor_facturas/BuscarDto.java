package com.ainochu.gestor_facturas;

import com.ainochu.gestor_facturas.base.Producto;
import com.ainochu.gestor_facturas.base.Seccion;
import com.ainochu.gestor_facturas.bean.JListSeccion;
import com.ainochu.gestor_facturas.util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;

public class BuscarDto extends JDialog  {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JListSeccion ltDpto;
    private JList ltDptoSeleccionados;
    private JButton btAdd;
    private JButton btDeshacer;
    private JTextField textNombreD;
    private JButton btGuardarDto;
    private Seccion s;
    private MongoDB r;
    private DefaultListModel modelo;
    private DefaultListModel modeloDtoS;
    private Seccion seccion;
    private ArrayList<Seccion> listaSecciones;
    private Seccion seccionBorrar;
    private Producto p;

    public BuscarDto(MongoDB r) {
        this.r = r;
        //cargarVentana();
        listaSecciones = new ArrayList<>();
    }

    /*
    public BuscarDto(MongoDB r, Producto p) {
        this.r = r;
        this.p = p;
        listaSecciones = new ArrayList<>();
        for(Seccion seccion: p.getListaSecciones()){
            listaSecciones.add(seccion);
        }
        cargarVentana();
        cargarLista();
    }

    public void cargarLista(){
        modeloDtoS = new DefaultListModel();
        modeloDtoS.removeAllElements();
        for(Seccion s: listaSecciones){
            modeloDtoS.addElement(s);
        }
        ltDptoSeleccionados.setModel(modeloDtoS);

    }



    public void cargarVentana() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        buttonOK.addActionListener(this);
        buttonOK.setActionCommand("Aceptar");
        buttonCancel.addActionListener(this);
        buttonCancel.setActionCommand("Cancelar");
        btGuardarDto.setActionCommand("Guardar dpto");
        btGuardarDto.addActionListener(this);
        btAdd.setActionCommand("Add");
        btAdd.addActionListener(this);
        btDeshacer.setActionCommand("Deshacer");
        btDeshacer.addActionListener(this);
        textNombreD.addFocusListener(this);
        ltDpto.rellenarListaDpto(r);
        ltDpto.addMouseListener(this);
        ltDptoSeleccionados.addMouseListener(this);
        modelo = new DefaultListModel();
        pack();
        setModal(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Aceptar":
                //GUARDAR EN UN ARRAY LOS DTO SELECCIONADOS
                dispose();
                break;
            case "Cancelar":
                dispose();
                break;
            case "Guardar dpto":
                Calendar fecha = Calendar.getInstance();
                s = new Seccion();
                s.setNombre(textNombreD.getText());
                s.setFecha_creacion(fecha.getTime());
                r.guardarSecccion(s);
                ltDpto.rellenarListaDpto(r);
                break;
            case "Add":
                //CUANDO PULSE ADD EL ELEMENTO SE PASARÁ A LA OTRA LISTA
                if(ltDpto.getSelectedIndex()==-1){
                    Util.mensajeConfirmacion("No se ha seleccionado ningún dpto.");
                    return;
                }
                listaSecciones.add(seccion);
                cargarLista();
                break;
            case "Deshacer":
                //CUANDO PULSE SE ELIMINARÁ LA SECCION DEL ARRAY Y DE LA LISTA
                if(ltDptoSeleccionados.getSelectedIndex()==-1){
                    Util.mensajeConfirmacion("No se ha seleccionado ningún dpto.");
                    return;
                }
                listaSecciones.remove(seccionBorrar);
                if(p!=null){
                    r.eliminarRelaciondeSeccionProducto(p,seccionBorrar);
                }
                cargarLista();
                break;
            default:
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        textNombreD.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(ltDpto.getSelectedIndex()!=-1) {
            ltDptoSeleccionados.setSelectedIndex(-1);
            seccion = ltDpto.getSelectedValue();
        }
        if(ltDptoSeleccionados.getSelectedIndex()!=-1){
            ltDpto.setSelectedIndex(-1);
            seccionBorrar = (Seccion)ltDptoSeleccionados.getSelectedValue();
            System.out.println(seccionBorrar.getNombre());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public ArrayList<Seccion> getListaSecciones() {
        return listaSecciones;
    }

    public void setListaSecciones(ArrayList<Seccion> listaSecciones) {
        this.listaSecciones = listaSecciones;
    }
    */
}

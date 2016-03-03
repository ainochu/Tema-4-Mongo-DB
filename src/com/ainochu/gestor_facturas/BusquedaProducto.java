package com.ainochu.gestor_facturas;

import com.ainochu.gestor_facturas.base.Producto;
import com.ainochu.gestor_facturas.bean.TableArticulos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BusquedaProducto extends JDialog {
    private JPanel contentPane;
    private Producto producto;
    private JButton btAceptar;
    private JButton btCancelar;
    private JTextField textBusqueda;
    private JButton btIncrementar;
    private JTextField textCantidad;
    private JButton btDecrementar;
    private TableArticulos tbProductos;
    private int contCantidad;
    private int cantidad;
    private MongoDB r;

    public BusquedaProducto(MongoDB r) {
        setContentPane(contentPane);
        tbProductos.rellenarTabla(r);
        textCantidad.setText("0");
        this.r = r;
        cargarListeners();
        setModal(true);
        pack();
        setVisible(true);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void cargarListeners(){
        btAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producto = tbProductos.cargarProducto(r);
                cantidad = Integer.parseInt(textCantidad.getText());
                dispose();
            }
        });
        btCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btIncrementar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contCantidad+=1;
                textCantidad.setText("" + contCantidad);
            }
        });
        btDecrementar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(contCantidad==0){
                    return;
                }
                contCantidad-=1;
                textCantidad.setText(""+contCantidad);
            }
        });
        textBusqueda.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        tbProductos.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_ENTER){
                    producto = tbProductos.cargarProducto(r);
                    cantidad = Integer.parseInt(textCantidad.getText());
                    dispose();
                }
            }
        });
    }


}

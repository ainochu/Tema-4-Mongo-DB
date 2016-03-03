package com.ainochu.gestor_facturas;

import javax.swing.*;
import java.awt.event.*;

public class Login extends JDialog implements ActionListener, KeyListener {
    private JPanel panel;
    private JPanel contentPane;
    private JTextField textNombreUsuario;
    private JPasswordField textPassword;
    private JComboBox cbTipoUsuario;
    private JButton btAceptar;
    private JButton btCancelar;
    private String usuario;
    private String contrasena;
    private String tipo_usuario;

    public Login() {
        this.add(panel);
        setTitle("Login");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setModal(true);
        iniciarListeners();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void iniciarListeners() {
        btAceptar.addActionListener(this);
        btAceptar.setActionCommand("Aceptar");
        btCancelar.setActionCommand("Cancelar");
        btCancelar.addActionListener(this);
        textNombreUsuario.addKeyListener(this);
        textPassword.addKeyListener(this);
        cbTipoUsuario.addItem("Admin");
        cbTipoUsuario.addItem("Local user");
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Aceptar":
                aceptar();
                break;
            case "Cancelar":
                cancelar();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    private void aceptar() {
        if (textNombreUsuario.getText().equals("") || String.valueOf(textPassword.getPassword()).equals("")) {
            JOptionPane.showMessageDialog(null, "Error de campos", "Introduzca usuario y contraseña", JOptionPane.ERROR_MESSAGE);
        } else {
            usuario = textNombreUsuario.getText();
            contrasena = String.valueOf(textPassword.getPassword());
            System.out.println(contrasena);
            tipo_usuario = cbTipoUsuario.getSelectedItem().toString();
            dispose();
        }
    }

    private void cancelar() {
        System.exit(0);
        this.setVisible(false);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == textNombreUsuario) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                textPassword.requestFocus();
            }
        }
        if (e.getSource() == textPassword) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                btAceptar.doClick();
            }
        }
    }
}

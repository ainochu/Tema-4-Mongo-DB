package com.ainochu.gestor_facturas.Main;

import com.ainochu.gestor_facturas.bean.*;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;

/**
 * Aplicación que gestiona los datos de Clientes, Articulos y Genera facturas
 *
 * @author Ainoa Andrés
 * @version curso 2015-2016
 */
public class V  {
    public static JFrame frame;
    public Connection conexion;
    public File archivoconf;
    public JTabbedPane tabbedPane1;
    public JPanel panel1;
    public JTextField tbNombre;
    public JTextField tbApellidos;
    public JButton btNuevoC;
    public JButton btGuardarC;
    public JButton btNuevoA;
    public JTextField textNombreA;
    public JTextField textId_ProductoA;
    public JTextField textPrecioA;
    public JButton btGuardarA;
    public JButton btBusquedaA;
    public JTextField textDomicilioF;
    public String[] tipo_cliente;
    public JButton btAnadirElemento;
    public JButton btNuevaF;
    public JButton btModificarF;
    public JButton btBusquedaF;
    public JButton btGuardarF;
    public JButton btModificarC;
    public JButton btBusquedaC;
    public JTextField textDomicilioC;
    public JPanel jpArticulos;
    public JPanel jpFacturas;
    public JPanel jpClientes;
    public JButton btModificarA;
    public JTable tbArticulos;
    public JScrollPane tbFFinal;
    public JComboBox cbCantidadA;
    public JList ltClientes;
    public JButton btEliminarC;
    public JList ltArticulos;
    public JList ltFacturas;
    public JButton btEliminarA;
    public JButton btEliminarF;
    public JButton btEliminarElemento;
    public JLabel lbTotalFactura;
    public JButton btExportarExcel;
    public JComboBox cbBusquedaC;
    public JButton btBusquedaAC;
    public JLabel lbTipoCliente;
    public JLabel lbNombreC;
    public JPanel JPBusquedaC;
    public JPanel JPBusquedaA;
    public JButton btLimpiarB;
    public JLabel lbBNombreA;
    public JTextField textBNombreA;
    public JLabel lbBPrecio;
    public JButton btBusquedaAA;
    public JTextField textBDptoA;
    public JButton btLimpiarA;
    public JTextField textBusquedaF;
    public JTextField textBusquedaFId;
    public JButton btBusquedaAF;
    public JButton btLimpiarF;
    public JPanel JPBusquedaF;
    public JButton btArticulosDescuento;
    public JButton btEliminarTipoCliente;
    public JButton btAplicarDto;
    public JLabel lbIdB;
    public JLabel lbDestinatarioB;
    public int cont = 0;
    public DefaultListModel lista;
    public File archivoClientes;
    public File archivoArticulo;
    public File archivoFactura;
    public int posicion;
    public boolean activado;
    public float total;
    public int contB = 0;
    public boolean aceptado;
    public String rol_usuario;
    public JMenu configuracion;
    public String destinatarioF;
    public ComboClientes cbClientes;
    public JComboBox cbTipoCliente;
    public JDateChooser dateNacimiento;
    public JComboBox cbDescuento;
    public ComboC cbNombreCliente;
    public JButton btElegirDto;
    public JDateChooser dateFechaFactura;
    public TableClientes tbClientes;
    public TableArticulos tbProductos;
    public TableFacturas tbFacturas;
    public JDateChooser dateFechaActual;
    public TableCarrito tbCarritoProducto;
    public JButton btModificarElemento;
    public JTextField textidFactura;
    public JDateChooser dateFechaFacturaB;
    public JMenuItem importar;
    public JMenuItem exportar;
    public JMenuItem configurar;


    public V(){
        crearVentana();
        createUIComponents();
    }
    public void crearVentana(){
        JFrame frame = new JFrame("Gestor Facturas");
        frame.setContentPane(panel1);
        frame.setJMenuBar(crearJMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,850);
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }
    //CREA EL JMENUBAR DE NUESTRO FRAME
    private JMenuBar crearJMenuBar(){

        JMenu archivo = new JMenu("Archivo");
        configuracion = new JMenu("Configuracion");
        importar = new JMenuItem("Importar...");
        exportar = new JMenuItem("Exportar...");
        configurar = new JMenuItem("Configurar");

        configurar.setActionCommand("Configuracion");
        exportar.setActionCommand("Exportar xml");
        importar.setActionCommand("Importar xml");

        archivo.add(importar);
        archivo.add(exportar);
        configuracion.add(configurar);
        JMenuBar mb = new JMenuBar();
        mb.add(archivo);
        mb.add(configuracion);

        return mb;
    }
}
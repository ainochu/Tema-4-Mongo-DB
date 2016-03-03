import com.ainochu.gestor_facturas.BuscarDto;
import com.ainochu.gestor_facturas.BusquedaProducto;
import com.ainochu.gestor_facturas.Login;
import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.*;
import com.ainochu.gestor_facturas.Main.V;
import com.ainochu.gestor_facturas.util.Timmer;
import com.ainochu.gestor_facturas.util.Util;
import com.ainochu.gestor_facturas.util.XML;
import org.hibernate.Hibernate;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.*;
import java.util.List;

/**
 * Created by Ainoa on 24/01/2016.
 */
public class Controller implements ActionListener, MouseListener, PropertyChangeListener {

    public V v;
    public Hibernate h;
    public Cliente c;
    public MongoDB r;
    public Producto p;
    public Factura f;
    public ArrayList<DetallePedido>listaProductos;
    private DetallePedido detallePedido;
    private boolean correcto;
    private BuscarDto buscarDto;
    private boolean pulsado;
    private boolean nuevoA;
    private boolean nuevaF;
    private ArrayList<DetallePedido>listaDetallePedidoFactura;
    private boolean factura;
    private String nombreC;

    public Controller(){
        this.v = v;
        this.h = h;
        this.r = new MongoDB();
        comprobarLogin();
        v = new V();
        cargarListeners();
        cargarTablas();
        cargarCombos();
        listaProductos = new ArrayList<>();
        listaDetallePedidoFactura = new ArrayList<>();
        cargarRefrescoLista();
    }


    public void comprobarLogin(){
        while(!correcto) {
            Login g = new Login();
        Usuario u = new Usuario();
        u.setNombre(g.getUsuario());
        u.setPassword(g.getContrasena());
        u.setTipo_usuario(g.getTipo_usuario());
            try {
                correcto = r.loginUser(u);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (correcto) {
                Util.mensajeConfirmacion("Correcto");
            } else {
                Util.mensajeConfirmacion("Error");
            }
        }
    }


    private void cargarTablas() {
        v.tbClientes.rellenarTabla();
        v.tbProductos.rellenarTabla(r);
        v.tbFacturas.rellenarTabla(r);
    }
    private void cargarCombos(){
        rellenarComboTipoCliente();
        rellenarComboDto();
        v.cbNombreCliente.rellenarCombo();
    }


    public void cargarListeners(){
        //JMENUBAR
        v.exportar.setActionCommand("Exportar xml");
        v.exportar.addActionListener(this);
        v.importar.setActionCommand("Importar xml");
        v.importar.addActionListener(this);
        v.configurar.setActionCommand("Configurar");
        v.configurar.addActionListener(this);
        //CLIENTE
        v.btNuevoC.addActionListener(this);
        v.btNuevoC.setActionCommand("NuevoC");
        v.btModificarC.addActionListener(this);
        v.btModificarC.setActionCommand("ModificarC");
        v.btEliminarC.addActionListener(this);
        v.btEliminarC.setActionCommand("EliminarC");
        v.btBusquedaAC.addActionListener(this);
        v.btGuardarC.setActionCommand("GuardarC");
        v.btGuardarC.addActionListener(this);
        v.tbClientes.addMouseListener(this);
        //ARTICULO
        v.btNuevoA.addActionListener(this);
        v.btNuevoA.setActionCommand("NuevoA");
        v.btModificarA.addActionListener(this);
        v.btModificarA.setActionCommand("ModificarA");
        v.btEliminarA.setActionCommand("EliminarA");
        v.btEliminarA.addActionListener(this);
        v.btGuardarA.addActionListener(this);
        v.btGuardarA.setActionCommand("GuardarA");
        //PARA SELECCIONAR VARIOS DTO
        v.btElegirDto.setActionCommand("ElegirDto");
        v.btElegirDto.addActionListener(this);
        v.tbProductos.addMouseListener(this);
        //FACTURA
        v.btNuevaF.addActionListener(this);
        v.btNuevaF.setActionCommand("NuevaF");
        v.btModificarF.addActionListener(this);
        v.btModificarF.setActionCommand("ModificarF");
        v.btEliminarF.addActionListener(this);
        v.btEliminarF.setActionCommand("EliminarF");
        v.btGuardarF.addActionListener(this);
        v.btGuardarF.setActionCommand("GuardarF");
        v.cbNombreCliente.setActionCommand("ElegirCliente");
        v.cbNombreCliente.addActionListener(this);
        v.tbFacturas.addMouseListener(this);
        //DETALLE_PEDIDO
        v.btAnadirElemento.setActionCommand("Anadir_producto");
        v.btAnadirElemento.addActionListener(this);
        v.btEliminarElemento.setActionCommand("Eliminar_producto");
        v.btEliminarElemento.addActionListener(this);
        v.btModificarElemento.setActionCommand("Modificar_producto");
        v.btModificarElemento.addActionListener(this);
        v.tbCarritoProducto.addPropertyChangeListener(this);
        //BUSQUEDAS
        v.btBusquedaAA.setActionCommand("BusquedaA");
        v.btBusquedaAA.addActionListener(this);
        v.btLimpiarA.setActionCommand("LimpiarA");
        v.btLimpiarA.addActionListener(this);
        v.btBusquedaAF.setActionCommand("BusquedaF");
        v.btBusquedaAF.addActionListener(this);
        v.btLimpiarF.setActionCommand("LimpiarF");
        v.btLimpiarF.addActionListener(this);
        v.btBusquedaAC.setActionCommand("BusquedaC");
        v.btBusquedaAC.addActionListener(this);
        v.btLimpiarB.setActionCommand("LimpiarC");
        v.btLimpiarB.addActionListener(this);
        v.btBusquedaC.setActionCommand("BusquedaAC");
        v.btBusquedaC.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            //CLIENTE
            case "NuevoC":
                //TODO RELLENAR EL CLIENTE
                nuevoCliente();
                break;
            case "ModificarC":
                c.setNombre(v.tbNombre.getText());
                c.setApellidos(v.tbApellidos.getText());
                c.setDomicilio(v.textDomicilioC.getText());
                c.setTipo_cliente(v.cbTipoCliente.getSelectedItem().toString());
                c.setFecha_nacimiento(v.dateNacimiento.getDate());
                r.updateCliente(c);
                v.tbClientes.rellenarTabla();
                break;
            case "EliminarC":
                r.deleteCliente(c.getNombre());
                v.tbClientes.rellenarTabla();
                nuevoCliente();
                break;
            case "GuardarC":
                c = new Cliente();
                boolean resultado = controlarErroresCliente();
                c.setNombre(v.tbNombre.getText());
                c.setApellidos(v.tbApellidos.getText());
                c.setDomicilio(v.textDomicilioC.getText());
                c.setTipo_cliente(v.cbTipoCliente.getSelectedItem().toString());
                c.setFecha_nacimiento(v.dateNacimiento.getDate());
                if (resultado == false)
                    return;
                r.addCliente(c);
                v.tbClientes.rellenarTabla();
                break;
            //ARTICULO
            case "NuevoA":
                nuevoArticulo();
                nuevoA = true;
                break;
            case "ModificarA":
                //TODO TERMINAR MODIFICAR
                p.setNombre(v.textNombreA.getText());
                p.setDescripcion(v.textId_ProductoA.getText());
                p.setPrecio(Float.parseFloat(v.textPrecioA.getText()));
                p.setDescuento(Boolean.parseBoolean(v.cbDescuento.getSelectedItem().toString()));
                //MODIFICAR EN LA BD
                r.updateProducto(p);
                v.tbProductos.rellenarTabla(r);
                break;
            case "EliminarA":
                //ELIMINAR DE LA BD
                r.deleteProducto(p.getNombre());
                v.tbProductos.rellenarTabla(r);
                //LIMPIAR CAJAS
                nuevoArticulo();
                break;
            case "GuardarA":
                p = new Producto();
                boolean resultadoC = controlarErroresArticulo();
                p.setNombre(v.textNombreA.getText());
                p.setDescripcion(v.textId_ProductoA.getText());
                p.setPrecio(Float.parseFloat(v.textPrecioA.getText()));
                p.setDescuento(Boolean.parseBoolean(v.cbDescuento.getSelectedItem().toString()));
                if (resultadoC == false)
                    return;
                //GUARDAR EN LA BD
                r.addProducto(p);
                v.tbProductos.rellenarTabla(r);
                nuevoA = false;
                break;
            case "ElegirDto":
                //ABRIR VENTANA EMERGENTE PARA SELECCIONAR LOS DTOS.
                /*
                if(nuevoA==true) {
                    buscarDto = new BuscarDto(r);
                    buscarDto.setVisible(true);
                }
                else{
                    buscarDto = new BuscarDto(r,p);
                    buscarDto.setVisible(true);
                }
                */
                break;
            //FACTURA
            case "NuevaF":
                nuevaFactura();
                v.cbNombreCliente.rellenarCombo();
                nuevaF = true;
                //CREAR UN NUEVO OBJETO DE FACTURA
                break;
            case "ModificarF":
                //TODO MODIFICAR FACTURA
                f.setDomicilio(v.textDomicilioF.getText());
                f.setFecha(v.dateFechaActual.getDate());
                f.setFechaEntrega(v.dateFechaFactura.getDate());
                f.setCliente(c.getNombre());
                r.updateFactura(f);
                //MODIFICAR EN LA BD
                v.tbFacturas.rellenarTabla(r);
                pulsado = false;
                break;
            case "EliminarF":
                //ELIMINAR DE LA BD
                r.deleteFactura(f.getCliente());
                v.tbFacturas.rellenarTabla(r);
                r.deleteCarrito(f.getCliente());
                v.tbCarritoProducto.removeAll();
                nuevaFactura();
                break;
            case "GuardarF":
                f = new Factura();
                boolean resultadof = controlarErroresFactura();
                f.setDomicilio(v.textDomicilioF.getText());
                f.setFecha(v.dateFechaActual.getDate());
                f.setFechaEntrega(v.dateFechaFactura.getDate());
                f.setCliente(c.getNombre());
                r.addFactura(f);
                //GUARDAR EN LA BD
                v.tbFacturas.rellenarTabla(r);
                pulsado = false;
                //ELIMINAR EL CONTENIDO DE LAS TABLAS DE CARRITO Y LOS CAMPOS DE FACTURA
                nuevaFactura();
                v.tbCarritoProducto.limpiarcarrito();
                break;
            case "ElegirCliente":
                c = v.cbNombreCliente.devolverCliente(r);
                System.out.println(c.getDomicilio());
                v.textDomicilioF.setText(c.getDomicilio());
                break;
            //JMENU
            case "Exportar xml":
                XML xml = new XML(r);
                xml.escribirXMLCliente();
                break;
            //DETALLE_PEDIDO

            case "Anadir_producto":
                //SE AÑADE UN PRODUCTO AL CARRITO
                if(factura) {
                    detallePedido = new DetallePedido();
                    BusquedaProducto busquedaProducto = new BusquedaProducto(r);
                    detallePedido.setFecha_factura(v.dateFechaActual.getDate());
                    detallePedido.setPrecio(busquedaProducto.getProducto().getPrecio());
                    detallePedido.setNombre_producto(busquedaProducto.getProducto().getNombre());
                    detallePedido.setDestinatario_compra(c.getNombre());
                    listaDetallePedidoFactura.add(detallePedido);
                    r.addProductoCesta(detallePedido);
                    v.tbCarritoProducto.rellenarTablaPedido(detallePedido);
                }
                break;

            case "Eliminar_producto":
                detallePedido = v.tbCarritoProducto.devolverProductoCesta(r);
                r.deleteElementoCarrito(detallePedido.getNombre_producto());
                cargarLineaDetalles(f);
                break;
            case "BusquedaA":
                String nombreA = v.textBNombreA.getText();
                String precio = v.textBDptoA.getText();
                List<Producto>listaProductos= null;
                try {
                    listaProductos = r.buscarProducto(nombreA,precio);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                v.tbProductos.rellenarTabla(listaProductos);
                break;
            case "LimpiarA":
                v.textBNombreA.setText("");
                v.textBDptoA.setText("");
                v.tbProductos.rellenarTabla(r);
                break;
            case "BusquedaC":
                int idF;
                if(v.textidFactura.getText().isEmpty()){
                    nombreC = null;
                }
                else {
                    nombreC = (v.textidFactura.getText());
                }
                String tipoC = v.cbBusquedaC.getSelectedItem().toString();
                List<Cliente>listaClientes= null;
                try {
                    listaClientes = r.buscarCliente(nombreC,tipoC);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                v.tbClientes.rellenarTabla(listaClientes);
                break;
            case "BusquedaF":
                String destinatario = v.textBusquedaF.getText();
                String domicilio = v.textBusquedaFId.getText();
                List<Factura>listaFacturas = null;
                try {
                    listaFacturas = r.buscarFactura(destinatario, domicilio);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                v.tbFacturas.rellenarTabla(listaFacturas);
                break;
            case "LimpiarF":
                v.textBusquedaFId.setText("");
                v.textBusquedaF.setText("");
                v.tbFacturas.rellenarTabla(r);
                break;
            case "LimpiarC":
                v.textBusquedaFId.setText("");
                v.cbTipoCliente.setSelectedItem(null);
                v.tbClientes.rellenarTabla();
                break;
            case "BusquedaAC":
                rellenarComboBusqueda();
                break;

            /*
            case "Modificar_producto":
                v.tbCarritoProducto.modificarProducto(detallePedido,r);
                v.tbCarritoProducto.rellenarTablaPedido(detallePedido
                );
                break;
             */
            default:
                break;
        }
    }

    private void rellenarComboBusqueda(){
        v.cbBusquedaC.removeAllItems();
        v.cbBusquedaC.addItem("Autonomo");
        v.cbBusquedaC.addItem("Empresario");
        v.cbBusquedaC.addItem("Trabajor");
    }

    private void rellenarComboTipoCliente() {
        v.cbTipoCliente.removeAllItems();
        v.cbTipoCliente.addItem("Autonomo");
        v.cbTipoCliente.addItem("Empresario");
        v.cbTipoCliente.addItem("Trabajador");
    }

    private void nuevaFactura() {
        Calendar fecha = Calendar.getInstance();
        v.textDomicilioF.setText("");
        v.dateFechaFactura.setDate(null);
        v.dateFechaActual.setDate(fecha.getTime());
    }

    public void nuevoCliente(){
        v.tbNombre.setText("");
        v.tbApellidos.setText("");
        v.textDomicilioC.setText("");
    }
    public void nuevoArticulo(){
        v.textNombreA.setText("");
        v.textId_ProductoA.setText("");
        v.textPrecioA.setText("");
    }

    public void rellenarComboDto(){
        v.cbDescuento.removeAllItems();
        v.cbDescuento.addItem("true");
        v.cbDescuento.addItem("false");
    }


    private void devolverDatosCliente() {
        v.tbNombre.setText(c.getNombre());
        v.tbApellidos.setText(c.getApellidos());
        v.textDomicilioC.setText(c.getDomicilio());
        v.cbTipoCliente.setSelectedItem(c.getTipo_cliente());
        v.dateNacimiento.setDate(c.getFecha_nacimiento());
    }
    private void devolverDatosProducto() {
        v.textNombreA.setText(p.getNombre());
        v.textId_ProductoA.setText(p.getDescripcion());
        v.textPrecioA.setText(String.valueOf(p.getPrecio()));
        v.cbDescuento.setSelectedItem(p.isDescuento());
    }
    private void devolverDatosFactura() {
        v.textDomicilioC.setText(f.getDomicilio());
        v.dateFechaFactura.setDate(f.getFechaEntrega());
        v.cbNombreCliente.setSelectedItem(f.getCliente());
        v.dateFechaActual.setDate(f.getFecha());
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(v.tbClientes.getSelectedRow()!=-1){
            c = v.tbClientes.cargarCliente(r);
            devolverDatosCliente();
        }
        if(v.tbProductos.getSelectedRow()!=-1){
            p = v.tbProductos.cargarProducto(r);
            devolverDatosProducto();
        }
        if(v.tbFacturas.getSelectedRow()!=-1){
            factura = true;
            f = v.tbFacturas.cargarFactura(r);
            devolverDatosFactura();
            cargarLineaDetalles(f);

            try {
                List<DetallePedido>carrito = r.getListaCompra(f.getCliente());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }




        }
        else{
            factura = false;
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

    public void cargarRefrescoLista(){
        Timmer modelo = new Timmer();
        modelo.addObserver (new Observer()
        {
            public void update (Observable unObservable, Object dato)
            {
                v.tbClientes.rellenarTabla();
            }
        });
    }

    public void cargarLineaDetalles(Factura f){

        List<DetallePedido>listaPedidos = null;
        try {
            listaPedidos = r.getListaCompra(f.getCliente());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        v.tbCarritoProducto.cargarCarrito(listaPedidos);

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        detallePedido = v.tbCarritoProducto.devolverProductoCesta(r);


    }

    private boolean controlarErroresCliente(){
        if(v.cbTipoCliente.getSelectedIndex()==0){
            Util.mensajeConfirmacion("Seleccione tipo de cliente");
            v.cbTipoCliente.setBackground(Color.red);
            return false;
        }
        else{
            v.cbTipoCliente.setBackground(Color.white);
        }
        if(v.tbNombre.getText().isEmpty()){
            Util.mensajeConfirmacion("El nombre de cliente no puede estar vacio");
            v.tbNombre.setBackground(Color.red);
            return false;
        }
        else{
            v.tbNombre.setBackground(Color.white);
        }
        return true;
    }

    private boolean controlarErroresArticulo(){
        if (v.textNombreA.getText().isEmpty()){
            Util.mensajeConfirmacion("El nombre del articulo no puede estar vacio");
            v.textNombreA.setBackground(Color.red);
            return false;
        }
        else{
            v.textNombreA.setBackground(Color.white);
        }

        if (v.textId_ProductoA.getText().isEmpty()){
            Util.mensajeConfirmacion("El id del producto no puede estar vacio");
            v.textId_ProductoA.setBackground(Color.red);
            return false;
        }
        else{
            v.textId_ProductoA.setBackground(Color.white);
        }
        if (v.textPrecioA.getText().isEmpty()){
            Util.mensajeConfirmacion("El precio no puede estar vacio");
            v.textPrecioA.setBackground(Color.red);
            return false;
        }
        else{
            v.textPrecioA.setBackground(Color.white);
        }
        return true;
    }

    private boolean controlarErroresFactura() {
        if (v.textDomicilioF.getText().isEmpty()) {
            Util.mensajeConfirmacion("La direccion no puede estar vacia");
            v.textDomicilioF.setBackground(Color.red);
            return false;
        } else {
            v.textDomicilioF.setBackground(Color.white);
        }
        return true;
    }
}

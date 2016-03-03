package com.ainochu.gestor_facturas;

import com.ainochu.gestor_facturas.base.*;
import com.ainochu.gestor_facturas.util.Util;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Ainoa on 26/02/2016.
 */
public class MongoDB {

    public MongoClient cliente;
    public MongoDatabase db;
    public String DB = "ainoa";
    public String COLECCIONC= "clientes";
    public String COLECCIONF= "facturas";
    public String COLECCIONP= "productos";
    public String COLECCIONL= "usuarios";
    public String COLECCIONCARRITO = "carrito_compra";


    public MongoDB(){
        conectar();
    }

    public void conectar(){
        cliente = new MongoClient();
        db = cliente.getDatabase(DB);
        addLoginUsers("ainoa","andres");
        addLoginUsers("santi","faci");
    }

    public List<Cliente> getClientes() throws ParseException {
        FindIterable<Document> findIterable = db.getCollection(COLECCIONC).find();
        return getListaClientes(findIterable);
    }

    private List<Cliente> getListaClientes(FindIterable<Document> findIterable) throws ParseException {
        List<Cliente>listaClientes = new ArrayList<>();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document documento = iterator.next();
            Cliente cliente = new Cliente();
            cliente.setId(documento.getObjectId("_id"));
            cliente.setNombre(documento.getString("nombre"));
            cliente.setApellidos(documento.getString("apellidos"));
            cliente.setDomicilio(documento.getString("domicilio"));
            cliente.setTipo_cliente(documento.getString("tipo_cliente"));
            cliente.setFecha_nacimiento(Util.parseFecha(documento.getString("fecha_nacimiento")));
            listaClientes.add(cliente);
        }
        return listaClientes;
    }


    public void addCliente(Cliente cliente){
        Document documento = new Document()
                .append("nombre",cliente.getNombre())
                .append("apellidos",cliente.getApellidos())
                .append("domicilio",cliente.getDomicilio())
                .append("tipo_cliente", cliente.getTipo_cliente())
                .append("fecha_nacimiento", Util.parseFecha(cliente.getFecha_nacimiento()));
        db.getCollection(COLECCIONC).insertOne(documento);
    }


    public void updateCliente(Cliente cliente){
        db.getCollection(COLECCIONC).replaceOne(new Document("_id", cliente.getId()), new Document()
                .append("nombre", cliente.getNombre())
                .append("apellidos", cliente.getApellidos())
                .append("domicilio", cliente.getDomicilio())
                .append("tipo_cliente", cliente.getTipo_cliente())
                .append("fecha_nacimiento", Util.parseFecha(cliente.getFecha_nacimiento())));
    }


    public Cliente getCliente(String nombre) throws ParseException {
        FindIterable<Document>findIterable = db.getCollection(COLECCIONC).find(new Document("nombre", nombre));
        Document document = findIterable.first();
        return getCliente(document);
    }

    public Cliente getCliente(Document documento) throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setId(documento.getObjectId("_id"));
        cliente.setNombre(documento.getString("nombre"));
        cliente.setApellidos(documento.getString("apellidos"));
        cliente.setDomicilio(documento.getString("domicilio"));
        cliente.setTipo_cliente(documento.getString("tipo_cliente"));
        cliente.setFecha_nacimiento(Util.parseFecha(documento.getString("fecha_nacimiento")));
        return cliente;
    }


    public void deleteCliente(String nombre){
        db.getCollection(COLECCIONC).deleteMany(new Document("nombre", nombre));
    }

    public void deleteClientes(String tipo_cliente) {
        db.getCollection(COLECCIONC).deleteMany(new Document("tipo_cliente", tipo_cliente));
    }


    //PARTE PRODUCTOS
    public List<Producto> getProductos() throws ParseException {
        FindIterable<Document> findIterable = db.getCollection(COLECCIONP).find();
        return getListaProductos(findIterable);
    }

    private List<Producto> getListaProductos(FindIterable<Document> findIterable) throws ParseException {
        List<Producto>listaProductos = new ArrayList<>();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document documento = iterator.next();
            Producto producto = new Producto();
            producto.setId(documento.getObjectId("_id"));
            producto.setNombre(documento.getString("nombre"));
            producto.setDescripcion(documento.getString("descripcion"));
            producto.setPrecio(Float.parseFloat(documento.getString("precio")));
            producto.setDescuento(Boolean.parseBoolean(documento.getString("descuento")));
            listaProductos.add(producto);
        }
        return listaProductos;
    }


    public void addProducto(Producto producto){
        Document documento = new Document()
                .append("nombre", producto.getNombre())
                .append("descripcion",producto.getDescripcion())
                .append("precio", String.valueOf(producto.getPrecio()))
                .append("descuento", String.valueOf(producto.isDescuento()));
        db.getCollection(COLECCIONP).insertOne(documento);
    }


    public void updateProducto(Producto producto){
        db.getCollection(COLECCIONP).replaceOne(new Document("_id", producto.getId()), new Document()
                .append("nombre", producto.getNombre())
                .append("descripcion", producto.getDescripcion())
                .append("precio", String.valueOf(producto.getPrecio()))
                .append("descuento", String.valueOf(producto.isDescuento())));
    }


    public Producto getProducto(String nombre) throws ParseException {
        FindIterable<Document>findIterable = db.getCollection(COLECCIONP).find(new Document("nombre", nombre));
        Document document = findIterable.first();
        return getProducto(document);
    }

    public Producto getProducto(Document documento) throws ParseException {
        Producto producto = new Producto();
        producto.setId(documento.getObjectId("_id"));
        producto.setNombre(documento.getString("nombre"));
        producto.setDescripcion(documento.getString("descripcion"));
        producto.setPrecio(Float.parseFloat(documento.getString("precio")));
        producto.setDescuento(Boolean.parseBoolean(documento.getString("descuento")));
        return producto;
    }


    public void deleteProducto(String nombre){
        db.getCollection(COLECCIONP).deleteMany(new Document("nombre", nombre));
    }

    public void deleteProductos(String departamento) {
        db.getCollection(COLECCIONP).deleteMany(new Document("departamento", departamento));
    }


    //FACTURAS
    public List<Factura> getFacturas() throws ParseException {
        FindIterable<Document> findIterable = db.getCollection(COLECCIONF).find();
        return getListaFacturas(findIterable);
    }

    private List<Factura> getListaFacturas(FindIterable<Document> findIterable) throws ParseException {
        List<Factura>listaFacturas = new ArrayList<>();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document documento = iterator.next();
            Factura factura = new Factura();
            factura.setId(documento.getObjectId("_id"));
            factura.setDomicilio(documento.getString("domicilio"));
            factura.setFecha(Util.parseFecha(documento.getString("fecha")));
            factura.setFechaEntrega(Util.parseFecha(documento.getString("fecha_entrega")));
            factura.setCliente(documento.getString("cliente"));
            listaFacturas.add(factura);
        }
        return listaFacturas;
    }


    public void addFactura(Factura factura){
        Document documento = new Document()
                .append("domicilio", factura.getDomicilio())
                .append("fecha",Util.parseFecha(factura.getFecha()))
                .append("fecha_entrega", Util.parseFecha(factura.getFechaEntrega()))
                .append("cliente", factura.getCliente());
        db.getCollection(COLECCIONF).insertOne(documento);
    }


    public void updateFactura(Factura factura){
        db.getCollection(COLECCIONF).replaceOne(new Document("_id", factura.getId()), new Document()
                .append("domicilio", factura.getDomicilio())
                .append("fecha", Util.parseFecha(factura.getFecha()))
                .append("fecha_entrega", Util.parseFecha(factura.getFechaEntrega()))
                .append("cliente", factura.getCliente()));
    }


    public Factura getFactura(String nombre_cliente) throws ParseException {
        FindIterable<Document>findIterable = db.getCollection(COLECCIONF).find(new Document("cliente", nombre_cliente));
        Document document = findIterable.first();
        return getFactura(document);
    }

    public Factura getFactura(Document documento) throws ParseException {
        Factura factura = new Factura();
        factura.setId(documento.getObjectId("_id"));
        factura.setDomicilio(documento.getString("domicilio"));
        factura.setFecha(Util.parseFecha(documento.getString("fecha")));
        factura.setFechaEntrega(Util.parseFecha(documento.getString("fecha_entrega")));
        factura.setCliente(documento.getString("cliente"));
        return factura;
    }


    //TODO MODIFICAR
    public void deleteFactura(String nombre_cliente){
        db.getCollection(COLECCIONF).deleteMany(new Document("cliente", nombre_cliente));
    }

    public void deleteFacturas(String nombre_cliente) {
        db.getCollection(COLECCIONF).deleteMany(new Document("cliente", nombre_cliente));
    }

    public void addLoginUsers(String nombre,String password){
        Document documento = new Document()
                .append("nombre", nombre)
                .append("password", password);
        db.getCollection(COLECCIONL).insertOne(documento);
    }


    public boolean loginUser(Usuario u) throws ParseException {

        Document documento = new Document("$and", Arrays.asList(
                new Document("nombre", u.getNombre()),
                new Document("password", u.getPassword())));

        FindIterable findIterable = db.getCollection(COLECCIONL)
                .find(documento);
        Iterator<Document> iterator = findIterable.iterator();
        if(iterator.hasNext()){
            return true;
        }
        return false;
    }


    //CARRITO
    public List<DetallePedido> getListaCompra(String nombre_cliente) throws ParseException {
        FindIterable<Document> findIterable = db.getCollection(COLECCIONCARRITO).find(new Document("comprador", nombre_cliente));
        return getListaCompra(findIterable);
    }

    private List<DetallePedido> getListaCompra(FindIterable<Document> findIterable) throws ParseException {
        List<DetallePedido>listaCarrito = new ArrayList<>();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document documento = iterator.next();
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(documento.getObjectId("_id"));
            detallePedido.setDestinatario_compra(documento.getString("comprador"));
            detallePedido.setNombre_producto(documento.getString("producto"));
            detallePedido.setFecha_factura(Util.parseFecha(documento.getString("fecha_emision")));
            detallePedido.setPrecio(Float.parseFloat(documento.getString("precio_producto")));
            listaCarrito.add(detallePedido);
        }
        return listaCarrito;
    }


    public void addProductoCesta(DetallePedido detallePedido){
        Document documento = new Document()
                .append("comprador", detallePedido.getDestinatario_compra())
                .append("fecha_emision", Util.parseFecha(detallePedido.getFecha_factura()))
                .append("producto", (detallePedido.getNombre_producto()))
                .append("precio_producto", String.valueOf(detallePedido.getPrecio()));
        db.getCollection(COLECCIONCARRITO).insertOne(documento);
    }


    public void updateCarrito(DetallePedido detallePedido){
        db.getCollection(COLECCIONCARRITO).replaceOne(new Document("_id", detallePedido.getId()), new Document()
                .append("comprador", detallePedido.getDestinatario_compra())
                .append("fecha_emision", Util.parseFecha(detallePedido.getFecha_factura()))
                .append("producto", (detallePedido.getNombre_producto()))
                .append("precio_producto", String.valueOf(detallePedido.getPrecio())));
    }


    public DetallePedido getElementoCarrito(String nombre_producto) throws ParseException {
        FindIterable<Document>findIterable = db.getCollection(COLECCIONCARRITO).find(new Document("producto", nombre_producto));
        Document document = findIterable.first();
        return getElementoCarrito(document);
    }

    public DetallePedido getElementoCarrito(Document documento) throws ParseException {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setId(documento.getObjectId("_id"));
        detallePedido.setDestinatario_compra(documento.getString("comprador"));
        detallePedido.setNombre_producto(documento.getString("producto"));
        detallePedido.setFecha_factura(Util.parseFecha(documento.getString("fecha_emision")));
        detallePedido.setPrecio(Float.parseFloat(documento.getString("precio_producto")));
        return detallePedido;
    }

    //hacer que se devuelva en funcion al nombre y nombre de producto
    public void deleteElementoCarrito(String nombre_producto){
        db.getCollection(COLECCIONCARRITO).deleteMany(new Document("producto",nombre_producto));
    }

    //HACER QUE SE ELIMINEN LAS FACTURAS POR ID DE CLIENTE
    public void deleteCarrito(String nombre_cliente) {
        db.getCollection(COLECCIONCARRITO).deleteMany(new Document("comprador", nombre_cliente));
    }

    //BUSQUEDAS COMPLEJAS
    public List<Cliente> buscarCliente(String nombre, String tipo_cliente) throws ParseException {

        Document documento = new Document("$and", Arrays.asList(
                new Document("nombre", nombre),
                new Document("tipo_cliente", tipo_cliente)));

        FindIterable findIterable = db.getCollection(COLECCIONC)
                .find(documento);
        return getListaClientes(findIterable);
    }

    public List<Producto> buscarProducto(String nombre,String descuento) throws ParseException {

        Document documento = new Document("$and", Arrays.asList(
                new Document("nombre", nombre),
                new Document("descuento", descuento)));

        FindIterable findIterable = db.getCollection(COLECCIONP)
                .find(documento);
        return getListaProductos(findIterable);
    }

    public List<Factura> buscarFactura(String nombre_comprador,String domicilio) throws ParseException {

        Document documento = new Document("$and", Arrays.asList(
                new Document("cliente", nombre_comprador),
                new Document("domicilio", domicilio)));

        FindIterable findIterable = db.getCollection(COLECCIONF)
                .find(documento);
        return getListaFacturas(findIterable);
    }

}

package com.ainochu.gestor_facturas.util;

import com.ainochu.gestor_facturas.MongoDB;
import com.ainochu.gestor_facturas.base.Cliente;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ainoa on 05/11/2015.
 */

public class XML {


    private MongoDB db;
    private List<Cliente> listaClientes;

    public XML(MongoDB db) {
        this.db = db;
    }

    /**
     * Metodo encargado de generar el archivo XML.Para ello creamos:
     * -Un nodo central: Articulos
     * -Un subnodo que engloba cada articulo: Articulo
     * -Dentro de articulos, tenemos: id, nombre, cantidad,precio.
     */

    public void escribirXMLCliente() {
        try {
            listaClientes = db.getClientes();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            Element raiz = documento.createElement("Clientes");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoCliente = null, nodoDatos = null;
            Text texto = null;
            for (Cliente cliente : listaClientes) {
                nodoCliente = documento.createElement("Cliente");
                raiz.appendChild(nodoCliente);

                nodoDatos = documento.createElement("id_cliente");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(cliente.getId()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("nombre");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("apellidos");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getApellidos());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("fecha_nacimiento");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(cliente.getFecha_nacimiento()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("domicilio");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getDomicilio());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tipo_cliente");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getTipo_cliente());
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File(System.getProperty("user.home") + File.separator + "archivoClientesMongo.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }

    }
}
    /*
    public void escribirXMLClientes(ArrayList<Cliente> listaClientes) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);

            Element raiz = documento.createElement("Clientes");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoCliente = null, nodoDatos = null;
            Text texto = null;
            for (Cliente cliente : listaClientes) {
                nodoCliente = documento.createElement("Cliente");
                raiz.appendChild(nodoCliente);

                nodoDatos = documento.createElement("id");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(Integer.toString(cliente.getId_cliente()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("nombre");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("apellidos");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getApellidos());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("fecha_nacimiento");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getFechaNacimiento().toString());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("domicilio");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getDireccion());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("tipo_cliente");
                nodoCliente.appendChild(nodoDatos);

                texto = documento.createTextNode(cliente.getTipo_Cliente());
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File(System.getProperty("user.home")+File.separator+"archivoCliente.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }
    public void escribirXMLFacturas(ArrayList<Factura> listaFacturas) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);

            Element raiz = documento.createElement("Facturas");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoFactura = null, nodoDatos = null;
            Text texto = null;
            for (Factura factura : listaFacturas) {
                nodoFactura = documento.createElement("Factura");
                raiz.appendChild(nodoFactura);

                nodoDatos = documento.createElement("id");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(Integer.toString(factura.getIdFactura()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("domicilio");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(factura.getDomicilioFactura());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("fecha_factura");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(factura.getFechaFactura().toString());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("destinatario_factura");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(factura.getDestinatarioFactura());
                nodoDatos.appendChild(texto);

            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File(System.getProperty("user.home")+File.separator+"archivoFactura.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }
    public void escribirXMLCarrito(ArrayList<Carrito> listaCarritos) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);

            Element raiz = documento.createElement("Compras");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoFactura = null, nodoDatos = null;
            Text texto = null;
            for (Carrito carrito : listaCarritos) {
                nodoFactura = documento.createElement("Carrito");
                raiz.appendChild(nodoFactura);

                nodoDatos = documento.createElement("id_articulo");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(Integer.toString(carrito.getIdArticulo()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("id_factura");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(Integer.toString(carrito.getIdFactura()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("total_factura");
                nodoFactura.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(carrito.getTotal()));
                nodoDatos.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File(System.getProperty("user.home")+File.separator+"archivoCarrito.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }
    /**
     * Metodo encargado de importar el archivo XML seleccionado anteriormente
     * de JFileChooser.
     * Recorremos el fichero y guardamos cada articulo en el array. Por ultimo lo guardamos
     * en el fichero.
     */

    /*
    public ArrayList<Articulo> leerFicheroArticulosXML(ArrayList<Articulo>listaArticulos) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(RUTAXMLPRODUCTOS);
            documento = builder.parse(file);
            // Recorre cada uno de los nodos 'Articulo'
            NodeList articulos = documento.getElementsByTagName("Articulo");
            for (int i = 0; i < articulos.getLength(); i++) {
                Articulo a1 = new Articulo();
                Node articulo = articulos.item(i);
                Element elemento = (Element) articulo;
                a1.setDescripcion(elemento.getElementsByTagName("id_articulo").item(0).
                        getChildNodes().item(0).getNodeValue());
                a1.setNombreArticulo(elemento.getElementsByTagName("nombre_articulo").item(0).
                        getChildNodes().item(0).getNodeValue());
                a1.setCantidadArticulos(Integer.parseInt(elemento.getElementsByTagName("cantidad_articulos").item(0).
                        getChildNodes().item(0).getNodeValue()));
                a1.setPrecioArticulo(Float.parseFloat(elemento.getElementsByTagName("precio_articulo").item(0).
                        getChildNodes().item(0).getNodeValue()));
                a1.setDescuentoArticulo(Boolean.parseBoolean(elemento.getElementsByTagName("Descuento_articulo").item(0).
                        getChildNodes().item(0).getNodeValue()));
                listaArticulos.add(a1);

            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        }
        return listaArticulos;
    }
    public ArrayList<Cliente> leerFicheroClientesXML(ArrayList<Cliente>listaClientes) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(RUTAXMLCLIENTES);
            documento = builder.parse(file);
            // Recorre cada uno de los nodos 'Articulo'
            NodeList clientes = documento.getElementsByTagName("Cliente");
            for (int i = 0; i < clientes.getLength(); i++) {
                Cliente c1 = new Cliente();
                Node cliente = clientes.item(i);
                Element elemento = (Element) cliente;
                c1.setId_cliente(Integer.parseInt(elemento.getElementsByTagName("id").item(0).
                        getChildNodes().item(0).getNodeValue()));
                c1.setNombre(elemento.getElementsByTagName("nombre").item(0).
                        getChildNodes().item(0).getNodeValue());
                c1.setApellidos(elemento.getElementsByTagName("apellidos").item(0).
                        getChildNodes().item(0).getNodeValue());
                c1.setFechaNacimiento(Date.valueOf(elemento.getElementsByTagName("fecha_nacimiento").item(0).
                        getChildNodes().item(0).getNodeValue()));
                c1.setDireccion(elemento.getElementsByTagName("domicilio").item(0).
                        getChildNodes().item(0).getNodeValue());
                c1.setTipo_Cliente(elemento.getElementsByTagName("tipo_cliente").item(0).
                        getChildNodes().item(0).getNodeValue());
                listaClientes.add(c1);
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        }
        return listaClientes;
    }

    */




package proyecto.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class TiendaDeMates {

    private String nombre;
    private String razonSocial;
    private int direccion;
    private Date fechaDeCreacion;
    private Date fechaDeModificacion;
    private Administrador administrador;
    private ArrayList<Pedido> ventas;
    private ArrayList<Producto> stock;
    private ArrayList<Pedido> pedidos;
    private Iterator<Producto> itrProductos;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Date getFechaDeModificacion() {
        return fechaDeModificacion;
    }

    public void setFechaDeModificacion(Date fechaDeModificacion) {
        this.fechaDeModificacion = fechaDeModificacion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Pedido> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Pedido> ventas) {
        this.ventas = ventas;
    }

    public ArrayList<Producto> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Producto> stock) {
        this.stock = stock;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    //Metodos
    public void actualizarFechaDeModificacion() {
        
    }
    
    public Producto obtenerProducto(int id) {
        itrProductos = stock.iterator();
        Producto productoObtenido = null;
        while (itrProductos.hasNext()) {
            Producto p = itrProductos.next();
            if (p.getId() == id) {
                productoObtenido = p;
            }
        }
        return productoObtenido;
    }
    
    public void agregarProducto(Producto p) {
        stock.add(p);
    }
    
    ////////Colocar excepciones para el caso donde el producto no existe
    public void eliminarProducto(int id) {
        stock.remove(obtenerProducto(id));
    }
    
    public void actualizarStock(int id, int nuevoStock) {
        Producto p = obtenerProducto(id);
        if (p != null) {
            p.setStock(nuevoStock);
        }
    }
    
    public void actualizarPrecio(int id, double nuevoPrecio) {
        Producto p = obtenerProducto(id);
        if (p != null) {
            p.setPrecio(nuevoPrecio);
        }
    }
    
    //REVISAR SI ES NECESARIO
    //EN CASO DE SERLO, SACAR obtenerProducto() Y
    //USAR buscar() de la interfaz??
}

package proyecto.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Hereda de EntidadBase
public class Pedido extends EntidadBase {
    
    private Usuario usuario; 
    private List<Producto> productos;
    private Date fechaPedido;
    private double total;
    private String estado; 

    public Pedido(int id, Usuario usuario, List<Producto> productos, double total, String estado) {
        super(id); 
        this.usuario = usuario;
        this.productos = new ArrayList<>(productos); 
        this.fechaPedido = new Date(); 
        this.total = total;
        this.estado = estado;
    }

    public Pedido(Usuario usuario, List<Producto> productos, double total, String estado) {
        this(0, usuario, productos, total, estado);
    }
    
    // Requisito: Encapsulamiento
    public Usuario getUsuario() { 
        return usuario; 
    }
    public List<Producto> getProductos() { 
        return productos; 
    }
    public double getTotal() { 
        return total; 
    }
    public String getEstado() { 
        return estado; 
    }
}
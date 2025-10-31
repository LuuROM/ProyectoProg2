package proyecto.modelos;

import java.util.ArrayList;
import java.util.List;

public class CarritoDeCompra {
    
    // Atributo privado y encapsulado
    private List<Producto> productos = new ArrayList<>();
    
    // Constructor vacío (ya que es creado y contenido por el Cliente)
    public CarritoDeCompra() {
        // La lista se inicializa arriba
    }
    
    // Metodos
    
    // El nombre del atributo cambió a 'productos' para mayor claridad
    public List<Producto> getProductos() {
        return productos;
    }

    // No se recomienda un setter completo, pero si lo deseas, debe usar List
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void eliminarProducto(Producto p) {
        productos.remove(p);
    }
    
    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        System.out.println("\n--- Productos en el Carrito ---");
        for (Producto p : productos) {
             // Polimorfismo: Llama al método de la clase Producto
             p.mostrarinfo(); 
        }
        System.out.println("---------------------------------");
    }
    
    public double calcularTotal() {
        double total = 0;
        // 🟢 Se corrige el bucle para la suma correcta
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }
    
    // Se renombra para mayor claridad
    public void vaciarCarrito() {
        productos.clear();
        System.out.println("Carrito vaciado.");
    }
    
    public boolean estaVacio() {
        return productos.isEmpty();
    }
}
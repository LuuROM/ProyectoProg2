
package proyecto.modelos;

import java.util.Iterator;

public class Cliente extends Usuario {
    
    private String direccion, metodoDePago;
    private int telefono, dni;
    private CarritoDeCompra carrito;
    //Constructor
      public Cliente(int telefono, String direccion, String metodoDePago, String nombre, String apellido,String email, int dni, int id) {
        super(nombre, apellido, email, id);
        this.direccion = direccion;
        this.metodoDePago = metodoDePago;
        this.telefono = telefono;
        this.dni = dni;
        this.carrito = carrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public CarritoDeCompra getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDeCompra carrito) {
        this.carrito = carrito;
    }
     //Metodos
    public void agregarProducto(Producto p) {
        carrito.agregarProducto(p);
    }

    public void eliminarProducto(Producto p) {
      carrito.eliminarProducto(p);
    }
}

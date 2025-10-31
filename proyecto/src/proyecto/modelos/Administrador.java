package proyecto.modelos;


public class Administrador extends Usuario {
     private String contrasenia;
    private TiendaDeMates tienda; 

    //Constructor

    public Administrador(String contrasenia, TiendaDeMates tienda, String nombre, String apellido, String email, int id) {
        super(nombre, apellido, email, id);
        this.contrasenia = contrasenia;
        this.tienda = tienda;
    }
    
 
    
    //Metodos
    public void agregarProducto(String tipo, String material, String color, int id, int stock, double precio) {
        tienda.agregarProducto(new Producto(tipo, material, color, id, stock, precio));
    }
    
    ////////Colocar excepciones para el caso donde el producto no existe
    public void eliminarProducto(int id) {
        tienda.eliminarProducto(id);
    }
    
    public void actualizarStock(int id, int nuevoStock) {
        tienda.actualizarStock(id, nuevoStock);
    }
    
    public void actualizarPrecio(int id, double nuevoPrecio) {
        tienda.actualizarPrecio(id, nuevoPrecio);
    }
}

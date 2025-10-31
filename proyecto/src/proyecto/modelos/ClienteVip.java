
package proyecto.modelos;


public class ClienteVip extends Cliente { ///Hacer que herede de Client

    private static final double DESCUENTO = 0.20;

    // Se asume que Usuario ahora requiere 'id' (int)

    public ClienteVip(int telefono, String direccion, String metodoDePago, String nombre, String apellido, String email, int dni, int id) {
        super(telefono, direccion, metodoDePago, nombre, apellido, email, dni, id);
    }
    
    // Getter y setter heredados de Cliente
    
    //Calcula el total de la compra con el 20% de descuento aplicado.
    public double calcularTotalConDescuento() {
        double totalBase = this.getCarrito().calcularTotal();
        double descuentoMonto = totalBase * DESCUENTO;
        return totalBase - descuentoMonto;
    }
}

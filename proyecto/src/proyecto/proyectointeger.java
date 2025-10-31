package proyecto;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import proyecto.dao.ProductoDAO;
import proyecto.Excepciones.DatosInvalidosException;
import proyecto.modelos.*;
import proyecto.ConexionDB;
public class proyectointeger {
    private static final int ADMIN_PASSWORD = 1234;
    private static final Scanner sc = new Scanner(System.in);
    private static final ProductoDAO productoDao = new ProductoDAO();
    
    public static void main(String[] args) {
        // TODO code application logic here
        ConexionDB.getinstancia();
        int opcion = 0 ;
          try {
            do{
                mostrarmenu();
                try {
                    opcion=sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            System.out.println("Saliendo del sistema.");
                            break;
                        default:
                            System.out.println("Error elija una opcion viable");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error entrada no valida");
                }
            }while(opcion!=4);
        } catch (Exception e) {
              System.out.println("Error inesperado" +e.getMessage());
        }
    }
 public static void mostrarmenu(){
     //Agregar opcion por si el usuario ya existe, buscar por dni
     System.out.println("==============================");
     System.out.println("Bienvenidos a Tienda Lunic");
     System.out.println("1. Acceder como cliente");
     System.out.println("2. Acceder como ClienteVip");
     System.out.println("3. Acceder como Administrador");
     System.out.println("4. Salir");
     System.out.println("Elija una opcion;");
 }   
 private static void iniciarSesionCliente(){
     System.out.println("Ingrese los datos del Cliente");
     try {
         System.out.println("Ingrese el Nombre;");
         String nombre = sc.nextLine();
         System.out.println("Ingrese el Apellido");
         String apellido= sc.nextLine();
         System.out.println("Ingrese el Email");
         String email = sc.nextLine();
         System.out.println("Ingrese su Direccion");
         String direccion = sc.nextLine();
         System.out.println("Ingrese solo numeros enteros para el telefono y Dni");
         System.out.println("Ingrese su Telefono");
         int telefono = sc.nextInt();
         System.out.println("Ingrese su Dni;");
         int Dni = sc.nextInt();
         Usuario usuario;
         int idTemporal=0;
        
     } catch (InputMismatchException e) {
         System.out.println("Error de ingreso por favor ingrese numeros validos");
         sc.nextLine();
     }catch (Exception e) {
         System.out.println("Error"+ e.getMessage());
     }
 }
 private static void Menucompra(Usuario usuario){
     int opc=0;
     CarritoDeCompra carrito;
     if (usuario instanceof Cliente) {
         //Con instanceof decimos si el usuario es una instancia de la clase Cliente 
         //o de la clase clientevip que heredan de Usuario
         Cliente cliente_normal = (Cliente) usuario;
         carrito= cliente_normal.getCarrito();
     }else{
         ClienteVip clientevip=(ClienteVip) usuario;
         carrito= clientevip.getCarrito();
     }
    do{
        System.out.println("=====Menu de Compra para "+ usuario.getNombre());
        System.out.println("1. Ver catalogo");
        System.out.println("2. Agregar producto al carrito por ID");
        System.out.println("3. Ver carrito y total a pagar");
        System.out.println("4. Finalizar compra y pagar");
        System.out.println("5. Volver al menu principal");
        System.out.println("Elija su opcion");
        try {
            opc = sc.nextInt();
            sc.nextLine();
            switch (opc) {
                case 1:
                    //catalago;
                    break;
                case 2:
                    //agregarproducto(carrito);
                    break;
                    
                case 3:
                    //Mostrarinfo
                    break;
                case 4:
                    //Finalizarcompra(usuario)
                    break;
                case 5:
                    System.out.println("Volviendo al menu");
                    break;
                default:
                    System.out.println("error al seleccionar una opcion");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error debe de ingresar un nmro para la operacion.");
            sc.nextLine();
        } catch(Exception e){ 
            System.out.println("Error: " + e.getMessage());
        }//Falla al poner otro catch
    }while(opc!=5) ;
 }
    private static void catalogo() throws Exception{
        System.out.println("Catalogo de productos disponibles");
        List<Producto> productos = productoDao.buscarTodo();
        if(productos.isEmpty()){
            System.out.println("El catalogo esta vacio , el admin debe de agregarlos");
            return;
        }
        for(Producto p : productos){
            p.mostrarinfo();
        }
    }
    private static void agregarProductoAlCarrito(CarritoDeCompra carrito) throws Exception{
        System.out.println("ingrese el ID del producto a agregar;");
        int id = sc.nextInt();
        //Agregar un try para capturar un error de letra
        Producto p = productoDao.buscar(id);
        if (p != null && p.getStock() > 0) { //Se crea una copia para evitar descontar el stock de la instancia
            Producto productoparaCarrito= new Producto(p.getMaterial(),p.getColor(),p.getNombre(),1,p.getId(),p.getPrecio());
            System.out.println("El producto "+ p.getNombre()+"/Fue agregado correctamente.");
            
        }else if (p ==null){
            System.out.println("El producto con id "+ id +"No se encuentra.");
        } else {
            System.out.println("Stock insuficiente para el producto"+ p.getNombre());
        }
            
    }
   private static void finalizarCompra(Usuario usuario) throws Exception{
       CarritoDeCompra carrito= null;
        if (usuario instanceof ClienteVip) {
         //Con instanceof decimos si el usuario es una instancia de la clase Cliente 
         //o de la clase clientevip que heredan de Usuario
        carrito = ((ClienteVip) usuario).getCarrito();
     }else if (usuario instanceof Cliente){
         carrito=((Cliente) usuario).getCarrito();
     }
        if(carrito.estaVacio()){
            System.out.println("El carrito esta vacio no se puede finalizar la compra");
            return;
        }
        double totalbase= carrito.calcularTotal();
        double totalfinal= totalbase;
       String mensajeDescuento= "";
       String metodopago= ((Cliente) usuario).getMetodoDePago();
       if (usuario instanceof ClienteVip){
           totalfinal=((ClienteVip) usuario).calcularTotalConDescuento();
           mensajeDescuento="%20 de descuento aplicado";
       }
       System.out.println("===================================");
       System.out.println("Pago de pedido de "+ usuario.getNombre().toUpperCase());
       System.out.println("===================================");
       System.out.println("total base; $ "+ String.format("%.2f",totalbase));
       System.out.println("total a pagar ; $"+ String.format("%.2f", totalfinal)+mensajeDescuento);
       System.out.println("Metodo de pago ;"+ metodopago);
       
       Pedido nuevoPedido = new Pedido(usuario, carrito.getProductos(),totalfinal, "Pagado y Pendiente de Envío");
       
       System.out.println("Pago confirmado correctamente !!!!!");
       System.out.println("Su pedido a sido procesado."+ nuevoPedido.getId());
       System.out.println("Se espera la entrega en 3 dias habiles");
       
       carrito.vaciarCarrito();
   }
   private static void iniciarsesionAdministrador(){
       System.out.println("Accediste como Administrador");
       System.out.println("Ingrese la contrasenia;");
       //Encerrar todo en un do-while para que tenga 3 intentos
       //sino lo devuelve al menu principal
       try {
           int contrasenia = sc.nextInt();
           sc.nextLine();
           if (contrasenia == ADMIN_PASSWORD){
               System.out.println("Acceso Correcto");
               //MenuAdministrador();
           }else{
               System.out.println("Error no acceso denegado");
           }
       } catch (InputMismatchException e) {
           System.out.println("Error al ingresar la contrasenia por favor que sean numero enteros");
           sc.nextLine();
       }
   }
   private static void menuAdministrador() throws Exception {
       int opc = 0;
       do{
           System.out.println("==Panel de Administrador==");
           System.out.println("1. Modificar stock de un producto");
           System.out.println("2. Volver al menu principal");
           System.out.println("3. Agregar producto");
           System.out.println("4. Eliminar producto");
           System.out.println("5. Modificar precio de un producto");
           System.out.println("Elija una opcion");
           try {
               opc = sc.nextInt();
               sc.nextLine();
               switch (opc) {
                   
                   case 1:
                       //modificarStock();
                       break;
                   case 2:
                       System.out.println("cerrando sesion en modo Admin.");
                       break;
                   default:
                       System.out.println("opcion invalida");
               }
           } catch (InputMismatchException e) {
               System.out.println("Error debe ingresar un numero");
               sc.nextLine();
               opc = 0;
           }
       }while(opc !=2);
       
   }
  private static void modificarStock(){
       try {
           System.out.println("Id del producto que quiere modificar");
           int id = sc.nextInt();
           System.out.println("nueva Cantidad de Stock");
           int nuuevaCantidad= sc.nextInt();
           sc.nextLine();
           productoDao.modificarStock(id, nuuevaCantidad);
           
      } catch (InputMismatchException e) {
           System.out.println("Error el id y la cantidad de Stock deben ser nmros enteros");
      }catch (DatosInvalidosException e){
           System.out.println("Error de Validacion "+ e.getMessage());
      } catch (Exception ex) {
           System.out.println("Error de Db"+ ex.getMessage());
        }
    
       }
}
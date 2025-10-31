package proyecto.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import proyecto.ConexionDB;
import proyecto.Excepciones.DatosInvalidosException;
import proyecto.interfaces.ICRUD;
import proyecto.modelos.Producto;
// Implementa el contrato ICRUD, especializado en Producto
public class ProductoDAO implements ICRUD<Producto> {

    private Connection conexion;
    private static final String TABLE_NAME = "Productos";

    public ProductoDAO() {
        // Obtiene la única conexión activa (Singleton)
        this.conexion = ConexionDB.getinstancia().getCon();
    }
    
    // Método traductor: Convierte una fila de la DB (ResultSet) a un objeto Producto de Java.
    //ResultSet es un contenedor de datos java es como una tabla virtual que contiene todas las filas de datos
    //que devolvio despues de ejecutar una consulta SELECT  y rs es su abreviatura
    private Producto mapearProducto(ResultSet rs) throws SQLException {
        // El orden de los argumentos debe coincidir con el constructor de Producto
        return new Producto(
            rs.getString("material"), 
            rs.getString("color"), 
            rs.getString("nombre"), 
            rs.getInt("stock"), 
            rs.getInt("id"), 
            rs.getDouble("precio")
        );
    }
    // Métodos CRUD básicos (Plantillas)
    
    @Override
    public void agregar(Producto producto) throws DatosInvalidosException, Exception { 
        // Lógica SQL: INSERT INTO Productos (nombre, material, color, precio, stock) VALUES (?, ?, ?, ?, ?)
        // Se usaría PreparedStatement para enviar el objeto a la DB.
        System.out.println("DAO: Producto agregado (Implementación pendiente).");
    }
    
    @Override
    public void modificar(Producto producto) throws DatosInvalidosException, Exception { 
        // Lógica SQL: UPDATE Productos SET nombre=?, ... WHERE id=?
        System.out.println("DAO: Producto modificado (Implementación pendiente).");
    }
    
    @Override
    public void eliminar(int id) throws DatosInvalidosException, Exception { 
        // Lógica SQL: DELETE FROM Productos WHERE id=?
        System.out.println("DAO: Producto eliminado (Implementación pendiente).");
    }
    // Métodos de Sobrecarga y Lectura
    @Override
    public Producto buscar(int id) throws DatosInvalidosException, Exception { 
        // Lógica SQL: SELECT * FROM Productos WHERE id = ?
        // Devuelve el objeto mapeado o null si no existe.
        return null; 
    }

    @Override
    public List<Producto> buscar(String nombre) throws Exception { 
        // Lógica SQL: SELECT * FROM Productos WHERE nombre LIKE ? (Requisito: Filtrado)
        return new ArrayList<>(); 
    }
    
    @Override
    public List<Producto> buscarTodo() throws Exception {
        // Lógica SQL: SELECT * FROM Productos
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;//pide todas las columnas de la tabla producto
        try (Statement stmt = conexion.createStatement();//crea un objeto para ejecutar la consulta
             ResultSet rs = stmt.executeQuery(sql)) {//Ejecuta la consulta y le pide a la base que devuelva un resultado
            while (rs.next()) {//se inicia un bucle para recorrer cada fila y rs.next() mueve el cursor a la siguientee fila
                productos.add(mapearProducto(rs));//a mapear se le pasa la fila buscada para convertir la fila de sql a un obj Producto ,
                //luego la agrega ese obj recien creado a la lista producto
            }
        } catch (SQLException e) {
            throw new Exception("Error al buscar todos los productos: " + e.getMessage());
        }
        return productos;
    }
    // Lógica Específica del Administrador
    
    // Método que actualiza el stock (función clave del Admin)
    public void modificarStock(int idProducto, int nuevaCantidad) throws DatosInvalidosException, Exception {
        String sql = "UPDATE " + TABLE_NAME + " SET stock = ? WHERE id = ?";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, nuevaCantidad);
            st.setInt(2, idProducto);
            
            if (st.executeUpdate() == 0) {//executeUpdate() se utuiliza para modificar el esquema o los datos dentro de la base de datos
                 // Lanza la excepción personalizada si el ID no existe en la DB
                 throw new DatosInvalidosException("No se encontró ningún producto con ID " + idProducto);
            }
            System.out.println("Stock del producto ID " + idProducto + " actualizado a " + nuevaCantidad + ".");
        } catch (SQLException e) {
            throw new Exception("Error al modificar stock: " + e.getMessage());
        }
    }
}
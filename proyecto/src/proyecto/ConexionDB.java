
package proyecto;
import java.sql.*;


public class ConexionDB {
   private static final String conexion_base_Datos = "jdbc:sqlite:TiendaLunic.db";
   private static ConexionDB instancia ;
   private Connection con;

    public ConexionDB() {
        try {
            con = DriverManager.getConnection(conexion_base_Datos);
            crearTablas();
            System.out.println("Conexion Exitosa por sampe");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    public static ConexionDB getinstancia() {
        if(instancia == null){
            instancia = new ConexionDB();   
        } 
            return instancia;
    }
   public void closeConnection(){
       if(con != null){
           try {
               con.close();
               con = null;
               instancia= null;
               System.out.println("Conexión cerrada.");
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }
   }
   private void crearTablas() throws SQLException {
       try (Statement st = con.createStatement()) {
            // Tabla Productos (incluye stock)
            String sql_productos = "CREATE TABLE IF NOT EXISTS Productos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL,"
                    + "material TEXT,"
                    + "color TEXT,"
                    + "precio REAL NOT NULL,"
                    + "stock INTEGER NOT NULL,"
                    + "tipo TEXT"
                    + ");";
            st.execute(sql_productos);
            
            //Tabla Pedidos
             String sql_pedidos = "CREATE TABLE IF NOT EXISTS Pedidos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "usuario_email TEXT NOT NULL," 
                    + "fecha_pedido TEXT NOT NULL,"
                    + "total REAL NOT NULL,"
                    + "estado TEXT NOT NULL"
                    + "producto_id INT NOT NULL references Productos (id)" //Revisar tambien
                    + "usuario_id INT NOT NULL references Usuarios (id)" //Revisar pero deberia referenciar
                    + ");";
            st.execute(sql_pedidos);
            //Tabla Usuarios (Para UsuarioDAO)
         String sql_usuarios = "CREATE TABLE IF NOT EXISTS Usuarios ("
                 + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + "nombre TEXT NOT NULL,"
                 + "apellido TEXT,"
                 + "email TEXT UNIQUE NOT NULL,"
                 + "direccion TEXT,"
                 + "telefono INTEGER,"
                 + "dni INTEGER,"
                 + "metodo_pago TEXT"
                 + ");";
         st.execute(sql_usuarios);
    }
        }
   }

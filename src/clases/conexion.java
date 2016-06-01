package clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    Connection comunicacion = null;
  
    public Connection conectando() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.comunicacion = ((Connection) DriverManager.getConnection("jdbc:mysql://192.168.1.25/notas_2016", "lista", "lista"));
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return this.comunicacion;
    }
    public void cerrarConexion() {
        try {
            this.comunicacion.close();
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
    }
    
}

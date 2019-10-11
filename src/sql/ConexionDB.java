package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    public ConexionDB() {
       // super();
    }
    private static String errorConexion = null;
    private static Connection  cnx = null;
       public static Connection hacerConexion(String servidorIP, String puerto, String nombreDB, String usuario, String password) {
          if (cnx == null) {
             try { 
                Class.forName("com.mysql.jdbc.Driver"); //cargar el conector mySQL en las librerias Java
                cnx = DriverManager.getConnection("jdbc:mysql://" + servidorIP + ":" + puerto + "/" + nombreDB, usuario, password);
                errorConexion = "Conexión realizada correctamente con el cliente MySQL. {:-){ ";
             } catch (SQLException ex) {
                 errorConexion = "Error 1: revisar el servidor apache, cliente MySQL, credenciales o la base de datos. " + ex.getMessage();
             } catch (ClassNotFoundException ex) {
                errorConexion = "Error 2: no se ha cargado el conector MySQL " + ex.getMessage();
             } catch (Exception ex) {
                errorConexion = "Error 3: error no determinado, intentar más tarde. " + ex.getMessage();
             }
          }else {
              errorConexion = "Ya existe una conexión previa con el servidor MySQL. ";
          }
          return cnx;
       }
       public static String desconectar() {
          if (cnx != null) {
            try {
                cnx.close();
            } catch (SQLException e) {
                return "Error de desconexión: " + e.getMessage();
            }
            cnx = null; //el recolector de basura de la JVM limpiará más tarde el objeto no referenciado
            errorConexion = "Se ha desconectado del servidor. ";
            return "Desconexión realizada. ";
          }else{
            return "No existe una conexión previa para desconectar! ";       
          }
       }
       public static String ultimoError() {
           return errorConexion;
       }

    public static Connection getCnx() {
        return cnx;
    }
}

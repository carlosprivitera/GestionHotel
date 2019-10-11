package sql;

import java.sql.SQLException;
import java.sql.Statement;

public class Alta {
    public Alta() {
        super();
    }
    public String altaRegistro(String tabla, String[] campos, String[] datos){
        Statement st;
        int nReg=0;
        String camposTemp="", datosTemp="";
        for(int c=0;c<campos.length;c++){
            if(c<campos.length-1){
                camposTemp = camposTemp + campos[c] + ",";
            }else{
                camposTemp = camposTemp + campos[c];
            }
            
        }
        for(int c=0;c<datos.length;c++){
            if(c<datos.length-1){
              datosTemp = datosTemp + "'" + datos[c] + "',";
            }else{
              datosTemp = datosTemp + "'" + datos[c] + "'";  
            }
        }
        String sql = "insert into " + tabla + "( " + camposTemp + ") values(" + datosTemp + ")";
        try {
            st = ConexionDB.getCnx().prepareStatement(sql);
            nReg = st.executeUpdate(sql);
        }catch (SQLException e1) {
            return "Error: " + e1.getMessage() + " - " + sql;
        }catch(Exception e2) {
            return "Error no determinado al enviar la siguiente sentencia SQL al servidor MySQL: " + e2.getMessage() + " - " + sql;   
        }
        return "" + nReg; //regresa "1" si todo esta bien
    }
}

package sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Buscar {
    private ArrayList<String[]> registrosDB = new ArrayList<String[]>();
    private ArrayList<String> nombreColumnas = new ArrayList<String>();
    public Buscar() {
        super();
    }
    private Statement s=null;
    private ResultSet rs;
    public ArrayList<String[]> buscarReg(String tabla) {
  //      String[] x = {"1", "2", "3", "4"};
  //      registrosDB.add(x);
  //      String[] x1 = {"1a", "2a", "3a", "4a"};
  //      registrosDB.add(x1);
  //      String[] regX = {" "," "," "," "," "," "," "," "," "," "};
        registrosDB.clear();
        nombreColumnas.clear();
        try {
            s = ConexionDB.getCnx().createStatement();
            rs = s.executeQuery ("select * from " + tabla);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Un error en la consulta SQL: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            return registrosDB;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Un error no determinado en la consulta SQL: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);    
            return registrosDB;
        }
        int nReg = 0;
        boolean quedanReg=false;
        try {
          quedanReg = rs.first();
          for(int c=1;c<rs.getMetaData().getColumnCount()+1;c++){
            nombreColumnas.add(rs.getMetaData().getColumnName(c));
          }             
        } catch (Exception e) {
            quedanReg=false;
        } 
        if(quedanReg==true) {
          do {
            try {
              if(quedanReg==true) {
                  nReg = nReg + 1;
                  String[] regX = {" "," "," "," "," "," "," "," "," "," "};
                  for(int c=1;c<rs.getMetaData().getColumnCount()+1;c++){
                      regX[c-1] = rs.getString(c);
                  }
                  registrosDB.add(regX);
              }
              quedanReg = rs.next();  
            } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, "Un error en los datos: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
              quedanReg=false;
            } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Un error no determinado en los datos: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
              quedanReg=false;                  
            }
          }while(quedanReg==true);
        }
        //System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3));
        return registrosDB;
    }

    public int nRegBuscados() {
        return registrosDB.size();
    }

    public ArrayList<String> getNombreColumnas() {
        return nombreColumnas;
    }
}

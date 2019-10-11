package vistaServlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import sql.Buscar;

public class Busquedas extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    private String queBuscar = "";
    private String nombreTabla = "";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            queBuscar = request.getParameter("queBuscar");
            nombreTabla = request.getParameter("nombreTabla");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Busquedas</title></head>");
        out.println("<body>");
        out.println("<center>" + resultadoBusqueda() + "</center>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
    private Buscar buscar = new Buscar();
    private ArrayList<String[]> registrosDB = new ArrayList<String[]>();
    private String salidaHTML="";
    private ArrayList<String> nombreColumnas = new ArrayList<String>();
    private String resultadoBusqueda() {
        registrosDB = buscar.buscarReg(nombreTabla);
        int nReg = registrosDB.size();
        salidaHTML = "<table border='1'><tr>";
        if(nReg>0) {
          nombreColumnas = buscar.getNombreColumnas();
          int nCol = nombreColumnas.size();
          
          for(int c=0; c<nCol;c++) {
            salidaHTML = salidaHTML + "<td>" + nombreColumnas.get(c) + "</td>";
          }
          salidaHTML = salidaHTML + "</tr>";
          for(int f=0; f<nReg;f++) {
            salidaHTML = salidaHTML + "<tr>";  
            for(int c=0; c<nCol;c++) {
              salidaHTML = salidaHTML + "<td>" + registrosDB.get(f)[c] + "</td>";
            }
            salidaHTML = salidaHTML + "</tr>";  
          }
          salidaHTML = salidaHTML + "</table>";
        }else{
            salidaHTML = "No hay registros en la tabla: " + nombreTabla;
        }
        return salidaHTML;
    }
}

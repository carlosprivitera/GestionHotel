package vistaServlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import sql.ConexionDB;

public class ConectarMySQL extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";


    public void init(ServletConfig config) throws ServletException {
            super.init(config);
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queBotonEnviar = "enviar";
        String servidorIP = "127.0.0.1";
        String puerto = "3306";
        String nombreDB = "hotel";
        String usuario = "usuario";
        String password = "12345678";
        try {
            queBotonEnviar = request.getParameter("enviar");
            servidorIP = request.getParameter("servidorIP");
            puerto = request.getParameter("puerto");
            nombreDB = request.getParameter("nombreDB");
            usuario = request.getParameter("usuario");
            password = request.getParameter("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>ConectarMySQL</title></head>");
        out.println("<body>");
        if(queBotonEnviar.equalsIgnoreCase("EnviarConexion")) {
            out.println("<p>" + form_ActionConexion(servidorIP, puerto, nombreDB, usuario, password) + "</p>");    
        }else{
            out.println("<p>" + form_ActionDesconexion() + "</p>");
        }
        try {
           out.println("<p> Conectado a: " + ConexionDB.getCnx().getCatalog()  + "</p>");
        } catch (SQLException e) {
            out.println("<p> Error SQL: " + e.getMessage() + "</p>");
        } catch (Exception e) {
            out.println("<p> Error no determinado: " + e.getMessage() + "</p>");
        }
        out.println("<p>El servlet Java há recibido un envío GET desde el cliente. Esta es la respuesta desde el servidor.</p>");
        out.println("</body></html>");
        out.close();
    }
    private ConexionDB conexionDB = new ConexionDB(); //no usado, es para marcar la relación entre clases 
    private String form_ActionConexion(String servidorIP, String puerto, String nombreDB, String usuario, String password) {
        //conexionDB.hacerConexion(servidorIP, puerto, nombreDB, usuario, password);
        ConexionDB.hacerConexion(servidorIP, puerto, nombreDB, usuario, password);
        return ConexionDB.ultimoError(); 
    }
    private String form_ActionDesconexion() {
        //return conexionDB.desconectar();
        return ConexionDB.desconectar();
    }
}

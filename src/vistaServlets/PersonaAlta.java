package vistaServlets;

import app.Personas;

import java.awt.event.ActionEvent;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.swing.JOptionPane;

public class PersonaAlta extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    private String dni = "";
    private String nombre = "";
    private String apellido = "";
    private String direccion = "";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        try {
            dni = request.getParameter("dni");
            nombre = request.getParameter("nombre");
            apellido = request.getParameter("apellido");
            direccion = request.getParameter("direccion");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>PersonaAlta</title></head>");
        out.println("<body>");
        
        out.println("<p>" + form_actionPersonasAlta() + "</p>");
        
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
    Personas personas = new Personas();
    private String form_actionPersonasAlta() {
        String error="", respuesta="";
        String DNI = dni;
        if(DNI.trim().length()>7) {
          String[] datos = {this.dni,
                          this.nombre,
                          this.apellido,
                          this.direccion,
                          };
         error = personas.alta(datos); 
        }else{
            error = "El DNI debe tener más dígitos.";
        }
        if(error.equals("1") == true) {
            respuesta = "Registro guardado correctamente. {:-)} ";
        }else{
            respuesta = error;
        }
        return respuesta;
    }

    public Personas getPersonas() {
        return personas;
    }

}

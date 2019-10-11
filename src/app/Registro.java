package app;

import java.util.ArrayList;

public class Registro {
    public Registro() {
        super();
    }
    private ArrayList<String[]> registrosDB = new ArrayList<String[]>(); 
    private String[] Campos = null; 
    private String nombreTabla = null;
    public void altaMapeo(String[] reg) {
        registrosDB.add(reg);
    }
    public void setCampos(String[] Campos) {
        this.Campos = Campos;
    }

    public String[] getCampos() {
        return Campos;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }
}

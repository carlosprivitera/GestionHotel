package app;


import sql.Alta;

public class Personas extends Registro {
    public Personas() {
        super();
        String[] campos = {"DNI", "Nombre","Apellido","Dirección"}; 
        this.setNombreTabla("Personas");
        this.setCampos(campos);
    }
    Alta altaMySQL  = new Alta();
    public String alta(String[] datos) {
        String error = altaMySQL.altaRegistro(this.getNombreTabla(), this.getCampos(), datos);
        if(error.equals("1")){
            altaMapeo(datos);
        }
        return error;
    }

    public String getNombreTabla() {
        return super.getNombreTabla();
    }

    public String[] getCampos() {
        return super.getCampos();
    }

    public void altaMapeo(String[] reg) {
        super.altaMapeo(reg);
    }
}

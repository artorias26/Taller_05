package Model;

public class Tipo2 {

    Integer id;
    String tipo_e;

    public Tipo2(Integer id, String tipo_e) {
        this.id = id;
        this.tipo_e = tipo_e;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_e() {
        return tipo_e;
    }

    public void setTipo_e(String tipo_e) {
        this.tipo_e = tipo_e;
    }
     @Override
    public String toString() {
        return this.getTipo_e();
    }

   
}

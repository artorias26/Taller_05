package Model;

public class TipoModel {

    Integer id;
    String Tipo;

    public TipoModel(Integer id, String Tipo) {
        this.id = id;
        this.Tipo = Tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

  @Override
    public String toString() {
        return this.getTipo();
    }
    
    

}

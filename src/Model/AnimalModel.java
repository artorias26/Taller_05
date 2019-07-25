
package Model;

import java.util.Date;
import javafx.scene.control.DatePicker;


public class AnimalModel {
    
    private static  AnimalModel instance;
    
    Integer codigo_animal,id_tipo,id_tipo_e;
    String nombre,especie,nombre_cuidador;
    Date fecha_ingreso,fecha_nacimiento;

    public AnimalModel(Integer codigo_animal, Integer id_tipo, Integer id_tipo_e, String nombre, String especie, String nombre_cuidador, Date fecha_ingreso, Date fecha_nacimiento) {
        this.codigo_animal = codigo_animal;
        this.id_tipo = id_tipo;
        this.id_tipo_e = id_tipo_e;
        this.nombre = nombre;
        this.especie = especie;
        this.nombre_cuidador = nombre_cuidador;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
     public static AnimalModel getInstance() {
        return instance;
    }

    public static void setInstance(AnimalModel instance) {
        AnimalModel.instance = instance;
    }

    public Integer getCodigo_animal() {
        return codigo_animal;
    }

    public void setCodigo_animal(Integer codigo_animal) {
        this.codigo_animal = codigo_animal;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getId_tipo_e() {
        return id_tipo_e;
    }

    public void setId_tipo_e(Integer id_tipo_e) {
        this.id_tipo_e = id_tipo_e;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getNombre_cuidador() {
        return nombre_cuidador;
    }

    public void setNombre_cuidador(String nombre_cuidador) {
        this.nombre_cuidador = nombre_cuidador;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }
    
    
}

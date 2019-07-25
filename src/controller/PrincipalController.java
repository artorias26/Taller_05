package controller;

import Model.AnimalModel;
import Model.Tipo2;
import Model.TipoModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import config.AlertHelper;
import config.ConexionMySQL;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

public class PrincipalController implements Initializable {

    @FXML
    private JFXTextField txt_numero_codigo;
    @FXML
    private JFXTextField nombre_animal;
    @FXML
    private JFXTextField especie;
    @FXML
    private JFXComboBox tipo;
    @FXML
    private JFXComboBox tipo2;
    @FXML
    private JFXTextField nombre_cuidador;
    @FXML
    private JFXDatePicker fecha_ingreso;
    @FXML
    private JFXDatePicker fecha_nacimiento;
    @FXML
    private TableColumn<AnimalModel, String> col_animal;
    @FXML
    private TableColumn<AnimalModel, String> col_nombre;
    @FXML
    private TableColumn<AnimalModel, String> col_especie;
    @FXML
    private TableColumn<AnimalModel, String> col_nombre_cuidador;
    @FXML
    private TableView<AnimalModel> tabla;
    @FXML
    ObservableList<AnimalModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaTipo();
        listaTipo2();
        listarAnimal();

    }

    private void limpiar() {
        txt_numero_codigo.setText(null);
        nombre_animal.setText(null);
        especie.setText(null);
        nombre_cuidador.setText(null);
        tipo.getSelectionModel().select(0);
        tipo2.getSelectionModel().select(0);
        fecha_ingreso.setValue(null);
        fecha_nacimiento.setValue(null);
    }

    @FXML
    private void btn_Limpiar(ActionEvent event) {
        limpiar();

    }

    public void listaTipo() {
        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();

        String sql = "SELECT * FROM tipo";

        ObservableList<TipoModel> lista = FXCollections.observableArrayList();
        lista.add(new TipoModel(0, "SELECCIONAR"));

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(new TipoModel(rs.getInt("id"), rs.getString("Tipo")));
            }

            tipo.setItems(lista);
            tipo.getSelectionModel().select(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void listaTipo2() {
        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();

        String sql = "SELECT * FROM tipo_e";

        ObservableList<Tipo2> lista = FXCollections.observableArrayList();
        lista.add(new Tipo2(0, "SELECCIONAR"));

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Tipo2(rs.getInt("id"), rs.getString("tipo_e")));
            }

            tipo2.setItems(lista);
            tipo2.getSelectionModel().select(0);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void btn_Guardar(ActionEvent event) {

        LocalDate Fecha = fecha_ingreso.getValue();
        LocalDate FEcha = fecha_nacimiento.getValue();
        TipoModel Tipo = (TipoModel) this.tipo.getSelectionModel().getSelectedItem();
        Tipo2 TIpo = (Tipo2) this.tipo2.getSelectionModel().getSelectedItem();

        if (txt_numero_codigo.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el numero del animal");
            return;
        }

        if (Fecha == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la fecha de ingreso");
            return;
        }

        if (nombre_animal.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el Nombre del animal");
            return;
        }

        if (especie.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la especie");
            return;
        }

        if (FEcha == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la fecha de nacimiento");
            return;
        }

        if (nombre_cuidador.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el nombre del cuidador");
            return;
        }

        if (Tipo.getId() == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor selecciona un Tipo de producto");
            return;
        }

        if (TIpo.getId() == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor selecciona un Tipo de producto");
            return;
        }

        String codigo = txt_numero_codigo.getText();
        String Nombre_animal = nombre_animal.getText();
        String Nombre_cuidador = nombre_cuidador.getText();
        String Especie = especie.getText();

        String Fechar = Fecha.toString();

        String FEchar = FEcha.toString();

        Integer Tipos = Tipo.getId();

        Integer TIpos = TIpo.getId();

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();

        String query = "INSERT INTO animal(codigo_animal,nombre_animal,"
                + "especie,nombre_cuidador,fecha_ingreso,fecha_nacimiento,"
                + "id_tipo,id_tipo_e)"
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = cn.prepareStatement(query);

            st.setString(1, codigo);
            st.setString(2, Nombre_animal);
            st.setString(3, Especie);
            st.setString(4, Nombre_cuidador);
            st.setString(5, Fechar);
            st.setString(6, FEchar);
            st.setInt(7, Tipos);
            st.setInt(8, TIpos);

            Integer res = st.executeUpdate();
            if (res > 0) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Operación Exitosa", "animal registrado");
                limpiar();
                tabla.getItems().clear();
                listarAnimal();
            }
            cn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void btn_actualizar(ActionEvent event) {
        LocalDate Fecha = fecha_ingreso.getValue();
        LocalDate FEcha = fecha_nacimiento.getValue();
        TipoModel Tipo = (TipoModel) this.tipo.getSelectionModel().getSelectedItem();
        Tipo2 TIpo = (Tipo2) this.tipo2.getSelectionModel().getSelectedItem();

        if (txt_numero_codigo.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el numero del animal");
            return;
        }

        if (Fecha == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la fecha de ingreso");
            return;
        }

        if (nombre_animal.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el Nombre del animal");
            return;
        }

        if (especie.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la especie");
            return;
        }

        if (FEcha == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar la fecha de nacimiento");
            return;
        }

        if (nombre_cuidador.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor agregar el nombre del cuidador");
            return;
        }

        if (Tipo.getId() == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor selecciona un Tipo de producto");
            return;
        }

        if (TIpo.getId() == 0) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", "Por favor selecciona un Tipo de producto");
            return;
        }

        String codigo = txt_numero_codigo.getText();
        String Nombre_animal = nombre_animal.getText();
        String Nombre_cuidador = nombre_cuidador.getText();
        String Especie = especie.getText();

        String Fechar = Fecha.toString();

        String FEchar = FEcha.toString();

        Integer Tipos = Tipo.getId();

        Integer TIpos = TIpo.getId();

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();

        String query = "UPDATE animal SET codigo_animal=?,nombre_animal=?,"
                + "especie=?,nombre_cuidador=?,fecha_ingreso=?,fecha_nacimiento=?,"
                + "id_tipo=?,id_tipo_e=? ";
        try {
            PreparedStatement st = cn.prepareStatement(query);

            st.setString(1, codigo);
            st.setString(2, Nombre_animal);
            st.setString(3, Especie);
            st.setString(4, Nombre_cuidador);
            st.setString(5, Fechar);
            st.setString(6, FEchar);
            st.setInt(7, Tipos);
            st.setInt(8, TIpos);

            Integer res = st.executeUpdate();
            if (res > 0) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Operación Exitosa", "animal Actualizado");
                limpiar();
                tabla.getItems().clear();
                listarAnimal();
            }
            cn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    private void btn_eliminar(ActionEvent event) {

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();
        String user = txt_numero_codigo.getText().toString();

        try {
            PreparedStatement ps = cn.prepareStatement("DELETE FROM animal WHERE codigo_animal='" + user + "'");
            Integer res = ps.executeUpdate();

            if (res > 0) {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Operación Exitosa", "animal eliminado");
                limpiar();
                tabla.getItems().clear();
                listarAnimal();
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void btn_buscar(ActionEvent event) {

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();
        String user = txt_numero_codigo.getText().toString();

        try {

            Statement ps = cn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM animal WHERE codigo_animal='" + user + "'");

            if (rs.next()) {

                LocalDate Date = LocalDate.parse(rs.getString("fecha_ingreso"));
                fecha_ingreso.setValue(Date);
                nombre_animal.setText(rs.getString("nombre_animal"));
                especie.setText(rs.getString("especie"));
                LocalDate pn = LocalDate.parse(rs.getString("fecha_nacimiento"));
                fecha_nacimiento.setValue(pn);
                nombre_cuidador.setText(rs.getString("nombre_cuidador"));
                tipo.getSelectionModel().select(rs.getInt("id_tipo"));
                tipo2.getSelectionModel().select(rs.getInt("id_tipo_e"));

            } else {

                AlertHelper.showAlert(Alert.AlertType.INFORMATION, "Información!", "El animal no existe");
                return;

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void listarAnimal() {

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();

        String sql=("SELECT * FROM animal");
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(new AnimalModel(
                        rs.getInt("codigo_animal"),
                        rs.getInt("id_tipo"),
                        rs.getInt("id_tipo_e"),
                        
                        rs.getString("nombre_animal"),
                        rs.getString("especie"),
                        rs.getString("nombre_cuidador"),
                        rs.getDate("fecha_ingreso"),
                        rs.getDate("fecha_nacimiento")
                        
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        col_animal.setCellValueFactory(new PropertyValueFactory<>("codigo_animal"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_especie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        col_nombre_cuidador.setCellValueFactory(new PropertyValueFactory<>("nombre_cuidador"));
       

        tabla.setItems(list);
    }
}

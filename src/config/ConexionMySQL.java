
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;


public class ConexionMySQL {

    public String db = "circo";
    public String url = "jdbc:mysql://127.0.0.1:3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public String user = "root";
    public String pass = "";
    
    public Connection Conectar()
    {   
        Connection conn = null;
        try
        {
            //Cargamos el Driver MySQL
            Class.forName("org.gjt.mm.mysql.Driver");
            //Creamos un enlace hacia la base de datos
            conn = DriverManager.getConnection(this.url, this.user, this.pass);
        }
        catch (Exception e)
        {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error!", e.toString());
        }
        return conn;  
    }    
}
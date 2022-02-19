package cantina.controlador;
import cantina.conexion.JdbcDAOFactory;
import cantina.conexion.DAOException;
import cantina.modelo.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Lucas
 */
public class loginDAOMySQL extends ControladorAbstract {
    
    public loginDAOMySQL() {
		super();
	}
    
    public loginDAOMySQL(Connection con) {
		super(con);
	}

    public login log(String usuario, String pass) {
        login l = null;
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND pass = ? AND estado = 1";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                l= new login();
                l.setId(rs.getInt("idusuario"));
                l.setNombre(rs.getString("nombre"));
                l.setPass(rs.getString("pass"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return l;
    }
}

package cantina.controlador;

import cantina.conexion.JdbcDAOFactory;
import java.sql.Connection;

/**
 *
 * @author Lucas
 */
public abstract class ControladorAbstract {
    
    protected Connection connection;
	
	public ControladorAbstract() {			
		super();	
		this.obtenerConexion(null);			
	}
	
	public ControladorAbstract(Connection connection) {	
		super();
		this.obtenerConexion(connection);		
	}
	
	private void obtenerConexion(Connection c){
		if(c==null){
			this.connection =  JdbcDAOFactory.obtenerConeccion();		
		}else{
			this.connection = connection;	
		}		
	}
    
}

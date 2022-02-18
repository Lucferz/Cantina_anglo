package cantina.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDAOFactory extends DAOFactory {

private static Connection connection=null;
	
	public JdbcDAOFactory() {
		obtenerConeccion();
	}

	public static  Connection  obtenerConeccion(){
		//singleton
		if (connection==null){
			conectar();
		}
		
		// verificar si esta cerrado
		try {
		
			if(connection.isClosed()){
//System.out.println("** Estaba cerrado **");					
				conectar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
//System.out.println("conexion: "+connection);

		return connection;
	}
	
	private static void conectar(){
		try {
			Class.forName("org.gjt.mm.mysql.Driver");//el nombre del jdbDrive que tiene relacion con la base utilizada
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cantina_anglo"
					, "root", "mysql");
		} catch (Exception e) {
			throw new RuntimeException("Error de conexion a base de datos", e);
		}			
	}
	


	public  Connection getConnection() {
		return connection;
	}
	
}

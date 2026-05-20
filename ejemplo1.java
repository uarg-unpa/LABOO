import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ejemplo1 {
	
	private Connection conexion=null;
	
	public ejemplo1() throws SQLException{
		try {
			conectar();
		} finally {
			cerrar();
		}
	}
	
	public void conectar() throws SQLException {
		//Class.forName("com.mysql.jdbc.Driver"); // no es necesario
		String jdbc = "jdbc:mysql://localhost/ejemplo";
		conexion = DriverManager.getConnection(jdbc, "root", "");
		System.out.println("Conexion OK");
	}
	
	public void cerrar() throws  SQLException{
		if (conexion!=null)
			conexion.close();
	}

	public static void main(String[] args) {
		try {
			new ejemplo1();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

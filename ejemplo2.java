import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ejemplo2 {
	
	private Connection conexion=null;
	
	public ejemplo2() throws SQLException{
		try {
			conectar();
			consultar();
		} finally {
			cerrar();
		}
	}
	
	public void conectar() throws SQLException {
		String jdbc = "jdbc:mysql://localhost/ejemplo";
		conexion = DriverManager.getConnection(jdbc, "root", "");
		System.out.println("Conexion OK");
	}
	
	public void consultar() throws SQLException {
		Statement stm = conexion.createStatement();
		ResultSet set = stm.executeQuery("select id, nombre, apellido from alumnos");
		while (set.next()) {
			int id = set.getInt("id");
			String nombre=set.getString("nombre");
			String apellido=set.getString("apellido");
			System.out.println("ID: " + id + " Alumno: " + nombre + " " + apellido );
		}
		set.close(); // liberar los ResultSet
		stm.close(); // liberar los Statement
	}
	
	public void cerrar() throws  SQLException{
		if (conexion!=null)
			conexion.close();
	}

	public static void main(String[] args) {
		try {
			new ejemplo2();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

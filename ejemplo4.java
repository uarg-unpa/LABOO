import java.sql.*;

public class ejemplo4 {

	public Connection conexion = null;

	public ejemplo4() throws SQLException{
		try {
			conectar();
			sentenciaPreparada();
		} finally {
			cerrar();
 		}
	}
	
	public void conectar() throws SQLException {
		String jdbc = "jdbc:mysql://localhost/ejemplo";
		conexion = DriverManager.getConnection(jdbc, "root", "");
		System.out.println("Conexion OK");
	}

	public void cerrar() throws SQLException{
		if (conexion != null) {
			conexion.close();
			System.out.println("Conexion cerrada");
		}
	}

	private void sentenciaPreparada() throws SQLException{
		String ARTICULO = "INSERT INTO articulos (codigo, descripcion, precio) VALUES (?, ?, ?)";
		String STOCK = "INSERT INTO stock (codigo, cantidad) VALUES (?, ?)";
		PreparedStatement articulo=null, stock=null;

		try {
			articulo = conexion.prepareStatement(ARTICULO);
			articulo.setInt(1,70);
			articulo.setString(2,"Tablet Samsung Galaxy Tab A9");
			articulo.setDouble(3,350000);
			articulo.executeUpdate();

			stock= conexion.prepareStatement(STOCK);
			stock.setInt(1,70);
			stock.setInt(2,9);
			stock.executeUpdate();
			
			System.out.println("Sentencia PreparedStatement OK");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(articulo!=null) 
				articulo.close();
			if (stock!=null)
				stock.close();
		}
	}

	public static void main(String args[]) {
		try {
			new ejemplo4();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
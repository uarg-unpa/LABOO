package tp1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {
    private final String url = "jdbc:mysql://localhost:3306/BANCO";
    private final String user = "root";
    private final String password = ""; // Cambiar en caso de tener contraseña

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // AGREGAR (Punto 1)
    public void agregar(int nro, String nombre, double saldo, char tipo) {
        String sql = "INSERT INTO CUENTAS (Cuenta, NombreCliente, Saldo, TipoCuenta) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nro);
            pstmt.setString(2, nombre);
            pstmt.setDouble(3, saldo);
            pstmt.setString(4, String.valueOf(tipo));
            pstmt.executeUpdate();
            System.out.println("Cuenta creada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
    }

    // BORRAR (Punto 1)
    public void borrar(int nro) {
        String sql = "DELETE FROM CUENTAS WHERE Cuenta = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nro);
            int filas = pstmt.executeUpdate();
            if (filas > 0) System.out.println("Cuenta eliminada.");
            else System.out.println("No se encontró la cuenta " + nro);
        } catch (SQLException e) {
            System.err.println("Error al borrar: " + e.getMessage());
        }
    }

    // MODIFICAR (Punto 1)
    public void modificar(int nro, String nuevoNombre, double nuevoSaldo) {
        String sql = "UPDATE CUENTAS SET NombreCliente = ?, Saldo = ? WHERE Cuenta = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nuevoNombre);
            pstmt.setDouble(2, nuevoSaldo);
            pstmt.setInt(3, nro);
            pstmt.executeUpdate();
            System.out.println("Cuenta actualizada.");
        } catch (SQLException e) {
            System.err.println("Error al modificar: " + e.getMessage());
        }
    }

    // BUSCAR (Punto 1)
    public void buscar(int nro) {
        String sql = "SELECT * FROM CUENTAS WHERE Cuenta = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nro);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Resultado: [" + rs.getInt("Cuenta") + "] " + 
                                       rs.getString("NombreCliente") + " - Saldo: $" + rs.getDouble("Saldo"));
                } else {
                    System.out.println("Cuenta no encontrada.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la búsqueda: " + e.getMessage());
        }
    }

    // MOSTRAR TODO (Punto 1)
    public void mostrarTodas() {
        String sql = "SELECT * FROM CUENTAS";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- LISTADO DE CUENTAS ---");
            while (rs.next()) {
                System.out.printf("Cuenta: %d | Cliente: %s | Saldo: %.2f | Tipo: %s%n",
                        rs.getInt("Cuenta"), rs.getString("NombreCliente"), 
                        rs.getDouble("Saldo"), rs.getString("TipoCuenta"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
    }
}
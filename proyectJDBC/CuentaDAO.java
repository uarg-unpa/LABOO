package proyectJDBC;

public class CuentaDAO {
    // Configuración de la conexión (Ajustá el usuario y contraseña según tu Workbench)
    private final String URL = "jdbc:mysql://localhost:3306/BANCO";
    private final String USER = "root";
    private final String PASS = "Axel1234"; 

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // --- MÉTODOS DEL PUNTO 1 (CRUD) ---

    public void agregar(int nro, String nombre, double saldo, char tipo) {
        String sql = "INSERT INTO CUENTAS (Cuenta, NombreCliente, Saldo, TipoCuenta) VALUES (?, ?, ?, ?)";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nro);
            pstmt.setString(2, nombre);
            pstmt.setDouble(3, saldo);
            pstmt.setString(4, String.valueOf(tipo));
            pstmt.executeUpdate();
            System.out.println("Cuenta agregada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al agregar: " + e.getMessage());
        }
    }

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

    public void buscar(int nro) {
        String sql = "SELECT * FROM CUENTAS WHERE Cuenta = ?";
        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nro);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("🔍 Encontrada: [" + rs.getInt("Cuenta") + "] " + 
                                       rs.getString("NombreCliente") + " - Saldo: $" + rs.getDouble("Saldo"));
                } else {
                    System.out.println("⚠️ La cuenta no existe.");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error en la búsqueda: " + e.getMessage());
        }
    }

    public void mostrarTodas() {
        String sql = "SELECT * FROM CUENTAS";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- LISTADO GENERAL DE CUENTAS ---");
            while (rs.next()) {
                System.out.printf("N°: %d | Cliente: %s | Saldo: $%.2f | Tipo: %s%n",
                        rs.getInt("Cuenta"), rs.getString("NombreCliente"), 
                        rs.getDouble("Saldo"), rs.getString("TipoCuenta"));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
    }

    // --- MÉTODOS DEL PUNTO 2 (TRANSACCIONES) ---

    public void depositar(int nroCuenta, double importe) {
        String sqlMov = "INSERT INTO MOVIMIENTOS (Cuenta, Mov, Importe) VALUES (?, 'D', ?)";
        String sqlAct = "UPDATE CUENTAS SET Saldo = Saldo + ? WHERE Cuenta = ?";
        
        Connection conn = null;
        try {
            conn = conectar();
            conn.setAutoCommit(false); // Inicia transacción 

            // 1. Registrar movimiento
            try (PreparedStatement pstM = conn.prepareStatement(sqlMov)) {
                pstM.setInt(1, nroCuenta);
                pstM.setDouble(2, importe);
                pstM.executeUpdate();
            }
            // 2. Actualizar saldo
            try (PreparedStatement pstA = conn.prepareStatement(sqlAct)) {
                pstA.setDouble(1, importe);
                pstA.setInt(2, nroCuenta);
                if (pstA.executeUpdate() == 0) throw new SQLException("La cuenta no existe."); // [cite: 15]
            }

            conn.commit();
            System.out.println("Depósito acreditado.");
        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            System.err.println("Falló el depósito: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public void extraer(int nroCuenta, double importe) {
        String sqlVer = "SELECT Saldo FROM CUENTAS WHERE Cuenta = ?";
        String sqlMov = "INSERT INTO MOVIMIENTOS (Cuenta, Mov, Importe) VALUES (?, 'E', ?)";
        String sqlAct = "UPDATE CUENTAS SET Saldo = Saldo - ? WHERE Cuenta = ?";

        Connection conn = null;
        try {
            conn = conectar();
            conn.setAutoCommit(false);

            // 1. Validar existencia y saldo suficiente [cite: 15, 16]
            double saldoActual = 0;
            try (PreparedStatement pstV = conn.prepareStatement(sqlVer)) {
                pstV.setInt(1, nroCuenta);
                ResultSet rs = pstV.executeQuery();
                if (rs.next()) saldoActual = rs.getDouble("Saldo");
                else throw new SQLException("La cuenta no existe.");
            }
            if (saldoActual < importe) throw new SQLException("Saldo insuficiente.");

            // 2. Registrar movimiento
            try (PreparedStatement pstM = conn.prepareStatement(sqlMov)) {
                pstM.setInt(1, nroCuenta);
                pstM.setDouble(2, importe);
                pstM.executeUpdate();
            }
            // 3. Actualizar saldo
            try (PreparedStatement pstA = conn.prepareStatement(sqlAct)) {
                pstA.setDouble(1, importe);
                pstA.setInt(2, nroCuenta);
                pstA.executeUpdate();
            }

            conn.commit();
            System.out.println("Extracción realizada.");
        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            System.err.println("Falló la extracción: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }
}
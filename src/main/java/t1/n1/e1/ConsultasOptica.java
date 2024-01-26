package t1.n1.e1;

import java.sql.*;

public class ConsultasOptica {
    private Connection conexion;

    public ConsultasOptica(String url, String usuario, String contraseña) {
        try {
            this.conexion = DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (this.conexion != null && !this.conexion.isClosed()) {
                this.conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Dentro de la clase ConsultasOptica

    public void listarFacturasCliente(int idCliente, Date fechaInicio, Date fechaFin) {
        String sql = "SELECT COUNT(*) FROM Ventas WHERE id_cliente = ? AND fecha_venta BETWEEN ? AND ?";
        try (PreparedStatement statement = this.conexion.prepareStatement(sql)) {
            statement.setInt(1, idCliente); // Establecer el ID del cliente
            statement.setDate(2, fechaInicio); // Establecer la fecha de inicio
            statement.setDate(3, fechaFin);    // Establecer la fecha de fin
    
            ResultSet resultSet = statement.executeQuery(); // Ejecutar la consulta
    
            if (resultSet.next()) {
                int totalFacturas = resultSet.getInt(1); // Obtener el total de facturas
                System.out.println("Total de facturas para el cliente " + idCliente + ": " + totalFacturas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void listarModelosVendidosPorEmpleado(int idEmpleado, int año) {
        String sql = "SELECT E.nombre, G.marca, V.fecha_venta FROM Ventas V " +
                     "JOIN Gafas G ON V.id_gafas = G.id_gafas " +
                     "JOIN Empleados E ON V.id_empleado = E.id_empleado " +
                     "WHERE V.id_empleado = ? AND YEAR(V.fecha_venta) = ? " +
                     "ORDER BY V.fecha_venta";
    
        try (PreparedStatement statement = this.conexion.prepareStatement(sql)) {
            statement.setInt(1, idEmpleado); // Establecer el ID del empleado
            statement.setInt(2, año);        // Establecer el año
    
            ResultSet resultSet = statement.executeQuery(); // Ejecutar la consulta
    
            while (resultSet.next()) {
                String nombreEmpleado = resultSet.getString("E.nombre"); // Obtener el nombre del empleado
                String marca = resultSet.getString("G.marca");           // Obtener la marca de la fila actual
                Date fechaVenta = resultSet.getDate("V.fecha_venta");    // Obtener la fecha de venta
    
                System.out.println("Empleado: " + nombreEmpleado + ", Marca vendida: " + marca + ", Fecha de venta: " + fechaVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    


public void listarProveedoresDeGafasVendidas() {
    String sql = "SELECT DISTINCT P.nombre FROM Ventas V JOIN Gafas G ON V.id_gafas = G.id_gafas JOIN Proveedores P ON G.id_proveedor = P.id_proveedor";
    try (Statement statement = this.conexion.createStatement()) {
        ResultSet resultSet = statement.executeQuery(sql); // Ejecutar la consulta

        while (resultSet.next()) {
            String nombreProveedor = resultSet.getString("nombre"); // Obtener el nombre del proveedor
            System.out.println("Proveedor: " + nombreProveedor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/OpticaDB";
    String usuario = "root";
    String contraseña = "";

    ConsultasOptica consultas = new ConsultasOptica(url, usuario, contraseña);

    // Llamadas a los métodos de consulta
    consultas.listarFacturasCliente(1, java.sql.Date.valueOf("2023-12-01"), java.sql.Date.valueOf("2024-02-28"));

    consultas.listarModelosVendidosPorEmpleado(4,2024);
    consultas.listarProveedoresDeGafasVendidas();

    consultas.cerrarConexion();

    
}

}
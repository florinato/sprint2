package t1.n1.e1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;

public class InsertarDatosOptica {

    private static final String URL = "jdbc:mysql://localhost:3306/OpticaDB";
    private static final String USUARIO = "root"; // Tu usuario de MySQL
    private static final String CONTRASENA = ""; // Tu contraseña de MySQL

    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);

            insertarProveedores(conexion);
            insertarGafas(conexion);
            insertarClientes(conexion);
            insertarEmpleados(conexion);
            insertarVentas(conexion);
            actualizarClientesRecomendados(conexion);
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarProveedores(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Proveedores (nombre, direccion, telefono, fax, NIF) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para proveedores
        String[][] datosProveedores = {
            {"Opticas Vision", "Calle Falsa 123, Piso 2, Puerta 4, Barcelona, 08001, España", "932123456", "932654321", "A12345678"},
            {"Gafas Modernas", "Avenida de la Luz 47, Madrid, 28001, España", "915789456", "915789654", "B87654321"},
            {"Estilo Visual", "Paseo de la Castellana 100, Madrid, 28046, España", "914567890", "914567098", "C23456789"},
            {"Vision Clara", "Gran Via 28, Madrid, 28013, España", "913334455", "913334655", "D34567890"}
        };
    
        for (String[] proveedor : datosProveedores) {
            preparedStatement.setString(1, proveedor[0]);
            preparedStatement.setString(2, proveedor[1]);
            preparedStatement.setString(3, proveedor[2]);
            preparedStatement.setString(4, proveedor[3]);
            preparedStatement.setString(5, proveedor[4]);
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    

    private static void insertarGafas(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Gafas (marca, graduacion_izq, graduacion_der, tipo_montura, color_montura, color_vidrio_izq, color_vidrio_der, precio, id_proveedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para gafas
    String[][] datosGafas = {
        {"Ray-Ban", "1.5", "1.5", "metálica", "negro", "transparente", "transparente", "149.99", "1"},
        {"Oakley", "2.0", "2.0", "pasta", "azul", "azul", "azul", "99.99", "1"},
        {"Gucci", "0.5", "0.5", "flotante", "dorado", "verde", "verde", "199.99", "2"},
        {"Prada", "1.0", "1.0", "pasta", "rojo", "negro", "negro", "129.99", "2"}
    };

    for (String[] gafa : datosGafas) {
        preparedStatement.setString(1, gafa[0]);
        preparedStatement.setFloat(2, Float.parseFloat(gafa[1]));
        preparedStatement.setFloat(3, Float.parseFloat(gafa[2]));
        preparedStatement.setString(4, gafa[3]);
        preparedStatement.setString(5, gafa[4]);
        preparedStatement.setString(6, gafa[5]);
        preparedStatement.setString(7, gafa[6]);
        preparedStatement.setBigDecimal(8, new BigDecimal(gafa[7]));
        preparedStatement.setInt(9, Integer.parseInt(gafa[8]));
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}


private static void insertarClientes(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Clientes (nombre, direccion_postal, telefono, email, fecha_registro, recomendado_por) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para clientes
    String[][] datosClientes = {
        {"Laura Martinez", "Calle Sol 15, Valencia, 46001, España", "961234567", "laura.martinez@email.com", "2023-01-10", null},
        {"Carlos Gomez", "Plaza Mayor 28, Sevilla, 41001, España", "954567890", "carlos.gomez@email.com", "2023-02-15",null},
        {"Ana López", "Avenida Diagonal 452, Barcelona, 08036, España", "932345678", "ana.lopez@email.com", "2023-03-20", null},
        {"David Jimenez", "Gran Via 33, Madrid, 28013, España", "913456789", "david.jimenez@email.com", "2023-04-25",null }
    };

    for (String[] cliente : datosClientes) {
        preparedStatement.setString(1, cliente[0]);
        preparedStatement.setString(2, cliente[1]);
        preparedStatement.setString(3, cliente[2]);
        preparedStatement.setString(4, cliente[3]);
        preparedStatement.setDate(5, Date.valueOf(cliente[4]));
        if (cliente[5] != null && !cliente[5].isEmpty()) {
            preparedStatement.setInt(6, Integer.parseInt(cliente[5]));
        } else {
            preparedStatement.setNull(6, Types.INTEGER);
        }
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}
private static void actualizarClientesRecomendados(Connection conexion) throws SQLException {
    String sql = "UPDATE Clientes SET recomendado_por = ? WHERE id_cliente = ?";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    Object[][] datosActualizados = {
        {1, 2}, // Carlos Gomez fue recomendado por Laura Martinez
        {1, 4}, // David Jimenez fue recomendado por Laura Martinez
        

    };

    for (Object[] actualizacion : datosActualizados) {
        preparedStatement.setInt(1, (Integer) actualizacion[0]); // ID del cliente que recomendó
        preparedStatement.setInt(2, (Integer) actualizacion[1]); // ID del cliente a actualizar
        preparedStatement.executeUpdate();
    }

   
    preparedStatement.close();
}


private static void insertarEmpleados(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Empleados (nombre) VALUES (?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para empleados
    String[] nombresEmpleados = {
        "Manuel Royo",
        "Juan Fernández",
        "Sara Gómez",
        "Carlos López"
    };

    for (String nombre : nombresEmpleados) {
        preparedStatement.setString(1, nombre);
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}


private static void insertarVentas(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Ventas (id_empleado, id_cliente, id_gafas, fecha_venta) VALUES (?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para ventas
    Object[][] datosVentas = {
        {1, 1, 1, "2024-01-15"},
        {2, 2, 2, "2024-01-20"},
        {3, 3, 3, "2024-01-25"},
        {4, 4, 4, "2024-01-30"},
        {4, 2, 3, "2024-01-30"}
    };

    for (Object[] venta : datosVentas) {
        preparedStatement.setInt(1, (Integer) venta[0]); // ID del empleado
        preparedStatement.setInt(2, (Integer) venta[1]); // ID del cliente
        preparedStatement.setInt(3, (Integer) venta[2]); // ID de las gafas
        preparedStatement.setDate(4, Date.valueOf((String) venta[3])); // Fecha de la venta
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}


    
}


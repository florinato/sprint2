package t1.n1.e2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class InsertarDatosComida {
    private static final String URL = "jdbc:mysql://localhost:3306/ComidaADomicilio";
    private static final String USUARIO = "root"; // Tu usuario de MySQL
    private static final String CONTRASENA = ""; // Tu contraseña de MySQL

    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            insertarTiendas(conexion);
            insertarCategorias(conexion);
            insertarProductos(conexion);
            insertarEmpleados(conexion);
            insertarClientes(conexion);
            insertarPedidos(conexion);
            crearTriggerYProcedimiento(conexion);
            insertarDetallePedidos(conexion);
            ProductosVendidosCategoriaYTienda( conexion, 1);
            contarPedidosPorEmpleado(conexion, 3);
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void insertarTiendas(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Tiendas (direccion, codigo_postal, localidad) VALUES (?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para tiendas
    Object[][] datosTiendas = {
        {"Calle Principal 123, Ciudad A", "28001", "Ciudad A"},
        {"Avenida Secundaria 456, Ciudad B", "28002", "Ciudad B"},
        // Añadir más tiendas según sea necesario
    };
    
    for (Object[] tienda : datosTiendas) {
        preparedStatement.setString(1, (String) tienda[0]); // Dirección
        preparedStatement.setString(2, (String) tienda[1]); // Código postal
        preparedStatement.setString(3, (String) tienda[2]); // Localidad
        preparedStatement.executeUpdate();
    }
    
    preparedStatement.close();
    
}
private static void insertarCategorias(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Categorias (nombre) VALUES (?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para categorías de pizza
    String[] datosCategorias = {"pizzas", "Hamburguesas", "bebidas"};

    for (String categoria : datosCategorias) {
        preparedStatement.setString(1, categoria);
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}
private static void insertarProductos(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Productos (nombre, descripcion, imagen, precio, tipo_producto, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para productos
    Object[][] datosProductos = {
        {"Pizza Margarita", "Pizza con tomate y mozzarella", "url_imagen_margarita", 8.99, "Pizza", 1},
        {"Pizza Pepperoni", "Pizza con pepperoni y queso", "url_imagen_pepperoni", 9.99, "Pizza", 1},
        {"Pizza Hawaiana", "Pizza con jamón y piña", "url_imagen_hawaiana", 10.99, "Pizza", 1},
        {"Hamburguesa Clásica", "Hamburguesa con carne, queso, lechuga y tomate", "url_imagen_hamburguesa_clasica", 7.99, "Hamburguesa", 2},
        {"Hamburguesa Vegana", "Hamburguesa vegana con verduras y salsa especial", "url_imagen_hamburguesa_vegana", 8.99, "Hamburguesa", 2},
        {"Coca Cola", "Lata de Coca Cola 330ml", "url_imagen_cocacola", 1.50, "Bebida", 3},
        {"Agua Mineral", "Botella de agua mineral 500ml", "url_imagen_agua", 1.00, "Bebida", 3}
        // Añade más productos según sea necesario
    };

    for (Object[] producto : datosProductos) {
        preparedStatement.setString(1, (String) producto[0]);
        preparedStatement.setString(2, (String) producto[1]);
        preparedStatement.setString(3, (String) producto[2]);
        preparedStatement.setDouble(4, (Double) producto[3]);
        preparedStatement.setString(5, (String) producto[4]);
        if (producto[5] == null) {
            preparedStatement.setNull(6, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(6, (Integer) producto[5]);
        }
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}
private static void insertarEmpleados(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Empleados (nombre, apellidos, NIF, telefono, rol, id_tienda) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para empleados
    // Asumiendo que los IDs de las tiendas son 1, 2, etc.
    Object[][] datosEmpleados = {
        {"Juan", "Pérez", "12345678A", "900123456", "Cocinero", 1},
        {"María", "López", "23456789B", "910234567", "Repartidor", 1},
        {"Carlos", "García", "34567890C", "920345678", "Cocinero", 2},
        {"Ana", "Martínez", "45678901D", "930456789", "Repartidor", 2},
        {"Luis", "Navarro", "56789012E", "940567890", "Cocinero", 1},
        {"Cristina", "Vega", "67890123F", "950678901", "Repartidor", 2},
        {"Pedro", "Núñez", "78901234G", "960789012", "Cocinero", 2},
        {"Sonia", "Campos", "89012345H", "970890123", "Repartidor", 1}
        // Añade más empleados según sea necesario
    };

    for (Object[] empleado : datosEmpleados) {
        preparedStatement.setString(1, (String) empleado[0]);
        preparedStatement.setString(2, (String) empleado[1]);
        preparedStatement.setString(3, (String) empleado[2]);
        preparedStatement.setString(4, (String) empleado[3]);
        preparedStatement.setString(5, (String) empleado[4]);
        preparedStatement.setInt(6, (Integer) empleado[5]);
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}
private static void insertarClientes(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Clientes (nombre, apellidos, direccion, codigo_postal, localidad, telefono) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para clientes
    Object[][] datosClientes = {
        {"Laura", "González", "Calle del Sol 15", "28001", "Hospitalet", "931234567"},
        {"Roberto", "Fernández", "Avenida de la Luna 27", "28002", "Rubí", "942345678"},
        {"Sofía", "Martín", "Paseo de la Estrella 33", "28003", "Comarruga", "953456789"},
        {"Miguel", "Rodríguez", "Camino del Río 42", "28004", "Altafulla", "964567890"},
        // Añade más clientes según sea necesario
    };

    for (Object[] cliente : datosClientes) {
        preparedStatement.setString(1, (String) cliente[0]); // nombre
        preparedStatement.setString(2, (String) cliente[1]); // apellidos
        preparedStatement.setString(3, (String) cliente[2]); // direccion
        preparedStatement.setString(4, (String) cliente[3]); // codigo_postal
        preparedStatement.setString(5, (String) cliente[4]);   // localidad
        preparedStatement.setString(6, (String) cliente[5]); // telefono
        preparedStatement.executeUpdate();
    }

    preparedStatement.close();
}

private static void insertarPedidos(Connection conexion) throws SQLException {
    String sql = "INSERT INTO Pedidos (fecha_hora, tipo, precio_total, id_cliente, id_tienda, id_empleado_repartidor) VALUES (?, ?, ?, ?, ?, ?)";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);

    // Datos de ejemplo para pedidos
    Object[][] datosPedidos = {
        {"2024-01-05 12:00:00", "Domicilio", null, 1, 1, 2}, // Repartidor María
        {"2024-01-06 13:30:00", "Domicilio", null, 2, 1, 2}, // Repartidor María
        {"2024-01-07 19:45:00", "Domicilio", null, 3, 2, 4}, // Repartidor Ana
        {"2024-01-08 18:20:00", "Local", null, 3, 2, 3},     // Cocinero Carlos
        {"2024-01-09 20:00:00", "Local", null, 3, 1, 1},     // Cocinero Juan
        /*{"2024-01-10 17:15:00", "Domicilio", null, 6, 2, 6}, // Repartidor Cristina
        {"2024-01-11 21:30:00", "Local", null, 7, 1, 5},     // Cocinero Luis
        {"2024-01-12 12:45:00", "Domicilio", null, 8, 1, 8}, // Repartidor Sonia
        {"2024-01-13 14:10:00", "Local", null, 9, 2, 7},     // Cocinero Pedro
        {"2024-01-14 19:00:00", "Domicilio", null, 10, 2, 4} // Repartidor Ana
        // Añade más pedidos según sea necesario*/
    };
// Datos de ejemplo para pedidos



for (Object[] pedido : datosPedidos) {
    preparedStatement.setString(1, (String) pedido[0]); // fecha_hora
    preparedStatement.setString(2, (String) pedido[1]); // tipo
    // Verifica si precio_total es null
    if (pedido[2] == null) {
        preparedStatement.setNull(3, java.sql.Types.DOUBLE); // precio_total
    } else {
        preparedStatement.setDouble(3, (Double) pedido[2]); // precio_total
    }
    preparedStatement.setInt(4, (Integer) pedido[3]); // id_cliente
    preparedStatement.setInt(5, (Integer) pedido[4]); // id_tienda
    preparedStatement.setInt(6, (Integer) pedido[5]); // id_empleado_repartidor
    preparedStatement.executeUpdate();
}

preparedStatement.close();

    

   
}
private static void insertarDetallePedidos(Connection conexion) throws SQLException {
    String sql = "INSERT INTO DetallePedidos (id_pedido, id_producto, precio) VALUES (?, ?, ? )";
    PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    // Preparar CallableStatement para el procedimiento almacenado
    CallableStatement callableStatement = (CallableStatement) conexion.prepareCall("{CALL calcular_precio_total_pedido(?)}");
    // Datos de ejemplo para detalle de pedidos
    // Asegúrate de que los IDs de pedidos y productos existan
    Object[][] datosDetallePedidos = {
        {1, 5, 8.99}, {1, 6, 1.50}, {1, 5, 8.99},
        {1, 7, 1.00}, {1, 5, 8.99}, {2, 3, 10.99},
        {2, 1, 8.99}, {2, 2, 9.99}, {2, 1, 8.99},
        {2, 4, 7.99}, {3, 4, 7.99}, {3, 6, 1.50},
        {3, 6, 1.50}, {3, 3, 10.99}, {4, 1, 8.99},
        {4, 5, 8.99}, {4, 4, 7.99}, {4, 4, 7.99},
        {5, 3, 10.99}, {5, 7, 1.00},  // 1 pizza Pepperoni en el pedido 2
        
    };

    for (Object[] detalle : datosDetallePedidos) {
        preparedStatement.setInt(1, (Integer) detalle[0]);
        preparedStatement.setInt(2, (Integer) detalle[1]);
        preparedStatement.setDouble(3, (Double) detalle[2]);
        preparedStatement.executeUpdate();

        callableStatement.setInt(1, (Integer) detalle[0]); // id_pedido
        callableStatement.execute();
    }
    callableStatement.close();
    preparedStatement.close();
}
public static void crearTriggerYProcedimiento(Connection conexion) {
    String crearProcedimiento = 
        "CREATE PROCEDURE calcular_precio_total_pedido(IN pedido_id INT) " +
        "BEGIN " +
        "    UPDATE Pedidos " +
        "    SET precio_total = (SELECT SUM(precio) FROM DetallePedidos WHERE id_pedido = pedido_id) " +
        "    WHERE id_pedido = pedido_id; " +
        "END;";


    String crearTrigger = 
        "CREATE TRIGGER actualizar_precio_total_despues_de_insertar_o_actualizar " +
        "AFTER INSERT ON DetallePedidos " +
        "FOR EACH ROW " +
        "BEGIN " +
        "    CALL calcular_precio_total_pedido(NEW.id_pedido); " +
        "END;";
    

    try (Statement statement = conexion.createStatement()) {
        // Crear procedimiento almacenado
        statement.execute(crearProcedimiento);

        // Crear trigger
        statement.execute(crearTrigger);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static void ProductosVendidosCategoriaYTienda(Connection conexion, int idTienda) {
    String sql = "SELECT COUNT(DP.id_producto) AS CantidadVendida " +
                 "FROM DetallePedidos DP " +
                 "JOIN Pedidos P ON DP.id_pedido = P.id_pedido " +
                 "JOIN Productos Prod ON DP.id_producto = Prod.id_producto " +
                 "JOIN Categorias C ON Prod.id_categoria = C.id_categoria " +
                 "JOIN Tiendas T ON P.id_tienda = T.id_tienda " +
                 "WHERE C.nombre = 'Bebidas' AND T.id_tienda = ?";

    try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
        preparedStatement.setInt(1, idTienda);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int cantidadVendida = resultSet.getInt("CantidadVendida");
            System.out.println("Cantidad de productos vendidos en la categoría 'Bebidas' en la tienda con ID '" + idTienda + "': " + cantidadVendida);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
private static void contarPedidosPorEmpleado(Connection conexion, int idEmpleado) {
    String sql = "SELECT E.nombre, COUNT(P.id_pedido) AS CantidadPedidos " +
                 "FROM Pedidos P " +
                 "JOIN Empleados E ON P.id_empleado_repartidor = E.id_empleado " +
                 "WHERE E.id_empleado = ? " +
                 "GROUP BY E.nombre";

    try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
        preparedStatement.setInt(1, idEmpleado);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String nombreEmpleado = resultSet.getString("nombre");
            int cantidadPedidos = resultSet.getInt("CantidadPedidos");
            System.out.println("Cantidad de pedidos realizados por el empleado '" + nombreEmpleado + "' (ID: " + idEmpleado + "): " + cantidadPedidos);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}

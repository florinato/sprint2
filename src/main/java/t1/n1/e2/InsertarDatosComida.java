package t1.n1.e2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertarDatosComida {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ComidaADomicilio";
    private static final String USUARIO = "root"; // Ajusta según tu configuración
    private static final String CONTRASENA = ""; // Ajusta según tu configuración

    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection(DB_URL, USUARIO, CONTRASENA);
            insertarTiendas(conexion);
            insertarCategorias(conexion);
            insertarProductos(conexion);
            insertarEmpleados(conexion);
            insertarClientes(conexion);
            insertarPedidos(conexion);
            insertarDetallePedidos(conexion);
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarTiendas(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Tiendas (direccion, codigo_postal, localidad) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        Object[][] datosTiendas = {
            {"Calle Principal 123, Ciudad A", "28001", "Ciudad A"},
            {"Avenida Secundaria 456, Ciudad B", "28002", "Ciudad B"}
        };

        for (Object[] tienda : datosTiendas) {
            preparedStatement.setString(1, (String) tienda[0]);
            preparedStatement.setString(2, (String) tienda[1]);
            preparedStatement.setString(3, (String) tienda[2]);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
    }

    private static void insertarCategorias(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Categorias (nombre) VALUES (?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        String[] datosCategorias = {"Pizzas", "Hamburguesas", "Bebidas"};

        for (String categoria : datosCategorias) {
            preparedStatement.setString(1, categoria);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
    }

    private static void insertarProductos(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Productos (nombre, descripcion, imagen, precio, tipo_producto, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        Object[][] datosProductos = {
            {"Pizza Margarita", "Pizza con tomate y mozzarella", "url_imagen_margarita", 8.99, "Pizza", 1},
            {"Pizza Pepperoni", "Pizza con pepperoni y queso", "url_imagen_pepperoni", 9.99, "Pizza", 1},
            {"Pizza Hawaiana", "Pizza con jamón y piña", "url_imagen_hawaiana", 10.99, "Pizza", 1},
            {"Hamburguesa Clásica", "Hamburguesa con carne, queso, lechuga y tomate", "url_imagen_hamburguesa_clasica", 7.99, "Hamburguesa", 2},
            {"Hamburguesa Vegana", "Hamburguesa vegana con verduras y salsa especial", "url_imagen_hamburguesa_vegana", 8.99, "Hamburguesa", 2},
            {"Coca Cola", "Lata de Coca Cola 330ml", "url_imagen_cocacola", 1.50, "Bebida", 3},
            {"Agua Mineral", "Botella de agua mineral 500ml", "url_imagen_agua", 1.00, "Bebida", 3}
        };

        for (Object[] producto : datosProductos) {
            preparedStatement.setString(1, (String) producto[0]);
            preparedStatement.setString(2, (String) producto[1]);
            preparedStatement.setString(3, (String) producto[2]);
            preparedStatement.setDouble(4, (Double) producto[3]);
            preparedStatement.setString(5, (String) producto[4]);
            preparedStatement.setInt(6, (Integer) producto[5]);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
    }

    private static void insertarEmpleados(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Empleados (nombre, apellidos, NIF, telefono, rol, id_tienda) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        Object[][] datosEmpleados = {
            {"Juan", "Pérez", "12345678A", "900123456", "Cocinero", 1},
            {"María", "López", "23456789B", "910234567", "Repartidor", 1},
            {"Carlos", "García", "34567890C", "920345678", "Cocinero", 2},
            {"Ana", "Martínez", "45678901D", "930456789", "Repartidor", 2},
            {"Luis", "Navarro", "56789012E", "940567890", "Cocinero", 1},
            {"Cristina", "Vega", "67890123F", "950678901", "Repartidor", 2},
            {"Pedro", "Núñez", "78901234G", "960789012", "Cocinero", 2},
            {"Sonia", "Campos", "89012345H", "970890123", "Repartidor", 1}
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

        Object[][] datosClientes = {
            {"Laura", "González", "Calle del Sol 15", "28001", "Hospitalet", "931234567"},
            {"Roberto", "Fernández", "Avenida de la Luna 27", "28002", "Rubí", "942345678"},
            {"Sofía", "Martín", "Paseo de la Estrella 33", "28003", "Comarruga", "953456789"},
            {"Miguel", "Rodríguez", "Camino del Río 42", "28004", "Altafulla", "964567890"}
        };

        for (Object[] cliente : datosClientes) {
            preparedStatement.setString(1, (String) cliente[0]);
            preparedStatement.setString(2, (String) cliente[1]);
            preparedStatement.setString(3, (String) cliente[2]);
            preparedStatement.setString(4, (String) cliente[3]);
            preparedStatement.setString(5, (String) cliente[4]);
            preparedStatement.setString(6, (String) cliente[5]);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
    }

    private static void insertarPedidos(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Pedidos (fecha_hora, tipo, precio_total, id_cliente, id_tienda, id_empleado_repartidor) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        Object[][] datosPedidos = {
            {"2024-01-05 12:00:00", "Domicilio", null, 1, 1, obtenerEmpleadoRepartidor(conexion, 1)},
            {"2024-01-06 13:30:00", "Domicilio", null, 2, 1, obtenerEmpleadoRepartidor(conexion, 1)},
            {"2024-01-07 19:45:00", "Domicilio", null, 3, 2, obtenerEmpleadoRepartidor(conexion, 2)},
            {"2024-01-08 18:20:00", "Local", null, 3, 2, obtenerEmpleado(conexion, "Carlos", "García")},
            {"2024-01-09 20:00:00", "Local", null, 3, 1, obtenerEmpleado(conexion, "Juan", "Pérez")}
        };

        for (Object[] pedido : datosPedidos) {
            preparedStatement.setString(1, (String) pedido[0]);
            preparedStatement.setString(2, (String) pedido[1]);
            if (pedido[2] == null) {
                preparedStatement.setNull(3, java.sql.Types.DOUBLE);
            } else {
                preparedStatement.setDouble(3, (Double) pedido[2]);
            }
            preparedStatement.setInt(4, (Integer) pedido[3]);
            preparedStatement.setInt(5, (Integer) pedido[4]);
            preparedStatement.setInt(6, (Integer) pedido[5]);

            try {
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error al insertar pedido: " + e.getMessage());
            }
        }

        preparedStatement.close();
    }

    private static void insertarDetallePedidos(Connection conexion) throws SQLException {
        String sql = "INSERT INTO DetallePedidos (id_pedido, id_producto, precio) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);

        Object[][] datosDetallePedidos = {
            {1, 5, 8.99}, {1, 6, 1.50}, {1, 7, 1.00},
            {2, 3, 10.99}, {2, 1, 8.99}, {2, 4, 7.99},
            {3, 4, 7.99}, {3, 6, 1.50}, {3, 3, 10.99},
            {4, 1, 8.99}, {4, 5, 8.99}, {4, 4, 7.99},
            {5, 3, 10.99}, {5, 7, 1.00}
        };

        for (Object[] detalle : datosDetallePedidos) {
            preparedStatement.setInt(1, (Integer) detalle[0]);
            preparedStatement.setInt(2, (Integer) detalle[1]);
            preparedStatement.setDouble(3, (Double) detalle[2]);
            preparedStatement.executeUpdate();
        }

        preparedStatement.close();
    }

    private static int obtenerEmpleadoRepartidor(Connection conexion, int tiendaId) throws SQLException {
        String sql = "SELECT id_empleado FROM Empleados WHERE rol = 'Repartidor' AND id_tienda = ? LIMIT 1";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setInt(1, tiendaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_empleado");
            }
        }
        return -1; // Valor para indicar que no se encontró un repartidor
    }

    private static int obtenerEmpleado(Connection conexion, String nombre, String apellidos) throws SQLException {
        String sql = "SELECT id_empleado FROM Empleados WHERE nombre = ? AND apellidos = ? LIMIT 1";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_empleado");
            }
        }
        return -1; // Valor para indicar que no se encontró el empleado
    }
}

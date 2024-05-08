package t1.n1.e2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ComidaADomicilioDB {

    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ComidaADomicilio";
    private static final String USUARIO = "root"; // Ajusta según tu configuración
    private static final String CONTRASENA = ""; // Ajusta según tu configuración

    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            Statement statement = conexion.createStatement();

            // Crear la base de datos
            String sql = "CREATE DATABASE IF NOT EXISTS ComidaADomicilio";
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada o ya existente.");

            // Cerrar la conexión y reconectar a la base de datos específica
            statement.close();
            conexion.close();
            conexion = DriverManager.getConnection(DB_URL, USUARIO, CONTRASENA);
            statement = conexion.createStatement();

            // Crear y configurar tablas
            createTableClientes(statement);
            createTableTiendas(statement);
            createTableEmpleados(statement);
            createTablePedidos(statement);
            createTableCategorias(statement);
            createTableProductos(statement);
            createTableDetallePedidos(statement);
            createTrigger(conexion);

            // Cerrar las conexiones
            statement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableClientes(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Clientes (" +
                     "id_cliente INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nombre VARCHAR(255), " +
                     "apellidos VARCHAR(255), " +
                     "direccion VARCHAR(255), " +
                     "codigo_postal VARCHAR(10), " +
                     "localidad VARCHAR(255), " +
                     "telefono VARCHAR(20))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Clientes creada con éxito.");
    }

    private static void createTableTiendas(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Tiendas (" +
                     "id_tienda INT AUTO_INCREMENT PRIMARY KEY, " +
                     "direccion VARCHAR(255), " +
                     "codigo_postal VARCHAR(10), " +
                     "localidad VARCHAR(255))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Tiendas creada con éxito.");
    }

    private static void createTableEmpleados(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Empleados (" +
                     "id_empleado INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nombre VARCHAR(255), " +
                     "apellidos VARCHAR(255), " +
                     "NIF VARCHAR(50), " +
                     "telefono VARCHAR(20), " +
                     "rol VARCHAR(50), " +
                     "id_tienda INT, " +
                     "FOREIGN KEY (id_tienda) REFERENCES Tiendas(id_tienda))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Empleados creada con éxito.");
    }

    private static void createTablePedidos(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Pedidos (" +
                     "id_pedido INT AUTO_INCREMENT PRIMARY KEY, " +
                     "fecha_hora DATETIME, " +
                     "tipo VARCHAR(50), " +
                     "precio_total DECIMAL(10, 2), " +
                     "id_cliente INT, " +
                     "id_tienda INT, " +
                     "id_empleado_repartidor INT, " +
                     "FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), " +
                     "FOREIGN KEY (id_tienda) REFERENCES Tiendas(id_tienda), " +
                     "FOREIGN KEY (id_empleado_repartidor) REFERENCES Empleados(id_empleado))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Pedidos creada con éxito.");
    }

    private static void createTableCategorias(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Categorias (" +
                     "id_categoria INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nombre VARCHAR(255))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Categorías creada con éxito.");
    }

    private static void createTableProductos(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Productos (" +
                     "id_producto INT AUTO_INCREMENT PRIMARY KEY, " +
                     "nombre VARCHAR(255), " +
                     "descripcion TEXT, " +
                     "imagen VARCHAR(255), " +
                     "precio DECIMAL(10, 2), " +
                     "tipo_producto VARCHAR(50), " +
                     "id_categoria INT, " +
                     "FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria))";
        statement.executeUpdate(sql);
        System.out.println("Tabla Productos creada con éxito.");
    }

    private static void createTableDetallePedidos(Statement statement) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS DetallePedidos (" +
                     "id_pedido INT, " +
                     "id_producto INT, " +
                     "precio DECIMAL(10, 2), " +
                     "FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido), " +
                     "FOREIGN KEY (id_producto) REFERENCES Productos(id_producto))";
        statement.executeUpdate(sql);
        System.out.println("Tabla DetallePedidos creada con éxito.");
    }

    
    

    private static void createTrigger(Connection conexion) throws SQLException {
        String sql = "CREATE TRIGGER actualizar_precio_total_despues_de_insertar_o_actualizar " +
                     "AFTER INSERT ON DetallePedidos " +
                     "FOR EACH ROW " +
                     "BEGIN " +
                     "UPDATE Pedidos SET precio_total = (SELECT SUM(precio) FROM DetallePedidos WHERE id_pedido = NEW.id_pedido) " +
                     "WHERE id_pedido = NEW.id_pedido; " +
                     "END;";
        try (Statement statement = conexion.createStatement()) {
            statement.execute(sql);
            System.out.println("Trigger para actualizar precio total creado con éxito.");
        }
    }
}

package t1.n1.e2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ComidaADomicilioDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root"; // Ajusta según tu configuración
        String contraseña = ""; // Ajusta según tu configuración

        try {
            // Conectar a MySQL
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement statement = conexion.createStatement();

            // Crear la base de datos
            String sql = "CREATE DATABASE IF NOT EXISTS ComidaADomicilio";
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada o ya existente.");

            // Cerrar la conexión y reconectar a la base de datos específica
            statement.close();
            conexion.close();
            url = "jdbc:mysql://localhost:3306/ComidaADomicilio";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();

            // Crear tabla Clientes
            sql = "CREATE TABLE IF NOT EXISTS Clientes (" +
                  "id_cliente INT AUTO_INCREMENT PRIMARY KEY, " +
                  "nombre VARCHAR(255), " +
                  "apellidos VARCHAR(255), " +
                  "direccion VARCHAR(255), " +
                  "codigo_postal VARCHAR(10), " +
                  "localidad VARCHAR(255), " +
                  "telefono VARCHAR(20) )" ;
                  
            statement.executeUpdate(sql);
            System.out.println("Tabla Clientes creada con éxito.");

            sql = "CREATE TABLE IF NOT EXISTS Tiendas (" +
                "id_tienda INT AUTO_INCREMENT PRIMARY KEY, " +
                "direccion VARCHAR(255), " +
                "codigo_postal VARCHAR(10), " +
                "localidad VARCHAR(255) ) "
                ;
            statement.executeUpdate(sql);
            System.out.println("Tabla Tiendas creada con éxito.");

            sql = "CREATE TABLE IF NOT EXISTS Pedidos (" +
                "id_pedido INT AUTO_INCREMENT PRIMARY KEY, " +
                "fecha_hora DATETIME, " +
                "tipo VARCHAR(50), " +
                "precio_total DECIMAL(10, 2), " +
                "id_cliente INT, " +
                "id_tienda INT, " +
                "FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), " +
                "FOREIGN KEY (id_tienda) REFERENCES Tiendas(id_tienda))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Pedidos creada con éxito.");
            
            sql = "CREATE TABLE IF NOT EXISTS Categorias (" +
                "id_categoria INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Categorías creada con éxito.");

            sql = "CREATE TABLE IF NOT EXISTS Productos (" +
                "id_producto INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255), " +
                "descripcion TEXT, " +
                "imagen VARCHAR(255), " +
                "precio DECIMAL(10, 2), " +
                "tipo_producto VARCHAR(50))"; // 'Pizza', 'Hamburguesa', 'Bebida'
            statement.executeUpdate(sql);
            System.out.println("Tabla Productos creada con éxito.");

            
            
            sql = "CREATE TABLE IF NOT EXISTS Empleados (" +
                "id_empleado INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255), " +
                "apellidos VARCHAR(255), " +
                "NIF VARCHAR(50), " +
                "telefono VARCHAR(20), " +
                "rol VARCHAR(50), " + // 'Cocinero', 'Repartidor'
                "id_tienda INT, " +
                "FOREIGN KEY (id_tienda) REFERENCES Tiendas(id_tienda))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Empleados creada con éxito.");

            sql = "ALTER TABLE Productos ADD COLUMN id_categoria INT, " +
                "ADD FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)";
            statement.executeUpdate(sql);
            System.out.println("Agregada relación de Productos a Categorías.");

            sql = "CREATE TABLE IF NOT EXISTS DetallePedidos (" +
                
                "id_pedido INT, " +
                "id_producto INT, " +
                "precio DECIMAL(10, 2), " +
                "FOREIGN KEY (id_pedido) REFERENCES Pedidos(id_pedido), " +
                "FOREIGN KEY (id_producto) REFERENCES Productos(id_producto))";
            statement.executeUpdate(sql);
            System.out.println("Tabla DetallePedidos creada con éxito.");

            sql = "ALTER TABLE Pedidos ADD COLUMN id_empleado_repartidor INT, " +
                "ADD FOREIGN KEY (id_empleado_repartidor) REFERENCES Empleados(id_empleado)";
            statement.executeUpdate(sql);
            System.out.println("Agregados detalles de reparto a la tabla Pedidos.");



            // Cerrar las conexiones
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

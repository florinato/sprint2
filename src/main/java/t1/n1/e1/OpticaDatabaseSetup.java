package t1.n1.e1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OpticaDatabaseSetup {

    public static void main(String[] args) {
        // Datos de conexión al servidor MySQL sin especificar la base de datos
        String urlSinDB = "jdbc:mysql://localhost:3306/";
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root"; // Tu usuario de MySQL
        String contrasena = ""; // Tu contraseña de MySQL

        try {
            // Conectar al servidor MySQL sin una base de datos específica
            Connection conexion = DriverManager.getConnection(urlSinDB, usuario, contrasena);
            Statement statement = conexion.createStatement();

            // Crear la base de datos OpticaDB si no existe
            String sql = "CREATE DATABASE IF NOT EXISTS OpticaDB";
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada o ya existente.");

            // Cerrar la conexión inicial
            statement.close();
            conexion.close();

            // Conectar a la base de datos OpticaDB
            url = "jdbc:mysql://localhost:3306/OpticaDB";
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            statement = conexion.createStatement();

            // Crear tabla Proveedores
            sql = "CREATE TABLE IF NOT EXISTS Proveedores (" +
                    "id_proveedor INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "direccion TEXT, " +
                    "telefono VARCHAR(20), " +
                    "fax VARCHAR(20), " +
                    "NIF VARCHAR(20))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Proveedores creada con éxito.");

            // Crear tabla Gafas
            sql = "CREATE TABLE IF NOT EXISTS Gafas (" +
                    "id_gafas INT AUTO_INCREMENT PRIMARY KEY, " +
                    "marca VARCHAR(255), " +
                    "graduacion_izq FLOAT, " +
                    "graduacion_der FLOAT, " +
                    "tipo_montura VARCHAR(100), " +
                    "color_montura VARCHAR(100), " +
                    "color_vidrio_izq VARCHAR(100), " +
                    "color_vidrio_der VARCHAR(100), " +
                    "precio DECIMAL(10,2), " +
                    "id_proveedor INT, " +
                    "FOREIGN KEY (id_proveedor) REFERENCES Proveedores(id_proveedor))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Gafas creada con éxito.");

            // Crear tabla Clientes
            sql = "CREATE TABLE IF NOT EXISTS Clientes (" +
                    "id_cliente INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "direccion_postal TEXT, " +
                    "telefono VARCHAR(20), " +
                    "email VARCHAR(255), " +
                    "fecha_registro DATE, " +
                    "recomendado_por INT, " +
                    "FOREIGN KEY (recomendado_por) REFERENCES Clientes(id_cliente))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Clientes creada con éxito.");
            sql = "CREATE TABLE IF NOT EXISTS Empleados (" +
                "id_empleado INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255))";
            
            statement.executeUpdate(sql);
            System.out.println("Tabla Empleados creada con éxito.");

            // Crear tabla Ventas
            sql = "CREATE TABLE IF NOT EXISTS Ventas (" +
                "id_venta INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_empleado INT, " +
                "id_cliente INT, " +
                "id_gafas INT, " +
                "fecha_venta DATE, " +
                "FOREIGN KEY (id_empleado) REFERENCES Empleados(id_empleado), " +
                "FOREIGN KEY (id_cliente) REFERENCES Clientes(id_cliente), " +
                "FOREIGN KEY (id_gafas) REFERENCES Gafas(id_gafas))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Ventas creada con éxito.");
            
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



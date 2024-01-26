package t1.n3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SpotifyDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root"; // Ajusta según tu configuración
        String contraseña = ""; // Ajusta según tu configuración

        try {
            // Conectar a MySQL
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement statement = conexion.createStatement();

            // Crear la base de datos
            String sql = "CREATE DATABASE IF NOT EXISTS SpotifyDB";
            statement.executeUpdate(sql);
            System.out.println("Base de datos SpotifyDB creada o ya existente.");

            // Cerrar la conexión y reconectar a la base de datos específica
            statement.close();
            conexion.close();
            url = "jdbc:mysql://localhost:3306/SpotifyDB";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();

            // Crear tabla Usuarios
            sql = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                  "id_usuario INT AUTO_INCREMENT PRIMARY KEY, " +
                  "email VARCHAR(255), " +
                  "password VARCHAR(255), " +
                  "nombre_usuario VARCHAR(255), " +
                  "fecha_nacimiento DATE, " +
                  "sexo VARCHAR(50), " +
                  "pais VARCHAR(255), " +
                  "codigo_postal VARCHAR(10), " +
                  "tipo VARCHAR(50))"; // 'free', 'premium'
            statement.executeUpdate(sql);
            System.out.println("Tabla Usuarios creada con éxito.");

            // Crear tabla Subscripciones
            sql = "CREATE TABLE IF NOT EXISTS Subscripciones (" +
                "id_subscripcion INT AUTO_INCREMENT PRIMARY KEY, " +
                "fecha_inicio DATE, " +
                "fecha_renovacion DATE, " +
                "tipo_pago VARCHAR(50), " + // 'Tarjeta', 'PayPal'
                "id_usuario INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))";
                statement.executeUpdate(sql);
            System.out.println("Tabla Subscripciones creada con éxito.");

            // Crear tabla para información de tarjetas de crédito
                sql = "CREATE TABLE IF NOT EXISTS TarjetasCredito (" +
                "id_tarjeta INT AUTO_INCREMENT PRIMARY KEY, " +
                "numero_tarjeta VARCHAR(255), " +
                "mes_caducidad INT, " +
                "ano_caducidad INT, " +
                "codigo_seguridad VARCHAR(10), " +
                "id_subscripcion INT, " +
                "FOREIGN KEY (id_subscripcion) REFERENCES Subscripciones(id_subscripcion))";
            statement.executeUpdate(sql);
            System.out.println("Tabla TarjetasCredito creada con éxito.");

                // Crear tabla para información de cuentas PayPal
                sql = "CREATE TABLE IF NOT EXISTS PayPal (" +
                "id_paypal INT AUTO_INCREMENT PRIMARY KEY, " +
                "usuario_paypal VARCHAR(255), " +
                "id_subscripcion INT, " +
                "FOREIGN KEY (id_subscripcion) REFERENCES Subscripciones(id_subscripcion))";
            statement.executeUpdate(sql);
            System.out.println("Tabla PayPal creada con éxito.");
            // Crear tabla Playlists
            sql = "CREATE TABLE IF NOT EXISTS Playlists (" +
                "id_playlist INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255), " +
                "numero_canciones INT, " +
                "fecha_creacion DATE, " +
                "eliminada BOOLEAN DEFAULT FALSE, " + // FALSE significa no eliminada
                "fecha_eliminacion DATE, " + // Fecha en la que se marcó como eliminada
                "id_usuario INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Playlists creada con éxito.");
            // Crear tabla Artistas
            sql = "CREATE TABLE IF NOT EXISTS Artistas (" +
                "id_artista INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255), " +
                "imagen_artista VARCHAR(255))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Artistas creada con éxito.");
            // Crear tabla Álbumes
            sql = "CREATE TABLE IF NOT EXISTS Albumes (" +
                "id_album INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255), " +
                "ano_publicacion INT, " +
                "imagen_portada VARCHAR(255), " +
                "id_artista INT, " + // Relación con la tabla Artistas
                "FOREIGN KEY (id_artista) REFERENCES Artistas(id_artista))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Álbumes creada con éxito.");
            // Crear tabla Canciones
            sql = "CREATE TABLE IF NOT EXISTS Canciones (" +
                "id_cancion INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255), " +
                "duracion INT, " + // Duración en segundos
                "veces_reproducida INT DEFAULT 0, " +
                "id_album INT, " + // Relación con la tabla Álbumes
                "FOREIGN KEY (id_album) REFERENCES Albumes(id_album))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Canciones creada con éxito.");
            
            
            // Crear tabla Seguimientos
            sql = "CREATE TABLE IF NOT EXISTS Seguimientos (" +
                "id_seguimiento INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_usuario INT, " +
                "id_artista INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_artista) REFERENCES Artistas(id_artista))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Seguimientos creada con éxito.");
            // Crear tabla PlaylistCanciones
            sql = "CREATE TABLE IF NOT EXISTS PlaylistCanciones (" +
                "id_playlist_cancion INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_playlist INT, " +
                "id_cancion INT, " +
                "id_usuario_que_anadio INT, " + // ID del usuario que añadió la canción
                "fecha_anadida DATE, " +
                "FOREIGN KEY (id_playlist) REFERENCES Playlists(id_playlist), " +
                "FOREIGN KEY (id_cancion) REFERENCES Canciones(id_cancion), " +
                "FOREIGN KEY (id_usuario_que_anadio) REFERENCES Usuarios(id_usuario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla PlaylistCanciones creada con éxito.");
            // Crear tabla RegistroPagos
            sql = "CREATE TABLE IF NOT EXISTS RegistroPagos (" +
                "id_pago INT AUTO_INCREMENT PRIMARY KEY, " +
                "fecha_pago DATE, " +
                "numero_orden VARCHAR(255), " +
                "total DECIMAL(10, 2), " +
                "id_subscripcion INT, " +
                "FOREIGN KEY (id_subscripcion) REFERENCES Subscripciones(id_subscripcion))";
            statement.executeUpdate(sql);
            System.out.println("Tabla RegistroPagos creada con éxito.");
            // Crear tabla RelacionesArtistas
            sql = "CREATE TABLE IF NOT EXISTS RelacionesArtistas (" +
                "id_relacion INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_artista1 INT, " +
                "id_artista2 INT, " +
                "FOREIGN KEY (id_artista1) REFERENCES Artistas(id_artista), " +
                "FOREIGN KEY (id_artista2) REFERENCES Artistas(id_artista))";
            statement.executeUpdate(sql);
            System.out.println("Tabla RelacionesArtistas creada con éxito.");

            // Crear tabla CancionesFavoritas
            sql = "CREATE TABLE IF NOT EXISTS CancionesFavoritas (" +
                "id_favorito_cancion INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_usuario INT, " +
                "id_cancion INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_cancion) REFERENCES Canciones(id_cancion))";
            statement.executeUpdate(sql);
            System.out.println("Tabla CancionesFavoritas creada con éxito.");

            // Crear tabla AlbumesFavoritos
            sql = "CREATE TABLE IF NOT EXISTS AlbumesFavoritos (" +
                "id_favorito_album INT AUTO_INCREMENT PRIMARY KEY, " +
                "id_usuario INT, " +
                "id_album INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_album) REFERENCES Albumes(id_album))";
            statement.executeUpdate(sql);
            System.out.println("Tabla AlbumesFavoritos creada con éxito.");

            // Modificar tabla PlaylistCanciones para manejar playlists compartidas
            sql = "ALTER TABLE PlaylistCanciones ADD COLUMN es_colaboracion BOOLEAN DEFAULT FALSE";
            statement.executeUpdate(sql);
            System.out.println("Tabla PlaylistCanciones modificada para manejar playlists compartidas.");



// Cerrar las conexiones
statement.close();
conexion.close();
           

            // Cerrar las conexiones
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package t1.n2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class YutubeDb {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root"; // Ajusta según tu configuración
        String contraseña = ""; // Ajusta según tu configuración

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            Statement statement = conexion.createStatement();

            // Crear la base de datos Yutube
            String sql = "CREATE DATABASE IF NOT EXISTS Yutube";
            statement.executeUpdate(sql);
            System.out.println("Base de datos creada o ya existente.");

            statement.close();
            conexion.close();

            url = "jdbc:mysql://localhost:3306/Yutube";
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            statement = conexion.createStatement();

            // Crear tabla Usuarios
            sql = "CREATE TABLE IF NOT EXISTS Usuarios (" +
                "id_usuario INT AUTO_INCREMENT PRIMARY KEY, " +
                "email VARCHAR(255) UNIQUE, " +
                "password VARCHAR(255), " +
                "nombre_usuario VARCHAR(255), " +
                "fecha_nacimiento DATE, " +
                "sexo VARCHAR(50), " +
                "pais VARCHAR(255), " +
                "codigo_postal VARCHAR(10))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Usuarios creada con éxito.");

            // Crear tabla Canales
            sql = "CREATE TABLE IF NOT EXISTS Canales (" +
                "id_canal INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255), " +
                "descripcion TEXT, " +
                "fecha_creacion DATE, " +
                "id_usuario INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Canales creada con éxito.");

            // Crear tabla Videos
            sql = "CREATE TABLE IF NOT EXISTS Videos (" +
                "id_video INT AUTO_INCREMENT PRIMARY KEY, " +
                "titulo VARCHAR(255), " +
                "descripcion TEXT, " +
                "tamano INT, " +
                "nombre_archivo VARCHAR(255), " +
                "duracion INT, " +
                "thumbnail VARCHAR(255), " +
                "num_reproducciones INT DEFAULT 0, " +
                "num_likes INT DEFAULT 0, " +
                "num_dislikes INT DEFAULT 0, " +
                "estado VARCHAR(50), " +
                "fecha_publicacion DATETIME, " +
                "id_usuario INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Videos creada con éxito.");

            // Crear tabla Etiquetas
            sql = "CREATE TABLE IF NOT EXISTS Etiquetas (" +
                "id_etiqueta INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre_etiqueta VARCHAR(255))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Etiquetas creada con éxito.");

            // Crear tabla VideoEtiquetas
            sql = "CREATE TABLE IF NOT EXISTS VideoEtiquetas (" +
                "id_video INT, " +
                "id_etiqueta INT, " +
                "PRIMARY KEY (id_video, id_etiqueta), " +
                "FOREIGN KEY (id_video) REFERENCES Videos(id_video), " +
                "FOREIGN KEY (id_etiqueta) REFERENCES Etiquetas(id_etiqueta))";
            statement.executeUpdate(sql);
            System.out.println("Tabla VideoEtiquetas creada con éxito.");

            // Crear tabla Playlists
            sql = "CREATE TABLE IF NOT EXISTS Playlists (" +
                "id_playlist INT AUTO_INCREMENT PRIMARY KEY, " +
                "nombre VARCHAR(255), " +
                "fecha_creacion DATETIME, " +
                "estado VARCHAR(50), " +
                "id_usuario INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Playlists creada con éxito.");

            // Crear tabla PlaylistVideos
            sql = "CREATE TABLE IF NOT EXISTS PlaylistVideos (" +
                "id_playlist INT, " +
                "id_video INT, " +
                "PRIMARY KEY (id_playlist, id_video), " +
                "FOREIGN KEY (id_playlist) REFERENCES Playlists(id_playlist), " +
                "FOREIGN KEY (id_video) REFERENCES Videos(id_video))";
            statement.executeUpdate(sql);
            System.out.println("Tabla PlaylistVideos creada con éxito.");

            // Crear tabla Comentarios
            sql = "CREATE TABLE IF NOT EXISTS Comentarios (" +
                "id_comentario INT AUTO_INCREMENT PRIMARY KEY, " +
                "texto TEXT, " +
                "fecha_hora DATETIME, " +
                "id_usuario INT, " +
                "id_video INT, " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_video) REFERENCES Videos(id_video))";
            statement.executeUpdate(sql);
            System.out.println("Tabla Comentarios creada con éxito.");

            // Crear tabla LikesDislikesVideos
            sql = "CREATE TABLE IF NOT EXISTS LikesDislikesVideos (" +
                "id_usuario INT, " +
                "id_video INT, " +
                "tipo VARCHAR(50), " +
                "fecha_hora DATETIME, " +
                "PRIMARY KEY (id_usuario, id_video), " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_video) REFERENCES Videos(id_video))";
            statement.executeUpdate(sql);
            System.out.println("Tabla LikesDislikesVideos creada con éxito.");

            // Crear tabla LikesDislikesComentarios
            sql = "CREATE TABLE IF NOT EXISTS LikesDislikesComentarios (" +
                "id_usuario INT, " +
                "id_comentario INT, " +
                "tipo VARCHAR(50), " +
                "fecha_hora DATETIME, " +
                "PRIMARY KEY (id_usuario, id_comentario), " +
                "FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario), " +
                "FOREIGN KEY (id_comentario) REFERENCES Comentarios(id_comentario))";
            statement.executeUpdate(sql);
            System.out.println("Tabla LikesDislikesComentarios creada con éxito.");

            // Cerrar las conexiones
            statement.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

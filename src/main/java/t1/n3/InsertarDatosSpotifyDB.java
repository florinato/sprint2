package t1.n3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertarDatosSpotifyDB {

      private static final String URL = "jdbc:mysql://localhost:3306/SpotifyDB";
      private static final String USUARIO = "root"; // Ajusta según tu configuración
      private static final String CONTRASENA = ""; // Ajusta según tu configuración

      public static void main(String[] args) {
            try {
                  // Conectar a la base de datos SpotifyDB
                  Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                  Statement statement = conexion.createStatement();

                  // Insertar datos en la tabla Usuarios
                  String sql = "INSERT INTO Usuarios (email, password, nombre_usuario, fecha_nacimiento, sexo, pais, codigo_postal, tipo) VALUES "
                              +
                              "('usuario1@example.com', 'password1', 'usuario1', '1990-01-01', 'Masculino', 'España', '28001', 'free'), "
                              +
                              "('usuario2@example.com', 'password2', 'usuario2', '1985-02-02', 'Femenino', 'España', '28002', 'premium')";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Usuarios.");

                  // Insertar datos en la tabla Subscripciones
                  sql = "INSERT INTO Subscripciones (fecha_inicio, fecha_renovacion, tipo_pago, id_usuario) VALUES " +
                              "('2023-01-01', '2023-02-01', 'Tarjeta', 2), " +
                              "('2023-01-01', '2023-02-01', 'PayPal', 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Subscripciones.");

                  // Insertar datos en la tabla TarjetasCredito
                  sql = "INSERT INTO TarjetasCredito (numero_tarjeta, mes_caducidad, ano_caducidad, codigo_seguridad, id_subscripcion) VALUES "
                              +
                              "('1234567890123456', 12, 2024, '123', 1)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla TarjetasCredito.");

                  // Insertar datos en la tabla PayPal
                  sql = "INSERT INTO PayPal (usuario_paypal, id_subscripcion) VALUES " +
                              "('paypal_user@example.com', 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla PayPal.");

                  // Insertar datos en la tabla Playlists
                  sql = "INSERT INTO Playlists (titulo, numero_canciones, fecha_creacion, id_usuario) VALUES " +
                              "('Mi Playlist Favorita', 10, '2023-01-01', 1)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Playlists.");

                  // Insertar datos en la tabla Artistas
                  sql = "INSERT INTO Artistas (nombre, imagen_artista) VALUES " +
                              "('Artista 1', 'imagen_artista1.jpg'), " +
                              "('Artista 2', 'imagen_artista2.jpg')";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Artistas.");

                  // Insertar datos en la tabla Albumes
                  sql = "INSERT INTO Albumes (titulo, ano_publicacion, imagen_portada, id_artista) VALUES " +
                              "('Album 1', 2020, 'imagen_portada1.jpg', 1), " +
                              "('Album 2', 2021, 'imagen_portada2.jpg', 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Albumes.");

                  // Insertar datos en la tabla Canciones
                  sql = "INSERT INTO Canciones (titulo, duracion, veces_reproducida, id_album) VALUES " +
                              "('Cancion 1', 200, 0, 1), " +
                              "('Cancion 2', 180, 0, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Canciones.");

                  // Insertar datos en la tabla Seguimientos
                  sql = "INSERT INTO Seguimientos (id_usuario, id_artista) VALUES " +
                              "(1, 1), " +
                              "(2, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla Seguimientos.");

                  // Insertar datos en la tabla PlaylistCanciones
                  sql = "INSERT INTO PlaylistCanciones (id_playlist, id_cancion, id_usuario_que_anadio, fecha_anadida, es_colaboracion) VALUES "
                              +
                              "(1, 1, 1, '2023-01-01', FALSE), " +
                              "(1, 2, 2, '2023-01-01', TRUE)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla PlaylistCanciones.");

                  // Insertar datos en la tabla RegistroPagos
                  sql = "INSERT INTO RegistroPagos (fecha_pago, numero_orden, total, id_subscripcion) VALUES " +
                              "('2023-01-01', 'ORD123', 9.99, 1), " +
                              "('2023-01-01', 'ORD124', 9.99, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla RegistroPagos.");

                  // Insertar datos en la tabla RelacionesArtistas
                  sql = "INSERT INTO RelacionesArtistas (id_artista1, id_artista2) VALUES " +
                              "(1, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla RelacionesArtistas.");

                  // Insertar datos en la tabla CancionesFavoritas
                  sql = "INSERT INTO CancionesFavoritas (id_usuario, id_cancion) VALUES " +
                              "(1, 1), " +
                              "(2, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla CancionesFavoritas.");

                  // Insertar datos en la tabla AlbumesFavoritos
                  sql = "INSERT INTO AlbumesFavoritos (id_usuario, id_album) VALUES " +
                              "(1, 1), " +
                              "(2, 2)";
                  statement.executeUpdate(sql);
                  System.out.println("Datos insertados en la tabla AlbumesFavoritos.");

                  // Cerrar las conexiones
                  statement.close();
                  conexion.close();

            } catch (Exception e) {
                  e.printStackTrace();
            }
      }
}

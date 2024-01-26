package t1.n2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class InsertarDatosYutube {
private static final String URL = "jdbc:mysql://localhost:3306/Yutube";
    private static final String USUARIO = "root"; // Tu usuario de MySQL
    private static final String CONTRASENA = ""; // Tu contraseña de MySQL

    public static void main(String[] args) {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            insertarUsuarios(conexion);
            insertarCanales(conexion);
            insertarVideos(conexion);
            insertarEtiquetas(conexion);
            insertarVideoEtiquetas(conexion);
            insertarPlaylists(conexion);
            insertarPlaylistVideos(conexion);
            insertarComentarios(conexion);
            insertarLikesDislikesVideos(conexion);
            insertarLikesDislikesComentarios(conexion);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void insertarUsuarios(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Usuarios (email, password, nombre_usuario, fecha_nacimiento, sexo, pais, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para usuarios
        Object[][] datosUsuarios = {
            {"usuario1@example.com", "pass123", "UsuarioUno", Date.valueOf("1990-01-01"), "Masculino", "España", "28001"},
            {"usuario2@example.com", "pass123", "UsuarioDos", Date.valueOf("1992-02-02"), "Femenino", "España", "28002"},
            {"usuario3@example.com", "pass123", "UsuarioTres", Date.valueOf("1993-03-03"), "Masculino", "México", "01010"},
            {"usuario4@example.com", "pass123", "UsuarioCuatro", Date.valueOf("1994-04-04"), "Femenino", "Argentina", "1000"},
            {"usuario5@example.com", "pass123", "UsuarioCinco", Date.valueOf("1995-05-05"), "Masculino", "Colombia", "110111"},
            {"usuario6@example.com", "pass123", "UsuarioSeis", Date.valueOf("1996-06-06"), "Femenino", "Perú", "15001"},
            {"usuario7@example.com", "pass123", "UsuarioSiete", Date.valueOf("1997-07-07"), "Masculino", "Chile", "8320000"},
            {"usuario8@example.com", "pass123", "UsuarioOcho", Date.valueOf("1998-08-08"), "Femenino", "España", "28008"},
            {"usuario9@example.com", "pass123", "UsuarioNueve", Date.valueOf("1999-09-09"), "Masculino", "México", "01210"},
            {"usuario10@example.com", "pass123", "UsuarioDiez", Date.valueOf("2000-10-10"), "Femenino", "Argentina", "2000"}
        };
    
        for (Object[] usuario : datosUsuarios) {
            preparedStatement.setString(1, (String) usuario[0]); // email
            preparedStatement.setString(2, (String) usuario[1]); // password
            preparedStatement.setString(3, (String) usuario[2]); // nombre_usuario
            preparedStatement.setDate(4, (Date) usuario[3]);    // fecha_nacimiento
            preparedStatement.setString(5, (String) usuario[4]); // sexo
            preparedStatement.setString(6, (String) usuario[5]); // pais
            preparedStatement.setString(7, (String) usuario[6]); // codigo_postal
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }  
    public static void insertarCanales(Connection conexion) throws SQLException {
        // SQL para insertar registros en la tabla Canales
        String sql = "INSERT INTO Canales (nombre, descripcion, fecha_creacion, id_usuario) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para canales
        Object[][] datosCanales = {
            {"Canal 1", "Descripción del Canal 1", Date.valueOf("2020-01-01"), 4},
            {"Canal 2", "Descripción del Canal 2", Date.valueOf("2020-02-02"), 2},
            {"Canal 3", "Descripción del Canal 3", Date.valueOf("2020-01-01"), 3},
            {"Canal 4", "Descripción del Canal 4", Date.valueOf("2020-02-02"), 2},
            {"Canal 5", "Descripción del Canal 5", Date.valueOf("2020-01-01"), 1},
            {"Canal 6", "Descripción del Canal 6", Date.valueOf("2020-02-02"), 2}
        };
        
        for (Object[] canal : datosCanales) {
            preparedStatement.setString(1, (String) canal[0]); // nombre del canal
            preparedStatement.setString(2, (String) canal[1]); // descripción del canal
            preparedStatement.setDate(3, (Date) canal[2]);    // fecha de creación del canal
            preparedStatement.setInt(4, (Integer) canal[3]);  // id_usuario (asegúrate de que exista en Usuarios)
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarVideos(Connection conexion) throws SQLException {
        // SQL para insertar registros en la tabla Videos
        String sql = "INSERT INTO Videos (titulo, descripcion, tamano, nombre_archivo,"+        
        "duracion, thumbnail, num_reproducciones, num_likes, num_dislikes, estado,"+ 
        "fecha_publicacion, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para videos
        Object[][] datosVideos = {
            {"Video 1", "Descripción del Video", 100, "video1.mp4", 120, "thumbnail1.jpg", 0, 0, 0, "publico",
             new java.sql.Timestamp(System.currentTimeMillis()), 1},
            {"Video 2", "Descripción del Video ", 200, "video2.mp4", 150, "thumbnail2.jpg", 0, 0, 0, "privado",
             new java.sql.Timestamp(System.currentTimeMillis()), 2},
            {"Video 3", "Descripción del Video ", 100, "video1.mp4", 120, "thumbnail1.jpg", 0, 0, 0, "publico",
             new java.sql.Timestamp(System.currentTimeMillis()), 1},
            {"Video 4", "Descripción del Video ", 200, "video2.mp4", 150, "thumbnail2.jpg", 0, 0, 0, "oculto",
             new java.sql.Timestamp(System.currentTimeMillis()), 2},
            {"Video 5", "Descripción del Video ", 100, "video1.mp4", 120, "thumbnail1.jpg", 0, 0, 0, "publico",
             new java.sql.Timestamp(System.currentTimeMillis()), 1},
            {"Video 6", "Descripción del Video ", 200, "video2.mp4", 150, "thumbnail2.jpg", 0, 0, 0, "privado",
             new java.sql.Timestamp(System.currentTimeMillis()), 2},
            {"Video 7", "Descripción del Video ", 100, "video1.mp4", 120, "thumbnail1.jpg", 0, 0, 0, "oculto",
             new java.sql.Timestamp(System.currentTimeMillis()), 1},
            {"Video 8", "Descripción del Video ", 200, "video2.mp4", 150, "thumbnail2.jpg", 0, 0, 0, "privado",
             new java.sql.Timestamp(System.currentTimeMillis()), 2},
            
        };
    
        for (Object[] video : datosVideos) {
            preparedStatement.setString(1, (String) video[0]); // titulo
            preparedStatement.setString(2, (String) video[1]); // descripcion
            preparedStatement.setInt(3, (Integer) video[2]);   // tamano
            preparedStatement.setString(4, (String) video[3]); // nombre_archivo
            preparedStatement.setInt(5, (Integer) video[4]);   // duracion
            preparedStatement.setString(6, (String) video[5]); // thumbnail
            preparedStatement.setInt(7, (Integer) video[6]);   // num_reproducciones
            preparedStatement.setInt(8, (Integer) video[7]);   // num_likes
            preparedStatement.setInt(9, (Integer) video[8]);   // num_dislikes
            preparedStatement.setString(10, (String) video[9]);// estado
            preparedStatement.setTimestamp(11, (java.sql.Timestamp) video[10]); // fecha_publicacion
            preparedStatement.setInt(12, (Integer) video[11]); // id_usuario
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarEtiquetas(Connection conexion) throws SQLException {
        // SQL para insertar registros en la tabla Etiquetas
        String sql = "INSERT INTO Etiquetas (nombre_etiqueta) VALUES (?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Lista de nombres de etiquetas
        String[] nombresEtiquetas = {
            "música", "cultura", "deporte", "tecnología", 
            "cocina", "viajes", "educación", "salud",
            "arte", "noticias", "humor", "ciencia"
        };
    
        for (String nombreEtiqueta : nombresEtiquetas) {
            preparedStatement.setString(1, nombreEtiqueta); // nombre_etiqueta
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarVideoEtiquetas(Connection conexion) throws SQLException {
        String sql = "INSERT INTO VideoEtiquetas (id_video, id_etiqueta) VALUES (?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        int[][] videoEtiquetas = {
            {1, 1},  // Asocia el video 1 con la etiqueta 1
            {1, 2},  // Asocia el video 1 con la etiqueta 2
            {2, 1},  // Asocia el video 2 con la etiqueta 1
            {2, 3},  // Asocia el video 2 con la etiqueta 3
            
        };
        // Iterar sobre cada par de video-etiqueta y ejecutar la inserción
    for (int[] videoE : videoEtiquetas) {
        preparedStatement.setInt(1, videoE[0]); // id_video
        preparedStatement.setInt(2, videoE[1]); // id_etiqueta

        preparedStatement.executeUpdate();
    }
        preparedStatement.close();
    }
    public static void insertarPlaylists(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Playlists (nombre, fecha_creacion, estado, id_usuario) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para playlists
        Object[][] datosPlaylists = {
            {"Playlist 1", new java.sql.Timestamp(System.currentTimeMillis()), "pública", 1},
            {"Playlist 2", new java.sql.Timestamp(System.currentTimeMillis()), "privada", 2},
            {"Playlist 3", new java.sql.Timestamp(System.currentTimeMillis()), "pública", 1},
            {"Playlist 4", new java.sql.Timestamp(System.currentTimeMillis()), "privada", 3},
            // Añadir más playlists según sea necesario...
        };
    
        for (Object[] playlist : datosPlaylists) {
            preparedStatement.setString(1, (String) playlist[0]); // nombre
            preparedStatement.setTimestamp(2, (java.sql.Timestamp) playlist[1]); // fecha_creacion
            preparedStatement.setString(3, (String) playlist[2]); // estado
            preparedStatement.setInt(4, (Integer) playlist[3]);   // id_usuario
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarPlaylistVideos(Connection conexion) throws SQLException {
        String sql = "INSERT INTO PlaylistVideos (id_playlist, id_video) VALUES (?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para relaciones playlist-video
        int[][] datosPlaylistVideos = {
            {1, 1}, // Asocia el video 1 con la playlist 1
            {1, 2}, 
            {2, 3}, 
            {2, 4}, 
            {3, 2}, 
            {3, 4},
            {3, 5},
            {2, 7},
        };
    
        for (int[] playlistVideo : datosPlaylistVideos) {
            preparedStatement.setInt(1,  playlistVideo[0]); // id_playlist
            preparedStatement.setInt(2,  playlistVideo[1]); // id_video
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarComentarios(Connection conexion) throws SQLException {
        String sql = "INSERT INTO Comentarios (texto, fecha_hora, id_usuario, id_video) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para comentarios
        Object[][] datosComentarios = {
            {"Me encantó este video", new java.sql.Timestamp(System.currentTimeMillis()), 1, 1},
            {"Muy informativo, gracias", new java.sql.Timestamp(System.currentTimeMillis()), 2, 1},
            {"No estoy de acuerdo con el punto de vista presentado", new java.sql.Timestamp(System.currentTimeMillis()), 3, 2},
            {"¿Podrías hacer un video sobre otro tema?", new java.sql.Timestamp(System.currentTimeMillis()), 4, 3},
            // Añadir más comentarios según sea necesario...
        };
    
        for (Object[] comentario : datosComentarios) {
            preparedStatement.setString(1, (String) comentario[0]); // texto
            preparedStatement.setTimestamp(2, (java.sql.Timestamp) comentario[1]); // fecha_hora
            preparedStatement.setInt(3, (Integer) comentario[2]);   // id_usuario
            preparedStatement.setInt(4, (Integer) comentario[3]);   // id_video
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarLikesDislikesVideos(Connection conexion) throws SQLException {
        String sql = "INSERT INTO LikesDislikesVideos (id_usuario, id_video, tipo, fecha_hora) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para likes y dislikes en videos
        Object[][] datosLikesDislikes = {
            {1, 1, "like", new java.sql.Timestamp(System.currentTimeMillis())},
            {2, 1, "like", new java.sql.Timestamp(System.currentTimeMillis())},
            {3, 2, "dislike", new java.sql.Timestamp(System.currentTimeMillis())},
            {4, 3, "like", new java.sql.Timestamp(System.currentTimeMillis())},
            // Añadir más registros según sea necesario...
        };
    
        for (Object[] likeDislike : datosLikesDislikes) {
            preparedStatement.setInt(1, (Integer) likeDislike[0]); // id_usuario
            preparedStatement.setInt(2, (Integer) likeDislike[1]); // id_video
            preparedStatement.setString(3, (String) likeDislike[2]); // tipo
            preparedStatement.setTimestamp(4, (java.sql.Timestamp) likeDislike[3]); // fecha_hora
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }
    public static void insertarLikesDislikesComentarios(Connection conexion) throws SQLException {
        String sql = "INSERT INTO LikesDislikesComentarios (id_usuario, id_comentario, tipo, fecha_hora) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
    
        // Datos de ejemplo para likes y dislikes en comentarios
        Object[][] datosLikesDislikesComentarios = {
            {1, 1, "like", new java.sql.Timestamp(System.currentTimeMillis())},
            {2, 1, "dislike", new java.sql.Timestamp(System.currentTimeMillis())},
            {3, 2, "like", new java.sql.Timestamp(System.currentTimeMillis())},
            {4, 3, "dislike", new java.sql.Timestamp(System.currentTimeMillis())},
            // Añadir más registros según sea necesario...
        };
    
        for (Object[] likeDislike : datosLikesDislikesComentarios) {
            preparedStatement.setInt(1, (Integer) likeDislike[0]); // id_usuario
            preparedStatement.setInt(2, (Integer) likeDislike[1]); // id_comentario
            preparedStatement.setString(3, (String) likeDislike[2]); // tipo
            preparedStatement.setTimestamp(4, (java.sql.Timestamp) likeDislike[3]); // fecha_hora
    
            preparedStatement.executeUpdate();
        }
    
        preparedStatement.close();
    }

}
    

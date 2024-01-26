package t2.e2;

import java.util.List;
import t2.e1.Consulta;
import t2.e1.GestorBaseDeDatos;
import t2.e1.LectorDeConsultas;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/universidad"; // Asegúrate de que esta URL es correcta
    private static final String USER = "root"; // Cambia esto por tu usuario de la base de datos
    private static final String PASSWORD = ""; // Cambia esto por tu contraseña de la base de datos

    public static void main(String[] args) {
        // Cambia el nombre del archivo por el camino correcto donde guardas tus consultas SQL
        List<Consulta> consultas = LectorDeConsultas.leerConsultasDeArchivo("src\\main\\java\\t2\\e2\\consultas_universidad.sql");
        GestorBaseDeDatos gestor = new GestorBaseDeDatos(URL, USER, PASSWORD);
        gestor.ejecutarConsultas(consultas);
    }
}


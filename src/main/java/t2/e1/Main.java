package t2.e1;

import java.util.List;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/schema_tienda";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        List<Consulta> consultas = LectorDeConsultas.leerConsultasDeArchivo("src\\main\\java\\t2\\e1\\consultas_tienda.sql");
        GestorBaseDeDatos gestor = new GestorBaseDeDatos(URL, USER, PASSWORD);
        gestor.ejecutarConsultas(consultas);
    }
}

    
package t2.e2;

import java.util.List;
import t2.e1.Consulta;
import t2.e1.GestorBaseDeDatos;
import t2.e1.LectorDeConsultas;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/universidad"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static void main(String[] args) {
        
        List<Consulta> consultas = LectorDeConsultas.leerConsultasDeArchivo("src\\main\\java\\t2\\e2\\consultas_universidad.sql");
        GestorBaseDeDatos gestor = new GestorBaseDeDatos(URL, USER, PASSWORD);
        gestor.ejecutarConsultas(consultas);
    }
}


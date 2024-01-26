package t2.e1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeConsultas {

    public static List<Consulta> leerConsultasDeArchivo(String nombreArchivo) {
        List<Consulta> consultas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String descripcion = linea;
                String sql = lector.readLine();
                // Omitir la línea vacía entre consultas
                lector.readLine();
                consultas.add(new Consulta(descripcion, sql));
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return consultas;
    }
}


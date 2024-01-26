package t2.e1;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class GestorBaseDeDatos {
    protected static  String URL;
    protected static String USER;
    protected static String PASSWORD;

    public GestorBaseDeDatos(String URL, String USER, String PASSWORD) {
        GestorBaseDeDatos.URL = URL;
        GestorBaseDeDatos.USER = USER;
        GestorBaseDeDatos.PASSWORD =PASSWORD;
    }

    public void ejecutarConsultas(List<Consulta> consultas) {
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner scanner = new Scanner(System.in)) {
            int numeroConsulta = 1;
            for (Consulta consulta : consultas) {
                System.out.println("Consulta " + numeroConsulta );
                ejecutarConsulta(conexion, consulta, scanner);
                numeroConsulta++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void ejecutarConsulta(Connection conexion, Consulta consulta, Scanner scanner) throws SQLException {
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(consulta.getSql())) {

            System.out.println(consulta.getDescripcion());
            imprimirResultados(resultSet);
            System.out.println("Presiona Enter para continuar...");
            scanner.nextLine();
        }
    }

    private void imprimirResultados(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + " ");
            }
            System.out.println();
        }

        if (columnCount == 0) {
            System.out.println("No hay datos para mostrar.");
        }
    }
}

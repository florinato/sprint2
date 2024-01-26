package t2.e1;

public class Consulta {
    private String descripcion;
    private String sql;

    public Consulta(String descripcion, String sql) {
        this.descripcion = descripcion;
        this.sql = sql;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getSql() {
        return sql;
    }
}

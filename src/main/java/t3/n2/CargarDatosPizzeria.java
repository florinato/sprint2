package t3.n2;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

public class CargarDatosPizzeria {

    public static void main(String[] args) {
        // Conectar a MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("pizzeria");

        // Eliminar la colección existente para evitar duplicados
        database.getCollection("tiendas").drop();

        // Crear la colección de Tiendas con toda la información autocontenida
        MongoCollection<Document> collection = database.getCollection("tiendas");

        Document tienda1 = new Document("_id", 1)
            .append("direccion", "Calle Falsa 123")
            .append("codigo_postal", "08001")
            .append("localidad", "Barcelona")
            .append("provincia", "Barcelona")
            .append("empleados", Arrays.asList(
                new Document("_id", 1)
                    .append("nombre", "Juan Perez")
                    .append("NIF", "12345678A")
                    .append("telefono", "600100200")
                    .append("rol", "cocinero"),
                new Document("_id", 2)
                    .append("nombre", "Ana Lopez")
                    .append("NIF", "87654321B")
                    .append("telefono", "600300400")
                    .append("rol", "repartidor")
            ))
            .append("clientes", Arrays.asList(
                new Document("_id", 1)
                    .append("nombre", "Roberto Morales")
                    .append("direccion", "Gran Via 22")
                    .append("codigo_postal", "08002")
                    .append("localidad", "Barcelona")
                    .append("provincia", "Barcelona")
                    .append("telefono", "610200300")
            ))
            .append("productos", Arrays.asList(
                new Document("_id", 1)
                    .append("nombre", "Pizza Margarita")
                    .append("descripcion", "Pizza de tomate y queso")
                    .append("imagen", "url-imagen-margarita")
                    .append("precio", 8),
                new Document("_id", 2)
                    .append("nombre", "Hamburguesa de Queso")
                    .append("descripcion", "Hamburguesa con queso cheddar")
                    .append("imagen", "url-imagen-hamburguesa")
                    .append("precio", 6),
                new Document("_id", 3)
                    .append("nombre", "Coca Cola")
                    .append("descripcion", "Bebida gaseosa")
                    .append("imagen", "url-imagen-coca")
                    .append("precio", 2)
            ))
            .append("comandas", Arrays.asList(
                new Document("_id", 1)
                    .append("cliente_id", 1)
                    .append("fecha_hora", new Date())
                    .append("tipo_entrega", "domicilio")
                    .append("productos", Arrays.asList(
                        new Document("_id", 1)
                            .append("nombre", "Pizza Margarita")
                            .append("cantidad", 1)
                            .append("precio", 8),
                        new Document("_id", 3)
                            .append("nombre", "Coca Cola")
                            .append("cantidad", 2)
                            .append("precio", 2)
                    ))
                    .append("total", 12)
                    .append("empleado_id", 2),
                new Document("_id", 3)
                    .append("cliente_id", 1)
                    .append("fecha_hora", new Date())
                    .append("tipo_entrega", "domicilio")
                    .append("productos", Arrays.asList(
                        new Document("_id", 1)
                            .append("nombre", "Pizza Margarita")
                            .append("cantidad", 2)
                            .append("precio", 8),
                        new Document("_id", 3)
                            .append("nombre", "Coca Cola")
                            .append("cantidad", 1)
                            .append("precio", 2)
                    ))
                    .append("total", 18)
                    .append("empleado_id", 2)
            ));

        Document tienda2 = new Document("_id", 2)
            .append("direccion", "Avenida Siempre Viva 742")
            .append("codigo_postal", "28001")
            .append("localidad", "Madrid")
            .append("provincia", "Madrid")
            .append("empleados", Arrays.asList(
                new Document("_id", 3)
                    .append("nombre", "Carlos Gomez")
                    .append("NIF", "18273645C")
                    .append("telefono", "600500600")
                    .append("rol", "cocinero"),
                new Document("_id", 4)
                    .append("nombre", "Laura Martín")
                    .append("NIF", "56473829D")
                    .append("telefono", "600700800")
                    .append("rol", "repartidor")
            ))
            .append("clientes", Arrays.asList(
                new Document("_id", 2)
                    .append("nombre", "Marta Vidal")
                    .append("direccion", "Paseo de la Castellana 100")
                    .append("codigo_postal", "28002")
                    .append("localidad", "Madrid")
                    .append("provincia", "Madrid")
                    .append("telefono", "620400500")
            ))
            .append("productos", Arrays.asList(
                new Document("_id", 1)
                    .append("nombre", "Pizza Margarita")
                    .append("descripcion", "Pizza de tomate y queso")
                    .append("imagen", "url-imagen-margarita")
                    .append("precio", 8),
                new Document("_id", 2)
                    .append("nombre", "Hamburguesa de Queso")
                    .append("descripcion", "Hamburguesa con queso cheddar")
                    .append("imagen", "url-imagen-hamburguesa")
                    .append("precio", 6),
                new Document("_id", 3)
                    .append("nombre", "Coca Cola")
                    .append("descripcion", "Bebida gaseosa")
                    .append("imagen", "url-imagen-coca")
                    .append("precio", 2)
            ))
            .append("comandas", Arrays.asList(
                new Document("_id", 2)
                    .append("cliente_id", 2)
                    .append("fecha_hora", new Date())
                    .append("tipo_entrega", "tienda")
                    .append("productos", Arrays.asList(
                        new Document("_id", 2)
                            .append("nombre", "Hamburguesa de Queso")
                            .append("cantidad", 2)
                            .append("precio", 6),
                        new Document("_id", 3)
                            .append("nombre", "Coca Cola")
                            .append("cantidad", 1)
                            .append("precio", 2)
                    ))
                    .append("total", 14)
                    .append("empleado_id", 3),
                new Document("_id", 4)
                    .append("cliente_id", 2)
                    .append("fecha_hora", new Date())
                    .append("tipo_entrega", "domicilio")
                    .append("productos", Arrays.asList(
                        new Document("_id", 2)
                            .append("nombre", "Hamburguesa de Queso")
                            .append("cantidad", 1)
                            .append("precio", 6),
                        new Document("_id", 3)
                            .append("nombre", "Coca Cola")
                            .append("cantidad", 3)
                            .append("precio", 2)
                    ))
                    .append("total", 12)
                    .append("empleado_id", 4)
            ));

        collection.insertMany(Arrays.asList(tienda1, tienda2));

        System.out.println("Datos insertados correctamente.");

        // Cerrar la conexión
        mongoClient.close();
    }
}
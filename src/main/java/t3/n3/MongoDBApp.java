package t3.n3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBApp {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("restaurants");
        MongoCollection<Document> collection = database.getCollection("coleccion");
        Scanner scanner = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\main\\java\\t3\\n3\\consultas.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Dividir enunciado y consulta
                String enunciado = parts[0];
                String consulta = parts[1];

                System.out.println(enunciado); // Mostrar el enunciado
                Document query = Document.parse(consulta);
                FindIterable<Document> result = collection.find(query);

                for (Document doc : result) {
                    System.out.println(doc.toJson());
                }
                System.out.println("Pulsa Enter para continuar...");
                scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
    }
}
}
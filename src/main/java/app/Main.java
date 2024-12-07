package app;

import controller.GraphController;
import model.Node;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GraphController graphController = new GraphController();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        final String graphPath = "resources\\data.json";

        graphController.loadGraph(graphPath);

        while (!exit) {
            System.out.println("Menú Principal");
            System.out.println("1. Recomendar productos a un usuario");
            System.out.println("2. Mostrar grafo");
            System.out.println("3. Mostrar ponderados de productos");
            System.out.println("4. Añadir nuevo usuario y calificación");
            System.out.println("5. Borrar nodo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: //Recomendar productos a un usuario
                    System.out.print("Ingrese el ID del usuario: ");
                    String userId = scanner.nextLine();
                    Node user = new Node(userId, "user");
                    List<Map.Entry<Node, Integer>> recommendations = graphController.recommendProducts(user);
                    for (Map.Entry<Node, Integer> entry : recommendations) {
                        System.out.println(entry.getKey().getId() + " - " + entry.getKey().getType() + " (" + entry.getValue() + ")");
                    }
                    break;
                case 2: // Mostrar Grafo
                    graphController.displayGraph();
                    break;
                case 3: // Mostrar ponderado de los productos actuales
                    System.err.print("Ponderados de productos:\n");
                    List<Map.Entry<Node, Integer>> productWeights = graphController.getProductWeights();
                    for (Map.Entry<Node, Integer> entry : productWeights) {
                        System.out.println(entry.getKey().getId() + " - " + entry.getKey().getType() + " (" + entry.getValue() + ")");
                    }
                    break;
                case 4: // Añadir nuevo usuario y calificación
                    System.out.print("Ingrese el ID del nuevo usuario: ");
                    String newUserId = scanner.nextLine();
                    System.out.print("Ingrese el ID del producto: ");
                    String productId = scanner.nextLine();
                    System.out.print("Ingrese la calificación (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    if(graphController.addUserRating(newUserId, productId, rating)){
                        graphController.saveGraph(graphPath);//Se guarda el grafo
                        graphController.loadGraph(graphPath);// Cargar el grafo actualizado
                    }
                    break;
                case 5: // Borrar nodo
                    System.out.print("Ingrese el ID del nodo a borrar: ");
                    String nodeId = scanner.nextLine();
                   if(graphController.deleteNode(nodeId)) {
                       graphController.saveGraph(graphPath);//Se guarda el grafo
                       graphController.loadGraph(graphPath);// Cargar el grafo actualizado
                   }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.err.print("Opción no válida. Por favor intente nuevamente.\n");
            }
            System.out.println();
        }

        scanner.close();
    }
}

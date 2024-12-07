package model;

import java.util.*;

public class GraphManager {
    private Graph graph;

    // Constructor
    public GraphManager() {
        this.graph = new Graph();
    }

    // Constructor que acepta un Graph
    public GraphManager(Graph graph) {
        this.graph = graph;
    }


    public boolean addUserRating(String userId, String productId, int rating) {
        // Verificar si el usuario ya existe
        Node user = graph.getNodeById(userId);

        // Si el  usuario ya existe, no permitir añadir
        if (user != null) {
            System.err.println("El usuario ya existe. No se puede añadir.");
            return false;
        }

        // Si el usuario no existe, crear uno nuevo
        user = new Node(userId, "user");
        graph.addNode(user);

        // Si el producto no existe, crear uno nuevo
        Node product = new Node(productId, "product");
        graph.addNode(product);

        // Añadir una arista con la calificación
        graph.addEdge(user, product, rating);

        System.err.println("Datos agregados con exito");
        return true;
    }

    public void addUserRatingForTest(String userId, String productId, int rating) {
        // Verificar si el usuario ya existe
        Node user = graph.getNodeById(userId);

        // Si el usuario no existe, crear uno nuevo
        if (user == null) {
            user = new Node(userId, "user");
            graph.addNode(user);
        }

        // Verificar si el producto ya existe
        Node product = graph.getNodeById(productId);

        // Si el producto no existe, crear uno nuevo
        if (product == null) {
            product = new Node(productId, "product");
            graph.addNode(product);
        }

        // Añadir una arista con la calificación
        graph.addEdge(user, product, rating);
    }


    // Método para eliminar un nodo y todas sus conexiones
    public boolean deleteNode(String nodeId) {
        Node node = graph.getNodeById(nodeId);

        if (node != null) {
            graph.removeNode(node);
            System.err.println("Nodo y sus conexiones eliminados exitosamente.");
            return true;
        } else {
            System.err.println("Nodo no encontrado.");
            return false;
        }
    }

    public List<Map.Entry<Node, Integer>> recommendProducts(Node user) {
        // Verificar si el usuario existe en el grafo
        if (graph.getNodeById(user.getId()) == null) {
            System.err.println("El usuario no existe en el grafo.");
            return new ArrayList<>();
        }

        Map<Node, Integer> recommendationScores = new HashMap<>(); // Almacena las valoraciones de cada producto
        Set<Node> productsRatedByUser = new HashSet<>(); // Almacena los productos ya valorados por el usuario

        // Buscar aristas donde el nodo de origen es el usuario dado
        for (Edge edge : graph.getEdges()) {
            if (edge.getSource().getId().equals(user.getId())) {
                Node product = edge.getTarget();
                productsRatedByUser.add(product);
            }
        }

        // Aumentar el puntaje de recomendación para productos que otros usuarios han valorado
        for (Edge edge : graph.getEdges()) {
            if (!productsRatedByUser.contains(edge.getTarget()) && !edge.getSource().getId().equals(user.getId())) {
                Node product = edge.getTarget();
                int weight = edge.getWeight();
                recommendationScores.put(product, recommendationScores.getOrDefault(product, 0) + weight);
            }
        }

        // Crear una lista ordenada de recomendaciones basadas en los puntajes
        List<Map.Entry<Node, Integer>> recommendations = new ArrayList<>(recommendationScores.entrySet());
        recommendations.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        return recommendations;
    }


    public List<Map.Entry<Node, Integer>> getProductWeights() {
        Map<Node, Integer> productWeights = new HashMap<>();

        for (Edge edge : graph.getEdges()) {
            Node product = edge.getTarget();
            int weight = edge.getWeight();
            productWeights.put(product, productWeights.getOrDefault(product, 0) + weight);
        }

        // Crear una lista ordenada de productos basados en los puntajes
        List<Map.Entry<Node, Integer>> sortedProducts = new ArrayList<>(productWeights.entrySet());
        sortedProducts.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        return sortedProducts;
    }

    public void displayGraph() {
        System.out.println(graph.toString());
    }

    public Graph getGraph() {
        return graph;
    }
}

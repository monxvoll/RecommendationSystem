package model;

import java.util.*;

public class GraphManager {
    private final Graph graph;

    // Constructor
    public GraphManager() {
        this.graph = new Graph();
    }

    // Constructor que acepta un Graph
    public GraphManager(Graph graph) {
        this.graph = graph;
    }


    public boolean addUserRating(String userId, String productId, int rating) {
        // Verificar si el producto existe
        Node product = graph.getNodeById(productId);
        if (product == null) {
            System.err.println("El producto debe existir");
            return false;
        }

        // Verificar si el usuario ya existe
        Node user = graph.getNodeById(userId);
        if (user != null) {
            System.err.println("El usuario ya existe. No se puede añadir.");
            return false;
        }

        // Si el usuario no existe, crear uno nuevo
        user = new Node(userId, "user");
        graph.addNode(user);

        // Añadir una arista con la calificación
        graph.addEdge(user, product, rating);

        System.err.println("Datos agregados con éxito");
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
        if (graph.getNodeById(user.getId()) == null) { // Utilizaz el map definido en graph para verificar si existe ese id en el map
            System.err.println("El usuario no existe en el grafo.");
            return new ArrayList<>();
        }
        // Se crea map que almacenara las valoraciones de cada producto (clave-valor)
        Map<Node, Integer> recommendationScores = new HashMap<>();
        // Set que almacena los productos ya valorados por el usuario (Nodos)
        Set<Node> productsRatedByUser = new HashSet<>();

        // Buscar aristas donde el nodo de origen es el usuario dado
        for (Edge edge : graph.getEdges()) { //Recorre cada arista de la lista
            if (edge.getSource().getId().equals(user.getId())) { //En cado de esa arista tener como origen el usuario actual
                Node product = edge.getTarget(); //Entonces guarda a donde apunta el usuario actual , el producto
                productsRatedByUser.add(product);
            }
        }

        // Aumentar el puntaje de recomendación para productos que otros usuarios han valorado
        for (Edge edge : graph.getEdges()) { //Recorra cada arista de la lista
            //Siempre y cuando el set no tenga el producto actual y su origen sea otro usuario , lo almacena
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

    //Metodo encargado de obtener los pesos de las aristas
    public List<Map.Entry<Node, Integer>> getProductWeights() {
        // Crear un mapa para almacenar los pesos (valoraciones) de cada producto
        Map<Node, Integer> productWeights = new HashMap<>();

        // Iterar sobre todas las aristas del grafo
        for (Edge edge : graph.getEdges()) {
            // Obtener el nodo de destino (producto) de la arista actual
            Node product = edge.getTarget();
            // Obtener el peso asociado a la arista actual
            int weight = edge.getWeight();
            // Añadir el peso al mapa, sumando al valor actual si el producto ya está en el mapa
            productWeights.put(product, productWeights.getOrDefault(product, 0) + weight);
        }

        // Crear una lista ordenada de productos basados en sus pesos acumulados
        List<Map.Entry<Node, Integer>> sortedProducts = new ArrayList<>(productWeights.entrySet());
        sortedProducts.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

        // Devolver la lista ordenada de productos
        return sortedProducts;
    }


    public void displayGraph() {
        System.out.println(graph.toString());
    }

    public Graph getGraph() {
        return graph;
    }
}

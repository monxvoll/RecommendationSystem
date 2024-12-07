package controller;

import model.Graph;
import model.Node;
import model.GraphManager;
import persistence.GraphPersistence;

import java.util.List;
import java.util.Map;

public class GraphController {
    private GraphManager graphManager;
    private GraphPersistence graphPersistence;

    // Constructor
    public GraphController() {
        this.graphManager = new GraphManager();
        this.graphPersistence = new GraphPersistence();
    }

    // Método para guardar el grafo en un archivo JSON
    public void saveGraph(String filePath) {
        graphPersistence.saveGraph(graphManager.getGraph(), filePath);
    }

    // Método para cargar el grafo desde un archivo JSON
    public void loadGraph(String filePath) {
        Graph graph = graphPersistence.loadGraph(filePath);
        if (graph != null) {
            this.graphManager = new GraphManager(graph);
        }
    }

    // Método para recomendar productos a un usuario específico
    public List<Map.Entry<Node, Integer>> recommendProducts(Node user) {
        return graphManager.recommendProducts(user);
    }

    //  Método para mostrar el grafo en la consola
    public void displayGraph() {
        graphManager.displayGraph();
    }

    public List<Map.Entry<Node, Integer>> getProductWeights() {
        return graphManager.getProductWeights();
    }

    public void addUserRating(String userId, String productId, int rating) {
        graphManager.addUserRating(userId, productId, rating);
    }

    public void deleteNode(String nodeId) {
        graphManager.deleteNode(nodeId);
    }
}

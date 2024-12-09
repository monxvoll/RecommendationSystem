package nointerface.app.controller;

import nointerface.app.model.Graph;
import nointerface.app.model.Node;
import nointerface.app.model.GraphManager;
import nointerface.app.persistence.GraphPersistence;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class GraphController {
    private GraphManager graphManager;
    private final GraphPersistence graphPersistence;

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


    public void displayGraph() {
        graphManager.displayGraph();
    }

    public List<Map.Entry<Node, Integer>> getProductWeights() {
        return graphManager.getProductWeights();
    }

    public boolean addUserRating(String userId, String productId, int rating) {
        return graphManager.addUserRating(userId, productId, rating);
    }

    //Metodo para obtener los productos existentes
    public List<String> getExistingProducts() {
        return graphManager.getExistingProducts();
    }
    public boolean deleteNode(String nodeId) {
        if(graphManager.deleteNode(nodeId)){
            return true;
        }
        return false;
    }
}

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Clase Graph para gestionar nodos y aristas
public class Graph implements Serializable {
    private Map<String, Node> nodes; // Mapa que guarda los nodos (usuarios y productos) con sus IDs como clave
    private List<Edge> edges; // Lista que guarda las aristas (interacciones)

    // Constructor
    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    // Método para agregar un nodo al grafo
    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    // Método para  agregar una arista al grafo
    public void addEdge(Node source, Node target, int weight) {
        Edge edge = new Edge(source, target, weight);
        edges.add(edge);
    }

    // Método para obtener un nodo por su ID
    public Node getNodeById(String id) {
        return nodes.get(id);
    }

    // Método para obtener los nodos del grafo
    public List<Node> getNodes() {
        return new ArrayList<>(nodes.values());
    }

    // Método para obtener las aristas del grafo
    public List<Edge> getEdges() {
        return edges;
    }

    // Método para eliminar un nodo y sus conexiones
    public void removeNode(Node node) {
        nodes.remove(node.getId());
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getSource().equals(node) || edge.getTarget().equals(node)) {
                iterator.remove();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph:\n");
        for (Edge edge : edges) {
            sb.append(edge.getSource().getId()).append(" -> ").append(edge.getTarget().getId()).append(" [").append(edge.getWeight()).append("]\n");
        }
        return sb.toString();
    }
}

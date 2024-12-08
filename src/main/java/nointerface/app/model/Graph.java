package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Clase Graph para gestionar nodos y aristas
public class Graph implements Serializable {
    private final Map<String, Node> nodes; // Mapa que guarda como clave el nombre del producto o usuario y como valor un objeto de tipo nodo que tiene la inforamcion de esa clave
    private final List<Edge> edges; // Lista que guarda las aristas (interacciones)


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
        return nodes.get(id); //Verifica si en el mpa existe un valor para esa clave
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
        nodes.remove(node.getId()); //Elimina un nodo del mapa
        Iterator<Edge> iterator = edges.iterator(); //Prepara un iterador sobre la lista de aristas
        while (iterator.hasNext()) { //Recorre de a 1 a 1
            Edge edge = iterator.next(); //Obtiene la siguiente arista
            if (edge.getSource().equals(node) || edge.getTarget().equals(node)) {//Si el nodo era el destino o el origen
                iterator.remove(); //Remove el iterador actual , la arista actual
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

package nointerface.app.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import nointerface.app.model.Graph;
import nointerface.app.model.Node;


import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GraphPersistence {

    // Método para guardar el grafo en un archivo JSON
    public void saveGraph(Graph graph, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(graph, writer);
            System.out.println("Grafo guardado exitosamente en " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el grafo.");
        }
    }

    // Método para cargar el grafo desde un archivo JSON.
    public Graph loadGraph(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type graphType = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> data = gson.fromJson(reader, graphType);

            // Cargar nodos
            Map<String, Map<String, String>> nodesMap = gson.fromJson(gson.toJson(data.get("nodes")), new TypeToken<Map<String, Map<String, String>>>() {}.getType());
            // Cargar aristas
            List<Map<String, Object>> edgesMap = gson.fromJson(gson.toJson(data.get("edges")), new TypeToken<List<Map<String, Object>>>() {}.getType());

            Graph graph = new Graph();

            // Añadir nodos al grafo
            for (Map.Entry<String, Map<String, String>> entry : nodesMap.entrySet()) {
                String id = entry.getKey();
                String type = entry.getValue().get("type");
                Node node = new Node(id, type);
                graph.addNode(node);
            }

            // Añadir  aristas al grafo
            for (Map<String, Object> edge : edgesMap) {
                Map<String, String> sourceMap = (Map<String, String>) edge.get("source");
                Map<String, String> targetMap = (Map<String, String>) edge.get("target");
                String sourceId = sourceMap.get("id");
                String targetId = targetMap.get("id");
                int weight = ((Double) edge.get("weight")).intValue();

                Node sourceNode = graph.getNodeById(sourceId);
                Node targetNode = graph.getNodeById(targetId);

                if (sourceNode != null && targetNode != null) {
                    graph.addEdge(sourceNode, targetNode, weight);
                } else {
                    System.out.println("No se pudo encontrar uno de los nodos para la arista: " + sourceId + " -> " + targetId);
                }
            }

            System.err.println("Grafo cargado exitosamente desde " + filePath);
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar el grafo.");
            return null;
        } catch (ClassCastException e) {
            e.printStackTrace();
            System.out.println("Error de casteo al cargar el grafo.");
            return null;
        }
    }
}

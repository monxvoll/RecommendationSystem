package withinterface.GraphicView.graph;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphDataLoader {

    public void cargarGrafoDesdeJSON(String filePath, SimpleGraph<String, DefaultEdge> grafo, Map<String, Map<String, Integer>> pesos, Map<String, String> tiposDeNodos) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            // Cargar nodos con su tipo
            JsonObject nodes = json.getAsJsonObject("nodes");
            for (Map.Entry<String, JsonElement> entry : nodes.entrySet()) {
                String nodeId = entry.getKey();
                String nodeType = entry.getValue().getAsJsonObject().get("type").getAsString();
                grafo.addVertex(nodeId);
                tiposDeNodos.put(nodeId, nodeType);  // Guardamos el tipo de cada nodo
            }

            // Cargar aristas y pesos
            JsonArray edges = json.getAsJsonArray("edges");
            for (JsonElement edgeElement : edges) {
                JsonObject edge = edgeElement.getAsJsonObject();
                String source = edge.getAsJsonObject("source").get("id").getAsString();
                String target = edge.getAsJsonObject("target").get("id").getAsString();
                int weight = edge.get("weight").getAsInt();

                grafo.addEdge(source, target);
                addPeso(pesos, source, target, weight);
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar el archivo JSON: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPeso(Map<String, Map<String, Integer>> pesos, String source, String target, int peso) {
        // Añadir el peso en ambas direcciones (si el grafo es no dirigido)
        pesos.computeIfAbsent(source, k -> new HashMap<>()).put(target, peso);
        pesos.computeIfAbsent(target, k -> new HashMap<>()).put(source, peso);
    }
}

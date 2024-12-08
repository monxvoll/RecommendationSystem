package withinterface.GraphicView.graph;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Map;
import java.util.Set;

public class GraphController {
    private SimpleGraph<String, DefaultEdge> grafo;
    private GraphDataLoader dataLoader;

    public GraphController() {
        grafo = new SimpleGraph<>(DefaultEdge.class);
        dataLoader = new GraphDataLoader();
    }

    public void cargarGrafoDesdeJSON(String filePath, Map<String, Map<String, Integer>> pesos, Map<String, String> tiposDeNodos) {
        dataLoader.cargarGrafoDesdeJSON(filePath, grafo, pesos, tiposDeNodos);
    }

    public Set<String> getNodos() {
        return grafo.vertexSet();
    }
}

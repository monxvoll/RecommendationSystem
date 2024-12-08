package withinterface.GraphicView.graph;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.graph.Graph;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomLayout extends AbstractLayout<String, String> {
    private Map<String, String> tiposDeNodos;

    public CustomLayout(Graph<String, String> graph, Map<String, String> tiposDeNodos) {
        super(graph);
        this.tiposDeNodos = tiposDeNodos;
    }

    @Override
    public void initialize() {
        int width = getSize().width;
        int height = getSize().height;

        // Filtrar los nodos por tipo
        List<String> users = graph.getVertices().stream()
                .filter(v -> "user".equals(tiposDeNodos.get(v)))
                .collect(Collectors.toList());

        List<String> products = graph.getVertices().stream()
                .filter(v -> "product".equals(tiposDeNodos.get(v)))
                .collect(Collectors.toList());

        // Calcular el espaciado horizontal y aumentar el tamaño de separación
        int userSpacing = width / (users.size() + 1);
        int productSpacing = width / (products.size() + 1);
        int userY = height / 4;
        int productY = 3 * height / 4;

        // Posicionar los nodos con mayor separación
        for (int i = 0; i < users.size(); i++) {
            int x = (i + 1) * userSpacing;
            setLocation(users.get(i), new Point(x, userY));
        }

        for (int i = 0; i < products.size(); i++) {
            int x = (i + 1) * productSpacing;
            setLocation(products.get(i), new Point(x, productY));
        }
    }

    @Override
    public void reset() {
        initialize();
    }
}

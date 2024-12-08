package withinterface.GraphicView.graph;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class GraphView extends JFrame {
    private Map<String, Map<String, Integer>> pesos; // Mapa para almacenar los pesos de las aristas
    private Map<String, String> tiposDeNodos; // Mapa para almacenar los tipos de los nodos
    private GraphController grafoController;

    public GraphView(GraphController grafoController) {
        this.grafoController = grafoController;
        this.pesos = new HashMap<>();
        this.tiposDeNodos = new HashMap<>();

        // Configurar la ventana
        setTitle("Grafo de Dispositivos");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el grafo utilizando JUNG
        Graph<String, String> grafo = new SparseMultigraph<>();

        // Cargar el grafo desde JSON
        grafoController.cargarGrafoDesdeJSON("resources/data.json", pesos, tiposDeNodos);

        // Añadir vértices al grafo
        for (String nodo : grafoController.getNodos()) {
            grafo.addVertex(nodo);
        }

        // Añadir aristas al grafo con identificadores únicos y control de duplicados
        AtomicInteger edgeId = new AtomicInteger(1);
        Set<String> addedEdges = new HashSet<>();
        for (Map.Entry<String, Map<String, Integer>> entry : pesos.entrySet()) {
            String source = entry.getKey();
            if ("user".equals(tiposDeNodos.get(source))) { // Solo añadir aristas desde los usuarios
                for (Map.Entry<String, Integer> targetEntry : entry.getValue().entrySet()) {
                    String target = targetEntry.getKey();
                    Integer peso = targetEntry.getValue();
                    String edgeKey1 = source + "->" + target;
                    String edgeKey2 = target + "->" + source; // Consideramos ambas direcciones para el control de duplicados
                    if (!addedEdges.contains(edgeKey1) && !addedEdges.contains(edgeKey2)) {
                        grafo.addEdge("Edge" + edgeId.getAndIncrement() + ":" + peso, source, target);
                        addedEdges.add(edgeKey1);
                        addedEdges.add(edgeKey2);
                    }
                }
            }
        }

        // Crear el layout personalizado y calcular el tamaño del grafo
        Layout<String, String> layout = new CustomLayout(grafo, tiposDeNodos);
        Dimension graphSize = new Dimension(1600, 1000);
        layout.setSize(graphSize);

        // Configurar la visualización con tamaño ajustado
        VisualizationViewer<String, String> vv = new VisualizationViewer<>(layout, graphSize);

        // Configurar las etiquetas de los vértices y aristas
        vv.getRenderContext().setVertexLabelTransformer(v -> v);
        vv.getRenderContext().setEdgeLabelTransformer(e -> {
            String[] parts = e.split(":");
            return parts[1];
        });
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

        // Personalizar la posición de las etiquetas de las aristas
        vv.getRenderContext().setEdgeLabelTransformer(e -> {
            String[] parts = e.split(":");
            return parts[1];
        });
        vv.getRenderContext().setEdgeLabelClosenessTransformer(e -> 0.1); // Coloca la etiqueta cerca del origen

        // Aumentar el tamaño de los vértices
        vv.getRenderContext().setVertexShapeTransformer(v -> new Ellipse2D.Double(-20, -20, 40, 40)); // Ajusta el tamaño aquí

        // Crear el GraphMouse con soporte para arrastrar con el botón derecho
        DefaultModalGraphMouse<String, String> gm = new DefaultModalGraphMouse<>();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);

        // Crear los controles de zoom
        ScalingControl scaler = new CrossoverScalingControl();
        vv.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                Point mousePoint = e.getPoint();
                if (e.getWheelRotation() < 0) {
                    scaler.scale(vv, 1.1f, mousePoint);
                } else {
                    scaler.scale(vv, 0.9f, mousePoint);
                }
            }
        });

        // Añadir la visualización al JFrame
        getContentPane().add(vv);

        // Ajustar el tamaño de la ventana al tamaño ajustado
        setSize(new Dimension(800, 600)); // Tamaño ajustado de la ventana

        // Hacer visible la ventana
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphController grafoController = new GraphController();
            GraphView frame = new GraphView(grafoController);
            frame.setVisible(true);
        });
    }
}

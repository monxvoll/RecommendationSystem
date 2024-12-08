package withinterface.GraphicView.recommend;

import nointerface.app.controller.GraphController;
import nointerface.app.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class RecommendActionListener implements ActionListener {
    private GraphController graphController;
    private RecommendView recomendarView;

    public RecommendActionListener(GraphController graphController, RecommendView recomendarView) {
        this.graphController = graphController;
        this.recomendarView = recomendarView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = recomendarView.getNombreTextField().getText().trim();

        // Cargar el grafo desde el archivo JSON
        graphController.loadGraph("resources/data.json");

        // Crear un nodo para el usuario
        Node user = new Node(nombre, "user");

        // Obtener las recomendaciones
        List<Map.Entry<Node, Integer>> recommendations = graphController.recommendProducts(user);

        if (recommendations.isEmpty()) {
            recomendarView.getMensajeLabel().setText("No se encontraron recomendaciones para '" + nombre + "'.");
            recomendarView.getMensajeLabel().setForeground(Color.RED);
        } else {
            // Construir texto de recomendaciones
            StringBuilder recomendacionTexto = new StringBuilder("Recomendaciones para '" + nombre + "':\n");
            for (Map.Entry<Node, Integer> entry : recommendations) {
                recomendacionTexto.append(entry.getKey().getId())
                        .append(" - ")
                        .append(entry.getKey().getType())
                        .append(" (Peso: ")
                        .append(entry.getValue())
                        .append(")\n");
            }

            // Crear un JTextArea para mostrar recomendaciones
            JTextArea textArea = new JTextArea(recomendacionTexto.toString());
            textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);

            // Agregar el JTextArea a un JScrollPane para soporte de scroll
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(400, 200));

            // Mostrar recomendaciones en un JOptionPane
            JOptionPane.showMessageDialog(null, scrollPane, "Recomendaciones", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

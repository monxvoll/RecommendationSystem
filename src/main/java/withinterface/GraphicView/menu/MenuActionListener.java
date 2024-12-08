package withinterface.GraphicView.menu;

import withinterface.GraphicView.rate.RateView;
import withinterface.GraphicView.delete.DeleteView;
import withinterface.GraphicView.graph.GraphController;
import withinterface.GraphicView.graph.GraphView;
import withinterface.GraphicView.recommend.RecommendView;
import withinterface.GraphicView.weighted.WeightedView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {
    private final String opcion;
    private final nointerface.app.controller.GraphController graphController;

    public MenuActionListener(String opcion, nointerface.app.controller.GraphController graphController) {
        this.opcion = opcion;
        this.graphController = graphController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Llamar al método correspondiente según la opción seleccionada
        switch (opcion) {
            case "Recomendar productos a un usuario":
                new RecommendView(graphController);  // Llamar a la interfaz de Recomendar
                break;
            case "Mostrar grafo":
                SwingUtilities.invokeLater(() -> {
                    GraphView grafoView = new GraphView(new GraphController());
                    grafoView.setVisible(true);
                });
                break;
            case "Mostrar ponderados de productos":
                // Llamar a la clase Ponderados para mostrar la tabla
                new WeightedView(graphController);
                break;
            case "Añadir nuevo usuario y calificación":
                // Llamar a la clase Calificar para mostrar el formulario
                new RateView(graphController);
                break;
            case "Borrar nodo":
                // Llamar a la clase Borrar para eliminar datos
                new DeleteView(graphController);
                break;
            default:
                break;
        }
    }
}

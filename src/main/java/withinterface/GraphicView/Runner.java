package withinterface.GraphicView;





import nointerface.app.controller.GraphController;
import withinterface.GraphicView.menu.MenuView;

import javax.swing.*;

public class Runner {
    public static void main(String[] args) {
        // Inicializar el GraphController y cargar el grafo
        GraphController graphController = new GraphController();
        graphController.loadGraph("resources/data.json");

        // Crear y mostrar el menú principal
        SwingUtilities.invokeLater(() -> new MenuView(graphController));
    }
}

package withinterface.GraphicView.rate;

import nointerface.app.controller.GraphController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RateActionListener implements ActionListener {
    private GraphController graphController;
    private RateView calificarView;

    public RateActionListener(GraphController graphController, RateView calificarView) {
        this.graphController = graphController;
        this.calificarView = calificarView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userId = calificarView.getNameField().getText().trim();
        String productId = (String) calificarView.getPhoneDropdown().getSelectedItem();
        int rating = calificarView.getStarsDropdown().getSelectedIndex() + 1; // Convertir índice a calificación (1-5)


        if (userId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El ID del usuario no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!userId.matches("[a-zA-Z0-9]{3,}")) {
            JOptionPane.showMessageDialog(null, "El ID del usuario debe contener solo letras o números y tener una longitud mínima de 3 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = graphController.addUserRating(userId, productId, rating);
        if (success) {
            graphController.saveGraph("resources/data.json");
            graphController.loadGraph("resources/data.json");
            JOptionPane.showMessageDialog(null, "Calificación añadida con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Este usuario ya existe, por favor digite otro.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

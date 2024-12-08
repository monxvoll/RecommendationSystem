package withinterface.GraphicView.delete;

import nointerface.app.controller.GraphController;

import javax.swing.*;
import java.awt.*;

public class DeleteView {
    private DeleteController borrarController;
    private JTextField nodeIdField;
    private JLabel mensajeLabel;

    public DeleteView(GraphController graphController) {
        this.borrarController = new DeleteController(graphController);
        crearInterfaz();
    }

    private void crearInterfaz() {
        // Crear la interfaz de usuario
        JFrame frame = new JFrame("Eliminar Nodo");
        frame.setSize(600, 400);  // Tamaño de la ventana más grande
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear un panel con fondo suave y distribución más controlada
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());  // Usamos GridBagLayout para centrar los componentes
        panel.setBackground(new Color(235, 245, 250));  // Fondo azul claro
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // Espaciado

        // Configuración de la cuadrícula
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espaciado entre los componentes
        gbc.anchor = GridBagConstraints.CENTER;  // Centrado de los elementos en la cuadrícula

        // Título
        JLabel title = new JLabel("Eliminar Nodo");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204));  // Color azul para el título
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(title, gbc);

        // Campo de texto para ingresar el ID del nodo
        nodeIdField = new JTextField(20);
        nodeIdField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nodeIdField.setPreferredSize(new Dimension(300, 40));  // Tamaño más adecuado
        nodeIdField.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));  // Borde azul
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Introduce el ID del nodo:"), gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nodeIdField, gbc);

        // Etiqueta para mostrar mensajes
        mensajeLabel = new JLabel(" ");
        mensajeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        mensajeLabel.setForeground(new Color(255, 69, 0));  // Color rojo para mensajes de error
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(mensajeLabel, gbc);

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));  // FlowLayout para los botones
        buttonPanel.setBackground(new Color(235, 245, 250));  // Fondo del panel de botones

        // Botón para eliminar el nodo
        JButton eliminarButton = new JButton("Eliminar Nodo");
        eliminarButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        eliminarButton.setBackground(new Color(64, 158, 255));  // Azul claro
        eliminarButton.setForeground(Color.WHITE);
        eliminarButton.setFocusPainted(false);
        eliminarButton.setPreferredSize(new Dimension(200, 40));
        eliminarButton.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2));  // Borde azul
        eliminarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(new Color(0, 123, 255));  // Efecto al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                eliminarButton.setBackground(new Color(64, 158, 255));  // Restaurar color
            }
        });
        eliminarButton.addActionListener(e -> {
            String nodeId = nodeIdField.getText().trim();

            if (nodeId.isEmpty()) {
                mensajeLabel.setText("Por favor, ingresa un ID de nodo.");
                return;
            }

            // Verificamos si el nodo existe
            if (borrarController.deleteNode(nodeId)) {
                mensajeLabel.setText("Nodo con ID '" + nodeId + "' eliminado.");
            } else {
                mensajeLabel.setText("No se encontró el nodo con ID: " + nodeId);
            }

            // Limpiar el campo de texto
            nodeIdField.setText("");
        });
        buttonPanel.add(eliminarButton);

        // Botón para volver
        JButton volverButton = new JButton("Volver");
        volverButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        volverButton.setBackground(new Color(204, 204, 204));  // Gris claro
        volverButton.setForeground(Color.BLACK);
        volverButton.setFocusPainted(false);
        volverButton.setPreferredSize(new Dimension(200, 40));
        volverButton.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169), 2));  // Borde gris
        volverButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                volverButton.setBackground(new Color(169, 169, 169));  // Efecto al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                volverButton.setBackground(new Color(204, 204, 204));  // Restaurar color
            }
        });
        volverButton.addActionListener(e -> frame.dispose()); // Ajuste para cerrar la ventana actual
        buttonPanel.add(volverButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(buttonPanel, gbc);  // Añadir el panel de botones

        // Crear un panel con fondo de color suave
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(235, 245, 250));  // Fondo azul claro
        backgroundPanel.setLayout(new GridBagLayout());  // Usamos GridBagLayout para centrar el contenido
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(panel, gbc);

        // Mostrar la interfaz
        frame.add(backgroundPanel);
        frame.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphController graphController = new GraphController();
            graphController.loadGraph("resources/data.json");
            new DeleteView(graphController);
        });
    }
}

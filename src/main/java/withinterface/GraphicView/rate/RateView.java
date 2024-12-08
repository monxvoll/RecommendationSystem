package withinterface.GraphicView.rate;

import nointerface.app.controller.GraphController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RateView {
    private GraphController graphController;
    private JTextField nameField;
    private JComboBox<String> starsDropdown;
    private JComboBox<String> phoneDropdown;

    public RateView(GraphController graphController) {
        this.graphController = graphController;
        crearInterfaz();
    }

    private void crearInterfaz() {
        // Crear el marco principal
        JFrame frame = new JFrame("Formulario de Calificación");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 350);  // Ajustar el tamaño
        frame.setLayout(new BorderLayout(10, 10));

        // Fondo suave y colores modernos
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Panel superior para el título
        JLabel titleLabel = new JLabel("Formulario de Calificación", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        titleLabel.setForeground(new Color(60, 60, 60));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel central para los campos
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta y campo de texto para el nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nombre del usuario:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        nameField.setFont(new Font("Roboto", Font.PLAIN, 14));
        formPanel.add(nameField, gbc);

        // Etiqueta y desplegable para calificación
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Calificación (1-5 estrellas):"), gbc);

        gbc.gridx = 1;
        String[] starsOptions = {"1 estrella", "2 estrellas", "3 estrellas", "4 estrellas", "5 estrellas"};
        starsDropdown = new JComboBox<>(starsOptions);
        starsDropdown.setFont(new Font("Roboto", Font.PLAIN, 14));
        formPanel.add(starsDropdown, gbc);

        // Etiqueta y desplegable para marcas de teléfonos
        gbc.gridx = 0;
        gbc.gridy = 2;

        // Obtener la lista de productos existentes en el grafo
        List<String> phoneOptionsList = getExistingProducts();
        String[] phoneOptions = phoneOptionsList.toArray(new String[0]);
        phoneDropdown = new JComboBox<>(phoneOptions);
        phoneDropdown.setFont(new Font("Roboto", Font.PLAIN, 14));
        phoneDropdown.setMaximumRowCount(phoneOptions.length);
        formPanel.add(phoneDropdown, gbc);

        frame.add(formPanel, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Botón para cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setPreferredSize(new Dimension(100, 35));

        // Botón para enviar
        JButton sendButton = new JButton("Enviar");
        sendButton.setFont(new Font("Roboto", Font.PLAIN, 14));
        sendButton.setBackground(new Color(40, 167, 69));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setPreferredSize(new Dimension(100, 35));
        sendButton.addActionListener(new RateActionListener(graphController, this));

        // Agregar botones al panel inferior
        buttonPanel.add(cancelButton);
        buttonPanel.add(sendButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Acción para el botón de cancelar
        cancelButton.addActionListener(e -> frame.dispose());

        // Estilo general de botones y componentes
        UIManager.put("Button.font", new Font("Roboto", Font.PLAIN, 14));
        UIManager.put("Label.font", new Font("Roboto", Font.PLAIN, 14));
        UIManager.put("ComboBox.font", new Font("Roboto", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Roboto", Font.PLAIN, 14));

        // Efecto hover para los botones
        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendButton.setBackground(new Color(33, 139, 56));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendButton.setBackground(new Color(40, 167, 69));
            }
        });

        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(185, 46, 57));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(220, 53, 69));
            }
        });

        // Hacer visible el marco
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private List<String> getExistingProducts() {
        return graphController.getExistingProducts();
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JComboBox<String> getStarsDropdown() {
        return starsDropdown;
    }

    public JComboBox<String> getPhoneDropdown() {
        return phoneDropdown;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraphController graphController = new GraphController();
            graphController.loadGraph("resources/data.json"); // Cargar el grafo aquí
            new RateView(graphController);
        });
    }
}


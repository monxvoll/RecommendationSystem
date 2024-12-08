package withinterface.GraphicView.menu;

import nointerface.app.controller.GraphController;

import javax.swing.*;
import java.awt.*;

public class MenuView {
    private GraphController graphController;

    public MenuView(GraphController graphController) {
        this.graphController = graphController;
        crearInterfaz();
    }

    private void crearInterfaz() {
        // Crear el marco principal
        JFrame frame = new JFrame("Menú Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);  // Aumentar el tamaño para mejorar la distribución
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear un panel principal con diseño de caja vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título con fondo y bordes
        JLabel title = new JLabel("Menú de Opciones");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));  // Fuente más moderna
        title.setForeground(new Color(0, 102, 204));  // Color azul para el título
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));  // Mayor espaciado adicional
        panel.add(title);

        // Fondo y estilo para los botones
        String[] opciones = {
                "Recomendar productos a un usuario",
                "Mostrar grafo",
                "Mostrar ponderados de productos",
                "Añadir nuevo usuario y calificación",
                "Borrar nodo"
        };

        Dimension buttonSize = new Dimension(400, 50);  // Dimensiones fijas para los botones

        for (String opcion : opciones) {
            JButton button = new JButton(opcion);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFont(new Font("Segoe UI", Font.BOLD, 18));  // Fuente más moderna y negrita
            button.setBackground(new Color(0, 123, 255));  // Color azul más oscuro
            button.setForeground(Color.WHITE);  // Color blanco para el texto
            button.setFocusPainted(false);
            button.setPreferredSize(buttonSize);  // Tamaño preferido
            button.setMaximumSize(buttonSize);  // Tamaño máximo
            button.setMinimumSize(buttonSize);  // Tamaño mínimo
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)));  // Bordes con línea azul y espacio

            // Efecto de pasar el mouse (hover)
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(30, 144, 255));  // Cambiar color al pasar el mouse
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(0, 123, 255));  // Restaurar color original
                }
            });

            // Acción del botón
            button.addActionListener(new MenuActionListener(opcion, graphController));

            // Añadir el botón al panel
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 20))); // Mayor espaciado entre botones
        }

        // Crear un panel con fondo sólido y sombra
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Establecer el fondo sólido (verde menta)
                g.setColor(new Color(144, 238, 144)); // Verde menta
                g.fillRect(0, 0, getWidth(), getHeight()); // Rellenar con el color de fondo

                // Sombra alrededor del panel
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 50)); // Sombra semitransparente
                g2d.fillRect(panel.getX() + 5, panel.getY() + 5, panel.getWidth(), panel.getHeight());
            }
        };

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER); // Añadir el panel con botones al panel con fondo sólido

        // Añadir el panel de fondo al marco
        frame.add(backgroundPanel);

        // Mostrar el marco
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Inicializar el GraphController y cargar el grafo
        GraphController graphController = new GraphController();
        graphController.loadGraph("resources/data.json");  // Cargar el grafo aquí

        // Crear y mostrar el menú principal
        SwingUtilities.invokeLater(() -> new MenuView(graphController));
    }
}

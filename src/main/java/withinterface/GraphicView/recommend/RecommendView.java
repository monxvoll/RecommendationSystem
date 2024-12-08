package withinterface.GraphicView.recommend;

import nointerface.app.controller.GraphController;

import javax.swing.*;
import java.awt.*;

public class RecommendView {

    public static JTextField nombreTextField;
    public static JLabel mensajeLabel;
    private static GraphController graphController;

    public RecommendView(GraphController graphController) {
        this.graphController = graphController;
        crearInterfaz();
    }

    public void crearInterfaz() {
        // Crear el JFrame con un diseño agradable
        JFrame frame = new JFrame("Verificador de Nombres");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 350); // Tamaño ajustado para evitar que los componentes se sobrepongan
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS)); // BoxLayout para distribución vertical

        // Fondo de la ventana
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // Fondo suave gris claro

        // Panel para el título y el campo de texto
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelTop.setBackground(new Color(245, 245, 245)); // Fondo del panel superior

        // Título
        JLabel tituloLabel = new JLabel("Ingrese un nombre para verificar");
        tituloLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelTop.add(tituloLabel);

        // Campo de texto para ingresar nombre
        nombreTextField = new JTextField(20);
        nombreTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nombreTextField.setPreferredSize(new Dimension(250, 30));
        panelTop.add(nombreTextField);

        // Botón para verificar el nombre
        JButton verificarButton = new JButton("Verificar");
        verificarButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        verificarButton.setBackground(new Color(64, 158, 255)); // Azul suave
        verificarButton.setForeground(Color.WHITE);
        verificarButton.setFocusPainted(false);
        verificarButton.setPreferredSize(new Dimension(150, 35));
        verificarButton.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237), 2)); // Borde azul
        verificarButton.addActionListener(new RecommendActionListener(graphController, this));
        panelTop.add(verificarButton);

        // Añadimos el panelTop al JFrame
        frame.add(panelTop);

        // Etiqueta para mostrar el mensaje (centrado)
        mensajeLabel = new JLabel("");
        mensajeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Tamaño de fuente más pequeño
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrado horizontal
        mensajeLabel.setPreferredSize(new Dimension(350, 30)); // Tamaño del label ajustado
        mensajeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrado horizontal dentro de BoxLayout
        frame.add(mensajeLabel); // Añadimos el mensaje después de los otros componentes

        // Espacio vacío para asegurar que el botón de cierre esté en la parte más baja
        frame.add(Box.createVerticalStrut(20)); // Espacio entre los componentes

        // Panel para el botón de cierre
        JPanel panelBottom = new JPanel();
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelBottom.setBackground(new Color(245, 245, 245)); // Fondo del panel inferior

        // Botón para cerrar la aplicación
        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cerrarButton.setBackground(new Color(220, 53, 69)); // Rojo para el botón de cierre
        cerrarButton.setForeground(Color.WHITE);
        cerrarButton.setFocusPainted(false);
        cerrarButton.setPreferredSize(new Dimension(150, 35));
        cerrarButton.setBorder(BorderFactory.createLineBorder(new Color(185, 46, 57), 2)); // Borde rojo
        cerrarButton.addActionListener(e -> frame.dispose()); // Cambio aquí
        panelBottom.add(cerrarButton);

        // Añadimos el panelBottom al JFrame
        frame.add(panelBottom);

        // Efecto hover para los botones
        verificarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                verificarButton.setBackground(new Color(0, 123, 255)); // Efecto hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                verificarButton.setBackground(new Color(64, 158, 255)); // Restaurar color al salir
            }
        });

        cerrarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarButton.setBackground(new Color(185, 46, 57)); // Efecto hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarButton.setBackground(new Color(220, 53, 69)); // Restaurar color al salir
            }
        });

        // Centrar la ventana
        frame.setLocationRelativeTo(null);

        // Establecer la visibilidad
        frame.setVisible(true);
    }

    public JTextField getNombreTextField() {
        return nombreTextField;
    }

    public JLabel getMensajeLabel() {
        return mensajeLabel;
    }
}

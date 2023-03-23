import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShapeDrawingApp extends JFrame {

    private JPanel shapePanel;
    private JButton circleButton, triangleButton, boxButton;

    public ShapeDrawingApp() {
        setTitle("Shape Drawing App");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shapePanel = new ShapePanel();
        add(shapePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        circleButton = new JButton("Circle");
        circleButton.addActionListener(new CircleButtonListener());
        buttonPanel.add(circleButton);

        triangleButton = new JButton("Triangle");
        triangleButton.addActionListener(new TriangleButtonListener());
        buttonPanel.add(triangleButton);

        boxButton = new JButton("Box");
        boxButton.addActionListener(new BoxButtonListener());
        buttonPanel.add(boxButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ShapePanel extends JPanel {

        private int x, y;

        public ShapePanel() {
            setBackground(Color.WHITE);
            addMouseListener(new ShapeMouseListener());
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (circleButton.getModel().isSelected()) {
                g.setColor(Color.RED);
                g.fillOval(x, y, 50, 50);
            } else if (triangleButton.getModel().isSelected()) {
                g.setColor(Color.GREEN);
                int[] xPoints = { x, x + 25, x + 50 };
                int[] yPoints = { y + 50, y, y + 50 };
                g.fillPolygon(xPoints, yPoints, 3);
            } else if (boxButton.getModel().isSelected()) {
                g.setColor(Color.BLUE);
                g.fillRect(x, y, 50, 50);
            }
        }

        private class ShapeMouseListener implements java.awt.event.MouseListener {

            public void mousePressed(java.awt.event.MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();
            }

            public void mouseClicked(java.awt.event.MouseEvent e) {
            }

            public void mouseReleased(java.awt.event.MouseEvent e) {
            }

            public void mouseEntered(java.awt.event.MouseEvent e) {
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
            }
        }
    }

    private class CircleButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            circleButton.getModel().setSelected(true);
            triangleButton.getModel().setSelected(false);
            boxButton.getModel().setSelected(false);
        }
    }

    private class TriangleButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            circleButton.getModel().setSelected(false);
            triangleButton.getModel().setSelected(true);
            boxButton.getModel().setSelected(false);
        }
    }

    private class BoxButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            circleButton.getModel().setSelected(false);
            triangleButton.getModel().setSelected(false);
            boxButton.getModel().setSelected(true);
        }
    }

    public static void main(String[] args) {
        ShapeDrawingApp app = new ShapeDrawingApp();
        app.setVisible(true);
    }
}

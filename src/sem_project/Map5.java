package src.sem_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class RouteFinderApp extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 800;
    private List<PointWithName> points = new ArrayList<>();
    private List<PointWithName> shortestPath = new ArrayList<>();
    private JTextField fromTextField;
    private JTextField toTextField;

    public RouteFinderApp() {
        setTitle("Route Finder App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        initializePoints();
        setupUI();
        pack();
        setLocationRelativeTo(null);
    }

    private void setupUI() {
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap(g);
                drawPoints(g);
                drawShortestPath(g);
            }
        };
        add(drawingPanel);

        JPanel controlPanel = new JPanel();
        fromTextField = new JTextField(15);
        toTextField = new JTextField(15);
        JButton findRouteButton = new JButton("Find Route");
        findRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromName = fromTextField.getText().trim();
                String toName = toTextField.getText().trim();

                PointWithName fromPoint = findPointByName(fromName);
                PointWithName toPoint = findPointByName(toName);

                if (fromPoint != null && toPoint != null) {
                    // In this basic example, we use a dummy shortest path between the points.
                    shortestPath.clear();
                    shortestPath.add(fromPoint);
                    shortestPath.add(toPoint);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(RouteFinderApp.this, "Invalid points!");
                }
            }
        });

        controlPanel.add(new JLabel("From: "));
        controlPanel.add(fromTextField);
        controlPanel.add(new JLabel("To: "));
        controlPanel.add(toTextField);
        controlPanel.add(findRouteButton);
        add(controlPanel, BorderLayout.NORTH);
    }

    private void initializePoints() {
        // Predefined points with names and coordinates
        points.add(new PointWithName(100, 200, "Point A"));
        points.add(new PointWithName(300, 100, "Point B"));
        points.add(new PointWithName(500, 300, "Point C"));
        points.add(new PointWithName(700, 200, "Point D"));
    }

    private void drawMap(Graphics g) {
        // In this basic example, we draw a simple map as a background.
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    private void drawPoints(Graphics g) {
        g.setColor(Color.RED);
        for (PointWithName point : points) {
            int x = point.x;
            int y = point.y;
            g.fillOval(x - 5, y - 5, 10, 10);
            g.drawString(point.name, x + 10, y - 10);
        }
    }

    private void drawShortestPath(Graphics g) {
        if (shortestPath.size() > 1) {
            g.setColor(Color.BLUE);
            PointWithName fromPoint = shortestPath.get(0);

            for (int i = 1; i < shortestPath.size(); i++) {
                PointWithName toPoint = shortestPath.get(i);

                // Calculate distance between points (just for demonstration purposes)
                double distance = calculateDistance(fromPoint, toPoint);
                String distanceText = String.format("%.2f km", distance);

                // Calculate estimated time (just for demonstration purposes)
                int estimatedTime = (int) (distance * 15); // Assuming average speed of 15 km/h
                String timeText = String.format("Estimated Time: %d mins", estimatedTime);

                // Draw curved path between points
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                g2d.draw(createCurvedLine(fromPoint, toPoint));

                // Draw distance and time text at the middle of the path
                int textX = (fromPoint.x + toPoint.x) / 2;
                int textY = (fromPoint.y + toPoint.y) / 2;
                g.drawString(distanceText, textX, textY);
                g.drawString(timeText, textX, textY + 15);

                fromPoint = toPoint;
            }
        }
    }

    private PointWithName findPointByName(String name) {
        for (PointWithName point : points) {
            if (point.name.equalsIgnoreCase(name)) {
                return point;
            }
        }
        return null;
    }

    private double calculateDistance(PointWithName p1, PointWithName p2) {
        int dx = p2.x - p1.x;
        int dy = p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy) / 100.0; // Dividing by 100 for demonstration purposes only
    }


    private Path2D createCurvedLine(Point p1, Point p2) {
        int controlX = (p1.x + p2.x) / 2;
        int controlY = Math.min(p1.y, p2.y) - 100;

        Path2D path = new Path2D.Double();
        path.moveTo(p1.x, p1.y);
        path.quadTo(controlX, controlY, p2.x, p2.y);
        return path;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RouteFinderApp().setVisible(true));
    }
}

class PointWithName extends Point {
    String name;

    public PointWithName(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }
}

package src.sem_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NorthwestCornerMethodApp extends JFrame {
    private static final int TABLE_ROWS = 5;
    private static final int TABLE_COLS = 5;
    private static final int CELL_WIDTH = 80;
    private static final int CELL_HEIGHT = 80;
    private static final int INFINITY = Integer.MAX_VALUE;
    private ArrayList<PointWithName> points = new ArrayList<>();
    private Map<PointWithName, List<PointWithName>> shortestPaths = new HashMap<>();
    private JTextField fromTextField;
    private JTextField toTextField;

    public NorthwestCornerMethodApp() {
        setTitle("Northwest Corner Method");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(CELL_WIDTH * TABLE_COLS, CELL_HEIGHT * TABLE_ROWS + 50)); // Added space for text fields
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
                drawTable(g);
                drawPoints(g);
                drawShortestPaths(g);
            }
        };
        add(drawingPanel);

        JPanel controlPanel = new JPanel();
        fromTextField = new JTextField(5);
        toTextField = new JTextField(5);
        JButton connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fromName = fromTextField.getText().trim();
                String toName = toTextField.getText().trim();

                PointWithName fromPoint = findPointByName(fromName);
                PointWithName toPoint = findPointByName(toName);

                if (fromPoint != null && toPoint != null) {
                    shortestPaths.clear();
                    shortestPaths.put(fromPoint, new ArrayList<>());
                    shortestPaths.get(fromPoint).add(toPoint);
                    calculateShortestPaths();
                    drawingPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(NorthwestCornerMethodApp.this, "Invalid points!");
                }
            }
        });

        controlPanel.add(new JLabel("From: "));
        controlPanel.add(fromTextField);
        controlPanel.add(new JLabel("To: "));
        controlPanel.add(toTextField);
        controlPanel.add(connectButton);
        add(controlPanel, BorderLayout.NORTH);
    }

    private void initializePoints() {
        // Predefined points with names
        points.add(new PointWithName(1, 1, "A"));
        points.add(new PointWithName(2, 3, "B"));
        points.add(new PointWithName(4, 0, "C"));
        points.add(new PointWithName(0, 4, "D"));
        points.add(new PointWithName(3, 2, "E"));
    }

    private void drawTable(Graphics g) {
        for (int row = 0; row < TABLE_ROWS; row++) {
            for (int col = 0; col < TABLE_COLS; col++) {
                int x = col * CELL_WIDTH;
                int y = row * CELL_HEIGHT;
                g.drawRect(x, y, CELL_WIDTH, CELL_HEIGHT);
            }
        }
    }

    private void drawPoints(Graphics g) {
        g.setColor(Color.RED);
        for (PointWithName point : points) {
            int x = point.y * CELL_WIDTH + CELL_WIDTH / 2;
            int y = point.x * CELL_HEIGHT + CELL_HEIGHT / 2;
            g.fillOval(x - 5, y - 5, 10, 10);
            g.drawString(point.name, x - 10, y - 10);
        }
    }

    private void drawShortestPaths(Graphics g) {
        g.setColor(Color.BLUE);
        for (Map.Entry<PointWithName, List<PointWithName>> entry : shortestPaths.entrySet()) {
            PointWithName src = entry.getKey();
            int srcX = src.y * CELL_WIDTH + CELL_WIDTH / 2;
            int srcY = src.x * CELL_HEIGHT + CELL_HEIGHT / 2;

            for (PointWithName dest : entry.getValue()) {
                int destX = dest.y * CELL_WIDTH + CELL_WIDTH / 2;
                int destY = dest.x * CELL_HEIGHT + CELL_HEIGHT / 2;
                g.drawLine(srcX, srcY, destX, destY);
            }
        }
    }

    private void calculateShortestPaths() {
        for (int i = 0; i < points.size(); i++) {
            PointWithName src = points.get(i);
            Map<PointWithName, Integer> distances = new HashMap<>();
            Map<PointWithName, PointWithName> previous = new HashMap<>();
            PriorityQueue<PointWithName> pq = new PriorityQueue<>((p1, p2) -> distances.get(p1) - distances.get(p2));

            for (PointWithName point : points) {
                distances.put(point, INFINITY);
                previous.put(point, null);
            }

            distances.put(src, 0);
            pq.add(src);

            while (!pq.isEmpty()) {
                PointWithName u = pq.poll();

                for (PointWithName v : points) {
                    if (!u.equals(v)) {
                        int alt = distances.get(u) + calculateDistance(u, v);
                        if (alt < distances.get(v)) {
                            distances.put(v, alt);
                            previous.put(v, u);
                            pq.add(v);
                        }
                    }
                }
            }

            shortestPaths.put(src, new ArrayList<>(previous.keySet()));
        }
    }

    private int calculateDistance(PointWithName p1, PointWithName p2) {
        // Simple Manhattan distance (can be customized if needed)
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private PointWithName findPointByName(String name) {
        for (PointWithName point : points) {
            if (point.name.equals(name)) {
                return point;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NorthwestCornerMethodApp().setVisible(true));
    }
}

class PointWithName extends Point {
    String name;

    public PointWithName(int x, int y, String name) {
        super(x, y);
        this.name = name;
    }
}

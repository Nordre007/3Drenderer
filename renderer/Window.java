package renderer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Window {
    int[] x = {0};
    int[] y = {0};

    public Window() {
        SwingUtilities.invokeLater(() -> createWindow());
    }

    public void createWindow() {
        JFrame frame = new JFrame("3D Renderer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        renderPanel renderpanel = new renderPanel();
        pane.add(renderpanel, BorderLayout.CENTER);

        frame.setSize(600, 600);
        frame.setVisible(true);

       

        renderpanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double yi = 180.0 / renderpanel.getHeight();
                double xi = 180.0 / renderpanel.getWidth();
                x[0] = (int) (e.getX() * xi);
                y[0] = -(int) (e.getY() * yi);
                renderpanel.repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    class renderPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getWidth(), getHeight());

            List<Triangle> tris = new ArrayList<Triangle>();
            tris.add(new Triangle(new Vertex(100, 100, 100),
                                new Vertex(-100, -100, 100),
                                new Vertex(-100, 100, -100),
                                Color.WHITE));
            tris.add(new Triangle(new Vertex(100, 100, 100),
                                new Vertex(-100, -100, 100),
                                new Vertex(100, -100, -100),
                                Color.RED));
            tris.add(new Triangle(new Vertex(-100, 100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(100, 100, 100),
                    Color.GREEN));
            tris.add(new Triangle(new Vertex(-100, 100, -100),
                    new Vertex(100, -100, -100),
                    new Vertex(-100, -100, 100),
                    Color.BLUE));

            double heading = Math.toRadians(x[0]);
                    Matrix headingTransform = new Matrix(new double[][]{
                            {Math.cos(heading), 0, -Math.sin(heading)},
                            {0, 1, 0},
                            {Math.sin(heading), 0, Math.cos(heading)}
                    });
            double pitch = Math.toRadians(y[0]);
                    Matrix pitchTransform = new Matrix(new double[][]{
                            {1, 0, 0},
                            {0, Math.cos(pitch), Math.sin(pitch)},
                            {0, -Math.sin(pitch), Math.cos(pitch)}
                    });
    // Merge matrices in advance
                    Matrix transform = headingTransform.multiplication(pitchTransform);
            
            g2.translate(getWidth() / 2, getHeight() / 2);
            g2.setColor(Color.WHITE);
            for (Triangle t : tris) {
                Vertex v1 = transform.transForm(t.v1);
                Vertex v2 = transform.transForm(t.v2);
                Vertex v3 = transform.transForm(t.v3);
                Path2D path = new Path2D.Double();
                path.moveTo(v1.x, v1.y);
                path.lineTo(v2.x, v2.y);
                path.lineTo(v3.x, v3.y);
                path.closePath();
                g2.draw(path);
            }


    }
}
    
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {

    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator generator;
    private Rectangle2D.Double range;

    /**
     * constructor
     */
    public FractalExplorer(int size) {
        displaySize = size;
        generator = new Mandelbrot();
        range = new Rectangle2D.Double();
        generator.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }

    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Lab4");
        myframe.add(display, BorderLayout.CENTER);
        JButton resetButton = new JButton("Reset");
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener((ActionListener) handler);
        myframe.add(resetButton, BorderLayout.SOUTH);
        MouseHandler click = new MouseHandler();
        display.addMouseListener((MouseListener) click);
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }

    private void drawFractal() {
        for (int x = 0; x < displaySize; x++) {
            for (int y = 0; y < displaySize; y++) {
                double xCoord = generator.getCoord(range.x, range.x + range.width, displaySize, x);
                double yCoord = generator.getCoord(range.y, range.y + range.height, displaySize, y);
                int iteration = generator.numIterations(xCoord, yCoord);
                if (iteration == -1) {
                    display.drawPixel(x, y, 0);
                } else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    private class ResetHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            generator.getInitialRange(range);
            drawFractal();
        }
    }


    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = generator.getCoord(range.x, range.x + range.width, displaySize, x);
            int y = e.getY();
            double yCoord = generator.getCoord(range.y, range.y + range.height, displaySize, y);
            generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}

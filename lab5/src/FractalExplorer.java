import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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
        JFrame myFrame = new JFrame("Lab5");
        myFrame.add(display, BorderLayout.CENTER);
        JButton resetButton = new JButton("Reset");
        ButtonHandler resetHandler = new ButtonHandler();
        resetButton.addActionListener(resetHandler);
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComboBox myComboBox = new JComboBox();
        FractalGenerator mandelbrotFractal = new Mandelbrot();
        myComboBox.addItem(mandelbrotFractal);
        FractalGenerator tricornFractal = new Tricorn();
        myComboBox.addItem(tricornFractal);
        FractalGenerator burningShipFractal = new BurningShip();
        myComboBox.addItem(burningShipFractal);
        ButtonHandler fractalChooser = new ButtonHandler();
        myComboBox.addActionListener(fractalChooser);
        JPanel myPanel = new JPanel();
        JLabel myLabel = new JLabel("Select fractal:");
        myPanel.add(myLabel);
        myPanel.add(myComboBox);
        myFrame.add(myPanel, BorderLayout.NORTH);
        JButton saveButton = new JButton("Save PNG");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);
        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
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

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                generator = (FractalGenerator) mySource.getSelectedItem();
                generator.getInitialRange(range);
                drawFractal();
            } else if (command.equals("Reset")) {
                generator.getInitialRange(range);
                drawFractal();
            } else if (command.equals("Save PNG")) {
                JFileChooser myFileChooser = new JFileChooser();
                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");
                myFileChooser.setFileFilter(extensionFilter);
                myFileChooser.setAcceptAllFileFilterUsed(false);
                int userSelection = myFileChooser.showSaveDialog(display);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = myFileChooser.getSelectedFile();
                    String file_name = file.toString();
                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Cannot Save Image",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else return;
            }
        }
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

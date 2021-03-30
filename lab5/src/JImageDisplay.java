import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent {
    private BufferedImage mainImage;

    /**
     * constructor
     *
     * @param width
     * @param height
     */
    public JImageDisplay(int width, int height) {
        mainImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(mainImage, 0, 0, mainImage.getWidth(),
                mainImage.getHeight(), null);
    }

    /**
     * clear Omage.
     */
    public void clearImage() {
        int[] blankArray = new int[getWidth() * getHeight()];
        mainImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }

    /**
     * pixel to Color.
     */
    public void drawPixel(int x, int y, int rgbColor) {
        mainImage.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return mainImage;
    }
}

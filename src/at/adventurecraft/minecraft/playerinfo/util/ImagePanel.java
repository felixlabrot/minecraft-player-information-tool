package at.adventurecraft.minecraft.playerinfo.util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * A {@link javax.swing.JPanel} which displays an {@link java.awt.Image} in its background.
 * <br>The image is sticked to the upper left corner of the panel.
 * @author Philipp Doppelhofer
 */
public final class ImagePanel extends AlphaPanel {
    private Image image;
    
    /**
     * Creates a new {@code ImagePanel}.
     * @param image the {@link java.awt.Image} to be displayed in the panel
     * @param alpha the initial alpha value (0 = fully transparent, 1 = fully visible)
     * @throws IOException
     * @throws IllegalArgumentException thrown if the specified alpha value is lower than 0 or greater than 1
     */
    public ImagePanel(URL image, float alpha) throws IOException, IllegalArgumentException {
        this(ImageIO.read(image), alpha);
    }
    
    /**
     * Creates a new {@code ImagePanel}.
     * @param image the {@link java.awt.Image} to be displayed in the panel
     * @param alpha the initial alpha value (0 = fully transparent, 1 = fully visible)
     * @throws IllegalArgumentException thrown if the specified alpha value is lower than 0 or greater than 1
     */
    public ImagePanel(Image image, float alpha) throws IllegalArgumentException {
        super(alpha);
        this.image = image;
    }
    
    /**
     * Returns the current {@link java.awt.Image} displayed.
     * @return the current {@link java.awt.Image}
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * Replaces the image displayed by the panel.
     * @param image the new {@link java.awt.Image}
     */
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
    
    /**
     * Replaces the image displayed by the panel.
     * @param image the URL to the new local image file
     * @throws IOException thrown if the image can't be loaded
     */
    public void setImage(URL image) throws IOException {
        setImage(ImageIO.read(image));
    }
    
    /**
     * Replaces the image displayed by the panel.
     * @param image the new image {@link java.io.File}
     * @throws IOException thrown if the image can't be loaded
     */
    public void setImage(File image) throws IOException {
        setImage(ImageIO.read(image));
    }
    
    /**
     * Returns the size of the image which is currently displayed in the panel.
     * @return the size of the image
     */
    public Dimension getImageSize() {
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }
    
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setPreferredSize(getImageSize());
        g.drawImage(this.image, 0, 0, this);
    }
}

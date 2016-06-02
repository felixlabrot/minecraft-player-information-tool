package at.adventurecraft.minecraft.playerinfo.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Philipp Doppelhofer
 */
public final class ImagePanel extends AlphaPanel {
    private Image image;
    
    public ImagePanel(URL image, float alpha) throws IOException, IllegalArgumentException {
        this(ImageIO.read(image), alpha);
    }
    
    public ImagePanel(Image image, float alpha) {
        super(alpha);
        this.image = image;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }
    
    public void setImage(URL imageURL) throws IOException {
        setImage(ImageIO.read(imageURL));
    }
    
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setPreferredSize(getImageSize());
        g.drawImage(this.image, 0, 0, this);
    }
    
    public Dimension getImageSize() {
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }
}

package at.adventurecraft.minecraft.playerinfo.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * An {@link javax.swing.JPanel} which supports transparency.
 * @author Philipp Doppelhofer
 */
public class AlphaPanel extends JPanel {
    private float alpha;
    
    /**
     * Creates a new {@code AlphaPanel}.
     * @param alpha the initial alpha value (0 = fully transparent, 1 = fully visible)
     * @throws IllegalArgumentException thrown if the specified alpha value is lower than 0 or greater than 1
     */
    public AlphaPanel(float alpha) {
        super();
        if(alpha < 0 || alpha > 1) throw new IllegalArgumentException("The alpha value must be between 0 and 1.");
        this.alpha = alpha;
    }
    
    /**
     * Returns the current alpha value.
     * @return the current alpha value (0 = fully transparent, 1 = fully visible)
     */
    public float getAlpha() {
        return alpha;
    }
    
    /**
     * Sets a new alpha value.
     * @param alpha the new alpha value (0 = fully transparent, 1 = fully visible)
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        repaint();
    }
    
    @Override public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        super.paint(g2);
        g2.dispose();
    }
}

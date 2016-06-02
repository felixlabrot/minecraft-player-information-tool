package at.adventurecraft.minecraft.playerinfo.gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public final class Main {
    private Main() {}
    
    private static PlayerInfoWindow piw;
    
    public static void main(String[] args) {
        try {
            main();
        } catch(Throwable t) {
            JOptionPane.showMessageDialog(null, "An unknown error occured!\n" + t, "Unknown Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void main() throws InterruptedException, InvocationTargetException, IOException {
        piw = new PlayerInfoWindow();
        
        SwingUtilities.invokeAndWait(() -> {
            piw.setVisible(true);
        });
    }
}

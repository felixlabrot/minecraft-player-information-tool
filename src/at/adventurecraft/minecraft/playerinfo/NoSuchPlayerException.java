package at.adventurecraft.minecraft.playerinfo;

/**
 * Thrown if the player doesn't exist.
 * @author Philipp Doppelhofer
 */
public final class NoSuchPlayerException extends RuntimeException {
    public NoSuchPlayerException() {
        super();
    }
    
    public NoSuchPlayerException(String message) {
        super(message);
    }
    
    public NoSuchPlayerException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NoSuchPlayerException(Throwable cause) {
        super(cause);
    }
}

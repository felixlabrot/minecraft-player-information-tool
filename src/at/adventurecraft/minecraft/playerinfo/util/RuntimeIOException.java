package at.adventurecraft.minecraft.playerinfo.util;

import java.io.IOException;

/**
 * Covers an {@link java.io.IOException} to a {@link java.lang.RuntimeException}
 * @author Philipp Doppelhofer
 */
public class RuntimeIOException extends RuntimeException {
    public RuntimeIOException(IOException e) {
        super(e);
    }
}

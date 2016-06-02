package at.adventurecraft.minecraft.playerinfo.gui;

import java.io.IOException;

public class RuntimeIOException extends RuntimeException {
    public RuntimeIOException(IOException e) {
        super(e);
    }
}

package com.puttysoftware.audio.wav;

import java.io.IOException;
import java.net.URL;

public abstract class WAVFactory {
    // Constants
    protected static final int EXTERNAL_BUFFER_SIZE = 4096; // 4Kb

    // Constructor
    protected WAVFactory() {
        super();
    }

    // Methods
    public abstract void start();

    public abstract void play() throws IOException;

    // Factories
    public static WAVFactory loadFile(final String file) {
        return new WAVFile(file);
    }

    public static WAVFactory loadResource(final URL resource) {
        return new WAVResource(resource);
    }

    public static void playFile(final String file) throws IOException {
        new WAVFile(file).play();
    }

    public static void playResource(final URL resource) throws IOException {
        new WAVResource(resource).play();
    }
}

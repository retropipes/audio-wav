package com.puttysoftware.audio.wav;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class WAVResource extends WAVFactory {
    private final URL soundURL;

    public WAVResource(final URL resURL) {
        super();
        this.soundURL = resURL;
    }

    @Override
    public void start() {
        if (this.soundURL != null) {
            try (InputStream inputStream = this.soundURL.openStream()) {
                new WAVPlayer(inputStream).start();
            } catch (final IOException e1) {
                return;
            }
        }
    }

    @Override
    public void play() throws IOException {
        if (this.soundURL != null) {
            try (InputStream inputStream = this.soundURL.openStream()) {
                new WAVPlayer(inputStream).start();
            }
        }
    }
}

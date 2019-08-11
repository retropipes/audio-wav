package com.puttysoftware.audio.wav;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

class WAVFile extends WAVFactory {
    private final String filename;

    public WAVFile(final String wavfile) {
        super();
        this.filename = wavfile;
    }

    @Override
    public void start() {
        if (this.filename != null) {
            final File soundFile = new File(this.filename);
            if (soundFile.exists()) {
                try (FileInputStream inputStream = new FileInputStream(
                        soundFile)) {
                    new WAVPlayer(inputStream).start();
                } catch (final IOException e1) {
                    return;
                }
            }
        }
    }

    @Override
    public void play() throws IOException {
        if (this.filename != null) {
            final File soundFile = new File(this.filename);
            if (soundFile.exists()) {
                try (FileInputStream inputStream = new FileInputStream(
                        soundFile)) {
                    new WAVPlayer(inputStream).start();
                }
            }
        }
    }
}

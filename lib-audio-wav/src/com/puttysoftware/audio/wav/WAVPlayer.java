package com.puttysoftware.audio.wav;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

class WAVPlayer extends Thread {
    private final InputStream soundStream;

    public WAVPlayer(final InputStream stream) {
        super();
        this.soundStream = stream;
    }

    @Override
    public void run() {
        try (AudioInputStream audioInputStream = AudioSystem
                .getAudioInputStream(this.soundStream)) {
            final AudioFormat format = audioInputStream.getFormat();
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class,
                    format);
            try (SourceDataLine auline = (SourceDataLine) AudioSystem
                    .getLine(info)) {
                auline.open(format);
                auline.start();
                int nBytesRead = 0;
                final byte[] abData = new byte[WAVFactory.EXTERNAL_BUFFER_SIZE];
                try {
                    while (nBytesRead != -1) {
                        nBytesRead = audioInputStream.read(abData, 0,
                                abData.length);
                        if (nBytesRead >= 0) {
                            auline.write(abData, 0, nBytesRead);
                        }
                    }
                } catch (final IOException e) {
                    return;
                } finally {
                    auline.drain();
                }
            } catch (final LineUnavailableException e) {
                try {
                    audioInputStream.close();
                } catch (final IOException e2) {
                    // Ignore
                }
                return;
            }
        } catch (final UnsupportedAudioFileException e1) {
            return;
        } catch (final IOException e1) {
            return;
        }
    }
}

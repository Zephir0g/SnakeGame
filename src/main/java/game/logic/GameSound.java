package game.logic;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GameSound {

    private Clip clip;

    public void playStartSound() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameSound.class.getResourceAsStream("/sound/start.wav"));
             AudioInputStream pcmStream = convertToPcm(audioInputStream)) {
            clip = AudioSystem.getClip();
            clip.open(pcmStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public void playEatSound() {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameSound.class.getResourceAsStream("/sound/eat.wav"));
             AudioInputStream pcmStream = convertToPcm(audioInputStream)) {
            clip = AudioSystem.getClip();
            clip.open(pcmStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }


    private AudioInputStream convertToPcm(AudioInputStream sourceStream) throws IOException, UnsupportedAudioFileException {
        AudioFormat sourceFormat = sourceStream.getFormat();
        AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                sourceFormat.getSampleRate(), 16,
                sourceFormat.getChannels(),
                sourceFormat.getChannels() * 2,
                sourceFormat.getSampleRate(), false);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (AudioInputStream targetStream = AudioSystem.getAudioInputStream(targetFormat, sourceStream)) {
            byte[] buffer = new byte[4096];
            int n;
            while ((n = targetStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
            }
        }

        byte[] pcmBytes = outputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(pcmBytes);
        return new AudioInputStream(byteArrayInputStream, targetFormat, pcmBytes.length / targetFormat.getFrameSize());
    }
}

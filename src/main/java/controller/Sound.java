package controller;

import org.example.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;

import static org.example.Main.soundVolume;

public class Sound {
    public static Clip collisionSound;
    public static Clip deathSound;
    public static Clip victorySound;
    public Sound() {
        try {
            File file = new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\collide.wav");
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                collisionSound = AudioSystem.getClip();
                collisionSound.open(sound);
                FloatControl gainControl =
                        (FloatControl) collisionSound.getControl(FloatControl.Type.MASTER_GAIN);

                gainControl.setValue(calculateVolumeDecrement());

            }
            else {
                throw new RuntimeException("Sound: file not found: ");
            }

            File deathSoundFile = new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\death.wav");
            if (deathSoundFile.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(deathSoundFile);
                // load the sound into memory (a Clip)
                deathSound = AudioSystem.getClip();
                deathSound.open(sound);
                FloatControl gainControl =
                        (FloatControl) deathSound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(calculateVolumeDecrement());
            }
            else {
                throw new RuntimeException("Sound: file not found: ");
            }


            File victorySoundFile = new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\victory.wav");
            if (victorySoundFile.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(victorySoundFile);
                // load the sound into memory (a Clip)
                victorySound = AudioSystem.getClip();
                victorySound.open(sound);
                FloatControl gainControl =
                        (FloatControl) victorySound.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(calculateVolumeDecrement());
            }
            else {
                throw new RuntimeException("Sound: file not found: ");
            }



        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    }
    public static void playBubble(){
        collisionSound.setFramePosition(0);  // Must always rewind!
        collisionSound.loop(0);
    }

    public static void playDeathSound(){
        deathSound.setFramePosition(0);  // Must always rewind!
        deathSound.loop(0);
    }

    public static void playVictorySound(){
//        victorySound.setFramePosition(0);  // Must always rewind!
        victorySound.loop(0);
    }

//    public static void pauseAllSounds(){
//        deathSound.stop();
//
//    }

    private float calculateVolumeDecrement(){
        return (float) 0.8*(soundVolume)-80;
    }
}
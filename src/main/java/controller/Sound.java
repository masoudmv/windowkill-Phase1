package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Sound {
    private Clip clip;
//    public static Sound sound;
    public static Clip bubble;
    public static Clip deathSound;
    public Sound() {
        // specify the sound to play
        // (assuming the sound can be played by the audio system)
        // from a wave File
        try {
            File file = new File("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\burst2.wav");
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                // load the sound into memory (a Clip)
                bubble = AudioSystem.getClip();
                bubble.open(sound);
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

        // play, stop, loop the sound clip
    }
    public static void playBubble(){
        bubble.setFramePosition(0);  // Must always rewind!
//        clip.start();
        bubble.loop(0);
    }

    public static void playDeathSound(){
        deathSound.setFramePosition(0);  // Must always rewind!
//        clip.start();
        deathSound.loop(0);
    }


    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
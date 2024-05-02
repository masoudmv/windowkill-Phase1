package controller;

import javax.sound.sampled.*;
import java.io.*;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import static controller.Game.clip;

public class SoundHandler {
    public static Clip clip;
    SoundHandler(){

    }

    public static void loadSound(){
        try
        {

        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

    public static void playClip(){
//        if (clip != null) {
        if (clip.isRunning()) {
            clip.stop();
            clip.close();// Stop the clip before rewinding it
        }
        clip.setFramePosition(0);  // Rewind to the beginning
        clip.start();         // Start playing
//        }
    }



    public static void doPlay() {
        try {
//            stopPlay();
//            clip.stop();
            InputStream is = new FileInputStream("C:\\Users\\masoo\\Desktop\\Projects\\windowkill_AP\\src\\main\\resources\\burst2.wav");
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn);
            AudioFormat format = ais.getFormat();
            // this is the value of format.
            // PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
//            stopPlay();
            clip.stop();
            e.printStackTrace();
        }
    }

}

package org.example;
import controller.*;
import view.MainFrame;
import view.charactersView.Menu;

import javax.sound.sampled.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static controller.Constants.FRAME_DIMENSION;
import static java.awt.Color.black;

public class Main {

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {



        MainFrame.getINSTANCE();
//        new Menu();
        new Game();
    }
}
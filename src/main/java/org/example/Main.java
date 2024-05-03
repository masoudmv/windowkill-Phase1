package org.example;
import controller.Game;
import controller.Sound;
import view.MainFrame;
import view.Menu;
import view.SkillTreeMenu;

import javax.sound.sampled.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {



        MainFrame.getINSTANCE();
        new Sound();



        new Game();



//        new Menu();

//        new SkillTreeMenu();

//        new Game();
    }
}
package view;

import controller.KeyController;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.FRAME_DIMENSION;
import static controller.Constants.PANEL_SIZE;

public final class myFrame extends JFrame {
    private static myFrame INSTANCE;
    private myFrame() throws HeadlessException {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
//        addKeyListener(KeyController);
//        setBackground(Color.BLACK);
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
    }

    public static myFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new myFrame();
        return INSTANCE;
    }
}
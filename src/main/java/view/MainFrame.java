package view;

//import controller.KeyController;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.FRAME_DIMENSION;
//import static controller.Constants.PANEL_SIZE;

public final class MainFrame extends JFrame {
    private static MainFrame INSTANCE;
    private MainFrame() throws HeadlessException {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));

//        getContentPane().setBackground(Color.black);

//        setBackground(Color.yellow);

//        addKeyListener(KeyController);
//        setBackground(Color.BLACK);
        setSize(FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
    }

    public static MainFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new MainFrame();
        return INSTANCE;
    }
}
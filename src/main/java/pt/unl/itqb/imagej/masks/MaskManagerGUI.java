package pt.unl.itqb.imagej.masks;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MaskManagerGUI {
    public static void run() {
        Frame frame = new Frame("eHookeJ - Mask Computation");
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        Button computeMaskButton = new Button("Create Mask");
        computeMaskButton.setBounds(20,50, 150, 30);
        computeMaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        frame.add(computeMaskButton);
        frame.setSize(200,800);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

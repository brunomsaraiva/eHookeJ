package pt.unl.itqb.imagej;

import ij.process.*;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.*;

import java.awt.*;
import java.awt.event.*;

import net.imglib2.roi.Mask;
import pt.unl.itqb.imagej.masks.MaskManager;
import pt.unl.itqb.imagej.parameters.Parameters;

public class Gui {

    private Frame frame;
    private Button maskButton;

    public static void run() {
        Parameters parameters = new Parameters();
        Frame frame = new Frame("eHookeJ");
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

        Label titleLabel = new Label("eHookeJ");
        titleLabel.setBounds(20, 50, 150, 30);

        Button computeMaskButton = new Button("Compute Mask");
        computeMaskButton.setBounds(20,90, 150, 30);
        computeMaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaskManager.run();
            }
        });

        Button computeSegmentsButton = new Button("Compute Segments");
        computeSegmentsButton.setBounds(20,130, 150, 30);
        computeSegmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaskManager.run();
            }
        });

        Button computeCellsButton = new Button("Compute Cells");
        computeCellsButton.setBounds(20,170, 150, 30);
        computeCellsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaskManager.run();
            }
        });

        frame.add(titleLabel);
        frame.add(computeMaskButton);
        frame.add(computeSegmentsButton);
        frame.add(computeCellsButton);
        frame.setSize(190,250);
        frame.setLayout(null);
        frame.setVisible(true);
    }


}

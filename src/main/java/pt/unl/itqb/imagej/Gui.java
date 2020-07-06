package pt.unl.itqb.imagej;

import ij.process.*;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.*;

import java.awt.*;
import java.awt.event.*;

import net.imagej.ImageJ;
import net.imglib2.roi.Mask;
import pt.unl.itqb.imagej.masks.MaskManager;
import pt.unl.itqb.imagej.masks.MaskManagerGUI;
import pt.unl.itqb.imagej.parameters.MaskParameters;
import pt.unl.itqb.imagej.parameters.Parameters;
import pt.unl.itqb.imagej.parameters.SegmentationParameters;
import pt.unl.itqb.imagej.segments.SegmentManager;
import pt.unl.itqb.imagej.segments.SegmentManagerGUI;

public class Gui {

    private Frame frame;
    private Button maskButton;
    private Parameters params;

    public static void run() {
        Parameters params = new Parameters();
        MaskManager maskmanager = new MaskManager(params);
        SegmentManager segmentmanager = new SegmentManager(params);
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

        Label titleLabel = new Label("eHookeJ", Label.CENTER);

        Button computeMaskButton = new Button("Compute Mask");
        computeMaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaskManagerGUI maskManagerGUI = new MaskManagerGUI(maskmanager, params);
            }
        });

        Button computeSegmentsButton = new Button("Compute Segments");
        computeSegmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SegmentManagerGUI segmentManagerGUI = new SegmentManagerGUI(segmentmanager, params);

            }
        });

        Button computeCellsButton = new Button("Compute Cells");
        computeCellsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.setSize(250,250);
        GridLayout gd = new GridLayout(5, 1);
        gd.setVgap(5);
        frame.setLayout(gd);
        frame.add(titleLabel);
        frame.add(computeMaskButton);
        frame.add(computeSegmentsButton);
        frame.add(computeCellsButton);
        frame.setVisible(true);
    }


}

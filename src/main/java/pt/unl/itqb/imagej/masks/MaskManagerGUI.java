package pt.unl.itqb.imagej.masks;

import ij.ImagePlus;
import ij.ImageListener;
import ij.plugin.Grid;
import ij.plugin.Text;
import javafx.scene.control.ComboBox;
import net.imglib2.ops.parse.token.Int;
import pt.unl.itqb.imagej.parameters.MaskParameters;
import pt.unl.itqb.imagej.parameters.Parameters;

import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import ij.WindowManager;

import javax.swing.*;

public class MaskManagerGUI {
    MaskManager maskmanager;
    TextField borderInput;
    Checkbox invertInput;
    Choice maskalgchoice;
    TextField blocksizeInput;
    TextField maskoffsetInput;
    Checkbox fillholesInput;
    TextField maskclosingInput;
    TextField maskdilationInput;
    Checkbox autoalignoption;
    TextField xalignInput;
    TextField yalignInput;

    public MaskManagerGUI(MaskManager maskmanager, Parameters params) {
        MaskParameters maskparams = params.getMaskparameters();
        Frame frame = new Frame("eHookeJ - Mask Computation");
        ArrayList<Container> frames  = new ArrayList<>();
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

        String[] imglist = WindowManager.getImageTitles();

        Container baseimgFrame = new Container();
        frames.add(baseimgFrame);
        GridLayout baseGD = new GridLayout(1,2);
        baseGD.setHgap(2);
        baseimgFrame.setLayout(baseGD);
        Label baseimgLabel = new Label("Select Base Image");
        baseimgFrame.add(baseimgLabel);
        Choice baseimgSelector = new Choice();
        for (String imgname : imglist) {
            baseimgSelector.add(imgname);
        }
        baseimgFrame.add(baseimgSelector);


        Container fluorimgFrame = new Container();
        frames.add(fluorimgFrame);
        GridLayout fluorGD = new GridLayout(1,2);
        fluorGD.setHgap(2);
        fluorimgFrame.setLayout(fluorGD);
        Label fluorimgLabel = new Label("Select Fluor Image");
        fluorimgFrame.add(fluorimgLabel);
        Choice fluorimgSelector = new Choice();
        for (String imgname : imglist) {
            fluorimgSelector.add(imgname);
        }
        fluorimgFrame.add(fluorimgSelector);

        baseimgSelector.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                String previousbase = baseimgSelector.getSelectedItem();
                String previousfluor = fluorimgSelector.getSelectedItem();
                baseimgSelector.removeAll();
                fluorimgSelector.removeAll();
                String[] imglist = WindowManager.getImageTitles();
                for (String img: imglist) {
                    baseimgSelector.add(img);
                    fluorimgSelector.add(img);
                    if (img.equals(previousbase)) {
                        baseimgSelector.select(img);
                    }
                    if (img.equals(previousfluor)) {
                        fluorimgSelector.select(img);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        fluorimgSelector.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                String previousbase = baseimgSelector.getSelectedItem();
                String previousfluor = fluorimgSelector.getSelectedItem();
                baseimgSelector.removeAll();
                fluorimgSelector.removeAll();
                String[] imglist = WindowManager.getImageTitles();
                for (String img: imglist) {
                    baseimgSelector.add(img);
                    fluorimgSelector.add(img);
                    if (img.equals(previousbase)) {
                        baseimgSelector.select(img);
                    }
                    if (img.equals(previousfluor)) {
                        fluorimgSelector.select(img);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        Container invertFrame = new Container();
        frames.add(invertFrame);
        GridLayout invertGD = new GridLayout(1, 2);
        invertGD.setHgap(2);
        invertFrame.setLayout(invertGD);
        Label invertLabel = new Label("Invert base image?");
        invertFrame.add(invertLabel);
        invertInput = new Checkbox();
        invertFrame.add(invertInput);

        Container maskalgFrame = new Container();
        frames.add(maskalgFrame);
        GridLayout maskalgGD = new GridLayout(1,2);
        maskalgGD.setHgap(2);
        maskalgFrame.setLayout(maskalgGD);
        Label maskalglabel = new Label("Mask Algorithm:");
        maskalgFrame.add(maskalglabel);
        maskalgchoice = new Choice();
        for (String alg: maskparams.getMaskalgorithms()) {
            maskalgchoice.add(alg);
        }
        maskalgchoice.select(maskparams.getMaskalgorithm());
        maskalgFrame.add(maskalgchoice);

        Container fillholesFrame = new Container();
        frames.add(fillholesFrame);
        GridLayout fillholesGD = new GridLayout(1, 2);
        fillholesGD.setHgap(2);
        fillholesFrame.setLayout(fillholesGD);
        Label fillholesLabel = new Label("Fill Holes?");
        fillholesFrame.add(fillholesLabel);
        fillholesInput = new Checkbox();
        fillholesFrame.add(fillholesInput);

        Container maskclosingFrame = new Container();
        frames.add(maskclosingFrame);
        GridLayout maskclosingGD = new GridLayout(1, 2);
        maskclosingGD.setHgap(2);
        maskclosingFrame.setLayout(maskclosingGD);
        Label maskclosinglabel = new Label("Mask Closing:");
        maskclosingFrame.add(maskclosinglabel);
        maskclosingInput = new TextField(Integer.toString(maskparams.getMaskclosing()));
        maskclosingFrame.add(maskclosingInput);

        Container maskdilationFrame = new Container();
        frames.add(maskdilationFrame);
        GridLayout maskdilationGD = new GridLayout(1, 2);
        maskdilationGD.setHgap(2);
        maskdilationFrame.setLayout(maskdilationGD);
        Label maskdilationlabel = new Label("Mask Dilation:");
        maskdilationFrame.add(maskdilationlabel);
        maskdilationInput = new TextField(Integer.toString(maskparams.getMaskdilation()));
        maskdilationFrame.add(maskdilationInput);

        Container borderFrame = new Container();
        frames.add(borderFrame);
        GridLayout labelGD = new GridLayout(1,2);
        labelGD.setHgap(2);
        borderFrame.setLayout(labelGD);
        Label borderLabel = new Label("Align Margin:");
        borderFrame.add(borderLabel);
        borderInput = new TextField(Integer.toString(maskparams.getBorder()));
        borderFrame.add(borderInput);

        Container autoalignFrame = new Container();
        frames.add(autoalignFrame);
        GridLayout autoalignGD = new GridLayout(1, 2);
        autoalignGD.setHgap(2);
        autoalignFrame.setLayout(autoalignGD);
        Label autoalignLabel = new Label("Autoalign?");
        autoalignFrame.add(autoalignLabel);
        autoalignoption = new Checkbox();
        autoalignFrame.add(autoalignoption);

        Container manualalignFrame = new Container();
        frames.add(manualalignFrame);
        GridLayout manualalignGD = new GridLayout(1,1);
        manualalignGD.setHgap(2);
        manualalignFrame.setLayout(manualalignGD);
        Label manualalignLabel = new Label("Manual Alignment Corrections:");
        manualalignFrame.add(manualalignLabel);

        Container xalignFrame = new Container();
        frames.add(xalignFrame);
        GridLayout xalignGD = new GridLayout(1, 2);
        xalignGD.setHgap(2);
        xalignFrame.setLayout(xalignGD);
        Label xalignlabel = new Label("X align:");
        xalignFrame.add(xalignlabel);
        xalignInput = new TextField(Integer.toString(maskparams.getXalign()));
        xalignFrame.add(xalignInput);

        Container yalignFrame = new Container();
        frames.add(yalignFrame);
        GridLayout yalignGD = new GridLayout(1, 2);
        yalignGD.setHgap(2);
        yalignFrame.setLayout(yalignGD);
        Label yalignlabel = new Label("Y align:");
        yalignFrame.add(yalignlabel);
        yalignInput = new TextField(Integer.toString(maskparams.getYalign()));
        yalignFrame.add(yalignInput);

        Container computeFrame = new Container();
        frames.add(computeFrame);
        GridLayout compGD = new GridLayout(1, 1);
        Button computeMaskButton = new Button("Create Mask");
        computeMaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateParams(maskparams);
                maskmanager.createMask((ImagePlus) WindowManager.getImage(baseimgSelector.getItem(baseimgSelector.getSelectedIndex())),
                                       (ImagePlus) WindowManager.getImage(fluorimgSelector.getItem(fluorimgSelector.getSelectedIndex())));
            }
        });
        computeFrame.setLayout(compGD);
        computeFrame.add(computeMaskButton);

        frame.setSize(260,600);
        frame.setLayout(null);

        int ypos = 50;
        for (Container frm: frames) {
            frm.setBounds(10, ypos, 240, 30);
            frame.add(frm);
            frm.setVisible(true);
            ypos += 35;
        }
        frame.setVisible(true);
    }

    public void updateParams(MaskParameters maskParameters) {
        maskParameters.setBorder(Integer.parseInt(borderInput.getText()));
        maskParameters.setIsfluorescence(invertInput.getState());
        maskParameters.setMaskalgorithm(maskalgchoice.getItem(maskalgchoice.getSelectedIndex()));
        maskParameters.setFillholes(fillholesInput.getState());
        maskParameters.setMaskclosing(Integer.parseInt(maskclosingInput.getText()));
        maskParameters.setMaskdilation(Integer.parseInt(maskdilationInput.getText()));
        maskParameters.setAutoalign(autoalignoption.getState());
        maskParameters.setXalign(Integer.parseInt(xalignInput.getText()));
        maskParameters.setYalign(Integer.parseInt(yalignInput.getText()));
    }
}

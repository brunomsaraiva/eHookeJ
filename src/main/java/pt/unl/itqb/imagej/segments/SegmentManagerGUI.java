package pt.unl.itqb.imagej.segments;

import ij.ImagePlus;
import ij.WindowManager;
import net.imglib2.ops.parse.token.Int;
import pt.unl.itqb.imagej.parameters.Parameters;
import pt.unl.itqb.imagej.parameters.SegmentationParameters;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SegmentManagerGUI {
    private TextField mindistInput;
    private TextField minheightInput;
    private TextField minedgeInput;
    private TextField maxpeaksInput;
    
    public SegmentManagerGUI(SegmentManager segmentManager, Parameters params) {
        SegmentationParameters segmentationParameters = params.getSegmentationparameters();
        Frame frame = new Frame("eHookeJ - Segmentation");
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


        Container maskFrame = new Container();
        frames.add(maskFrame);
        GridLayout maskGD = new GridLayout(1,2);
        maskGD.setHgap(2);
        maskFrame.setLayout(maskGD);
        Label maskLabel = new Label("Select Mask");
        maskFrame.add(maskLabel);
        Choice maskSelector = new Choice();
        for (String imgname : imglist) {
            maskSelector.add(imgname);
        }
        maskFrame.add(maskSelector);

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
                String previousmask = maskSelector.getSelectedItem();
                baseimgSelector.removeAll();
                maskSelector.removeAll();
                String[] imglist = WindowManager.getImageTitles();
                for (String img: imglist) {
                    baseimgSelector.add(img);
                    maskSelector.add(img);
                    if (img.equals(previousbase)) {
                        baseimgSelector.select(img);
                    }
                    if (img.equals(previousmask)) {
                        maskSelector.select(img);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        maskSelector.addMouseListener(new MouseListener() {
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
                String previousmask = maskSelector.getSelectedItem();
                baseimgSelector.removeAll();
                maskSelector.removeAll();
                String[] imglist = WindowManager.getImageTitles();
                for (String img: imglist) {
                    baseimgSelector.add(img);
                    maskSelector.add(img);
                    if (img.equals(previousbase)) {
                        baseimgSelector.select(img);
                    }
                    if (img.equals(previousmask)) {
                        maskSelector.select(img);
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        
        Container mindistFrame = new Container();
        frames.add(mindistFrame);
        GridLayout mindistGD = new GridLayout(1, 2);
        mindistGD.setHgap(2);
        mindistFrame.setLayout(mindistGD);
        Label mindistLabel = new Label("Peak Min Distance:");
        mindistFrame.add(mindistLabel);
        mindistInput = new TextField(Integer.toString(segmentationParameters.getPeakmindist()));
        mindistFrame.add(mindistInput);

        Container minheightFrame = new Container();
        frames.add(minheightFrame);
        GridLayout minheightGD = new GridLayout(1, 2);
        minheightGD.setHgap(2);
        minheightFrame.setLayout(minheightGD);
        Label minheightLabel = new Label("Peak Min Height:");
        minheightFrame.add(minheightLabel);
        minheightInput = new TextField(Integer.toString(segmentationParameters.getPeakminheight()));
        minheightFrame.add(minheightInput);

        Container minedgeFrame = new Container();
        frames.add(minedgeFrame);
        GridLayout minedgeGD = new GridLayout(1, 2);
        minedgeGD.setHgap(2);
        minedgeFrame.setLayout(minedgeGD);
        Label minedgeLabel = new Label("Peak Min Edge:");
        minedgeFrame.add(minedgeLabel);
        minedgeInput = new TextField(Integer.toString(segmentationParameters.getPeakminedge()));
        minedgeFrame.add(minedgeInput);

        Container maxpeaksFrame = new Container();
        frames.add(maxpeaksFrame);
        GridLayout maxpeaksGD = new GridLayout(1, 2);
        maxpeaksGD.setHgap(2);
        maxpeaksFrame.setLayout(maxpeaksGD);
        Label maxpeaksLabel = new Label("Max Peaks:");
        maxpeaksFrame.add(maxpeaksLabel);
        maxpeaksInput = new TextField(Integer.toString(segmentationParameters.getMaxpeaks()));
        maxpeaksFrame.add(maxpeaksInput);

        Container computeFrame = new Container();
        frames.add(computeFrame);
        GridLayout compGD = new GridLayout(1, 1);
        Button computeSegmentsButton = new Button("Compute Segments");
        computeSegmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateParams(segmentationParameters);
                segmentManager.createMask((ImagePlus) WindowManager.getImage(baseimgSelector.getItem(baseimgSelector.getSelectedIndex())),
                        (ImagePlus) WindowManager.getImage(maskSelector.getItem(maskSelector.getSelectedIndex())));
            }
        });
        computeFrame.setLayout(compGD);
        computeFrame.add(computeSegmentsButton);

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

    public void updateParams(SegmentationParameters segmentationParameters) {
        segmentationParameters.setPeakmindist(Integer.parseInt(mindistInput.getText()));
        segmentationParameters.setPeakminheight(Integer.parseInt(minheightInput.getText()));
        segmentationParameters.setPeakminedge(Integer.parseInt(minedgeInput.getText()));
        segmentationParameters.setMaxpeaks(Integer.parseInt(maxpeaksInput.getText()));
    }
}

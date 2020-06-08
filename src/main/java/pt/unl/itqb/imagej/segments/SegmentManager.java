package pt.unl.itqb.imagej.segments;

import ij.ImagePlus;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import pt.unl.itqb.imagej.parameters.Parameters;

public class SegmentManager {
    private ImagePlus baseimg_aligned;
    private ImagePlus mask;
    private Parameters parameters;
    private ImagePlus baseoverlay;

    public SegmentManager(Parameters params) {
        this.baseimg_aligned = new ImagePlus();
        this.mask = new ImagePlus();
        this.parameters = params;
        this.baseoverlay = new ImagePlus();
    }

    public void createMask(ImagePlus baseimg, ImagePlus mask) {

    }
}

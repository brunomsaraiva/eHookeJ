package pt.unl.itqb.imagej.segments;

import ij.ImagePlus;
import ijopencv.ij.ImagePlusMatConverter;
import ijopencv.opencv.MatImagePlusConverter;
import net.imagej.ImageJ;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;
import pt.unl.itqb.imagej.parameters.Parameters;
import pt.unl.itqb.imagej.parameters.SegmentationParameters;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void createSegments(ImagePlus baseimg, ImagePlus mask) {
        SegmentationParameters segmentparams = this.parameters.getSegmentationparameters();
        ImageJ ij = new ImageJ();
        Mat mat_baseimg = ImagePlusMatConverter.toMat(baseimg.getProcessor());
        Mat mat_mask = ImagePlusMatConverter.toMat(mask.getProcessor());

        Mat mat_dist = new Mat();
        opencv_imgproc.distanceTransform(mat_mask, mat_dist, opencv_imgproc.DIST_L2, 3);
        opencv_imgproc.threshold(mat_dist, mat_dist, segmentparams.getPeakminheight(), 1.0, opencv_imgproc.THRESH_BINARY);

        Mat mat_dist8u = new Mat();
        mat_dist.convertTo(mat_dist8u, CvType.CV_8U);

        opencv_core.MatVector contours = new opencv_core.MatVector();
        Mat hierarchy = new Mat();
        opencv_imgproc.findContours(mat_dist8u, contours, hierarchy, opencv_imgproc.RETR_EXTERNAL, opencv_imgproc.CHAIN_APPROX_SIMPLE);

        Mat markers = Mat.zeros(mat_dist.size(), CvType.CV_32S).asMat();

        for (int i = 0; i < contours.size(); i++) {
            opencv_imgproc.drawContours(markers, contours, i, new Scalar(i+1, -1));
        }

        Mat markersScaled = new Mat();
        markers.convertTo(markersScaled, CvType.CV_32F);
        markersScaled.convertTo(markersScaled, CvType.CV_32F, 1.0 / 255.0, 0);

        opencv_imgproc.circle(markersScaled, new Point(5, 5), 3, new Scalar(255.0));
        opencv_imgproc.circle(markers, new Point(5, 5), 3, new Scalar(255.0));

        Mat imgResult = new Mat();
        System.out.println(imgResult.type());
        System.out.println(markers.type());

        opencv_imgproc.watershed(imgResult, markers);

        ImagePlus watershed = new ImagePlus("Watershed", MatImagePlusConverter.toImageProcessor(imgResult));
        watershed.show();
    }
}

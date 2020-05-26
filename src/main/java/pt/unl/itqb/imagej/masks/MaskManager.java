package pt.unl.itqb.imagej.masks;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import net.imagej.ImageJ;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.img.basictypeaccess.array.ByteArray;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.img.ImagePlusAdapter;
import pt.unl.itqb.imagej.parameters.MaskParameters;
import pt.unl.itqb.imagej.parameters.Parameters;

public class MaskManager {
    private ImagePlus baseimg_aligned;
    private ImagePlus fluorimg_aligned;
    private ImagePlus mask;
    private Parameters parameters;

    public MaskManager(Parameters params) {
        this.baseimg_aligned = new ImagePlus();
        this.fluorimg_aligned = new ImagePlus();
        this.mask = new ImagePlus();
        this.parameters = params;
    }

    public void createMask(ImagePlus baseimg, ImagePlus fluorimg) {
        ImageJ ij = new ImageJ();
        this.baseimg_aligned = baseimg;
        this.fluorimg_aligned = fluorimg;
        MaskParameters maskparams = this.parameters.getMaskparameters();
        int xborder = maskparams.getBorder();
        int yborder = maskparams.getBorder();

        if (baseimg_aligned.getBitDepth() == 24) {
            ImageProcessor baseimg_proc = baseimg_aligned.getProcessor();
            baseimg_aligned = new ImagePlus("Grayscale_"+baseimg_aligned.getTitle(), baseimg_proc.convertToByte(false));
        }

        if (fluorimg_aligned.getBitDepth() == 24) {
            ImageProcessor fluorimg_proc = fluorimg_aligned.getProcessor();
            fluorimg_aligned = new ImagePlus("Grayscale_"+fluorimg_aligned.getTitle(), fluorimg_proc.convertToByte(false));
        }

        if (maskparams.getBorder() > 0) {
            ImagePlus cropped_baseimg_aligned = clipBorder(baseimg_aligned, maskparams.getBorder(), maskparams.getBorder());
            cropped_baseimg_aligned.setTitle("Cropped_"+baseimg_aligned.getTitle());
            baseimg_aligned = cropped_baseimg_aligned;
        }

        mask = thresholdMethod(baseimg_aligned, maskparams);
        mask.show();

        if (maskparams.getFillHoles()) {
            // not working, missing fill holes, closing, dilation and autoalign
            mask = (ImagePlus) ij.op().morphology().fillHoles((RandomAccessibleInterval) ImageJFunctions.wrapReal(mask));
        }

        if (maskparams.getAutoalign()) {
            fluorimg_aligned = autoAlign(fluorimg_aligned, mask);
        } else {
            fluorimg_aligned = clipBorder(fluorimg_aligned,
                                  xborder+maskparams.getXalign(),
                                  yborder+maskparams.getYalign());
        }
    }

    public static ImagePlus clipBorder(ImagePlus img, int xborder, int yborder) {
        img.setRoi(xborder, yborder, img.getWidth()-2*xborder, img.getHeight()-2*yborder);
        return img.crop();
    }

    public static ImagePlus thresholdMethod(ImagePlus img, MaskParameters maskparams) {
        ImageProcessor ip = img.getProcessor();
        ip.setAutoThreshold(maskparams.getMaskalgorithm());
        ip.threshold((int) ip.getMaxThreshold());
        if (!maskparams.getIsFluorescence()) {
            ip.invert();
        }
        return new ImagePlus("Mask", ip);
    }

    public static ByteArray fillHoles(ByteArray mask) {
        return mask;
    }

    public static ImagePlus autoAlign(ImagePlus img, ImagePlus mask) {
        return img;
    }

    public static ImagePlus overlayMask(ImagePlus img, ByteArray mask) {
        return img;
    }
}

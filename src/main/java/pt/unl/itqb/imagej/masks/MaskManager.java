package pt.unl.itqb.imagej.masks;

import ij.IJ;
import ij.ImagePlus;
import ij.process.Blitter;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import net.imagej.ImageJ;
import pt.unl.itqb.imagej.parameters.MaskParameters;
import pt.unl.itqb.imagej.parameters.Parameters;

public class MaskManager {
    private ImagePlus baseimg_aligned;
    private ImagePlus fluorimg_aligned;
    private ImagePlus mask;
    private Parameters parameters;
    private ImagePlus baseoverlay;
    private ImagePlus fluoroverlay;

    public MaskManager(Parameters params) {
        this.baseimg_aligned = new ImagePlus();
        this.fluorimg_aligned = new ImagePlus();
        this.mask = new ImagePlus();
        this.parameters = params;
        this.baseoverlay = new ImagePlus();
        this.fluoroverlay = new ImagePlus();
    }

    public void createMask(ImagePlus baseimg, ImagePlus fluorimg) {
        ImageJ ij = new ImageJ();
        this.baseimg_aligned = baseimg;
        this.fluorimg_aligned = fluorimg;
        this.baseoverlay = new ImagePlus();
        this.fluoroverlay = new ImagePlus();
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
            ImagePlus cropped_baseimg_aligned = clipBorder(baseimg_aligned, 0, 0, maskparams.getBorder());
            cropped_baseimg_aligned.setTitle("Aligned_"+baseimg_aligned.getTitle());
            baseimg_aligned = cropped_baseimg_aligned;
        }

        this.mask = (ImagePlus) baseimg_aligned.duplicate();
        mask = thresholdMethod(mask, maskparams);
        mask.show();
        IJ.run(mask, "Make Binary", "");

        if (maskparams.getFillHoles()) {
            IJ.run(mask, "Fill Holes", "");
        }

        if (maskparams.getMaskclosing() > 0) {
            for (int i = 0; i<maskparams.getMaskclosing(); i++) {
                IJ.run(mask, "Close-", "");
            }
        }

        if (maskparams.getMaskdilation() > 0) {
            for (int i=0; i<maskparams.getMaskdilation(); i++) {
                IJ.run(mask, "Dilate", "");
            }
        }

        mask.changes = false;

        if (maskparams.getAutoalign()) {
            ImagePlus cropped_fluorimage = autoAlign(fluorimg_aligned, mask, maskparams.getBorder());
            cropped_fluorimage.setTitle("Aligned_" + fluorimg_aligned.getTitle());
            fluorimg_aligned = cropped_fluorimage;
        } else {
            ImagePlus cropped_fluorimage = clipBorder(fluorimg_aligned,
                                           maskparams.getXalign(),
                                           maskparams.getYalign(),
                                           maskparams.getBorder());
            cropped_fluorimage.setTitle("Aligned_" + fluorimg_aligned.getTitle());
            fluorimg_aligned = cropped_fluorimage;
        }

        baseimg_aligned.show();
        fluorimg_aligned.show();
        olMask(baseimg_aligned, mask);
        olMask(fluorimg_aligned, mask);
    }

    public static ImagePlus clipBorder(ImagePlus img, int x, int y, int border) {
        if (x > border) {
            x = border;
        }
        if (y > border) {
            y = border;
        }
        img.setRoi(border+x, border+y, img.getWidth()-2*border, img.getHeight()-2*border);
        return img.crop();
    }

    public static ImagePlus thresholdMethod(ImagePlus img, MaskParameters maskparams) {
        ImageProcessor ip = img.getProcessor();
        ip.setAutoThreshold(maskparams.getMaskalgorithm());
        ip.threshold((int) ip.getMaxThreshold());
        if (!maskparams.getIsFluorescence()) {
            ip.invert();
        }
        return new ImagePlus("Mask", ip.convertToByteProcessor());
    }

    public static ImagePlus autoAlign(ImagePlus img, ImagePlus mask, int border) {
        int xalign = 0;
        int yalign = 0;
        int minscore = 0;
        int maxalign = border;
        ImagePlus binmask = (ImagePlus) mask.duplicate();
        ImageProcessor binmaskip = binmask.getProcessor();
        binmaskip.multiply(0.003921568627451);

        for (int i=-maxalign; i<=maxalign; i++) {
            for (int ii=-maxalign; ii<=maxalign; ii++) {
                ImagePlus tmp = (ImagePlus) clipBorder(img, i, ii, maxalign);
                ImageProcessor tmpip = tmp.getProcessor();
                tmpip.copyBits(binmaskip, 0, 0, Blitter.MULTIPLY);
                int calc = 0;
                for (int w=0; w<tmp.getWidth(); w++) {
                    for (int h=0; h<tmp.getHeight(); h++) {
                        calc += tmpip.get(w, h);
                    }
                }

                if (calc> minscore) {
                    minscore = calc;
                    xalign = i;
                    yalign = ii;
                }
            }
        }
        return clipBorder(img, xalign, yalign, maxalign);
    }

    public static void olMask(ImagePlus img, ImagePlus mask) {
        ImagePlus edges = (ImagePlus) mask.duplicate();
        ImagePlus tmp = (ImagePlus) img.duplicate();
        ImageConverter convertedtmp = new ImageConverter(tmp);
        convertedtmp.convertToGray8();
        tmp.setTitle("8bit " + img.getTitle());
        tmp.show();
        IJ.run(edges, "Find Edges", "");
        edges.setTitle("Edges");
        edges.show();
        IJ.run(tmp,"Merge Channels...", "c4=["+tmp.getTitle()+"] c5=["+edges.getTitle()+"] create");
        tmp.close();
        edges.close();
    }
}

package pt.unl.itqb.imagej.parameters;

import net.imglib2.roi.Mask;

import java.util.ArrayList;

public class MaskParameters {
    private int border;
    private boolean isfluorescence;
    private ArrayList<String> maskalgorithms;
    private String maskalgorithm;
    private int blocksize;
    private double maskoffset;
    private boolean fillholes;
    private int maskclosing;
    private int maskdilation;
    private boolean autoalign;
    private int xalign;
    private int yalign;

    public MaskParameters() {
        border = 10;
        isfluorescence = false;

        maskalgorithms = new ArrayList<>();
        maskalgorithms.add("IsoData");
        maskalgorithms.add("Default");
        maskalgorithms.add("Huang");
        maskalgorithms.add("Intermodes");
        maskalgorithms.add("IJ_IsoData");
        maskalgorithms.add("Li");
        maskalgorithms.add("MaxEntropy");
        maskalgorithms.add("Mean");
        maskalgorithms.add("MinError");
        maskalgorithms.add("Minimum");
        maskalgorithms.add("Moments");
        maskalgorithms.add("Otsu");
        maskalgorithms.add("Percentile");
        maskalgorithms.add("RenyiEntropy");
        maskalgorithms.add("Shanbhag");
        maskalgorithms.add("Triangle");
        maskalgorithms.add("Yen");
        maskalgorithm = "IsoData";

        fillholes = false;

        maskclosing = 1;
        maskdilation = 0;

        autoalign = false;

        xalign = 0;
        yalign = 0;
    }

    public int getBorder() {
        return this.border;
    }

    public void setBorder(int bordervalue) {
        this.border = bordervalue;
    }

    public boolean getIsFluorescence() {
        return this.isfluorescence;
    }

    public void setIsfluorescence(boolean isfluorvalue) {
        this.isfluorescence = isfluorvalue;
    }

    public ArrayList<String> getMaskalgorithms() {
        return this.maskalgorithms;
    }

    public String getMaskalgorithm() {
        return this.maskalgorithm;
    }

    public void setMaskalgorithm(String maskalgorithmvalue) {
        this.maskalgorithm = maskalgorithmvalue;
    }

    public boolean getFillHoles() {
        return this.fillholes;
    }

    public void setFillholes(boolean fillholesvalue) {
        this.fillholes = fillholesvalue;
    }

    public int getMaskclosing() {
        return this.maskclosing;
    }

    public void setMaskclosing(int maskclosingvalue) {
        this.maskclosing = maskclosingvalue;
    }

    public int getMaskdilation() {
        return this.maskdilation;
    }

    public void setMaskdilation(int maskdilationvalue) {
        this.maskdilation = maskdilationvalue;
    }

    public boolean getAutoalign() {
        return this.autoalign;
    }

    public void setAutoalign(boolean autoalignvalue) {
        this.autoalign = autoalignvalue;
    }

    public int getXalign() {
        return this.xalign;
    }

    public void setXalign(int xalign) {
        this.xalign = xalign;
    }

    public int getYalign() {
        return this.yalign;
    }

    public void setYalign(int yalign) {
        this.yalign = yalign;
    }
}

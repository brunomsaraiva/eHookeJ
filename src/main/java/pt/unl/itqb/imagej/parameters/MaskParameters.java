package pt.unl.itqb.imagej.parameters;

import net.imglib2.roi.Mask;

import java.util.ArrayList;

public class MaskParameters {

    public MaskParameters() {
        int border = 10;
        boolean isfluorescent = false;

        ArrayList<String> maskalgorithms = new ArrayList<>();
        maskalgorithms.add("Local Average");
        maskalgorithms.add("Isodata");
        String maskalgorithm = "Isodata";

        int blocksize = 150;
        double maskoffset = 0.02;

        boolean fillholes = false;

        int maskclosing = 1;
        int maskdilation = 0;

        boolean autoalign = false;

        int xalign = 0;
        int yalign = 0;
    }
}

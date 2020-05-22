package pt.unl.itqb.imagej.parameters;

import java.util.ArrayList;

public class CellParameters {
    public CellParameters() {
        int axialstep = 5;

        boolean findseptum = false;
        ArrayList<String> septumalgorithms = new ArrayList<>();
        septumalgorithms.add("Box");
        septumalgorithms.add("Isodata");
        String septumalgorithm = "Isodata";

        boolean classifycells = false;
        ArrayList<String> microscopes = new ArrayList<>();
        microscopes.add("Epifluorescence");
        microscopes.add("SIM");
        String microscope = "Epifluorescence";
    }
}

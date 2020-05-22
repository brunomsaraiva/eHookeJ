package pt.unl.itqb.imagej.parameters;

import java.io.File;

public class Parameters {
    MaskParameters maskparameters;
    SegmentationParameters segmentationparameters;
    CellParameters cellparameters;

    public void Parameters() {
        maskparameters = new MaskParameters();
        segmentationparameters = new SegmentationParameters();
        cellparameters = new CellParameters();
    }
}
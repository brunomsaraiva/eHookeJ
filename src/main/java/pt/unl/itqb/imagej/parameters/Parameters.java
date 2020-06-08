package pt.unl.itqb.imagej.parameters;

public class Parameters {
    MaskParameters maskparameters;
    SegmentationParameters segmentationparameters;
    CellParameters cellparameters;

    public Parameters() {
        maskparameters = new MaskParameters();
        segmentationparameters = new SegmentationParameters();
        cellparameters = new CellParameters();
    }

    public MaskParameters getMaskparameters() {
        return this.maskparameters;
    }

    public SegmentationParameters getSegmentationparameters() {
        return this.segmentationparameters;
    }
}
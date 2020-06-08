package pt.unl.itqb.imagej.parameters;

public class SegmentationParameters {
    private int peakmindist;
    private int peakminheight;
    private int peakminedge;
    private int maxpeaks;

    public SegmentationParameters() {
        peakmindist = 5;
        peakminheight =  5;
        peakminedge = 10;
        maxpeaks = 10000;
    }

    public void setPeakmindist(int mindist) {
        this.peakmindist = mindist;
    }

    public int getPeakmindist() {
        return this.peakmindist;
    }

    public void setPeakminheight(int minheight) {
        this.peakminheight = minheight;
    }

    public int getPeakminheight() {
        return this.peakminheight;
    }

    public void setPeakminedge(int minedge) {
        this.peakminedge = minedge;
    }

    public int getPeakminedge() {
        return peakminedge;
    }

    public void setMaxpeaks(int maxpeaks) {
        this.maxpeaks = maxpeaks;
    }

    public int getMaxpeaks() {
        return maxpeaks;
    }
}

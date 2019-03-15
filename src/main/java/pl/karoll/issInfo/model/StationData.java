package pl.karoll.issInfo.model;

import org.springframework.stereotype.Component;

@Component
public class StationData {
    private Long timestamp;
    private double degLon;
    private double degLat;
    private double leftPicturePosition;
    private double topPicturePosition;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getDegLon() {
        return degLon;
    }

    public void setDegLon(double degLon) {
        this.degLon = degLon;
    }

    public double getDegLat() {
        return degLat;
    }

    public void setDegLat(double degLat) {
        this.degLat = degLat;
    }

    public double getLeftPicturePosition() {
        return leftPicturePosition;
    }

    public void setLeftPicturePosition(double leftPicturePosition) {
        this.leftPicturePosition = leftPicturePosition;
    }

    public double getTopPicturePosition() {
        return topPicturePosition;
    }

    public void setTopPicturePosition(double topPicturePosition) {
        this.topPicturePosition = topPicturePosition;
    }

    @Override
    public String toString() {
        return "StationData{" +
                "timestamp=" + timestamp +
                ", degLon=" + degLon +
                ", degLat=" + degLat +
                ", leftPicturePosition=" + leftPicturePosition +
                ", topPicturePosition=" + topPicturePosition +
                '}';
    }
}

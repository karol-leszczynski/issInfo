package pl.karoll.issInfo.service;

import org.springframework.stereotype.Service;

@Service
public class SpaceTimeCalculations {

    final int earthRadius = 6371000;
    final double earthRadiusRadians = Math.toRadians(earthRadius);
    final int issAverageAlitude = 408000;
    final double worlSizeMeters = 40075016;
    final double pictureSizePixels = 600;
    private int issAverageOrbit = earthRadius+issAverageAlitude;



    //    transformations from degre WGS84 (epsg 4326) to meters web preudo mercator (epsg 3857)
    public int xCoordinate (double degLon){
        return (int) Math.round(degLon*earthRadiusRadians);
    }
    public int yCoordinate (double degLat){
        return (int) Math.round(earthRadius
                *Math.log(Math.tan((Math.toRadians(45))
                +(Math.toRadians(degLat)/2))));
    }

    //    transformations from meters web preudo mercator (epsg 3857) to pixels position on picture
    public int leftPosion (double degLon){
        double scale = pictureSizePixels/worlSizeMeters;
        return (int) Math.round(xCoordinate(degLon)
                *scale
                +(pictureSizePixels/2));
    }

    public int TopPosion (double degLat){
        double scale = pictureSizePixels/worlSizeMeters;
        return (int) Math.round(-yCoordinate(degLat)
                *scale
                +(pictureSizePixels/2));
    }

    // Speed calculations
    public int speedMPS (int distanceInMeters
            , long timeSec1
            , long timeSec2){
        return Math.round(distanceInMeters/(timeSec2-timeSec1));
    }

    public double speedKPH (int distanceInMeters
            , long timeSec1
            , long timeSec2){
        return speedMPS(distanceInMeters, timeSec1, timeSec2)*3.6;
    }

    public int speedMPSwithData (double degLon1, double degLat1, long timeSec1
            , double degLon2, double degLat2, long timeSec2){
        return Math.round(distanceInMeters(degLon1, degLat1, degLon2, degLat2)/(timeSec2-timeSec1));
    }

    public double speedKPHwithData (double degLon1, double degLat1, long timeSec1
            , double degLon2, double degLat2, long timeSec2){
        return speedMPSwithData(degLon1, degLat1, timeSec1, degLon2, degLat2, timeSec2)*3.6;
    }

    //    Distance method with sattisfying accuracy - moderate complexity
    public int distanceInMeters (double degLon1, double degLat1
            , double degLon2, double degLat2) {

        double radLonDelta = Math.toRadians(degLon2-degLon1);
        double radLat1 = Math.toRadians(degLat1);
        double radLat2 = Math.toRadians(degLat2);

        double d = issAverageOrbit
                *Math.acos(Math.sin(radLat1)
                *Math.sin(radLat2)
                +Math.cos(radLat1)
                *Math.cos(radLat2)
                *Math.cos(radLonDelta));
        return (int) Math.round(d);
    }

// Distance method more accurate on ground - most complex
/*
    public int distanceInMeters (double degLon1, double degLat1
            , double degLon2, double degLat2) {

        double latDelta = Math.toRadians(degLat2 - degLat1);
        double lonDelta = Math.toRadians(degLon2 - degLon1);

        double a = Math.pow(Math.sin(latDelta/2), 2) +
                (Math.cos(Math.toRadians(degLat1))
                        * Math.cos(Math.toRadians(degLat2))
                        * Math.pow(Math.sin(lonDelta/2), 2));

        double c = 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        double distance = issAverageOrbit * c;
        return (int) Math.round(distance);
    }
*/

    // Distance method, less accurate in some coordinates
/*
    public int distanceInMeters (double degLon1, double degLat1
            , double degLon2, double degLat2) {

        double radLon1 = Math.toRadians(degLon1);
        double radLon2 = Math.toRadians(degLon2);
        double radLat1 = Math.toRadians(degLat1);
        double radLat2 = Math.toRadians(degLat2);

        double x = (radLon2-radLon1)*Math.cos((radLat1+radLat2)/2);
        double y = (radLat2-radLat1);
        double d = issAverageOrbit*Math.sqrt(Math.pow(x,2)+Math.pow(y,2));

        System.out.println(Math.round(d/3));

        return (int) Math.round(d);
    }
*/

}

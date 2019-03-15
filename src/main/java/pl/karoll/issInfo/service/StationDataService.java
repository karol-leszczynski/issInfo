package pl.karoll.issInfo.service;

import org.springframework.stereotype.Service;
import pl.karoll.issInfo.ExternalData;
import pl.karoll.issInfo.model.StationData;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class StationDataService {

    private ExternalDataService externalDataService;
    private SpaceTimeCalculations spaceTimeCalculations;
    private HttpSession session;

    public StationDataService(ExternalDataService externalDataService
            , SpaceTimeCalculations spaceTimeCalculations
            , HttpSession session) {
        this.externalDataService = externalDataService;
        this.spaceTimeCalculations = spaceTimeCalculations;
        this.session = session;
    }

    public StationData stationData (){
        StationData stationData = new StationData();
        ExternalData externalData = externalDataService.getCurrentIssData();
        stationData.setTimestamp(externalData.getTimestamp());
        double degLat = externalData.getIss_position().get("latitude");
        double degLon = externalData.getIss_position().get("longitude");
        stationData.setDegLat(degLat);
        stationData.setDegLon(degLon);
        stationData.setLeftPicturePosition
                (spaceTimeCalculations.leftPosion(degLon));
        stationData.setTopPicturePosition
                (spaceTimeCalculations.TopPosion(degLat));
        return stationData;
    }

    public void setSpeedAndDistanceToSession () {
        List<StationData> positions =
                (List<StationData>) session.getAttribute("stationTrail");
        StationData stationData1 = positions.get(positions.size()-2);
        StationData stationData2 = positions.get(positions.size()-1);
        int currenDistanceMeters = spaceTimeCalculations.distanceInMeters(
                stationData1.getDegLon()
                , stationData1.getDegLat()
                ,stationData2.getDegLon()
                ,stationData2.getDegLat()
        );
        double currentSpeedFull = spaceTimeCalculations.speedKPH(currenDistanceMeters
        , stationData1.getTimestamp()
        ,stationData2.getTimestamp());
        double currentSpeed = Math.round(currentSpeedFull);
        session.setAttribute("currentSpeed", currentSpeed);
        long currenDistanceKilometers = currenDistanceMeters/1000;
        if (session.getAttribute("summedDistance")==null){
            session.setAttribute("summedDistance", currenDistanceKilometers);
        }
        long summedDistanceKilometers = (long)session.getAttribute("summedDistance")
                + currenDistanceKilometers;
        session.setAttribute("summedDistance", summedDistanceKilometers);
    }

    public StationData stationTrailToSession (){
        if(session.getAttribute("stationTrail")==null){
            StationData data = stationData();
            List<StationData> stationTrail = new ArrayList<>();
            stationTrail.add(data);
            session.setAttribute("stationTrail", stationTrail);
            return data;
        }
        List<StationData> stationTrail =
                (List<StationData>) session.getAttribute("stationTrail");
        StationData data = stationData();
        stationTrail.add(data);
        session.setAttribute("stationTrail", stationTrail);
        setSpeedAndDistanceToSession();
        return data;
    }

}

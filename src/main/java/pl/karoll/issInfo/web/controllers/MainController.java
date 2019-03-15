package pl.karoll.issInfo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.karoll.issInfo.model.StationData;
import pl.karoll.issInfo.service.ExternalDataService;
import pl.karoll.issInfo.service.StationDataService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private StationDataService stationDataService;

    public MainController(StationDataService stationDataService) {
        this.stationDataService = stationDataService;
    }

    @GetMapping
    public String home(Model model, HttpSession session){
        model.addAttribute("position", stationDataService.stationTrailToSession());
        List<StationData> trail =
                (List<StationData>) session.getAttribute("stationTrail");
        model.addAttribute("trail",trail);
        if(session.getAttribute("currentSpeed")!=null){
            double currentSpeed =
                    (double) session.getAttribute("currentSpeed");
            long summedDistance =
                    (long) session.getAttribute("summedDistance");
            model.addAttribute("currentSpeed", currentSpeed);
            model.addAttribute("summedDistance", summedDistance);
        }
        return "main";
    }

}

package io.luisneckar.covid19tracker.controllers;

import io.luisneckar.covid19tracker.model.LocationStats;
import io.luisneckar.covid19tracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**Ebensee, 4802, Österreich
 * Created by Luis Neckar on 18.07.2020.
 * Time: 13:42
 */

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("locationStats", allStats);

        return "home";
    }
}


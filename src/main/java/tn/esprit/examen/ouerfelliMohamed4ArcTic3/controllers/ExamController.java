package tn.esprit.examen.ouerfelliMohamed4ArcTic3.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Attendee;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Film;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Screening;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.services.IExamService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ExamController {

    private final IExamService service;

    @PostMapping("add-film")
    public Film addFilm(@RequestBody Film film) {
        return service.addFilm(film);
    }

    @PostMapping("add-screening-assign-to-film/{filmTitle}")
    public Screening addScreeningAndAssignToFilm(@RequestBody Screening screening,@PathVariable("filmTitle") String filmTitle) {
        return service.addScreeningAndAssignToFilm(screening, filmTitle);
    }

    @GetMapping("coming-screening/{filmTitle}")
    public Screening findComingScreeningForTitle(@PathVariable("filmTitle") String filmTitle) {
        return service.findComingScreeningForTitle(filmTitle);
    }

    @PostMapping("add-attendee")
    public Attendee addAttendeeAndAssignToScreening(@RequestBody Attendee attendee, @RequestParam Set<String> filmTitles) {
        return service.addAttendeeAndAssignToScreening(attendee, filmTitles);
    }

    @GetMapping("get-screening-venue/{venue}")
    public List<Screening> findScreeningByVenue(@PathVariable("venue") String venue) {
        return service.findScreeningByVenue(venue);
    }

    @GetMapping("popularity")
    public void filmsPopularity() {
        service.filmsPopularity();
    }
}

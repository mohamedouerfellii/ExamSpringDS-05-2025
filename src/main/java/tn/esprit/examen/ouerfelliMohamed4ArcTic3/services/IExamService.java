package tn.esprit.examen.ouerfelliMohamed4ArcTic3.services;

import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Attendee;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Film;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Screening;

import java.util.List;
import java.util.Set;

public interface IExamService {
    Film addFilm(Film film);
    Screening addScreeningAndAssignToFilm(Screening screening, String filmTitle);
    Screening findComingScreeningForTitle(String titleFilm);
    Attendee addAttendeeAndAssignToScreening(Attendee attendee, Set<String> filmTitles);
    List<Screening> findScreeningByVenue(String venue);
    void showFilmsNotScheduled();
    void filmsPopularity();
}

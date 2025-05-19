package tn.esprit.examen.ouerfelliMohamed4ArcTic3.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Attendee;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Film;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Screening;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.AttendeeRepository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.FilmRepository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories.ScreeningRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements IExamService{

    private final FilmRepository filmRepository;
    private final ScreeningRepository screeningRepository;
    private final AttendeeRepository attendeeRepository;

    @Override
    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Screening addScreeningAndAssignToFilm(Screening screening, String filmTitle) {
        Film film = filmRepository.findByTitleEquals(filmTitle).orElseThrow(
                () -> new IllegalArgumentException("Film not found.")
        );
        if (screeningRepository.findByScreeningDateAndScreeningTimeAndFilm_Title(screening.getScreeningDate(), screening.getScreeningTime(), filmTitle).isPresent()) {
            log.info("Screnning exist with the same date and time for film : " + filmTitle);
            return null;
        }
        screening.setFilm(film);
        return screeningRepository.save(screening);
    }

    @Override
    public Screening findComingScreeningForTitle(String titleFilm) {
        return screeningRepository.findComingScreeningForTitle(titleFilm, LocalDate.now(), LocalTime.now()).orElse(null);
    }

    @Override
    public Attendee addAttendeeAndAssignToScreening(Attendee attendee, Set<String> filmTitles) {
        Set<Screening> screenings = new HashSet<>();
        for (String title : filmTitles) {
            Screening screening = screeningRepository.findComingScreeningForTitle(
                    title, LocalDate.now(), LocalTime.now()
            ).orElseThrow(() -> new IllegalArgumentException("screening not found"));
            screenings.add(screening);
        }
        attendee.setScreenings(screenings);
        return attendeeRepository.save(attendee);
    }

    @Override
    public List<Screening> findScreeningByVenue(String venue) {
        return screeningRepository.findAllByVenueOrderByScreeningDateAscScreeningTimeAsc(venue);
    }

    @Scheduled(fixedDelay = 60000)
    @Override
    @Transactional
    public void showFilmsNotScheduled() {
        List<Film> films = screeningRepository.findNotScheduledFilms();
        log.info("Films with no upcoming scheduled screenings : ");
        films.forEach(f -> log.info(f.getTitle() + " ( " + f.getProducerName() + " )"));
    }

    @Override
    public void filmsPopularity() {
        List<Film> films = filmRepository.findAll();
        films.forEach(
                f -> {
                    log.info("Film : " + f.getTitle() + " - NBR Séances : " + screeningRepository.countAllByFilm_Title(f.getTitle()));
                }
        );
    }
}

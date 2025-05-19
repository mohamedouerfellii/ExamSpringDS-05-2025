package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Film;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Screening;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    Optional<Screening> findByScreeningDateAndScreeningTimeAndFilm_Title(LocalDate date, LocalTime time, String filmTitle);

    @Query(value = "SELECT * FROM screening s " +
            "JOIN film f ON s.film_num_film = f.num_film " +
            "WHERE f.title = :titleFilm AND " +
            "(s.screening_date > :today OR (s.screening_date = :today AND s.screening_time >= :nowTime)) " +
            "ORDER BY s.screening_date ASC, s.screening_time DESC LIMIT 1", nativeQuery = true)
    Optional<Screening> findComingScreeningForTitle(
            @Param("titleFilm") String titleFilm,
            @Param("today") LocalDate today,
            @Param("nowTime") LocalTime nowTime);

    List<Screening> findAllByVenueOrderByScreeningDateAscScreeningTimeAsc(String venue);

    @Query("SELECT f FROM Film f WHERE f NOT IN (SELECT s.film FROM Screening s)")
    List<Film> findNotScheduledFilms();

    long countAllByFilm_Title(String filmTitle);

}

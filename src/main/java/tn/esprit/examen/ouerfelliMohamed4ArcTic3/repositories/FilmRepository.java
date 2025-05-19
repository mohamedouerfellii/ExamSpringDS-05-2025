package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Film;

import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByTitleEquals(String title);
}

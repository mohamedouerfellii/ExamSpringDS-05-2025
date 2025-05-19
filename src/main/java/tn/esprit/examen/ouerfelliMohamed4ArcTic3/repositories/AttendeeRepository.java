package tn.esprit.examen.ouerfelliMohamed4ArcTic3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}

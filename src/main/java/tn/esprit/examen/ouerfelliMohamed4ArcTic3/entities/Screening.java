package tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Screening implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numScreening;
    LocalDate screeningDate;
    LocalTime screeningTime;
    String venue;
    @ManyToOne
    Film film;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JsonIgnore
    Set<Attendee> attendees;
}

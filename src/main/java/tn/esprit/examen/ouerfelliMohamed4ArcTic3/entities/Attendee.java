package tn.esprit.examen.ouerfelliMohamed4ArcTic3.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.examen.ouerfelliMohamed4ArcTic3.enums.AttendeeType;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long numAttendee;
    String fullName;
    String profession;
    @Enumerated(EnumType.STRING)
    AttendeeType attendeeType;
    @ManyToMany(mappedBy = "attendees")
    Set<Screening> screenings;
}

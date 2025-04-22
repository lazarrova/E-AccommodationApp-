package mk.ukim.finki.emt.rent_room_application.repository;

import mk.ukim.finki.emt.rent_room_application.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.rent_room_application.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryReservationRepository  extends JpaRepository<TemporaryReservation,Long> {
    Optional<TemporaryReservation> findByUser(User user);

}

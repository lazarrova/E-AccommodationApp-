package mk.ukim.finki.emt.rent_room_application.service.domain;

import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.TemporaryReservation;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationService {

    List<Accommodation> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservation> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservation> confirmReservation(String username);

}

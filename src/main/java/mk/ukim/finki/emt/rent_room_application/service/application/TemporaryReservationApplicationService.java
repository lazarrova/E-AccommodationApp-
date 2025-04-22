package mk.ukim.finki.emt.rent_room_application.service.application;

import mk.ukim.finki.emt.rent_room_application.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.dto.TemporaryReservstionDto;

import java.util.List;
import java.util.Optional;

public interface TemporaryReservationApplicationService {

    List<DisplayAccomodationDto> listAllAccommodationsInTemporaryReservation(String username);
    Optional<TemporaryReservstionDto> addAccommodation(String username, Long accommodationId);
    Optional<TemporaryReservstionDto> confirmReservation(String username);
}

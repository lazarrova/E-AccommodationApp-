package mk.ukim.finki.emt.rent_room_application.service.application.impl;

import mk.ukim.finki.emt.rent_room_application.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.dto.TemporaryReservstionDto;
import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.TemporaryReservation;
import mk.ukim.finki.emt.rent_room_application.model.domain.User;
import mk.ukim.finki.emt.rent_room_application.model.exceptions.AccommodationNotAvailable;
import mk.ukim.finki.emt.rent_room_application.model.exceptions.AccommodationNotFound;
import mk.ukim.finki.emt.rent_room_application.model.exceptions.NoTemporaryReservations;
import mk.ukim.finki.emt.rent_room_application.repository.TemporaryReservationRepository;
import mk.ukim.finki.emt.rent_room_application.service.application.TemporaryReservationApplicationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.AccommodationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TemporaryReservationApplicationServiceImpl implements TemporaryReservationApplicationService {


    private final TemporaryReservationRepository temporaryReservationRepository;
    private final AccommodationService accommodationService;
    private final UserService userService;

    public TemporaryReservationApplicationServiceImpl(TemporaryReservationRepository temporaryReservationRepository, AccommodationService accommodationService, UserService userService) {
        this.temporaryReservationRepository = temporaryReservationRepository;
        this.accommodationService = accommodationService;
        this.userService = userService;
    }


    @Override
    public List<DisplayAccomodationDto> listAllAccommodationsInTemporaryReservation(String username) {
        User user = userService.findByUsername(username);
        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);
        return DisplayAccomodationDto.from(reservation.getAccommodations());
    }

    @Override
    public Optional<TemporaryReservstionDto> addAccommodation(String username, Long accommodationId) {
        User user = userService.findByUsername(username);
        Accommodation accommodation = accommodationService.findById(accommodationId).orElseThrow(AccommodationNotFound::new);
        if (accommodation.isRented()) {
            throw new AccommodationNotAvailable();
        }

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseGet(() -> {
                    TemporaryReservation reservation1 = new TemporaryReservation();
                    reservation1.setUser(user);
                    reservation1.setAccommodations(new ArrayList<>());
                    return reservation1;
                });
        if (!reservation.getAccommodations().contains(accommodation)) {
            reservation.getAccommodations().add(accommodation);
        }

        TemporaryReservation saved = temporaryReservationRepository.save(reservation);
        return Optional.of(TemporaryReservstionDto.from(saved));
    }

    @Override
    public Optional<TemporaryReservstionDto> confirmReservation(String username) {
        User user = userService.findByUsername(username);

        TemporaryReservation reservation = temporaryReservationRepository.findByUser(user)
                .orElseThrow(NoTemporaryReservations::new);

        for (Accommodation accommodation : reservation.getAccommodations()) {
            accommodation.setRented(true);
            accommodationService.save(accommodation);
        }
        TemporaryReservstionDto dto = TemporaryReservstionDto.from(reservation);
        temporaryReservationRepository.delete(reservation);
        return Optional.of(dto);
    }
}


package mk.ukim.finki.emt.rent_room_application.service.application;


import mk.ukim.finki.emt.rent_room_application.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.dto.CreateReviewDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayAccomodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccomodationDto> findAll();

    Optional<DisplayAccomodationDto> findById(Long id);

    Optional<DisplayAccomodationDto> update(Long id, CreateAccomodationDto accommodation);

    Optional<DisplayAccomodationDto> save(CreateAccomodationDto accommodation);

    Optional<DisplayAccomodationDto> markAsRented(Long id);

    void deleteById(Long id);

    Optional<DisplayAccomodationDto> addReview(Long id,  CreateReviewDto reviewDto);
}
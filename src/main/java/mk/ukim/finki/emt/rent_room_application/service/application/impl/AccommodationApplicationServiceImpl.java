package mk.ukim.finki.emt.rent_room_application.service.application.impl;

import mk.ukim.finki.emt.rent_room_application.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.dto.CreateReviewDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
import mk.ukim.finki.emt.rent_room_application.model.domain.Review;
import mk.ukim.finki.emt.rent_room_application.service.application.AccommodationApplicationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.AccommodationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.HostService;
import mk.ukim.finki.emt.rent_room_application.service.domain.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;
    private final ReviewService reviewService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService, ReviewService reviewService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
        this.reviewService = reviewService;
    }

    @Override
    public List<DisplayAccomodationDto> findAll() {
        return accommodationService.findAll().stream().map(DisplayAccomodationDto::from).toList();

    }

    @Override
    public Optional<DisplayAccomodationDto> findById(Long id) {
        return accommodationService.findById(id).map(DisplayAccomodationDto::from);

    }

    @Override
    public Optional<DisplayAccomodationDto> update(Long id, CreateAccomodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());
        return accommodationService.update(id,
                accommodation.toAccommodation(
                        host.orElse(null)
                )
        ).map(DisplayAccomodationDto::from);
    }

    @Override
    public Optional<DisplayAccomodationDto> save(CreateAccomodationDto accommodation) {
        Optional<Host> host = hostService.findById(accommodation.hostId());

        if (host.isPresent()) {
            return accommodationService.save(accommodation.toAccommodation(host.get()))
                    .map(DisplayAccomodationDto::from);
        }
        return Optional.empty();
    }

    @Override
    public Optional<DisplayAccomodationDto> markAsRented(Long id) {
        return accommodationService.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accommodationService.save(accommodation)
                    .map(DisplayAccomodationDto::from)
                    .orElse(null);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccomodationDto> addReview(Long id, CreateReviewDto reviewDto) {
        return accommodationService.findById(id).map(accommodation -> {
            Review review = reviewDto.toReview();

            review = reviewService.save(review).orElseThrow(()->new RuntimeException("Review not saved"));

            accommodation.getReviewList().add(review);

            return accommodationService.update(id,accommodation)
                    .map(DisplayAccomodationDto::from)
                    .orElse(null);
        });
    }

    @Override
    public List<DisplayAccomodationDto> searchBy(String name, String category, Long hostId, Integer numOfRooms) {
        List<Accommodation> results = accommodationService.search(name, category, hostId, numOfRooms);
        return results.stream().map(DisplayAccomodationDto::from).toList();
    }


}

package mk.ukim.finki.emt.rent_room_application.service.domain.impl;

import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.Review;
import mk.ukim.finki.emt.rent_room_application.repository.AccommodationPerHostViewRepository;
import mk.ukim.finki.emt.rent_room_application.repository.AccomodationRepository;
import mk.ukim.finki.emt.rent_room_application.service.domain.AccommodationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.HostService;
import mk.ukim.finki.emt.rent_room_application.service.domain.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccomodationRepository accomodationRepository;
    private final HostService hostService;
    private final ReviewService reviewService;
    private final AccommodationPerHostViewRepository accommodationPerHostViewRepository;

    public AccommodationServiceImpl(AccomodationRepository accomodationRepository, HostService hostService, ReviewService reviewService, AccommodationPerHostViewRepository accommodationPerHostViewRepository) {
        this.accomodationRepository = accomodationRepository;
        this.hostService = hostService;
        this.reviewService = reviewService;
        this.accommodationPerHostViewRepository = accommodationPerHostViewRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accomodationRepository.findAll();
    }

    @Override
    public Page<Accommodation> findAll(Pageable pageable) {
        return this.accomodationRepository.findAll(pageable);
    }


    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accomodationRepository.findById(id);    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accomodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null) {
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null) {
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
                        existingAccommodation.setHost(hostService.findById(accommodation.getHost().getId()).get());
                    }
                    if (accommodation.getNumRooms() != null) {
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return accomodationRepository.save(existingAccommodation);
                });    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getCategory() != null && hostService.findById(accommodation.getHost().getId()).isPresent()) {
            return Optional.of(
                    accomodationRepository.save(new Accommodation(accommodation.getName(), accommodation.getCategory(),
                            hostService.findById(accommodation.getHost().getId()).get(), accommodation.getNumRooms(), new Review()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> markAsRented(Long id) {
        return accomodationRepository.findById(id).map(accommodation -> {
            accommodation.setRented(true);
            return accomodationRepository.save(accommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.accomodationRepository.deleteById(id);

    }

    @Override
    public Optional<Accommodation> addReview(Long id, Review review) {
        review = reviewService.save(review).get();
        Accommodation accommodation = this.accomodationRepository.findById(id).get();

        accommodation.getReviewList().add(review);
        return Optional.of(accomodationRepository.save(accommodation));
    }

    @Override
    public List<Accommodation> search(String name, String category, Long hostId, Integer numRooms) {
        return accomodationRepository.findAll().stream()
                .filter(a -> name == null || a.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(a -> category == null || a.getCategory().name().equalsIgnoreCase(category))
                .filter(a -> hostId == null || a.getHost().getId().equals(hostId))
                .filter(a -> numRooms == null || a.getNumRooms().equals(numRooms))
                .toList();
    }

    @Override
    public void refreshMaterializedView() {
        accommodationPerHostViewRepository.refreshMaterializedView();
    }



}

package mk.ukim.finki.emt.rent_room_application.service.domain;

import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    List<Accommodation> findAll();
    Page<Accommodation> findAll(Pageable pageable);   //paginacija

    Optional<Accommodation> findById(Long id);

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> save(Accommodation accommodation);

    Optional<Accommodation> markAsRented(Long id);

    void deleteById(Long id);

    Optional<Accommodation>addReview(Long id, Review review);

    List<Accommodation> search(String name, String category, Long hostId, Integer numRooms);

    void refreshMaterializedView();




}

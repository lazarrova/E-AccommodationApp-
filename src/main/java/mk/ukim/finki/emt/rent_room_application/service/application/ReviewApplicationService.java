package mk.ukim.finki.emt.rent_room_application.service.application;

import mk.ukim.finki.emt.rent_room_application.dto.CreateReviewDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewApplicationService {
    List<DisplayReviewDto> findAll();

    Optional<DisplayReviewDto> findById(Long id);

    Optional<DisplayReviewDto> update(Long id, CreateReviewDto reviewDto);

    Optional<DisplayReviewDto> save(CreateReviewDto reviewDto);

    void deleteById(Long id);
}

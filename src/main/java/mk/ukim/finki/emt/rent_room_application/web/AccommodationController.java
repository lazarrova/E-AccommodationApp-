package mk.ukim.finki.emt.rent_room_application.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emt.rent_room_application.dto.CreateAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.dto.CreateReviewDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayAccomodationDto;
import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accommodations")
@Tag(name = "Accommodations", description = "Accommodation API")
public class AccommodationController {

    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping
    @Operation(summary = "Return all accomodations")
    public List<DisplayAccomodationDto> findAll() {
        return accommodationApplicationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns accommodation by ID")
    public ResponseEntity<DisplayAccomodationDto> findById(@PathVariable Long id) {
        return this.accommodationApplicationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @Operation(summary = "Adds a new accommodation")
    public ResponseEntity<DisplayAccomodationDto> save(@RequestBody CreateAccomodationDto accommodation) {
        return accommodationApplicationService.save(accommodation).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Updates an accommodation record")
    public ResponseEntity<DisplayAccomodationDto> update(@PathVariable Long id, @RequestBody CreateAccomodationDto accommodation) {
        return accommodationApplicationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Marks an accommodation as rented")
    public ResponseEntity<DisplayAccomodationDto> markAsRented(@PathVariable Long id) {
        return accommodationApplicationService.markAsRented(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes an accommodation by its ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationApplicationService.findById(id).isPresent()) {
            accommodationApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add-review/{id}")
    @Operation(summary = "Adds an accommodation review")
    public Optional<DisplayAccomodationDto> addReview(@PathVariable Long id, CreateReviewDto reviewDto) {
        return accommodationApplicationService.addReview(id, reviewDto);
    }

    @GetMapping("/search")
    @Operation(summary = "Search accommodations by name, category, host, or number of rooms")
    public List<DisplayAccomodationDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Integer numRooms
    ) {
        return accommodationApplicationService.searchBy(name, category, hostId, numRooms);
    }

}

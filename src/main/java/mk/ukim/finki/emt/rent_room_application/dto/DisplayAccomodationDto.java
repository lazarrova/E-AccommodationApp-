package mk.ukim.finki.emt.rent_room_application.dto;

import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAccomodationDto(
    Long id,
    String name,
    Category category,
    Long hostId,
    Integer numRooms,
    boolean isRented,

    List<DisplayReviewDto> reviewList
) {

    public static DisplayAccomodationDto from(Accommodation accommodation){

        return new DisplayAccomodationDto(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms(),
                accommodation.isRented(),
                accommodation.getReviewList().stream().map(DisplayReviewDto::from).collect(Collectors.toList())

        );
    }
    public static List<DisplayAccomodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(DisplayAccomodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host hostId) {
        return new Accommodation(name, category, hostId, numRooms);
    }


}

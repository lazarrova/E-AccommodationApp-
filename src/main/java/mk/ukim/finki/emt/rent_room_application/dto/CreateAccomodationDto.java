package mk.ukim.finki.emt.rent_room_application.dto;

import mk.ukim.finki.emt.rent_room_application.model.domain.Accommodation;
import mk.ukim.finki.emt.rent_room_application.model.domain.Country;
import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAccomodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms
) {


    public static CreateAccomodationDto from(Accommodation accommodation){
        return new CreateAccomodationDto(
                accommodation.getName(),
                accommodation.getCategory(),
                accommodation.getHost().getId(),
                accommodation.getNumRooms()
        );
    }

    public static List<CreateAccomodationDto> from(List<Accommodation> accommodations) {
        return accommodations.stream().map(CreateAccomodationDto::from).collect(Collectors.toList());
    }

    public Accommodation toAccommodation(Host hostId) {
        return new Accommodation(name,category, hostId,numRooms);
    }



}

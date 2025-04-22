package mk.ukim.finki.emt.rent_room_application.dto;

import mk.ukim.finki.emt.rent_room_application.model.domain.TemporaryReservation;

import java.util.List;

public record TemporaryReservstionDto(
        Long id,
        DisplayUserDto user,
        List<DisplayAccomodationDto> accommodations
) {

    public static TemporaryReservstionDto from(TemporaryReservation temporaryReservation) {
        return new TemporaryReservstionDto(
                temporaryReservation.getId(),
                DisplayUserDto.from(temporaryReservation.getUser()),
                DisplayAccomodationDto.from(temporaryReservation.getAccommodations())
        );

    }


}

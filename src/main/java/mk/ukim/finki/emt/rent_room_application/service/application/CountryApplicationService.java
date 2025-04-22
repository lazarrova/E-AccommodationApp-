package mk.ukim.finki.emt.rent_room_application.service.application;

import mk.ukim.finki.emt.rent_room_application.dto.CreateCountryDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto);

    Optional<DisplayCountryDto> save(CreateCountryDto countryDto);

    void deleteById(Long id);

}
package mk.ukim.finki.emt.rent_room_application.service.application.impl;

import mk.ukim.finki.emt.rent_room_application.dto.CreateCountryDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayCountryDto;
import mk.ukim.finki.emt.rent_room_application.service.application.CountryApplicationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
   private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);

    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id,countryDto.toCountry()).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto countryDto) {
        return countryService.save(countryDto.toCountry()).map(DisplayCountryDto::from);

    }

    @Override
    public void deleteById(Long id) {
        this.countryService.deleteById(id);

    }
}

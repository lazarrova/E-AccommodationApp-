package mk.ukim.finki.emt.rent_room_application.service.application.impl;

import mk.ukim.finki.emt.rent_room_application.dto.CreateHostDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayHostDto;
import mk.ukim.finki.emt.rent_room_application.model.domain.Country;
import mk.ukim.finki.emt.rent_room_application.service.application.HostApplicationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.CountryService;
import mk.ukim.finki.emt.rent_room_application.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return DisplayHostDto.from(hostService.findAll());
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());
        return hostService.update(id,
                hostDto.toHost(
                        country.orElse(null)
                )
        ).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto hostDto) {
        Optional<Country> country = countryService.findById(hostDto.country());
        if (country.isPresent()) {
            return hostService.save(hostDto.toHost(country.get())).map(DisplayHostDto::from);
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.hostService.deleteById(id);
    }
}

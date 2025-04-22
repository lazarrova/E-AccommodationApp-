package mk.ukim.finki.emt.rent_room_application.service.application;

import mk.ukim.finki.emt.rent_room_application.dto.CreateHostDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayHostDto;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto hostDto);

    Optional<DisplayHostDto> save(CreateHostDto hostDto);

    void deleteById(Long id);
}

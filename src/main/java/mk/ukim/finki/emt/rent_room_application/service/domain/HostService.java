package mk.ukim.finki.emt.rent_room_application.service.domain;

import mk.ukim.finki.emt.rent_room_application.model.domain.Host;

import java.util.List;
import java.util.Optional;

public interface HostService {

    List<Host> findAll();

    Optional<Host> findById(Long id);

    Optional<Host> save(Host host);

    Optional<Host> update(Long id, Host host);

    void deleteById(Long id);
}

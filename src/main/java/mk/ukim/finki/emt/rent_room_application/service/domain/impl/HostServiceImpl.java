package mk.ukim.finki.emt.rent_room_application.service.domain.impl;

import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
//import mk.ukim.finki.emt.rent_room_application.repository.HostByCountryViewRepository;
import mk.ukim.finki.emt.rent_room_application.repository.HostPerCountryViewRepository;
import mk.ukim.finki.emt.rent_room_application.repository.HostRepository;
import mk.ukim.finki.emt.rent_room_application.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
   private final HostPerCountryViewRepository hostPerCountryViewRepository;

    public HostServiceImpl(HostRepository hostRepository, HostPerCountryViewRepository hostPerCountryViewRepository) {
        this.hostRepository = hostRepository;
        this.hostPerCountryViewRepository = hostPerCountryViewRepository;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);

    }

    @Override
    public Optional<Host> save(Host host) {
        return Optional.of(this.hostRepository.save(host));
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id).map(existingHost -> {
            if (host.getName() != null) {
                existingHost.setName(host.getName());
            }
            if (host.getSurname() != null){
                existingHost.setSurname(host.getSurname());
            }
            if (host.getCountry() != null){
                existingHost.setCountry(host.getCountry());
            }
            return this.hostRepository.save(existingHost);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);

    }

    @Override
    public void refreshMaterializedView() {
        hostPerCountryViewRepository.refreshMaterializedView();

    }
}

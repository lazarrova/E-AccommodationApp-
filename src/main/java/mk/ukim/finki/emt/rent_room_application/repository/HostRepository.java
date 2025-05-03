package mk.ukim.finki.emt.rent_room_application.repository;

import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
import mk.ukim.finki.emt.rent_room_application.model.projection.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<HostProjection> findAllBy();

}

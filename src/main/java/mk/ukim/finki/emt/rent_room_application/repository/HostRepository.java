package mk.ukim.finki.emt.rent_room_application.repository;

import mk.ukim.finki.emt.rent_room_application.model.domain.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}

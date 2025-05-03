package mk.ukim.finki.emt.rent_room_application.repository;


import mk.ukim.finki.emt.rent_room_application.model.views.HostPerCountryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HostPerCountryViewRepository extends JpaRepository<HostPerCountryView, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.hosts_per_countries", nativeQuery = true)
    void refreshMaterializedView();
}

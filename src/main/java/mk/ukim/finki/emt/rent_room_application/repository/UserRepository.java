package mk.ukim.finki.emt.rent_room_application.repository;

import mk.ukim.finki.emt.rent_room_application.model.enumerations.Role;
import mk.ukim.finki.emt.rent_room_application.model.projection.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import mk.ukim.finki.emt.rent_room_application.model.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

//    @EntityGraph(
//            type = EntityGraph.EntityGraphType.FETCH,
//            attributePaths = {"carts"}
//    )
//    @Query("select u from User u")
//    List<User> fetchAll();

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"hosts","countries","accommodations" } // празно: не се наведува "temporaryReservations"
    )
    @Query("SELECT u FROM User u")
    List<User> findAll();

    UserProjection findByRole(Role role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}


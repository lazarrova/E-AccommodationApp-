package mk.ukim.finki.emt.rent_room_application.service.application;

import mk.ukim.finki.emt.rent_room_application.dto.CreateUserDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayUserDto;
import mk.ukim.finki.emt.rent_room_application.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);

}

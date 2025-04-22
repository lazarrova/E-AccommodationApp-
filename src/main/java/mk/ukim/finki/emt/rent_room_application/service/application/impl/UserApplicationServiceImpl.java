package mk.ukim.finki.emt.rent_room_application.service.application.impl;

import mk.ukim.finki.emt.rent_room_application.dto.CreateUserDto;
import mk.ukim.finki.emt.rent_room_application.dto.DisplayUserDto;
import mk.ukim.finki.emt.rent_room_application.dto.LoginUserDto;
import mk.ukim.finki.emt.rent_room_application.model.domain.User;
import mk.ukim.finki.emt.rent_room_application.service.application.UserApplicationService;
import mk.ukim.finki.emt.rent_room_application.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

}
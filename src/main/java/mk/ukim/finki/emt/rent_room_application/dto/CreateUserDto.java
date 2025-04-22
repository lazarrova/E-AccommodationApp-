package mk.ukim.finki.emt.rent_room_application.dto;

import mk.ukim.finki.emt.rent_room_application.model.domain.User;
import mk.ukim.finki.emt.rent_room_application.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser(){
        return new User(username,password,name,surname,role);
    }
}

package mk.ukim.finki.emt.rent_room_application.model.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("User not found");
    }
}

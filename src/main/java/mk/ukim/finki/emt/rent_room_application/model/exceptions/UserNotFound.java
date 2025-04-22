package mk.ukim.finki.emt.rent_room_application.model.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("User not found");
    }
}

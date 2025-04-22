package mk.ukim.finki.emt.rent_room_application.model.exceptions;

public class AccommodationNotFound extends RuntimeException{
    public AccommodationNotFound() {
        super("Accommodation not found");
    }
}

package mk.ukim.finki.emt.rent_room_application.model.exceptions;

public class AccommodationNotAvailable extends RuntimeException{
    public AccommodationNotAvailable() {
        super("Accommodation is not available");
    }
}

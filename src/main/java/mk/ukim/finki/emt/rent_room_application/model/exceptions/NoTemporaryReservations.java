package mk.ukim.finki.emt.rent_room_application.model.exceptions;

public class NoTemporaryReservations extends RuntimeException{
    public NoTemporaryReservations() {
        super("No temporary reservations");
    }
}

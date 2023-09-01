package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Booking;
import org.hibernate.Session;

public class BookingRepository extends BaseRepository<Long, Booking>{

    public BookingRepository(Session session) {
        super(Booking.class, session);
    }
}

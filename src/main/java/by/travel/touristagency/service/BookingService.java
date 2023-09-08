package by.travel.touristagency.service;

import by.travel.touristagency.dto.BookingDto;
import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.mapper.BookingMapper;
import by.travel.touristagency.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookingService {
    private final SessionFactory sessionFactory;
    private final BookingMapper bookingMapper;
    private BookingRepository bookingRepository;

    public void createBooking(BookingDto bookingDto) {
        try (Session session = sessionFactory.openSession()) {
            bookingRepository = new BookingRepository(session);
            session.beginTransaction();

            bookingRepository.save(bookingMapper.map(bookingDto));

            session.getTransaction().commit();
        }
    }

    public List<Voucher> getVouchersByUserId(Long id) {
        List<Voucher> userVouchers;

        try (Session session = sessionFactory.openSession()) {
            bookingRepository = new BookingRepository(session);
            session.beginTransaction();

            userVouchers = bookingRepository.getVouchersByUserId(id);

            session.getTransaction().commit();
        }

        return userVouchers;
    }

    public Optional<Booking> getBookingByVoucherIdAndUserId(Long voucherId, Long userId) {
        Optional<Booking> booking;

        try (Session session = sessionFactory.openSession()) {
            bookingRepository = new BookingRepository(session);
            session.beginTransaction();

            booking = bookingRepository.getBookingByVoucherIdAndUserId(voucherId, userId);

            session.getTransaction().commit();
        }

        return booking;
    }

    public void deleteBookingFromUserProfile(Long id) {
        try (Session session = sessionFactory.openSession()) {
            bookingRepository = new BookingRepository(session);
            session.beginTransaction();

            bookingRepository.delete(id);

            session.getTransaction().commit();
        }
    }
}

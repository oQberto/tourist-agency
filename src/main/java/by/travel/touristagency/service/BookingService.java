package by.travel.touristagency.service;

import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.repository.BookingRepository;
import by.travel.touristagency.util.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BookingService {
    private static volatile BookingService instance;
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getInstance().buildSessionFactory();
    private BookingRepository bookingRepository;

    public void createBooking(Booking booking) {
        try (Session session = sessionFactory.openSession()) {
            bookingRepository = new BookingRepository(session);
            session.beginTransaction();

            bookingRepository.save(booking);

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

    public static BookingService getInstance() {
        BookingService result = instance;
        if (result != null) {
            return result;
        }
        synchronized (BookingService.class) {
            if (instance == null) {
                instance = new BookingService();
            }
            return instance;
        }
    }
}

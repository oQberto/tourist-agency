package by.travel.touristagency.repository;

import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.Voucher;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static by.travel.touristagency.entity.QBooking.booking;
import static by.travel.touristagency.entity.QUser.user;
import static by.travel.touristagency.entity.QVoucher.voucher;

public class BookingRepository extends BaseRepository<Long, Booking> {

    public BookingRepository(Session session) {
        super(Booking.class, session);
    }

    public List<Voucher> getVouchersByUserId(Long id) {
        return new JPAQuery<Voucher>(getSession())
                .select(voucher)
                .from(voucher)
                .join(voucher.bookings, booking)
                .join(booking.user, user)
                .where(user.id.eq(id))
                .fetch();
    }

    public Optional<Booking> getBookingByVoucherIdAndUserId(Long voucherId, Long userId) {
        return Optional.ofNullable(
                new JPAQuery<Booking>(getSession())
                        .select(booking)
                        .from(booking)
                        .join(booking.voucher, voucher)
                        .join(booking.user, user)
                        .where(voucher.id.eq(voucherId).and(user.id.eq(userId)))
                        .fetchOne()
        );
    }
}

package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.BookingDto;
import by.travel.touristagency.entity.Booking;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BookingMapper implements Mapper<BookingDto, Booking> {
    private static volatile BookingMapper instance;

    @Override
    public Booking map(BookingDto object) {
        return Booking.builder()
                .user(object.getUser())
                .voucher(object.getVoucher())
                .numberOfPersons(object.getNumberOfPersons())
                .build();
    }

    public static BookingMapper getInstance() {
        BookingMapper result = instance;
        if (result != null) {
            return result;
        }

        synchronized (BookingMapper.class) {
            if (instance == null) {
                instance = new BookingMapper();
            }
            return instance;
        }
    }
}

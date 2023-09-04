package by.travel.touristagency.mapper;

import by.travel.touristagency.dto.BookingDto;
import by.travel.touristagency.entity.Booking;
import by.travel.touristagency.entity.User;
import by.travel.touristagency.entity.Voucher;
import by.travel.touristagency.entity.enums.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookingMapperTest {
    private final BookingMapper bookingMapper = BookingMapper.getInstance();

    @Test
    void map() {
        BookingDto bookingDto = BookingDto.builder()
                .user(User.builder()
                        .username("UName")
                        .email("name@gmail.com")
                        .password("123")
                        .build())
                .voucher(Voucher.builder()
                        .name("Voucher1")
                        .price(125.5)
                        .type(Type.SHOPPING)
                        .description("description")
                        .build())
                .numberOfPersons(4)
                .build();

        Booking actualResult = bookingMapper.map(bookingDto);
        Booking expectedResult = Booking.builder()
                .user(User.builder()
                        .username("UName")
                        .email("name@gmail.com")
                        .password("123")
                        .build())
                .voucher(Voucher.builder()
                        .name("Voucher1")
                        .price(125.5)
                        .type(Type.SHOPPING)
                        .description("description")
                        .build())
                .numberOfPersons(4)
                .build();

        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
package by.travel.touristagency.service;

import by.travel.touristagency.dto.BookingDto;
import by.travel.touristagency.entity.*;
import by.travel.touristagency.mapper.BookingMapper;
import by.travel.touristagency.util.HibernateTestUtil;
import by.travel.touristagency.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static by.travel.touristagency.entity.enums.Type.EXCURSION;
import static org.assertj.core.api.Assertions.assertThat;

class BookingServiceTest {
    private static final SessionFactory SESSION_FACTORY = HibernateTestUtil.buildSessionFactory();
    private final BookingService bookingService = new BookingService(
            SESSION_FACTORY,
            BookingMapper.getInstance()
    );

    @BeforeAll
    public static void setUp() {
        TestDataImporter.importData(SESSION_FACTORY);
    }

    @Test
    void createBooking() {
        BookingDto bookingDto = BookingDto.builder()
                .user(buildUser())
                .voucher(buildVoucher())
                .numberOfPersons(4)
                .build();
        Booking booking = BookingMapper.getInstance().map(bookingDto);
        booking.setId(42L);

        bookingService.createBooking(bookingDto);
        Optional<Booking> actualResult = bookingService.getBookingByVoucherIdAndUserId(buildVoucher().getId(), buildUser().getId());

        assertThat(actualResult).isPresent();
    }

    @Test
    void getVouchersByUserId() {
        Long userId = buildUser().getId();

        List<Voucher> actualResult = bookingService.getVouchersByUserId(userId);
        assertThat(actualResult).hasSize(6);

        List<String> voucherNames = actualResult.stream()
                .map(Voucher::getName)
                .toList();
        assertThat(voucherNames).contains("Voucher9", "Voucher", "Voucher7", "Voucher5", "Voucher6", "Voucher8");
    }

    @Test
    void getBookingByVoucherIdAndUserId() {
        Optional<Booking> actualResult = bookingService.getBookingByVoucherIdAndUserId(8L, 2L);

        assertThat(actualResult).isPresent();
    }

    @Test
    void deleteBookingFromUserProfile() {
        // TODO: create a test here
    }

    private Voucher buildVoucher() {
        return Voucher.builder()
                .id(9L)
                .name("Voucher8")
                .company(buildCompany())
                .price(131.35)
                .type(EXCURSION)
                .description(null)
                .image("greece.webp")
                .build();
    }

    private Company buildCompany() {
        return Company.builder()
                .id(2L)
                .name("Greece Agency")
                .image("greeceCompany.webp")
                .build();
    }

    private User buildUser() {
        return User.builder()
                .id(1L)
                .username("UName")
                .email("uname@gmail.com")
                .password("123")
                .profile(buildProfile())
                .build();
    }

    private Profile buildProfile() {
        return Profile.builder()
                .id(1L)
                .lastName("LName")
                .firstName("FName")
                .birthday(LocalDate.of(2000, 9, 15))
                .build();
    }
}
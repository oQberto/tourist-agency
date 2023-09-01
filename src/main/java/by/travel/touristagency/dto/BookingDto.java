package by.travel.touristagency.dto;

import by.travel.touristagency.entity.User;
import by.travel.touristagency.entity.Voucher;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookingDto {
    Long id;
    User user;
    Voucher voucher;
    Integer numberOfPersons;
}

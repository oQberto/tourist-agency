package by.travel.touristagency.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ProfileDto {
    Integer id;
    String firstName;
    String lastName;
    LocalDate birthday;
}

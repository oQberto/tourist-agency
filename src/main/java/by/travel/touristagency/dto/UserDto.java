package by.travel.touristagency.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDto {
    String username;
    String email;
    String password;
}

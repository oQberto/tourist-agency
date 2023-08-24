package by.travel.touristagency.dto;

import by.travel.touristagency.entity.enums.Transport;
import by.travel.touristagency.entity.enums.Type;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class VoucherFilter {
    String name;
    Double priceFrom;
    Double priceTo;
    Type type;

    String country;
    Integer amountOfDays;
    Transport transport;
    boolean food;
}

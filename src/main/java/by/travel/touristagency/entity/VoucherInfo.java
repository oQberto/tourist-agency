package by.travel.touristagency.entity;

import by.travel.touristagency.entity.enums.Country;
import by.travel.touristagency.entity.enums.Food;
import by.travel.touristagency.entity.enums.Transport;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "voucher")
@Entity
@Table(name = "voucher_info", schema = "public")
public class VoucherInfo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @Column(name = "country")
    @Enumerated(STRING)
    private Country country;

    @Column(name = "start_on")
    private LocalDate startOn;

    @Column(name = "end_on")
    private LocalDate endOn;

    @Column(name = "transport")
    @Enumerated(STRING)
    private Transport transport;

    @Column(name = "food")
    private Food food;
}

package by.travel.touristagency.entity;

import by.travel.touristagency.entity.enums.Country;
import by.travel.touristagency.entity.enums.Transport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private boolean isFoodInclude;
}

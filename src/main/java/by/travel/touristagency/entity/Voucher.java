package by.travel.touristagency.entity;

import by.travel.touristagency.entity.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"company", "info", "bookings"})
@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    @Enumerated(STRING)
    private Type type;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToOne(
            mappedBy = "voucher",
            cascade = ALL
    )
    private VoucherInfo info;

    @OneToMany(
            mappedBy = "voucher",
            orphanRemoval = true,
            cascade = ALL
    )
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();
}

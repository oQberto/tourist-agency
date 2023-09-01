package by.travel.touristagency.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "persons")
    private Integer numberOfPersons;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}

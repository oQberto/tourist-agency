package by.travel.touristagency.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"users", "vouchers"})
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company_image")
    private String image;

    @OneToMany(
            mappedBy = "company",
            cascade = ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Voucher> vouchers = new ArrayList<>();

    @OneToMany(mappedBy = "company")
    @Builder.Default
    private List<User> users = new ArrayList<>();
}

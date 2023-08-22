package by.travel.touristagency.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

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

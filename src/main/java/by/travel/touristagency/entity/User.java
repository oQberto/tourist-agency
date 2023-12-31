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
@EqualsAndHashCode(exclude = {"profile", "bookings"})
@ToString(exclude = {"profile", "bookings"})
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(
            mappedBy = "user",
            cascade = ALL,
            orphanRemoval = true
    )
    private Profile profile;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = ALL
    )
    @Builder.Default
    private List<Booking> bookings = new ArrayList<>();
}

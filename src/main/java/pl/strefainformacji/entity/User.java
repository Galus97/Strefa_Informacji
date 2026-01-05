package pl.strefainformacji.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @Size(min = 5)
    @Column(unique = true)
    @Email
    private String email;

    @Size(min = 5)
    private String password;

    private boolean enabled;

    private String emailCode;

}

package pl.strefainformacji.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Length(min = 3)
    private String firstName;

    @Length(min = 3)
    private String lastName;

    @Length(min = 5)
    @Column(unique = true)
    @Email
    private String email;

    @Length(min = 5)
    private String password;

    private boolean isSubscriber;

    private boolean enabled;

    private String emailCode;
}

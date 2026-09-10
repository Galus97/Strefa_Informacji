package pl.strefainformacji.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@Builder
@SQLDelete(sql = "UPDATE usersData SET is_deleted = true WHERE user_data_id = ?")
@SQLRestriction("is_deleted = false")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usersData")
public class UserData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_data_id")
    private Long userDataId;

    @Length(min = 3)
    private String city;

    @Length(min = 3)
    private String street;

    @NotNull
    private Integer streetNumber;

    @NotNull
    private Integer apartmentNumber;

    @NotBlank
    private String zipCode;

    @NotNull
    private Integer phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "is_deleted") // add this
    private boolean isDeleted = false;
}
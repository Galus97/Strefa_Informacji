package pl.strefainformacji.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Size(min = 3)
    private String title;

    @Size(min = 10)
    private String shortDescription;

    @Size(min = 10)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private List<Category> categories;

    @NotNull
    private List<Tag> tags;
}

package pl.strefainformacji.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import pl.strefainformacji.component.Category;
import pl.strefainformacji.component.Tag;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@SQLDelete(sql = "UPDATE articles SET is_deleted = true WHERE article_id=?")
@SQLRestriction("is_deleted=false")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Size(min = 3)
    private String title;

    @Size(min = 10)
    @Column(name = "short_description")
    private String shortDescription;

    @Size(min = 10)
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    private List<Category> categories;

    @NotNull
    private List<Tag> tags;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "is_deleted")
    private boolean isDeleted = false;
}

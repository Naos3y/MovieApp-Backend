package lopes.bruno.movieappwithsecuriry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMovie {
    @EmbeddedId
    private UserMovieId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Boolean watched = false;

    @Column(nullable = true)
    private Integer rating; // 1-5 stars

    @Column(length = 1000)
    private String review;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

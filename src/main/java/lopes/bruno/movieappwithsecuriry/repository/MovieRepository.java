package lopes.bruno.movieappwithsecuriry.repository;

import lopes.bruno.movieappwithsecuriry.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT m.id, m.title, m.description, m.director, " +
            "m.release_year, m.genre, u.username " +
            "FROM movies m " +
            "JOIN user_movies um ON m.id = um.movie_id " +
            "JOIN users u ON um.user_id = u.id",
            nativeQuery = true)
    List<Object[]> findAllMoviesWithUsersNative();
}

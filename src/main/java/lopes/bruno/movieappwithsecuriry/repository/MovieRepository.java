package lopes.bruno.movieappwithsecuriry.repository;

import lopes.bruno.movieappwithsecuriry.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

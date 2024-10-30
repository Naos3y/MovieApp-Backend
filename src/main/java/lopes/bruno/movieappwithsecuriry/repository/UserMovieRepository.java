package lopes.bruno.movieappwithsecuriry.repository;

import lopes.bruno.movieappwithsecuriry.entity.User;
import lopes.bruno.movieappwithsecuriry.entity.UserMovie;
import lopes.bruno.movieappwithsecuriry.entity.UserMovieId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMovieRepository extends JpaRepository<UserMovie, UserMovieId> {
    List<UserMovie> findByUser(User user);
}

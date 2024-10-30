package lopes.bruno.movieappwithsecuriry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lopes.bruno.movieappwithsecuriry.entity.Movie;
import lopes.bruno.movieappwithsecuriry.entity.User;
import lopes.bruno.movieappwithsecuriry.entity.UserMovie;
import lopes.bruno.movieappwithsecuriry.entity.UserMovieId;
import lopes.bruno.movieappwithsecuriry.repository.MovieRepository;
import lopes.bruno.movieappwithsecuriry.repository.UserMovieRepository;
import lopes.bruno.movieappwithsecuriry.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final UserMovieRepository userMovieRepository;

    public Movie createMovie(Movie movie, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Movie savedMovie = movieRepository.save(movie);

        UserMovieId userMovieId = new UserMovieId(user.getId(), savedMovie.getId());
        UserMovie userMovie = new UserMovie(userMovieId, user, savedMovie, false, null, null, LocalDateTime.now(), LocalDateTime.now());

        userMovieRepository.save(userMovie);

        return savedMovie;
    }

    public List<Movie> getMoviesByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<UserMovie> userMovies = userMovieRepository.findByUser(user);

        return userMovies.stream()
                .map(UserMovie::getMovie)
                .collect(Collectors.toList());
    }
}

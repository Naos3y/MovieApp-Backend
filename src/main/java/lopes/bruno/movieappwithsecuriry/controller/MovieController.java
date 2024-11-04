package lopes.bruno.movieappwithsecuriry.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lopes.bruno.movieappwithsecuriry.dto.MoviesWithUserDTO;
import lopes.bruno.movieappwithsecuriry.entity.Movie;
import lopes.bruno.movieappwithsecuriry.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie, Principal principal) {
        Movie createdMovie = movieService.createMovie(movie, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @GetMapping("/saved")
    public ResponseEntity<List<Movie>> getUserMovies(Principal principal) {
        List<Movie> userMovies = movieService.getMoviesByUser(principal.getName());
        return ResponseEntity.ok(userMovies);
    }

    @GetMapping()
    public ResponseEntity<List<MoviesWithUserDTO>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMoviesWithUsers());
    }
}

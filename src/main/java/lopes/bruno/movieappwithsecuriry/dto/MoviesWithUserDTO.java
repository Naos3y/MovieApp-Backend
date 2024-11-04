package lopes.bruno.movieappwithsecuriry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoviesWithUserDTO {
    private Long id;
    private String title;
    private String description;
    private String director;
    private Integer releaseYear;
    private String genre;
    private String addedByUsername;
}

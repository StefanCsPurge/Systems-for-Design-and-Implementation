package ro.ubb.movieapp.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.web.dto.MovieDto;

import java.util.ArrayList;

@Component
public class MovieConverter extends BaseConverter<Movie, MovieDto>{
    @Autowired
    private ActorConverter actorConverter;

    @Override
    public Movie convertDtoToModel(MovieDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .year(dto.getYear())
                .build();
        movie.setId(dto.getId());
        return movie;
    }

    @Override
    public MovieDto convertModelToDto(Movie movie) {
        MovieDto dto = MovieDto.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .build();
        try {
           dto.setActors(actorConverter.convertModelsToDtos(movie.getActors()));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            dto.setActors(new ArrayList<>());
        }
        dto.setId(movie.getId());
        return dto;
    }
}

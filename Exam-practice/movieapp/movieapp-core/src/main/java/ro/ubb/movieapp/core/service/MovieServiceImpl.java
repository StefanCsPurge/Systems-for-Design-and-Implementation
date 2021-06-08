package ro.ubb.movieapp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: Spurge
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesByYear(int year, boolean lessThan) {
        if(lessThan && year>0)
            return movieRepository.findByOrderByYearDesc().stream().filter(movie -> movie.getYear() <= year).collect(Collectors.toList());
        if(year > 0)
            return movieRepository.findByOrderByYearDesc().stream().filter(movie -> movie.getYear() > year).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    public List<Movie> getMoviesWithActorsByYear(int year, boolean lessThan) {
        if(lessThan && year>0)
            return movieRepository.findAllWithActors().stream().filter(movie -> movie.getYear() <= year).collect(Collectors.toList());
        if(year > 0)
            return movieRepository.findAllWithActors().stream().filter(movie -> movie.getYear() > year).collect(Collectors.toList());
        return new ArrayList<>();
    }

    @Override
    public void deleteActor(Long movieId, Long actorId) {
        var movie = movieRepository.findMovieWithActors(movieId);
        //var actor = movie.getActors().stream().filter(a -> a.getId().equals(actorId)).collect(Collectors.toList()).get(0);
        movie.getActors().removeIf(a -> a.getId().equals(actorId));
        movieRepository.save(movie);
    }
}

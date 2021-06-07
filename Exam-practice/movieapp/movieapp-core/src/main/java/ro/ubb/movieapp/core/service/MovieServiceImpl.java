package ro.ubb.movieapp.core.service;

import ro.ubb.movieapp.core.model.Movie;

import java.util.List;

/**
 * author: radu
 */


public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getMoviesByYear(int year, boolean lessThan) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public List<Movie> getMoviesWithActorsByYear(int year, boolean lessThan) {
        throw new RuntimeException("not yet implemented");
    }

    @Override
    public void deleteActor(Long movieId, Long actorId) {
        throw new RuntimeException("not yet implemented");
    }
}

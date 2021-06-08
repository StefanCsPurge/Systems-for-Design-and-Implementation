package ro.ubb.movieapp.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.movieapp.core.model.Movie;

import java.util.List;

/**
 * author: Spurge
 */
public interface MovieRepository extends MovieAppRepository<Movie, Long> {

    @Query("select distinct m from Movie m")
    @EntityGraph(value = "MovieWithActors", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllWithActors();

    @Query("select distinct m from Movie m where m.id = ?1")
    @EntityGraph(value = "MovieWithActors", type = EntityGraph.EntityGraphType.LOAD)
    Movie findMovieWithActors(Long movieId);

    List<Movie> findByOrderByYearDesc();
}

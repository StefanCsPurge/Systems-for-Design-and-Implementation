package ro.ubb.movieapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.movieapp.core.service.MovieService;
import ro.ubb.movieapp.web.converter.MovieConverter;
import ro.ubb.movieapp.web.dto.MovieDto;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieConverter movieConverter;

    @RequestMapping(value = "/movies/byYear/{year}+{lessThan}", method = RequestMethod.GET)
    List<MovieDto> getMoviesByYear(@PathVariable int year, @PathVariable int lessThan){
        if(lessThan == 1)
            return movieConverter.convertModelsToDtos(movieService.getMoviesByYear(year,true));
        return movieConverter.convertModelsToDtos(movieService.getMoviesByYear(year,false));
    }

    @RequestMapping(value = "/movies/withCast/byYear/{year}+{lessThan}", method = RequestMethod.GET)
    List<MovieDto> getMoviesWithCastByYear(@PathVariable int year, @PathVariable int lessThan){
        if(lessThan == 1)
            return movieConverter.convertModelsToDtos(movieService.getMoviesWithActorsByYear(year,true));
        return movieConverter.convertModelsToDtos(movieService.getMoviesWithActorsByYear(year,false));
    }

    @RequestMapping(value = "/movies/deleteActor/{mid}+{aid}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteActor(@PathVariable Long mid, @PathVariable Long aid) {
        var result = new ResponseEntity<>(HttpStatus.OK);
        try {
            movieService.deleteActor(mid, aid);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }
}

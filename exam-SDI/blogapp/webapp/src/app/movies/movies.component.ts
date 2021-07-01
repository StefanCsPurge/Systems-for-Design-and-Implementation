import { Component, OnInit } from '@angular/core';
import {MovieService} from "./shared/movie.service";
import {Movie} from "./shared/movie.model";
import {Actor} from "./shared/actor.model";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  movies: Movie[];
  gotMovies = false;
  gotCast = false;
  interval: string;
  year: number;
  constructor(private movieService: MovieService) { }

  ngOnInit() {
  }

  showMovies(interval: string, value: string) {
      this.gotCast = false;
      this.year = +value;
      this.interval = interval;
      if(this.interval === 'before')
        this.movieService.getMoviesByYear(this.year,1)
          .subscribe(movies => {
            this.movies = movies;
            this.movies.sort((m1,m2)=> (m1.year > m2.year ? -1 : 1));
            this.gotMovies = true});
      else
        this.movieService.getMoviesByYear(this.year,0)
          .subscribe(movies => {
            this.movies = movies;
            this.movies.sort((m1,m2)=> (m1.year > m2.year ? -1 : 1));
            this.gotMovies = true});
  }

  getMoviesWithActors() {
    if(this.interval === 'before')
      this.movieService.getMoviesWithCastByYear(this.year,1)
        .subscribe(movies => {
          this.movies = movies;
          this.movies.sort((m1,m2)=> (m1.year > m2.year ? -1 : 1));
          this.gotCast = true});
    else if (this.interval === 'after')
      this.movieService.getMoviesWithCastByYear(this.year,0)
        .subscribe(movies => {
          this.movies = movies;
          this.movies.sort((m1,m2)=> (m1.year > m2.year ? -1 : 1));
          this.gotCast = true;
        });
  }

  deleteActor(movie: Movie, actor: Actor) {
    if (confirm("Are you sure you want to delete this actor ?"))
      this.movieService.deleteActor(movie.id,actor.id).subscribe(_ => {
        //this.getMoviesWithActors();
        movie.actors = movie.actors.filter(a => a.id != actor.id);
      });
  }
}

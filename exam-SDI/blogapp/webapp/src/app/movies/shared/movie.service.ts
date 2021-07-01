import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Movie} from "./movie.model";

@Injectable()
export class MovieService {
  private moviesUrl = 'http://localhost:8080/api/movies';
  constructor(private httpClient: HttpClient) { }

  getMoviesByYear(year: number, lessThan: number) {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl + '/byYear/' + year + '+' + lessThan);
  }

  getMoviesWithCastByYear(year: number, lessThan: number) {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl + '/withCast/byYear/' + year + '+' + lessThan);
  }

  deleteActor(mid: number, aid: number) {
    const url = `${this.moviesUrl}/deleteActor/${mid}+${aid}`;
    return this.httpClient.delete<Response>(url);
  }
}

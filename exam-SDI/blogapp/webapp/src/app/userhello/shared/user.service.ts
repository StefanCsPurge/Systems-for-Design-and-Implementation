import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';

import {Observable} from 'rxjs';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {Name} from "./name.model";
import {UserFollowers} from "./userfollowers.model";
import {UserFollowersPosts} from "./userfollowersposts.model";


@Injectable()
export class UserService {
  private usersUrl = 'http://localhost:8080/api/';

  constructor(private httpClient: HttpClient) {
  }


  getUserName(id: number): Observable<Name> {
    const url = `${this.usersUrl}/user-hello`;
    return this.httpClient.post<Name>(url, id);
  }

  getUserAndFollowers(id: number): Observable<UserFollowers> {
    const url = `${this.usersUrl}/userAndFollowers/${id}`;
    return this.httpClient
      .get<UserFollowers>(url);
  }

  getUserAndFollowersPosts(id: number): Observable<UserFollowersPosts> {
    const url = `${this.usersUrl}/userAndFollowersAndPosts/${id}`;
    return this.httpClient
      .get<UserFollowersPosts>(url);
  }
}

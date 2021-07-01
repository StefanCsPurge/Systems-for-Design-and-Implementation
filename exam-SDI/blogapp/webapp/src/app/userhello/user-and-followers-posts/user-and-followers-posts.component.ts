import { Component, OnInit } from '@angular/core';
import {UserFollowersPosts} from "../shared/userfollowersposts.model";
import {UserService} from "../shared/user.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";

@Component({
  selector: 'app-user-and-followers-posts',
  templateUrl: './user-and-followers-posts.component.html',
  styleUrls: ['./user-and-followers-posts.component.css']
})
export class UserAndFollowersPostsComponent implements OnInit {
  userFollowersPosts: UserFollowersPosts;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.route.params
      .pipe(switchMap((params: Params) => this.userService.getUserAndFollowersPosts(+params['id'])))
      .subscribe(n => {
        console.log(n);
        this.userFollowersPosts = n
      });
  }

}

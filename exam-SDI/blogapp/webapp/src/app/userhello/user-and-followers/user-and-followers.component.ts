import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/user.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs/operators";
import {UserFollowers} from "../shared/userfollowers.model";

@Component({
  selector: 'app-user-and-followers',
  templateUrl: './user-and-followers.component.html',
  styleUrls: ['./user-and-followers.component.css']
})
export class UserAndFollowersComponent implements OnInit {
  userFollowers: UserFollowers;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.route.params
    .pipe(switchMap((params: Params) => this.userService.getUserAndFollowers(+params['id'])))
    .subscribe(n => {
      console.log(n);
      this.userFollowers = n
    });
  }

}
